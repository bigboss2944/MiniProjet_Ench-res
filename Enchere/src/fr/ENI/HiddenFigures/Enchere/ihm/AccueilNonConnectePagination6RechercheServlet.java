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
@WebServlet({ "/AccueilNonConnectePagination6RechercheServlet" })
public class AccueilNonConnectePagination6RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
	private static final int recordsPerPage  =6;
 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilNonConnectePagination6RechercheServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8") ;
		// Afficher les libellés dans la liste roulante de catégorie
		CategorieModel categorieModel = new CategorieModel();
		List<Categorie> listCategories = managerCategories.getCategories();
		categorieModel.setLstCategories(listCategories);
		request.setAttribute("categorieModel", categorieModel);
		// Afficher la liste des enchères en cours = les articles en état "en cours"
		// selon les mots recherche
		String nomArticleContient = request.getParameter("nomArticleContient");
		String libelleCategorie = request.getParameter("categorie");

		//request.setAttribute("nomArticleContient",nomArticleContient);
		

		//request.setAttribute("libelleCategorie",libelleCategorie);
		if(nomArticleContient !=null && libelleCategorie !=null) {
			request.getSession().setAttribute("nomArticleContientSession", nomArticleContient);
			request.getSession().setAttribute("libelleCategorieSession", libelleCategorie);
		}
		else {
			nomArticleContient = (String) request.getSession().getAttribute("nomArticleContientSession");
			libelleCategorie = (String) request.getSession().getAttribute("libelleCategorieSession");
		}
		
		
		//System.out.println(libelleCategorie);
		List<ArticleVendu> listArticlesVendusEncours = new ArrayList<>();
		try {
			listArticlesVendusEncours = managerArticles.getArticleByEtatVenteEnCours();
		} catch (BLLException e) {
			request.setAttribute("message", e.getMessage());
		}
		List<ArticleVendu> listArticlesParNomArticle = new ArrayList<>();
		List<ArticleVendu> listArticlesParNomArticleEtCategorie = new ArrayList<>();
		
		 
		Integer no_categorie = 0;
		
		
		if(listCategories != null) {
			
			for (Categorie categorie : listCategories) {
				if(categorie.getLibelle().equals(libelleCategorie)) {
					no_categorie = categorie.getNoCategorie();
				}
				
			}
		}	
		List<Utilisateur> listUtilisateur = new ArrayList<>();	
		Utilisateur utilisateur = new Utilisateur();
		List<ArticleVendu> listArtcilesParPage = new ArrayList<>();	
		String currentPageStr = request.getParameter("currentPage");
		
		
		 
			
		int  	currentPage = Integer.parseInt(currentPageStr);
			
			
			
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
				listArtcilesParPage =  listArtcilesParPage(listArticlesParNomArticleEtCategorie,currentPage );
			}
			
//		}
//		else {
//			listArticlesParNomArticleEtCategorie = listArticlesVendusEncours;
////TODO: viet ham lay ra 6 l'article dau tien
//			if(currentPageStr!=null) {
//				currentPage = Integer.parseInt(currentPageStr);
//			}
//			else {
//			currentPage = 1;
//			}
//			listArtcilesParPage =  listArtcilesParPage(listArticlesParNomArticleEtCategorie,currentPage );
//			
//		}
		
		
		if(listArtcilesParPage!=null ) {
			for (ArticleVendu articleVendu : listArtcilesParPage) {
				try {
					utilisateur= managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(articleVendu.getNoUtilisateur());
					Utilisateur vendeur  =new Utilisateur();
					vendeur.setPseudo(utilisateur.getPseudo());
					vendeur.setNoUtilisateur(utilisateur.getNoUtilisateur() );
					vendeur.setListArticlesVendus(new ArrayList<ArticleVendu>()); //eviter de cummuler les articles vendus précédents
					vendeur.getListArticlesVendus().add(articleVendu);
					listUtilisateur.add(vendeur );
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		
		UtilisateurModel utilisateurModel = new UtilisateurModel();
		utilisateurModel.setListUtilisateur(listUtilisateur);
		
		// request.setAttribute("pseudo",utilisateurModel);
		request.setAttribute("utilisateurModel", utilisateurModel);

		int rows = listUtilisateur.size();
		int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {

            nOfPages++;
        }
        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage); 
		
		request.getRequestDispatcher("accueilNonConnectePagination6Recherche.jsp").forward(request, response);
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
	
	protected List<ArticleVendu> listArtcilesParPage(List<ArticleVendu> listArticles, int currentPage) {
		List<ArticleVendu> listArtcilesParPage = new ArrayList<>();
		if(listArticles!=null) {
			for (int i = 0; i < Math.min(recordsPerPage, listArticles.size() - recordsPerPage*(currentPage-1)) ; i++) {
				listArtcilesParPage.add(listArticles.get(recordsPerPage*(currentPage -1) +i ));
				
			}
		}
		return listArtcilesParPage;
	}

}
