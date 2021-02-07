package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
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
 * Servlet implementation class AccueilNonConnecte
 */
@WebServlet("/ListeEncheresConnecteAdminPagination6RechercheServlet")
public class ListeEncheresConnecteAdminPagination6RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
	private ManagerEncheres managerEncheres = ManagerEncheresSingl.getInstance();
	private static final int recordsPerPage  =6;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeEncheresConnecteAdminPagination6RechercheServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8") ;
		
		
		
		
		if (request.getSession().getAttribute("user") != null  ) {
			Utilisateur utilisateur_current = (Utilisateur) request.getSession().getAttribute("user");
			// Afficher les libellés dans la liste roulante de catégorie
			CategorieModel categorieModel = new CategorieModel();
			List<Categorie> listCategories = managerCategories.getCategories();
			categorieModel.setLstCategories(listCategories);
			request.setAttribute("categorieModel", categorieModel);
			
			List<ArticleVendu> listArticlesVendus = managerArticles.getLstArticleVendus();
			List<ArticleVendu> listArticlesVendusEncours = new ArrayList<>();
			try {
				listArticlesVendusEncours = managerArticles.getArticleByEtatVenteEnCours();
			} catch (BLLException e) {
				request.setAttribute("message", e.getMessage());
			}
			// Afficher la liste des enchères en cours = les articles en état "en cours"
			// selon les mots recherche
			List<ArticleVendu> listArticleAutresAvecEncheresOuvertes = new ArrayList<>();
			if (listArticlesVendus!=null) {
				for (ArticleVendu articleVendu : listArticlesVendusEncours) {
					if(articleVendu.getNoUtilisateur() != utilisateur_current.getNoUtilisateur()) {
						listArticleAutresAvecEncheresOuvertes.add(articleVendu);
					}
					
				}
			}
			
			
			List<ArticleVendu> listArticlesJeFaisEnchere = new ArrayList<>();
			//TODO: à modifier par sql join
			List<Enchere> listMesEncheres = managerEncheres.getLstEnchereOfUserById(utilisateur_current.getNoUtilisateur());
			if (listArticlesVendusEncours!=null &&listMesEncheres!=null) {
				for (Enchere enchere : listMesEncheres) {
					//TODO: cette boucle peut être remplacée par managerArticlesVendus.getArticleById(enchere.getNoArticle) par Fabrice
					for (ArticleVendu articleVendu : listArticlesVendusEncours) {
						if(articleVendu.getNoArticle()==enchere.getNo_article()) {
							listArticlesJeFaisEnchere.add(articleVendu);
						}
					}
				}
			}
			
			
			if(listArticlesJeFaisEnchere.size() >= 1) {
				for (int i = 0; i < listArticlesJeFaisEnchere.size()-1; i++) {
					if(listArticlesJeFaisEnchere.get(i).getNoArticle() == listArticlesJeFaisEnchere.get(i+1).getNoArticle()) {
						listArticlesJeFaisEnchere.remove(i);
						i = i-1;
					}
					
				}
				
			}
			
			
			List<ArticleVendu> listArticlesJeRemporte = new ArrayList<>();
			List<Enchere> listMesEncheresRemportees = managerEncheres.getLstEnchereWonOfUserById(utilisateur_current.getNoUtilisateur());
			if (listArticlesVendus!=null &&listMesEncheresRemportees!=null) {
				for (ArticleVendu articleVendu : listArticlesVendus) {
					for (Enchere enchere : listMesEncheresRemportees) { 
						if(articleVendu.getNoArticle()==enchere.getNo_article()) {
							listArticlesJeRemporte.add(articleVendu);
						}
					}
					
				}
			}
			
			
			
			List<ArticleVendu> listMesVentesEC = new ArrayList<>();
			if (listArticlesVendusEncours!=null) {
				for (ArticleVendu articleVendu : listArticlesVendusEncours) {
					if(articleVendu.getNoUtilisateur() ==utilisateur_current.getNoUtilisateur()) {
						listMesVentesEC.add(articleVendu);
					}
				}
			}
			
			
			List<ArticleVendu> listMesVentesNonDebutees = new ArrayList<>();
			List<ArticleVendu> listArticlesNonDebutees = managerArticles.getArticleByEtatNonDebute();
			if (listArticlesNonDebutees!=null) {
				for (ArticleVendu articleVendu : listArticlesNonDebutees) {
					if(articleVendu.getNoUtilisateur() ==utilisateur_current.getNoUtilisateur()) {
						listMesVentesNonDebutees.add(articleVendu);
					}
				}
			}
				
			
			List<ArticleVendu> listMesVentesTerminees = new ArrayList<>();
			List<ArticleVendu> listArticlesTermines = managerArticles.getArticleByEtatTermine();
			if (listArticlesTermines!=null ) {
				for (ArticleVendu articleVendu : listArticlesTermines) {
					if(articleVendu.getNoUtilisateur() ==utilisateur_current.getNoUtilisateur()) {
						listMesVentesTerminees.add(articleVendu);
					}
				}
			}
			
			
			//Faire la recherche selon request
			List<Utilisateur> listVendeurs = new ArrayList<>();
			String nomArticleContient = request.getParameter("nomArticleContient");
			String libelleCategorie = request.getParameter("categorie");
			
			String achatVente = request.getParameter("achatVente");
			
			String encheresOuvertes = request.getParameter("encheresOuvertes");
			String mesEncheres = request.getParameter("mesEncheres");
			String mesEncheresRemportees = request.getParameter("mesEncheresRemportees");
			
			String mesVentesEC = request.getParameter("mesVentesEC");
			String ventesNonDebutees = request.getParameter("ventesNonDebutees");
			String ventesTerminees = request.getParameter("ventesTerminees");
			
			if(nomArticleContient !=null && libelleCategorie !=null) {
				request.getSession().setAttribute("nomArticleContientSession", nomArticleContient);
				request.getSession().setAttribute("libelleCategorieSession", libelleCategorie);
				request.getSession().setAttribute("achatVenteSession", achatVente);
				request.getSession().setAttribute("encheresOuvertesSession", encheresOuvertes);
				request.getSession().setAttribute("mesEncheresSession", mesEncheres);
				request.getSession().setAttribute("mesEncheresRemporteesSession", mesEncheresRemportees);
				request.getSession().setAttribute("mesVentesECSession", mesVentesEC);
				request.getSession().setAttribute("ventesNonDebuteesSession", ventesNonDebutees);
				request.getSession().setAttribute("ventesTermineesSession", ventesTerminees);
				
			}
			else {
				nomArticleContient = (String) request.getSession().getAttribute("nomArticleContientSession");
				libelleCategorie = (String) request.getSession().getAttribute("libelleCategorieSession");
				achatVente = (String) request.getSession().getAttribute("achatVenteSession");
				encheresOuvertes = (String) request.getSession().getAttribute("encheresOuvertesSession");
				mesEncheres = (String) request.getSession().getAttribute("mesEncheresSession");
				mesEncheresRemportees = (String) request.getSession().getAttribute("mesEncheresRemporteesSession");
				mesVentesEC = (String) request.getSession().getAttribute("mesVentesECSession");
				ventesNonDebutees = (String) request.getSession().getAttribute("ventesNonDebuteesSession");
				ventesTerminees = (String) request.getSession().getAttribute("ventesTermineesSession");
			}
			
			Utilisateur utilisateur = new Utilisateur();
			List<ArticleVendu> listArticlesParNomArticleEtCategorieAchatVente = new ArrayList<>();
			
			List<ArticleVendu> listArtcilesParPage = new ArrayList<>();	
			String currentPageStr = request.getParameter("currentPage");
				
			int  	currentPage = Integer.parseInt(currentPageStr);
			
			
			
			if( nomArticleContient!=null) {
				
				
				//Liste des articles trouvés par nom et catégorie
				Integer no_categorie = 0; //categorie = toutes
				if (listCategories!=null ) {
					for (Categorie categorie : listCategories) {
						if (categorie.getLibelle().equals(libelleCategorie)) {
							no_categorie = categorie.getNoCategorie();
						}

					}
					
					
					
				}
				
				
				List<ArticleVendu> listArticlesParNomArticleEtCategorie = new ArrayList<>();
				
				if (nomArticleContient != null && !"".equals(nomArticleContient.trim()) ) {
					if ( no_categorie !=0) {
						listArticlesParNomArticleEtCategorie= managerArticles.getArticleByNomArticleContientEtNoCategorie(nomArticleContient, no_categorie);	 
					}
					else {
						listArticlesParNomArticleEtCategorie= managerArticles.getArticleByNomArticleContient(nomArticleContient);	
					}
					
				}
				else {
					if ( no_categorie !=0) {
						listArticlesParNomArticleEtCategorie= managerArticles.getArticleByCategorie(no_categorie);	 
					}
					else {
						listArticlesParNomArticleEtCategorie= listArticlesVendus;	
						
					}
				}
				
				//Liste des articles trouvés par nom,catégorie et AchatVente
				
				if(achatVente !=null) {
					//Achats
					
					if (encheresOuvertes!=null &&mesEncheres ==null && mesEncheresRemportees ==null
							&& listArticlesParNomArticleEtCategorie!=null && listArticlesVendusEncours !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listArticleAutresAvecEncheresOuvertes) {
								if(articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
									listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
								}
							}
							
						}
					}
					
					if (encheresOuvertes==null &&mesEncheres !=null && mesEncheresRemportees ==null
							&& listArticlesParNomArticleEtCategorie!=null && listArticlesJeFaisEnchere !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							
							for (ArticleVendu articleVendu2 : listArticlesJeFaisEnchere) {
								
								if(articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
									listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
									
								}
							}
							
						}
					}
					
					if (encheresOuvertes==null &&mesEncheres ==null && mesEncheresRemportees !=null
							&& listArticlesParNomArticleEtCategorie!=null && listArticlesJeRemporte !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listArticlesJeRemporte) {
								if(articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
									listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
								}
							}
							
						}
					}
					if (encheresOuvertes!=null &&mesEncheres !=null && mesEncheresRemportees ==null
							&& listArticlesParNomArticleEtCategorie!=null && listArticlesVendusEncours !=null
							&& listArticlesJeFaisEnchere !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listArticleAutresAvecEncheresOuvertes) {
								for (ArticleVendu articleVendu3 : listArticlesJeFaisEnchere) {
									if(articleVendu3.getNoArticle() ==articleVendu2.getNoArticle() && articleVendu3.getNoArticle() == articleVendu.getNoArticle()) {
										listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
									}
								}
								
							}
							
						}
					}
					if (encheresOuvertes==null &&mesEncheres !=null && mesEncheresRemportees !=null
							&& listArticlesParNomArticleEtCategorie!=null && listArticlesJeFaisEnchere !=null
							&& listArticlesJeRemporte !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listArticlesJeFaisEnchere) {
								for (ArticleVendu articleVendu3 : listArticlesJeRemporte) {
									if(articleVendu3.getNoArticle() ==articleVendu2.getNoArticle() && articleVendu3.getNoArticle() == articleVendu.getNoArticle()) {
										listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
									}
								}
								
							}
							
						}
					}
					if (encheresOuvertes!=null &&mesEncheres ==null && mesEncheresRemportees !=null
							&& listArticlesParNomArticleEtCategorie!=null && listArticlesVendusEncours !=null
							&& listArticlesJeRemporte !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listArticleAutresAvecEncheresOuvertes) {
								for (ArticleVendu articleVendu3 : listArticlesJeRemporte) {
									if(articleVendu3.getNoArticle() ==articleVendu2.getNoArticle() && articleVendu3.getNoArticle() == articleVendu.getNoArticle()) {
										listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
									}
								}
								
							}
							
						}
					}
					if (encheresOuvertes!=null &&mesEncheres !=null && mesEncheresRemportees !=null
							&& listArticlesParNomArticleEtCategorie!=null && listArticlesVendusEncours !=null
							&& listArticlesJeFaisEnchere !=null&& listArticlesJeRemporte !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listArticleAutresAvecEncheresOuvertes) {
								for (ArticleVendu articleVendu3 : listArticlesJeFaisEnchere) {
									for (ArticleVendu articleVendu4 : listArticlesJeRemporte) {
										if(articleVendu4.getNoArticle() ==articleVendu3.getNoArticle() 
												&& articleVendu3.getNoArticle() == articleVendu2.getNoArticle()
												&& articleVendu2.getNoArticle() == articleVendu.getNoArticle()) {
											listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
										}
									}
									
								}
								
							}
							
						}
					}
					
					//Ventes
					 
					if (mesVentesEC!=null &&ventesNonDebutees ==null && ventesTerminees ==null
							&& listArticlesParNomArticleEtCategorie !=null&& listMesVentesEC !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listMesVentesEC) {
								if(articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
									listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
								}
							}
							
						}
					}
					if (mesVentesEC==null &&ventesNonDebutees !=null && ventesTerminees ==null
							&& listArticlesParNomArticleEtCategorie !=null&& listMesVentesNonDebutees !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listMesVentesNonDebutees) {
								if(articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
									listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
								}
							}
							
						}
					}
					if (mesVentesEC==null &&ventesNonDebutees ==null && ventesTerminees !=null
							&& listArticlesParNomArticleEtCategorie !=null&& listMesVentesTerminees !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listMesVentesTerminees) {
								if(articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
									listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
								}
							}
							
						}
					}
					if (mesVentesEC!=null &&ventesNonDebutees !=null && ventesTerminees ==null
							&& listArticlesParNomArticleEtCategorie!=null && listMesVentesEC !=null
							&& listMesVentesNonDebutees !=null ) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listMesVentesEC) {
								for (ArticleVendu articleVendu3 : listMesVentesNonDebutees) {
									if(articleVendu3.getNoArticle() ==articleVendu2.getNoArticle()
											&& articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
										listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
									}
								}
							}
							
						}
					}
					if (mesVentesEC!=null &&ventesNonDebutees ==null && ventesTerminees !=null
							&& listArticlesParNomArticleEtCategorie!=null && listMesVentesEC !=null
							&& listMesVentesTerminees !=null ) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listMesVentesEC) {
								for (ArticleVendu articleVendu3 : listMesVentesTerminees) {
									if(articleVendu3.getNoArticle() ==articleVendu2.getNoArticle()
											&& articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
										listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
									}
								}
							}
							
						}
					}
					if (mesVentesEC==null &&ventesNonDebutees !=null && ventesTerminees !=null
							&& listArticlesParNomArticleEtCategorie!=null && listMesVentesNonDebutees !=null
							&& listMesVentesTerminees !=null ) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listMesVentesNonDebutees) {
								for (ArticleVendu articleVendu3 : listMesVentesTerminees) {
									if(articleVendu3.getNoArticle() ==articleVendu2.getNoArticle()
											&& articleVendu2.getNoArticle() ==articleVendu.getNoArticle() ) {
										listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
									}
								}
							}
							
						}
					}
					if (mesVentesEC!=null &&ventesNonDebutees !=null && ventesTerminees !=null
							&& listArticlesParNomArticleEtCategorie!=null && listMesVentesEC !=null
							&& listMesVentesNonDebutees !=null&& listMesVentesTerminees !=null) {
						for (ArticleVendu articleVendu : listArticlesParNomArticleEtCategorie) {
							for (ArticleVendu articleVendu2 : listMesVentesEC) {
								for (ArticleVendu articleVendu3 : listMesVentesNonDebutees) {
									for (ArticleVendu articleVendu4 : listMesVentesTerminees) {
										if(articleVendu4.getNoArticle() ==articleVendu3.getNoArticle()
												&& articleVendu3.getNoArticle() ==articleVendu2.getNoArticle()
												&& articleVendu2.getNoArticle() ==articleVendu.getNoArticle()) {
											listArticlesParNomArticleEtCategorieAchatVente.add(articleVendu);
										}
									}
									
								}
							}
							
						}
					}
				}
				else
				{
					listArticlesParNomArticleEtCategorieAchatVente =listArticlesParNomArticleEtCategorie;
				}
				
				
				
				
				if (listArticlesParNomArticleEtCategorieAchatVente.size() == 0) {
					request.setAttribute("message", "0 résultat trouvé");
				} else {
					listArtcilesParPage =  listArtcilesParPage(listArticlesParNomArticleEtCategorieAchatVente,currentPage );
				}
					
			}
			
			if(listArtcilesParPage!=null) {
				
				listVendeurs =new ArrayList<Utilisateur>();
				
				for (ArticleVendu articleVendu : listArtcilesParPage) {
					try {
						utilisateur= managerUtilisateurs.rechercherUtilisateurParNoUtilisateur(articleVendu.getNoUtilisateur());
						Utilisateur vendeur  =new Utilisateur();
						vendeur.setPseudo(utilisateur.getPseudo());
						vendeur.setNoUtilisateur(utilisateur.getNoUtilisateur() );
						vendeur.setListArticlesVendus(new ArrayList<ArticleVendu>()); //eviter de cummuler les articles vendus précédents
						vendeur.getListArticlesVendus().add(articleVendu);
						listVendeurs.add(vendeur);
						
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
			UtilisateurModel utilisateurModel = new UtilisateurModel();
			utilisateurModel.setListUtilisateur(listVendeurs);
			
			
			
			// request.setAttribute("pseudo",utilisateurModel);
			request.setAttribute("utilisateurModel", utilisateurModel);
			
			int rows = listVendeurs.size();
			int nOfPages = rows / recordsPerPage;

	        if (nOfPages % recordsPerPage > 0) {

	            nOfPages++;
	        }
	        request.setAttribute("noOfPages", nOfPages);
	        request.setAttribute("currentPage", currentPage);
	        request.setAttribute("recordsPerPage", recordsPerPage); 
			
			request.getRequestDispatcher("listeEncheresConnecteAdminPagination6Recherche.jsp").forward(request, response);
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
	protected List<ArticleVendu> listArtcilesParPage(List<ArticleVendu> listArticles, int currentPage) {
		List<ArticleVendu> listArtcilesParPage = new ArrayList<>();
		if(listArticles!=null) {
			for (int i = 0; i < Math.min(recordsPerPage, listArticles.size() - recordsPerPage*(currentPage-1)) ; i++) {
				listArtcilesParPage.add(listArticles.get(recordsPerPage*(currentPage -1) +i ));
				
			}
		}
		return listArtcilesParPage;
	}

}
