package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.time.LocalDateTime;
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
    private EnchereModel enchereModel = new EnchereModel();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EnchereServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utilisateur utilisateur_current =  (Utilisateur) request.getSession().getAttribute("user");
		List<ArticleVendu> listArticleVendus = managerArticles.getLstArticleVendus();
		List<Categorie> listCategories = managerCategories.getCategories();
		String article=(String) request.getParameter("nomArticle");
		String article_temp=null;
		//String articleParam=(String)request.getAttribute("Article");
		//System.out.println(articleParam);
		ArticleVendu a;
		if((!article.equals(null))&&(!"".equals(article))) {
			System.out.println(utilisateur_current.getNoUtilisateur());
						
			try {
				a = managerArticles.getArticleVenduByNom(article);
				enchereModel.setArticleVendu(a);
				
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		String prop = request.getParameter("propButton");
		Integer enchereInteger = null;
		LocalDateTime dateEnchere = null;
		if(prop!=null){
			enchereInteger = Integer.parseInt(prop);
			try {
				managerUtilisateurs.modifierCredit(utilisateur_current.getNoUtilisateur(), enchereInteger);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArticleVendu articleVendu;
		Integer encherePlusHauteIdUser;
		Integer encherePlusHaute;
		Categorie categorie;
		if(enchereInteger!=null) {
			Enchere enchere = new Enchere(dateEnchere.now(),enchereInteger);
			try {
				enchere.setNo_utilisateur(utilisateur_current.getNoUtilisateur());
				enchere.setNo_article(enchereModel.getArticleVendu().getNoArticle());
				managerEncheres.addEnchere(enchere);
			} catch (BLLException | EnchereException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			request.getRequestDispatcher("listeEncheresConnecte.jsp").forward(request, response);
					
					
				
		}
		
		else {
			for(ArticleVendu art : listArticleVendus) {
				if(art.getNomArticle().equals(article)) {
					articleVendu=art;
					request.setAttribute("article", articleVendu);
					for(Categorie c : listCategories) {
						if(c.getNoCategorie()==art.getNoCategorie()) {
							request.setAttribute("categorie", c.getLibelle());
						}
					}
					try {
						encherePlusHauteIdUser=managerEncheres.IdUserEncherePlusHaute(articleVendu.getNoArticle());
						if(encherePlusHauteIdUser!=0){
							
							encherePlusHauteIdUser=managerEncheres.IdUserEncherePlusHaute(articleVendu.getNoArticle());
							encherePlusHaute=managerEncheres.EncherePlusHaute(articleVendu.getNoArticle());
							Utilisateur pseudo=managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(encherePlusHauteIdUser);
							request.setAttribute("encherePlusHauteIdUser", encherePlusHaute);
							request.setAttribute("encherePlusHauteUtilisateur", pseudo);
						}
						
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//request.getSession().setAttribute("user", managerUtilisateurs.getUtilisateurById(articleVendu.getNoUtilisateur()));
					
					
				}
			}
			request.setAttribute("nomArticle",article);
			request.getRequestDispatcher("Enchere.jsp").forward(request, response);
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
