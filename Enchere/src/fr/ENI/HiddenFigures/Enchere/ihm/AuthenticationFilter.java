package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurAuthToken;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurAuthTokenSingl;
import fr.ENI.HiddenFigures.Enchere.bo.UtilisateurAuthToken;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/LoginServlet")
public class AuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// read cookie
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		Cookie[] cookies = httpRequest.getCookies();
		String selector = "";
		String rawValidator = "";

		for (Cookie aCookie : cookies) {
			if (aCookie.getName().equals("selector")) {
				selector = aCookie.getValue();
			} else if (aCookie.getName().equals("validator")) {
				rawValidator = aCookie.getValue();
			}
		}
		
	
		if (!"".equals(selector) && !"".equals(rawValidator)) {
			ManagerUtilisateurAuthToken managerUtilisateurAuthToken = ManagerUtilisateurAuthTokenSingl.getInstance();
			UtilisateurAuthToken token = managerUtilisateurAuthToken.findBySelector(selector);
 
			if (token != null) {
				
				String hashedValidatorDatabase = token.getValidator();
				String hashedValidatorCookie = "";
				try {
					hashedValidatorCookie = HashGeneratorUtils.generateSHA256(rawValidator);

					if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
						session = httpRequest.getSession();
						session.setAttribute("user", token.getUtilisateur());
						// loggedIn = true;

						// update new token in database
						String newSelector = RandomStringUtils.randomAlphanumeric(12);

						List<UtilisateurAuthToken> listUtilisateurAuthToken = managerUtilisateurAuthToken
								.getListUtilisateurAuthToken();
						List<String> listSelector = new ArrayList<>();

						if (listUtilisateurAuthToken != null) {
							for (UtilisateurAuthToken utilisateurAuthToken : listUtilisateurAuthToken) {
								listSelector.add(utilisateurAuthToken.getSelector());
							}
					
							while (listSelector.contains(newSelector)) {
								newSelector = RandomStringUtils.randomAlphanumeric(12); // il rassure que aucun selector est
																						// répété dans la BD
							}
						}
						String newRawValidator = RandomStringUtils.randomAlphanumeric(64);

						String newHashedValidator = HashGeneratorUtils.generateSHA256(newRawValidator);
						token.setSelector(newSelector);
						token.setValidator(newHashedValidator);
						managerUtilisateurAuthToken.modifier(token);

						// update cookie
						Cookie cookieSelector = new Cookie("selector", newSelector);
						cookieSelector.setMaxAge(604800);

						Cookie cookieValidator = new Cookie("validator", newRawValidator);
						cookieValidator.setMaxAge(604800);

						httpResponse.addCookie(cookieSelector);
						httpResponse.addCookie(cookieValidator);
					
						request.getRequestDispatcher("ListeEncheresConnectePagination6Servlet").forward(request, response);

					}
				} catch (HashGenerationException | BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				// user have never logged in
				// forward to the login page
				chain.doFilter(request, response);
			}

		} else {
			// user have never logged in
			// forward to the login page
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
