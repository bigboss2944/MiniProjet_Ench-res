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
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/ModifierProfilServlet")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();   
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
		Utilisateur utilisateur_current =  (Utilisateur) request.getSession().getAttribute("user");
		if(utilisateur_current!=null) {
			String new_pseudo = request.getParameter("pseudo");
			String new_nom = request.getParameter("nom");
			String new_prenom = request.getParameter("prenom");
			String new_email = request.getParameter("email");
			String new_telephone = request.getParameter("telephone");
			String new_rue = request.getParameter("rue");
			String new_codePostal = request.getParameter("codePostal");
			String new_ville = request.getParameter("ville");
			String old_motDePasse = request.getParameter("motDePasse");
			String new_motDePasse = request.getParameter("nouveauMotDePasse");
			String new_motDePasse2 = request.getParameter("nouveauMotDePasse2");
			 
			
			Utilisateur utilisateurModifie = new Utilisateur(utilisateur_current.getPseudo(), utilisateur_current.getNom(),
					utilisateur_current.getPrenom() , utilisateur_current.getEmail(), utilisateur_current.getTelephone(),
					utilisateur_current.getRue(), utilisateur_current.getCodePostal(), utilisateur_current.getVille(), 
					utilisateur_current.getMotDePasse(),utilisateur_current.getCredit(), utilisateur_current.getAdministrateur());
			utilisateurModifie.setNoUtilisateur(utilisateur_current.getNoUtilisateur());
			
			
			if (old_motDePasse!=null) {
				//dans tous cas, il faut mettre le mot de passe actuel
				if(utilisateur_current.getMotDePasse().equals(old_motDePasse)) {
					//cas: modifier pseudo
					if( new_pseudo != null && ! "".equals(new_pseudo.trim())) {
						utilisateurModifie.setPseudo(new_pseudo);
						try {
							managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.pseudoEstUnique(utilisateurModifie);
							managerUtilisateurs.pseudoContientQueAlphanumeriques(utilisateurModifie);
							managerUtilisateurs.modifierPseudo(utilisateurModifie.getNoUtilisateur(), new_pseudo);
							request.setAttribute("message", "Modification est bien enregistrée ");
							utilisateur_current.setPseudo(new_pseudo);
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
					}
					//cas: modifier nom
					if( new_nom != null && ! "".equals(new_nom.trim())) {
						utilisateurModifie.setNom(new_nom);
						try {
							managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.modifierNom(utilisateurModifie.getNoUtilisateur(), new_nom);
							request.setAttribute("message", "Modification est bien enregistrée ");
							utilisateur_current.setNom(new_nom);
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
					}
					//cas: modifier prenom
					if( new_prenom != null && ! "".equals(new_prenom.trim())) {
						utilisateurModifie.setPrenom(new_prenom);
						try {
							managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.modifierPrenom(utilisateurModifie.getNoUtilisateur(), new_prenom);
							request.setAttribute("message", "Modification est bien enregistrée ");
							utilisateur_current.setPrenom(new_prenom);
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
					}
					//cas: modifier email
					if( new_email != null && ! "".equals(new_email.trim())) {
						utilisateurModifie.setEmail(new_email);
						try {
							managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.emailEstUnique(utilisateurModifie);
							managerUtilisateurs.modifierEmail(utilisateurModifie.getNoUtilisateur(), new_email);
							request.setAttribute("message", "Modification est bien enregistrée ");
							utilisateur_current.setEmail(new_email);
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
					}
					//cas: modifier téléphone
					if( new_telephone != null && ! "".equals(new_telephone.trim())) {
						utilisateurModifie.setTelephone(new_telephone);
						try {
							managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.verificationTelephone(utilisateurModifie);
							managerUtilisateurs.modifierTelephone(utilisateurModifie.getNoUtilisateur(), new_telephone);
							request.setAttribute("message", "Modification est bien enregistrée ");
							utilisateur_current.setTelephone(new_telephone);
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
					}
					//cas: modifier rue
					if( new_rue != null && ! "".equals(new_rue.trim())) {
						utilisateurModifie.setRue(new_rue);
						try {
							managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.modifierRue(utilisateurModifie.getNoUtilisateur(), new_rue);
							request.setAttribute("message", "Modification est bien enregistrée ");
							utilisateur_current.setRue(new_rue);
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
					}
					//cas: modifier code postal
					if( new_codePostal != null && ! "".equals(new_codePostal.trim())) {
						utilisateurModifie.setCodePostal(new_codePostal);
						try {
							managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.modifierCodePostal(utilisateurModifie.getNoUtilisateur(), new_codePostal);
							request.setAttribute("message", "Modification est bien enregistrée ");
							utilisateur_current.setCodePostal(new_codePostal);
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
					}
					//cas: modifier ville
					if( new_ville != null && ! "".equals(new_ville.trim())) {
						utilisateurModifie.setVille(new_ville);
						try {
							managerUtilisateurs.lesAutresContraints(utilisateurModifie);
							managerUtilisateurs.modifierVille(utilisateurModifie.getNoUtilisateur(), new_ville);
							request.setAttribute("message", "Modification est bien enregistrée ");
							utilisateur_current.setVille(new_ville);
						} catch (BLLException e) {
							request.setAttribute("message", e.getMessage());
						}
					}
					//cas: modifier mot de passe
					if( new_motDePasse != null && ! "".equals(new_motDePasse.trim())) {
						if(new_motDePasse.equals(new_motDePasse2)) {
							utilisateurModifie.setMotDePasse(new_motDePasse);
							try {
								managerUtilisateurs.lesAutresContraints(utilisateurModifie);
								managerUtilisateurs.modifierMotDePasse(utilisateurModifie.getNoUtilisateur(), new_motDePasse);
								request.setAttribute("message", "Modification est bien enregistrée ");
								utilisateur_current.setMotDePasse(new_motDePasse);
							} catch (BLLException e) {
								request.setAttribute("message", e.getMessage());
							}
						}
						else {
							request.setAttribute("message", "New passwords did not match ");
						}
						
					}
					
				}
				else {
					request.setAttribute("message", "Mot de passe n'est pas correct");
				}
			}
			
			
			request.getRequestDispatcher("modifierProfil.jsp").forward(request, response);
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
