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

/**
 * Servlet implementation class AdminSupprimerCompteServlet
 */
@WebServlet("/AdminSupprimerCompteServlet")
public class AdminSupprimerCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSupprimerCompteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noUtilisateurStr = request.getParameter("noVendeur");
		//System.out.println(noVendeurStr);
		if (noUtilisateurStr != null) {
			Integer noUtilisateur = Integer.parseInt(noUtilisateurStr);
			try {
				managerUtilisateurs.supprimerUtilisateurParNoUtilisateur(noUtilisateur);
				request.setAttribute("message", "Un compte est bien supprimé");
				
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("AdministrationServlet").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
