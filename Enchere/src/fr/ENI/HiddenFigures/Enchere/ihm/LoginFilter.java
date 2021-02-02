package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter( urlPatterns= {"/EnchereServlet","/GestionAchatVenteServlet","/ListeEncheresConnecteServlet",
		"/ModifierProfilServlet","/ModifierVenteServlet","/ProfilServlet","/SuppressionCompteServlet",
		"/SuppressionUnArticleVendu","/VendreUnArticleServlet", "/AdministrationServlet", "/ListeEncheresConnecteAdminServlet",
		"/AdminDesactiverCompteServlet", "/AdminActiverCompteServlet", "/AdminSupprimerCategorieServlet",
		"/AdminModifierCategorieServlet", "/AdminAjouterCategorieServlet"})
		
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println( "Passer par filtre login");
		if(((HttpServletRequest)request).getSession().getAttribute("user")==null) {
			//request.getRequestDispatcher("LoginServlet").forward(request, response);
			request.setAttribute("message", "Il faut se connecter pour continuer");
			request.getRequestDispatcher("LoginServlet").forward(request, response);
		}
		else {
				chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
