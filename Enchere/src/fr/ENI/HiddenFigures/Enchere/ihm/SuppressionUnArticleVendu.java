package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendus;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendusSingl;
import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class SuppressionUnArticleVendu
 */
@WebServlet("/SuppressionUnArticleVendu")
public class SuppressionUnArticleVendu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuppressionUnArticleVendu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8") ;
		
		Utilisateur utilisateur_current =  (Utilisateur) request.getSession().getAttribute("user");
		if (utilisateur_current!=null) {
			String noArticleString = request.getParameter("noArticleVendu");
			if(noArticleString!=null) {
				Integer noArticle = Integer.parseInt(noArticleString);
				String refPhoto =managerArticles.getArticleVendu(noArticle).getRefPhoto();
				try {
					managerArticles.deleteArticleVendu(noArticle);
					//TODO: delete photo dans server
					String fileImg = request.getServletContext().getRealPath("images/") +"Utilisateur"
							+ utilisateur_current.getNoUtilisateur() + File.separator + refPhoto;
					File file = new File(fileImg);
		System.out.println(fileImg);			
					if (file.exists()) {
						file.delete();
					}
					request.setAttribute("message", "Votre article est bien supprimé");
					request.getRequestDispatcher("ListeEncheresConnecteServlet").forward(request, response);
					
				} catch (BLLException e) {
					request.setAttribute("message", e.getMessage());
				}
				
			}	
			 
		}
		else {
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
