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
 * Servlet implementation class NouveauPasswordServlet
 */
@WebServlet("/NouveauPasswordServlet")
public class NouveauPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouveauPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8") ;
		 
		String email = request.getParameter("email");
		Utilisateur utilisateurModifie = new Utilisateur();
		String new_motDePasse = request.getParameter("nouveauMotDePasse");
		String new_motDePasse2 = request.getParameter("nouveauMotDePasse2");
		if (email != null) {
			try {
				utilisateurModifie =managerUtilisateurs.rechercherUtilisateurParEmail(email);
				if( new_motDePasse != null && ! "".equals(new_motDePasse.trim())) {
					if(new_motDePasse.equals(new_motDePasse2)) {
						utilisateurModifie.setMotDePasse(new_motDePasse);
						try {
							//managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.modifierMotDePasse(utilisateurModifie.getNoUtilisateur(), new_motDePasse);
							request.setAttribute("message", "Votre nouveuu mot de passe est enregistré ");
							 
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
						 
					}
					else {
						request.setAttribute("message", "New passwords did not match ");
						 
					}
					
				}
				else {
					request.setAttribute("message", "Entrer nouveau mot de passe ");
					 
				}
				
			} catch (BLLException e) {
				request.setAttribute("message", e.getMessage());
				

			}
			
			
		}
		request.getRequestDispatcher("nouveauPassword.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
