package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/ModifierProfilServlet")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String new_pseudo = request.getParameter("pseudo");
		String new_nom = request.getParameter("nom");
		String new_prenom = request.getParameter("prenom");
		String new_email = request.getParameter("email");
		String new_telephone = request.getParameter("telephone");
		String new_rue = request.getParameter("rue");
		String new_codePostal = request.getParameter("codePostal");
		String new_ville = request.getParameter("ville");
		String new_motDePasse = request.getParameter("motDePasse");
		String new_motDePasse2 = request.getParameter("motDePasse2");
		
		request.getRequestDispatcher("modifierProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
