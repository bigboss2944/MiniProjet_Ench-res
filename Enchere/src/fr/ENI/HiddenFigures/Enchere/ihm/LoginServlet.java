package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// private ManagerUsers managerUsers = ManagerUsersSingl.getInstance();
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();

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
						response.sendRedirect(request.getContextPath() + "/ListeEncheresConnectePagination6Servlet");
						//request.getRequestDispatcher("ListeEncheresConnecteServlet").forward(request, response);
					}
					else {
						request.getSession().setAttribute("user", null  );
						request.setAttribute("messageNonTrouve", "Votre compte est désactivé");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					 
					}
					
				}
				

			} catch (BLLException e) {
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
