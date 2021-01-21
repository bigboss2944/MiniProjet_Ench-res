package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur_current =  (Utilisateur) request.getSession().getAttribute("user");
		System.out.println(utilisateur_current.getNoUtilisateur());
		if (utilisateur_current.getNoUtilisateur() != null) {
			//Integer noVendeur = Integer.parseInt(noVendeurStr);
			try {
				Utilisateur vendeur = managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(utilisateur_current.getNoUtilisateur());
				UtilisateurModel vendeurModel = new UtilisateurModel(vendeur);
				request.setAttribute("user", vendeur);
				request.getRequestDispatcher("monProfil.jsp").forward(request, response);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
