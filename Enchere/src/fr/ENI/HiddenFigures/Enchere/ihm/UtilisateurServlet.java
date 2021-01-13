package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;




/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UtilisateurServlet")
public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();   
	//private ManagerUsersDAO managerUsers = ManagerUsersDAOSingl.getInstance();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//UtilisateurModel model = new UtilisateurModel();
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		String motDePasse2 = request.getParameter("motDePasse2");
		Utilisateur utilisateur = new Utilisateur();
		if(pseudo!=null&& nom !=null&& prenom!=null&&email!=null&&rue!=null&&codePostal!=null&&ville!=null&&motDePasse!=null) {
			
			if (motDePasse2.equals(motDePasse)) {
				utilisateur.setPseudo(pseudo);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				utilisateur.setEmail(email);
				utilisateur.setTelephone(telephone);
				utilisateur.setRue(rue);
				utilisateur.setCodePostal(codePostal);
				utilisateur.setVille(ville);
				utilisateur.setMotDePasse(motDePasse);
				utilisateur.setCredit(100);
				utilisateur.setAdministrateur(false);
				try {
					managerUtilisateurs.lesAutresContraints(utilisateur);
					managerUtilisateurs.pseudoEstUnique(utilisateur);
					managerUtilisateurs.emailEstUnique(utilisateur);
					managerUtilisateurs.pseudoContientQueAlphanumeriques(utilisateur);
					managerUtilisateurs.verificationTelephone(utilisateur);
					managerUtilisateurs.addUtilisateur(utilisateur);
				} catch (BLLException e1) {
					request.setAttribute("message", e1.getMessage());
				}
				
				
				
			}
			else {
				request.setAttribute("message", "Passwords did not match ");
			}
			
		}
		
		
		//request.setAttribute("model",model);
		request.getRequestDispatcher("utilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
