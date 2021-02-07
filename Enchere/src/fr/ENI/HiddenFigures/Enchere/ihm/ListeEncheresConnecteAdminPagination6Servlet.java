package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
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
import fr.ENI.HiddenFigures.Enchere.bll.ManagerEncheres;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerEncheresSingl;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;
import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class AccueilNonConnecte
 */
@WebServlet("/ListeEncheresConnecteAdminPagination6Servlet")
public class ListeEncheresConnecteAdminPagination6Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
	private ManagerEncheres managerEncheres = ManagerEncheresSingl.getInstance();
	private static final int recordsPerPage = 6;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeEncheresConnecteAdminPagination6Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		request.getSession().setAttribute("nomArticleContientSession", null);
		request.getSession().setAttribute("libelleCategorieSession", null);
		request.getSession().setAttribute("achatVenteSession", null);
		request.getSession().setAttribute("encheresOuvertesSession", null);
		request.getSession().setAttribute("mesEncheresSession", null);
		request.getSession().setAttribute("mesEncheresRemporteesSession", null);
		request.getSession().setAttribute("mesVentesECSession", null);
		request.getSession().setAttribute("ventesNonDebuteesSession", null);
		request.getSession().setAttribute("ventesTermineesSession", null);
		
		
		

		if (request.getSession().getAttribute("user") != null) {
			Utilisateur utilisateur_current = (Utilisateur) request.getSession().getAttribute("user");
			// Afficher les libellés dans la liste roulante de catégorie
			CategorieModel categorieModel = new CategorieModel();
			List<Categorie> listCategories = managerCategories.getCategories();
			categorieModel.setLstCategories(listCategories);
			request.setAttribute("categorieModel", categorieModel);

			List<ArticleVendu> listArticlesVendus = managerArticles.getLstArticleVendus();
			List<ArticleVendu> listArticlesVendusEncours = new ArrayList<>();
			try {
				listArticlesVendusEncours = managerArticles.getArticleByEtatVenteEnCours();
			} catch (BLLException e) {
				request.setAttribute("message", e.getMessage());
			}
			// Afficher la liste des enchères en cours = les articles en état "en cours"

			List<ArticleVendu> listArticleAutresAvecEncheresOuvertes = new ArrayList<>();
			if (listArticlesVendus != null) {
				for (ArticleVendu articleVendu : listArticlesVendusEncours) {
					if (articleVendu.getNoUtilisateur() != utilisateur_current.getNoUtilisateur()) {
						listArticleAutresAvecEncheresOuvertes.add(articleVendu);
					}

				}
			}

			List<ArticleVendu> listArticlesJeFaisEnchere = new ArrayList<>();
			// TODO: à modifier par sql join
			List<Enchere> listMesEncheres = managerEncheres
					.getLstEnchereOfUserById(utilisateur_current.getNoUtilisateur());
			if (listArticlesVendusEncours != null && listMesEncheres != null) {
				for (Enchere enchere : listMesEncheres) {
					// TODO: cette boucle peut être remplacée par
					// managerArticlesVendus.getArticleById(enchere.getNoArticle) par Fabrice
					for (ArticleVendu articleVendu : listArticlesVendusEncours) {
						if (articleVendu.getNoArticle() == enchere.getNo_article()) {
							listArticlesJeFaisEnchere.add(articleVendu);
						}
					}
				}
			}

			if (listArticlesJeFaisEnchere.size() >= 1) {
				for (int i = 0; i < listArticlesJeFaisEnchere.size() - 1; i++) {
					if (listArticlesJeFaisEnchere.get(i).getNoArticle() == listArticlesJeFaisEnchere.get(i + 1)
							.getNoArticle()) {
						listArticlesJeFaisEnchere.remove(i);
						i = i - 1;
					}

				}

			}

			List<ArticleVendu> listArticlesJeRemporte = new ArrayList<>();
			List<Enchere> listMesEncheresRemportees = managerEncheres
					.getLstEnchereWonOfUserById(utilisateur_current.getNoUtilisateur());
			if (listArticlesVendus != null && listMesEncheresRemportees != null) {
				for (ArticleVendu articleVendu : listArticlesVendus) {
					for (Enchere enchere : listMesEncheresRemportees) {
						if (articleVendu.getNoArticle() == enchere.getNo_article()) {
							listArticlesJeRemporte.add(articleVendu);
						}
					}

				}
			}

			List<ArticleVendu> listMesVentesEC = new ArrayList<>();
			if (listArticlesVendusEncours != null) {
				for (ArticleVendu articleVendu : listArticlesVendusEncours) {
					if (articleVendu.getNoUtilisateur() == utilisateur_current.getNoUtilisateur()) {
						listMesVentesEC.add(articleVendu);
					}
				}
			}

			List<ArticleVendu> listMesVentesNonDebutees = new ArrayList<>();
			List<ArticleVendu> listArticlesNonDebutees = managerArticles.getArticleByEtatNonDebute();
			if (listArticlesNonDebutees != null) {
				for (ArticleVendu articleVendu : listArticlesNonDebutees) {
					if (articleVendu.getNoUtilisateur() == utilisateur_current.getNoUtilisateur()) {
						listMesVentesNonDebutees.add(articleVendu);
					}
				}
			}

			List<ArticleVendu> listMesVentesTerminees = new ArrayList<>();
			List<ArticleVendu> listArticlesTermines = managerArticles.getArticleByEtatTermine();
			if (listArticlesTermines != null) {
				for (ArticleVendu articleVendu : listArticlesTermines) {
					if (articleVendu.getNoUtilisateur() == utilisateur_current.getNoUtilisateur()) {
						listMesVentesTerminees.add(articleVendu);
					}
				}
			}

			List<Utilisateur> listVendeurs = new ArrayList<>();

			Utilisateur utilisateur = new Utilisateur();
			List<ArticleVendu> listArticlesParNomArticleEtCategorieAchatVente = new ArrayList<>();

			listArticlesParNomArticleEtCategorieAchatVente = listArticlesVendusEncours;

			if (listArticlesJeRemporte != null) {
				for (ArticleVendu articleVendu : listArticlesJeRemporte) {
					listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
				}
			}
			if (listMesVentesTerminees != null) {
				for (ArticleVendu articleVendu : listMesVentesTerminees) {
					listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
				}

			}
			if (listMesVentesNonDebutees != null) {
				for (ArticleVendu articleVendu : listMesVentesNonDebutees) {
					listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
				}

			}
			int currentPage = 0;

			List<ArticleVendu> listArtcilesParPage = new ArrayList<>();
			String currentPageStr = request.getParameter("currentPage");
			if (currentPageStr != null) {
				currentPage = Integer.parseInt(currentPageStr);
			} else {
				currentPage = 1;
			}
			listArtcilesParPage = listArtcilesParPage(listArticlesParNomArticleEtCategorieAchatVente, currentPage);
			
			listVendeurs = new ArrayList<Utilisateur>();
			if (listArtcilesParPage != null) {
				for (ArticleVendu articleVendu : listArtcilesParPage) {
					try {
						utilisateur = managerUtilisateurs
								.rechercherUtilisateurParNoUtilisateur(articleVendu.getNoUtilisateur());
						Utilisateur vendeur = new Utilisateur();
						vendeur.setPseudo(utilisateur.getPseudo());
						vendeur.setNoUtilisateur(utilisateur.getNoUtilisateur());
						vendeur.setListArticlesVendus(new ArrayList<ArticleVendu>()); // eviter de cummuler les articles
																						// vendus précédents
						vendeur.getListArticlesVendus().add(articleVendu);
						listVendeurs.add(vendeur);

					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			

			UtilisateurModel utilisateurModel = new UtilisateurModel();
			utilisateurModel.setListUtilisateur(listVendeurs);

			// request.setAttribute("pseudo",utilisateurModel);
			request.setAttribute("utilisateurModel", utilisateurModel);
			
			int rows = listVendeurs.size();
			int nOfPages = rows / recordsPerPage;

			if (nOfPages % recordsPerPage > 0) {

				nOfPages++;
			}
			request.setAttribute("noOfPages", nOfPages);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("recordsPerPage", recordsPerPage);

			request.getRequestDispatcher("listeEncheresConnecteAdminPagination6.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("AccueilNonConnectePagination6Servlet").forward(request, response);
		}

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
		if (listArticles != null) {
			for (int i = 0; i < Math.min(recordsPerPage,
					listArticles.size() - recordsPerPage * (currentPage - 1)); i++) {
				listArtcilesParPage.add(listArticles.get(recordsPerPage * (currentPage - 1) + i));

			}
		}
		return listArtcilesParPage;
	}

}
