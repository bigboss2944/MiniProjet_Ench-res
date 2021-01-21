package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;

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
import fr.ENI.HiddenFigures.Enchere.bo.Enchere;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class EnchereServlet
 */
@WebServlet("/EnchereServletThuy")
public class EnchereServletThuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
	private ManagerEncheres managerEncheres = ManagerEncheresSingl.getInstance();
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnchereServletThuy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8") ;
		
		
		if (request.getSession().getAttribute("user") != null  ) {
			
			
			Utilisateur utilisateur_current = (Utilisateur) request.getSession().getAttribute("user");
			String montantEnchereMaxPrecedentString = request.getParameter("montantEnchereMaxPrecedent");
			String noEncherisseurCurrentString = request.getParameter("noEncherisseurCurrent");
			String noArticleVenduString = request.getParameter("noArticleVendu");
			String maPropositionString = request.getParameter("maProposition");
			
			
			if(noArticleVenduString!=null&& maPropositionString !=null) {
				Integer  maProposition =Integer.parseInt(maPropositionString);
				Integer noArticleVendu =Integer.parseInt(noArticleVenduString);
				
				Enchere monEnchere = new Enchere();
				monEnchere.setMontant_enchere(maProposition);
				monEnchere.setNo_article(noArticleVendu);
				monEnchere.setNo_utilisateur(utilisateur_current.getNoUtilisateur());
				
				try {
					
					managerEncheres.addEnchere(monEnchere);
					System.out.println("bien inserere");
					Integer newCredit = utilisateur_current.getCredit() - maProposition;
					managerUtilisateurs.modifierCredit(utilisateur_current.getNoUtilisateur(), newCredit);
					
					if(montantEnchereMaxPrecedentString!=null&& noEncherisseurCurrentString !=null) {
						Integer  montantEnchereMaxPrecedent =Integer.parseInt(montantEnchereMaxPrecedentString);
						Integer noEncherisseurCurrent =Integer.parseInt(noEncherisseurCurrentString);
						
						Utilisateur encherisseurCurrent=managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(noEncherisseurCurrent);
						Integer newCredit2 = encherisseurCurrent.getCredit()+montantEnchereMaxPrecedent;
						managerUtilisateurs.modifierCredit(utilisateur_current.getNoUtilisateur(), newCredit2);
						
						
					}
					request.setAttribute("message", "Votre enchere est bien enregistreée");
					request.getRequestDispatcher("listeEncheresConnecte.jsp").forward(request, response);
					
				} catch (BLLException e) {
					request.setAttribute("message", e.getMessage());
				} catch (EnchereException e) {
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
