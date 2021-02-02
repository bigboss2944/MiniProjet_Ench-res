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
 * Servlet implementation class AdminSupprimerCompteServlet
 */
@WebServlet("/AdminModifierCategorieServlet")
public class AdminModifierCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifierCategorieServlet() {
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
			String libelle="";
			Categorie categorie = new Categorie();
			try {
				categorie=managerCategories.getCategorieParNoCategorie(noCategorie);
				libelle = categorie.getLibelle();
			} catch (BLLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String libelle_new = request.getParameter("libelle");
			if(libelle_new !=null && !"".equals(libelle_new)&& !libelle.equals(libelle_new)) {
				try {
					managerCategories.modifierCategorieParNoCategorie(noCategorie, libelle_new);
					request.setAttribute("message", "La catégorie est bien modifiée");
					categorie = new Categorie(libelle_new);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			request.setAttribute("categorie", categorie);
			request.getRequestDispatcher("modifierCategorie.jsp").forward(request, response);
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
