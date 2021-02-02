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
import fr.ENI.HiddenFigures.Enchere.bll.ManagerRetrait;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerRetraitSingl;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;
import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.bo.Retrait;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class AcquisitionServlet
 */
@WebServlet("/GestionAchatVente")
public class GestionAchatVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
	private ManagerEncheres managerEncheres = ManagerEncheresSingl.getInstance();
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
	private ManagerRetrait managerRetraits = ManagerRetraitSingl.getInstance();

	// private EnchereModel enchereModel = new EnchereModel();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GestionAchatVenteServlet() {
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

		if (request.getSession().getAttribute("user") != null) {
			Utilisateur utilisateur_current = (Utilisateur) request.getSession().getAttribute("user");

			String noArticleString = request.getParameter("noArticleVendu");
			if (noArticleString != null) {
				Integer noArticle = Integer.parseInt(noArticleString);
				ArticleVendu article_current = new ArticleVendu();

				article_current = managerArticles.getArticleVendu(noArticle);

				ArticleVenduModel articleVenduModel = new ArticleVenduModel(article_current);
				request.setAttribute("articleVenduModel", articleVenduModel);

				// ajouter la retrait ici pour afficher dans jsp

				Retrait retrait = new Retrait();
				try {
					retrait = managerRetraits.selectRetrait(noArticle);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				request.setAttribute("retrait", retrait);
				// list enchérisseurs de l'article current
				List<Utilisateur> listEncherisseursOfArticleCurrent = new ArrayList<>();
				List<Enchere> listEnchereOfArticleCurrentOrderByMontant = new ArrayList<>();
				try {
					listEnchereOfArticleCurrentOrderByMontant = managerEncheres.getLstEnchereOrderByMontant(noArticle);
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Utilisateur encherisseurOfArticleCurrent = new Utilisateur();
				if (listEnchereOfArticleCurrentOrderByMontant != null) {
					for (Enchere enchere : listEnchereOfArticleCurrentOrderByMontant) {
						try {
							encherisseurOfArticleCurrent = managerUtilisateurs
									.rechercherUtilisateurParNoUtilisateur(enchere.getNo_utilisateur());
							encherisseurOfArticleCurrent.setListEncheres(new ArrayList<>());
							encherisseurOfArticleCurrent.getListEncheres().add(enchere);// TODO: à vérifier s'il cummule
																						// les results précédents
							listEncherisseursOfArticleCurrent.add(encherisseurOfArticleCurrent);
						} catch (BLLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				UtilisateurModel utilisateurModel = new UtilisateurModel();
				utilisateurModel.setListUtilisateur(listEncherisseursOfArticleCurrent);

				// System.out.println(article_current.getNoUtilisateur() +" et " +
				// utilisateur_current.getNoUtilisateur());

				if (article_current.getNoUtilisateur() == utilisateur_current.getNoUtilisateur()) {
					if (article_current.getDateDebutEncheres().compareTo(LocalDate.now()) > 0) {

						CategorieModel categorieModel = new CategorieModel();
						List<Categorie> listCategories = new ArrayList<>();

						String libelleCategorie = "";
						if (managerCategories.getCategories() != null) {
							for (Categorie categorie : managerCategories.getCategories()) {
								if (categorie.getNoCategorie() == article_current.getNoCategorie()) {
									libelleCategorie = categorie.getLibelle();
								} else {
									listCategories.add(categorie);
								}

							}
						}

						categorieModel.setLstCategories(listCategories);
						request.setAttribute("categorieModel", categorieModel);
						request.setAttribute("libelleCategorie", libelleCategorie);

						request.getRequestDispatcher("enchereNonCommencee.jsp").forward(request, response);
					}
					if (article_current.getDateFinEncheres().compareTo(LocalDate.now()) < 0) {

						Integer noEncherisseur = 0;
						Integer montantEnchere = 0;
						List<Enchere> listEncheresAvecPlusHauteOffre = managerEncheres.getLstEnchereOfHighestOffer();

						if (listEncheresAvecPlusHauteOffre != null) {
							for (Enchere enchere : listEncheresAvecPlusHauteOffre) {

								if (enchere.getNo_article() == noArticle) {
									noEncherisseur = enchere.getNo_utilisateur();
									montantEnchere = enchere.getMontant_enchere();
								}
							}
						}

						Utilisateur encherisseur = new Utilisateur();
						try {
							encherisseur = managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(noEncherisseur);
						} catch (BLLException e) {
							System.out.println(
									"GestionAchatVenteServlet - Il y a personne qui fait l'enchere sur cette vente");
						}

						request.setAttribute("utilisateurModel", utilisateurModel);

						request.setAttribute("encherisseur", encherisseur);
						request.setAttribute("montantEnchere", montantEnchere);
						request.getRequestDispatcher("detailMaVenteFinEnchere.jsp").forward(request, response);
					}
					if (article_current.getDateFinEncheres().compareTo(LocalDate.now()) >= 0
							&& LocalDate.now().compareTo(article_current.getDateDebutEncheres()) >= 0) {
						Integer noEncherisseur = 0;
						Integer montantEnchere = 0;
						List<Enchere> listEncheresAvecPlusHauteOffre = managerEncheres.getLstEnchereOfHighestOffer();

						if (listEncheresAvecPlusHauteOffre != null) {
							for (Enchere enchere : listEncheresAvecPlusHauteOffre) {

								if (enchere.getNo_article() == noArticle) {
									noEncherisseur = enchere.getNo_utilisateur();
									montantEnchere = enchere.getMontant_enchere();
								}
							}
						}

						Utilisateur encherisseur = new Utilisateur();
						if (noEncherisseur != 0) {
							try {
								encherisseur = managerUtilisateurs
										.rechercherUtilisateurParNoUtilisateur(noEncherisseur);
							} catch (BLLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						request.setAttribute("utilisateurModel", utilisateurModel);
						request.setAttribute("encherisseur", encherisseur);
						request.setAttribute("montantEnchere", montantEnchere);
						request.getRequestDispatcher("detailMaVenteEnCours.jsp").forward(request, response);
					}

				} else {

					if (article_current.getDateFinEncheres().compareTo(LocalDate.now()) < 0) {

						Integer montantEnchere = 0;
						List<Enchere> listEncheresAvecPlusHauteOffre = managerEncheres.getLstEnchereOfHighestOffer();

						if (listEncheresAvecPlusHauteOffre != null) {
							for (Enchere enchere : listEncheresAvecPlusHauteOffre) {
								if (enchere.getNo_article() == noArticle) {
									montantEnchere = enchere.getMontant_enchere();
								}
							}
						}
						Utilisateur vendeur = new Utilisateur();
						try {
							vendeur = managerUtilisateurs
									.rechercherUtilisateurParNoUtilisateur(article_current.getNoUtilisateur());
						} catch (BLLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.setAttribute("vendeur", vendeur);
						request.setAttribute("montantEnchere", montantEnchere);
						request.getRequestDispatcher("acquisition.jsp").forward(request, response);
					} else {
						if (article_current.getDateFinEncheres().compareTo(LocalDate.now()) >= 0
								&& LocalDate.now().compareTo(article_current.getDateDebutEncheres()) >= 0) {

							String libelleCategorie = "";
							if (managerCategories.getCategories() != null) {
								for (Categorie categorie : managerCategories.getCategories()) {
									if (categorie.getNoCategorie() == article_current.getNoCategorie()) {
										libelleCategorie = categorie.getLibelle();
									}
								}
							}
							request.setAttribute("libelleCategorie", libelleCategorie);
							Integer noEncherisseur = 0;
							Integer montantEnchere = 0;
							List<Enchere> listEncheresAvecPlusHauteOffre = managerEncheres
									.getLstEnchereOfHighestOffer();

							if (listEncheresAvecPlusHauteOffre != null) {
								for (Enchere enchere : listEncheresAvecPlusHauteOffre) {

									if (enchere.getNo_article() == noArticle) {
										noEncherisseur = enchere.getNo_utilisateur();
										montantEnchere = enchere.getMontant_enchere();
									}
								}
							}

							Utilisateur encherisseur = new Utilisateur();
							if (noEncherisseur != 0) {
								try {
									encherisseur = managerUtilisateurs
											.rechercherUtilisateurParNoUtilisateur(noEncherisseur);
								} catch (BLLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							request.setAttribute("encherisseur", encherisseur);
							request.setAttribute("montantEnchere", montantEnchere);
							Utilisateur vendeur = new Utilisateur();
							try {
								vendeur = managerUtilisateurs
										.rechercherUtilisateurParNoUtilisateur(article_current.getNoUtilisateur());
							} catch (BLLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							request.setAttribute("vendeur", vendeur);

							request.getRequestDispatcher("encherir.jsp").forward(request, response);
						} else {
							request.getRequestDispatcher("ListeEncheresConnecteServlet").forward(request, response);
						}
					}
				}
			}

		} else {
			request.getRequestDispatcher("AccueilNonConnecteServlet").forward(request, response);
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

}

//String dateDebutEnchereString = request.getParameter("dateDebutEncheres");
//String dateFinEnchereString = request.getParameter("dateFinEncheres");
//
//
//if(dateDebutEnchereString.contains("-")) {
//	dateDebutEnchereString = dateDebutEnchereString.substring(8) +"/"+dateDebutEnchereString.substring(5,7)+"/"+dateDebutEnchereString.substring(0,4);
//	
//}
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//LocalDate dateDebutEnchere = LocalDate.parse(dateDebutEnchereString,formatter);
//
//if(dateFinEnchereString.contains("-")) {
//	dateFinEnchereString = dateFinEnchereString.substring(8) +"/"+dateFinEnchereString.substring(5,7)+"/"+dateFinEnchereString.substring(0,4);
//	
//}
// 
//LocalDate dateFinEnchere = LocalDate.parse(dateFinEnchereString,formatter);
