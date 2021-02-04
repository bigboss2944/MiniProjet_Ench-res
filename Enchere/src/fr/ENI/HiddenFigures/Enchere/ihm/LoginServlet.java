package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurAuthToken;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurAuthTokenSingl;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;
import fr.ENI.HiddenFigures.Enchere.bo.UtilisateurAuthToken;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private ManagerUsers managerUsers = ManagerUsersSingl.getInstance();
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
	private ManagerUtilisateurAuthToken managerUtilisateurAuthToken = ManagerUtilisateurAuthTokenSingl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		

		String loginEcran = request.getParameter("login");
		String passwordEcran = request.getParameter("password");
		
System.out.println("LoginServlet rememberme " + request.getParameter("rememberMe"));		
		
		boolean rememberMe = "true".equals(request.getParameter("rememberMe"));

		if (loginEcran != null && passwordEcran != null) {
			Utilisateur userAchercher = new Utilisateur();
			userAchercher.setMotDePasse(passwordEcran);
			if (loginEcran.contains("@")) {
				userAchercher.setEmail(loginEcran);
			} else {
				userAchercher.setPseudo(loginEcran);
			}

			try {
				Map<Integer, Utilisateur> mapUtilisateurAchercher = new HashMap<Integer, Utilisateur>();
				mapUtilisateurAchercher = managerUtilisateurs.rechercherUtilisateurParLoginPassword(loginEcran,
						passwordEcran);
				
				for (Integer noUtilisateur : mapUtilisateurAchercher.keySet()) {
					request.getSession().setAttribute("user", mapUtilisateurAchercher.get(noUtilisateur));
					//System.out.println(mapUtilisateurAchercher.get(noUtilisateur));                        
					//System.out.println("User vient de récupérer par chercher dans BDD "+ mapUtilisateurAchercher.get(noUtilisateur));
//						request.getSession().setAttribute("loginUsername", mapUtilisateurAchercher.get(noUtilisateur).getPseudo());
//						request.getSession().setAttribute("loginNoUser",noUtilisateur  );
				}
				//Sessions utilisateur de 5mn
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(300);
				 
				Utilisateur utilisateur_current = (Utilisateur) request.getSession().getAttribute("user");
				if(utilisateur_current.getAdministrateur()) {
					response.sendRedirect(request.getContextPath() + "/ListeEncheresConnecteAdminServlet");
					//request.getRequestDispatcher("ListeEncheresConnecteAdminServlet").forward(request, response);
					
				}
				else {
					if("A".equals(utilisateur_current.getEtatCompte())) {
						if (rememberMe) {
				            // create new token (selector, validator)
							UtilisateurAuthToken newToken = new UtilisateurAuthToken();
							 
							String selector = RandomStringUtils.randomAlphanumeric(12);
							
							List<UtilisateurAuthToken> listUtilisateurAuthToken = managerUtilisateurAuthToken.getListUtilisateurAuthToken();
							List<String> listSelector = new ArrayList<>();
							
							if (listUtilisateurAuthToken !=null)  {
								for (UtilisateurAuthToken utilisateurAuthToken : listUtilisateurAuthToken) {
									listSelector.add(utilisateurAuthToken.getSelector());
								}
								while(listSelector.contains(selector)) {
									selector = RandomStringUtils.randomAlphanumeric(12); //il rassure que aucun selector est répété dans la BD
								}
							}
							
							String rawValidator =  RandomStringUtils.randomAlphanumeric(64);
							 
							String hashedValidator = HashGeneratorUtils.generateSHA256(rawValidator);
							
							newToken.setSelector(selector);
							newToken.setValidator(hashedValidator);
							 
							newToken.setUtilisateur(utilisateur_current);
				             
				            // save the token into the database
							managerUtilisateurAuthToken.addutilisateurAuthToken(newToken);
							
							
				             
				            // create a cookie to store the selector
				             
				            // create a cookie to store the validator
							
							Cookie cookieSelector = new Cookie("selector", selector);
							cookieSelector.setMaxAge(604800); //7 days
							 
							Cookie cookieValidator = new Cookie("validator", rawValidator);
							cookieValidator.setMaxAge(604800);
							 
							response.addCookie(cookieSelector);
							response.addCookie(cookieValidator);
				                 
				        }
						response.sendRedirect(request.getContextPath() + "/ListeEncheresConnectePagination6Servlet");
						//request.getRequestDispatcher("ListeEncheresConnecteServlet").forward(request, response);
					}
					else {
						request.getSession().setAttribute("user", null  );
						request.setAttribute("messageNonTrouve", "Votre compte est désactivé");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					 
					}
					
				}
				

			} catch (BLLException | HashGenerationException e) {
				// request.getSession().setAttribute("loginUsername", null);
				request.setAttribute("messageNonTrouve", e.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
