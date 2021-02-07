package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.EnchereException;
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
 * Servlet implementation class EnchereServlet
 */
@WebServlet("/EnchereServlet")
public class EnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
	private ManagerEncheres managerEncheres = ManagerEncheresSingl.getInstance();
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
	// private EnchereModel enchereModel = new EnchereModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnchereServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		if (request.getSession().getAttribute("user") != null) {
			Utilisateur utilisateur_current = (Utilisateur) request.getSession().getAttribute("user");
			List<ArticleVendu> listArticleVendus = managerArticles.getLstArticleVendus();

			String article_temp = null;

			ArticleVendu article_current;

			String noArticleString = request.getParameter("noArticleVendu");
			if (noArticleString != null) {
				Integer noArticle = Integer.parseInt(noArticleString);
				article_current = new ArticleVendu();

				article_current = managerArticles.getArticleVendu(noArticle);
				// enchereModel.setArticleVendu(article_current);

				ArticleVenduModel articleVenduModel = new ArticleVenduModel(article_current);
				request.setAttribute("articleVenduModel", articleVenduModel);

				CategorieModel categorieModel = new CategorieModel();
				List<Categorie> listCategories = new ArrayList<>();

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
				List<Enchere> listEncheresAvecPlusHauteOffre = managerEncheres.getLstEnchereOfHighestOffer();

				if (listEncheresAvecPlusHauteOffre != null) {
					for (Enchere enchere : listEncheresAvecPlusHauteOffre) {

						if (enchere.getNo_article() == noArticle) {
							noEncherisseur = enchere.getNo_utilisateur();
							montantEnchere = enchere.getMontant_enchere();
						}
					}
				}
				// System.out.println("noEncherisseur :" + noEncherisseur);
				Utilisateur encherisseur = new Utilisateur();
				if (noEncherisseur != 0) {
					try {
						encherisseur = managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(noEncherisseur);
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

				String prop = request.getParameter("propButton");
				Integer enchereInteger = null;
				LocalDateTime dateEnchere = null;
				if (prop != null && !"".equals(prop)) {
					enchereInteger = Integer.parseInt(prop);
				}

				ArticleVendu articleVendu;
				Integer encherePlusHauteIdUser;
				Integer encherePlusHaute;
				// Categorie categorie;
				if (enchereInteger != null) { // enchereInteger = ma proposition
					Enchere enchere = new Enchere(dateEnchere.now(), enchereInteger);
					try {
						enchere.setNo_utilisateur(utilisateur_current.getNoUtilisateur());
						enchere.setNo_article(noArticle);
						// managerEncheres.deleteEnchereByNoArticle(enchere.getNo_article());
						managerEncheres.addEnchere(enchere);

						managerArticles.modifierPrixVente(noArticle, enchereInteger);
						// Fabrice
						// managerUtilisateurs.modifierCredit(utilisateur_current.getNoUtilisateur(),
						// enchereInteger);
						System.out.println("montant enchere plus haute" + montantEnchere);
						System.out.println("montant enchere à proposer" + enchereInteger);
						// Thuy
						Integer newCredit = utilisateur_current.getCredit() - enchereInteger;
						managerUtilisateurs.modifierCreditThuy(utilisateur_current.getNoUtilisateur(), newCredit);

						if (noEncherisseur != 0) {
							Integer newCredit2 = encherisseur.getCredit() + montantEnchere;
							managerUtilisateurs.modifierCreditThuy(encherisseur.getNoUtilisateur(), newCredit2);
						}

						request.setAttribute("message", "Votre enchère est bien enregistrée");
						request.getRequestDispatcher("ListeEncheresConnectePagination6Servlet").forward(request, response);
					} catch (BLLException | EnchereException e) {

						// TODO Auto-generated catch block
						request.setAttribute("message", e.getMessage());
						request.getRequestDispatcher("Enchere.jsp").forward(request, response);
					}

				}

			}

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

}
