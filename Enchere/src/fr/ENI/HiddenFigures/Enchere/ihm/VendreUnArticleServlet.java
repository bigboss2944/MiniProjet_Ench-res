package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendus;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendusSingl;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerCategories;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerCategoriesSingl;
import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class VendreUnArticleServlet
 */
@WebServlet("/VendreUnArticleServlet")
public class VendreUnArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendreUnArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null  ) {
			Utilisateur utilisateur_current = (Utilisateur) request.getSession().getAttribute("user");
			CategorieModel categorieModel = new CategorieModel();
			List<Categorie> listCategories = managerCategories.getCategories();
			categorieModel.setLstCategories(listCategories);
			request.setAttribute("categorieModel", categorieModel);
			
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			String libelleCategorie = request.getParameter("categorie");
			String miseAPrixString = request.getParameter("miseAPrix");
			String dateDebutEnchereString = request.getParameter("dateDebutEnchere");
			String dateFinEnchereString = request.getParameter("dateFinEnchere");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			ArticleVendu articleVendu = new ArticleVendu();
			if(nomArticle !=null && description !=null &&libelleCategorie !=null &&miseAPrixString !=null &&dateDebutEnchereString !=null 
					&&dateFinEnchereString !=null &&dateFinEnchereString !=null &&codePostal !=null &&ville !=null) {
				Integer no_categorie = 0;
				if (listCategories!=null ) {
					for (Categorie categorie : listCategories) {
						if (categorie.getLibelle().equals(libelleCategorie)) {
							no_categorie = categorie.getNoCategorie();
						}

					}
				}
				articleVendu.setNomArticle(nomArticle);
				articleVendu.setDescription(description);
				articleVendu.setNoCategorie(no_categorie);
				
				if(dateDebutEnchereString.contains("-")) {
					dateDebutEnchereString = dateDebutEnchereString.substring(8) +"/"+dateDebutEnchereString.substring(5,7)+"/"+dateDebutEnchereString.substring(0,4);
					
				}
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate dateDebutEnchere = LocalDate.parse(dateDebutEnchereString,formatter);
				articleVendu.setDateDebutEncheres(dateDebutEnchere);
				
				if(dateFinEnchereString.contains("-")) {
					dateFinEnchereString = dateFinEnchereString.substring(8) +"/"+dateFinEnchereString.substring(5,7)+"/"+dateFinEnchereString.substring(0,4);
					
				}
				 
				LocalDate dateFinEnchere = LocalDate.parse(dateFinEnchereString,formatter);
				articleVendu.setDateFinEncheres(dateFinEnchere);
				
				articleVendu.setNoUtilisateur(utilisateur_current.getNoUtilisateur());
				
				Integer miseAPrix = Integer.parseInt(miseAPrixString);
				articleVendu.setMiseAprix(miseAPrix);
				try {
					managerArticles.addArticleVendu(articleVendu);
					request.setAttribute("message", "Votre article est bien enregistré");
				} catch (BLLException e) {
					request.setAttribute("message", e.getMessage());
				}
				
			}
			
			request.getRequestDispatcher("vendreUnArticle.jsp").forward(request, response);
		} else {
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
