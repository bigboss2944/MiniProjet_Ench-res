package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerCategories;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerCategoriesSingl;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;

/**
 * Servlet implementation class AdminSupprimerCompteServlet
 */
@WebServlet("/AdminSupprimerCategorieServlet")
public class AdminSupprimerCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSupprimerCategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noCategorieStr = request.getParameter("noCategorie");
		//System.out.println(noVendeurStr);
		if (noCategorieStr != null) {
			Integer noCategorie = Integer.parseInt(noCategorieStr);
			try {
				managerCategories.supprimerCategorieParNoCategorie(noCategorie);
				request.setAttribute("message", "Une catégorie est bien supprimée");
				
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
