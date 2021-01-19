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
 * Servlet implementation class SuppressionCompteServlet
 */
@WebServlet("/SuppressionCompteServlet")
public class SuppressionCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppressionCompteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur_current =  (Utilisateur) request.getSession().getAttribute("user");
		if (utilisateur_current!=null) {
			try {
				managerUtilisateurs.supprimerUtilisateurParNoUtilisateur(utilisateur_current.getNoUtilisateur());
				request.getSession().setAttribute("user", null  );
				request.getRequestDispatcher("AccueilNonConnecteServlet").forward(request, response);
			} catch (BLLException e) {
				request.setAttribute("message", e.getMessage());
				 
			}
		}
		else {
			request.getRequestDispatcher("AccueilNonConnecteServlet").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
