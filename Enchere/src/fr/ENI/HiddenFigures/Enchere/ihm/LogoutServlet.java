package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurAuthToken;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurAuthTokenSingl;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurAuthToken managerUtilisateurAuthToken = ManagerUtilisateurAuthTokenSingl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		Cookie[] cookies = httpRequest.getCookies();
		String selector = "";
		 

		for (Cookie aCookie : cookies) {
			if (aCookie.getName().equals("selector")) {
				selector = aCookie.getValue();
			}  
		}
		
		try {
			managerUtilisateurAuthToken.supprimerTokenParSelector(selector);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cookie cookieSelector = new Cookie("selector", null);
		cookieSelector.setMaxAge(0); 
		 
		Cookie cookieValidator = new Cookie("validator", null);
		cookieValidator.setMaxAge(0);
		
		response.addCookie(cookieSelector);
		response.addCookie(cookieValidator);
		
		
		
		request.getSession().setAttribute("user", null  );
		
		//HttpSession session = request.getSession();
		session.invalidate();
		
		
		
		request.getRequestDispatcher("AccueilNonConnectePagination6Servlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
