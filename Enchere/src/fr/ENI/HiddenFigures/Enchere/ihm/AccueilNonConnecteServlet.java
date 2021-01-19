package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
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
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;
import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class AccueilNonConnecte
 */
@WebServlet({ "/AccueilNonConnecteServlet", "" })
public class AccueilNonConnecteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilNonConnecteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Afficher les libellés dans la liste roulante de catégorie
		CategorieModel categorieModel = new CategorieModel();
		List<Categorie> listCategories = managerCategories.getCategories();
		categorieModel.setLstCategories(listCategories);
		request.setAttribute("categorieModel", categorieModel);
		// Afficher la liste des enchères en cours = les articles en état "en cours"
		// selon les mots recherche
		String nomArticleContient = request.getParameter("nomArticleContient");
		String libelleCategorie = request.getParameter("categorie");
		List<ArticleVendu> listArticlesVendusEncours = new ArrayList<>();
		try {
			listArticlesVendusEncours = managerArticles.getArticleByEtatVenteEnCours();
		} catch (BLLException e) {
			request.setAttribute("message", e.getMessage());
		}
		List<ArticleVendu> listArticlesParNomArticle = new ArrayList<>();
		List<ArticleVendu> listArticlesParNomArticleEtCategorie = new ArrayList<>();
		List<Utilisateur> listUtilisateur = new ArrayList<>();
		 
		Integer no_categorie = 0;
		if(listCategories != null) {
			for (Categorie categorie : listCategories) {
				if(categorie.getLibelle().equals(libelleCategorie)) {
					no_categorie = categorie.getNoCategorie();
				}
				
			}
		}	
			
		Utilisateur utilisateur = new Utilisateur();
		if (nomArticleContient!=null) {
			if(!"".equals(nomArticleContient.trim()) && listArticlesVendusEncours !=null ) {
				for (ArticleVendu articleVendu : listArticlesVendusEncours) {
					if(articleVendu.getNomArticle().toLowerCase().contains(nomArticleContient.toLowerCase())) {
						listArticlesParNomArticle.add(articleVendu);
					}

				}
				
			}
			else {
				listArticlesParNomArticle = listArticlesVendusEncours;
			}
			if (no_categorie!= 0 && listArticlesParNomArticle !=null) {
				for (ArticleVendu articleVendu : listArticlesParNomArticle) {
					if(articleVendu.getNoCategorie()==no_categorie) {
						listArticlesParNomArticleEtCategorie.add(articleVendu);
					}
				}
			}
			else {
				listArticlesParNomArticleEtCategorie = listArticlesParNomArticle;
			}
			if(listArticlesParNomArticleEtCategorie==null ||listArticlesParNomArticleEtCategorie.size()==0) {
				request.setAttribute("message", "0 résultat trouvé");
			}
			else {
				for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
					try {
						utilisateur = managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(articleVendu.getNoUtilisateur());
						utilisateur.setListArticlesVendus(new ArrayList<ArticleVendu>()); //eviter de cummuler les articles vendus précédents
						utilisateur.getListArticlesVendus().add(articleVendu);
						listUtilisateur.add(utilisateur );
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		else {
			if(listArticlesVendusEncours != null) {
				for (ArticleVendu articleVendu : listArticlesVendusEncours) {
					try {
						utilisateur = managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(articleVendu.getNoUtilisateur());
						utilisateur.setListArticlesVendus(new ArrayList<ArticleVendu>()); //eviter de cummuler les articles vendus précédents
						utilisateur.getListArticlesVendus().add(articleVendu);
						listUtilisateur.add(utilisateur );
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else
			{
				request.setAttribute("message", "Il n'y a pas d'enchere en cours");
			}
			
		}
		
		UtilisateurModel utilisateurModel = new UtilisateurModel();
		utilisateurModel.setListUtilisateur(listUtilisateur);
		
		// request.setAttribute("pseudo",utilisateurModel);
		request.setAttribute("utilisateurModel", utilisateurModel);


		 
		
		request.getRequestDispatcher("accueilNonConnecte.jsp").forward(request, response);
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
