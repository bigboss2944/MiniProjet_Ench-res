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
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;

/**
 * Servlet implementation class AdminAjouterCategorieServlet
 */
@WebServlet("/AdminAjouterCategorieServlet")
public class AdminAjouterCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAjouterCategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String libelleCat = request.getParameter("libelleCat");
		if(libelleCat!=null && !"".equals(libelleCat.trim())) {
			Categorie categorie = new Categorie(libelleCat);
			try {
				managerCategories.libelleEstUnique(categorie);
				managerCategories.addCategorie(categorie);
				request.setAttribute("message", "Une catégorie est bien ajoutée");
			} catch (BLLException e) {
				request.setAttribute("message", e.getMessage());
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
