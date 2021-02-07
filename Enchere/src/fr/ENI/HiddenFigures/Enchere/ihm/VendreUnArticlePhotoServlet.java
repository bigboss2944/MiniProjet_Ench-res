package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendus;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendusSingl;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerCategories;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerCategoriesSingl;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerRetrait;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerRetraitSingl;
import fr.ENI.HiddenFigures.Enchere.bo.ArticleVendu;
import fr.ENI.HiddenFigures.Enchere.bo.Categorie;
import fr.ENI.HiddenFigures.Enchere.bo.Retrait;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class VendreUnArticleServlet
 */
@WebServlet("/VendreUnArticlePhotoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50, // 50MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
/*
* Annotation MultipartConfig: Được sử dụng để đánh dấu cho các Servlet dùng để
* xử lý multipart/form-data requests (những request chứa file upload)
* fileSizeThreshold: Nếu kích thước file upload lơn hơn threshold sẽ được ghi
* trực tiếp vào ổ đĩa thay vì lưu ở memory đệm. location: folder chứa file được
* lưu thông qua method Part.write(). maxFileSize: Kích thước tối da của file
* được upload. maxRequestSize: Kích thước tối đa cho một request.
*/
public class VendreUnArticlePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerCategories managerCategories = ManagerCategoriesSingl.getInstance();
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();
	private ManagerRetrait managerRetraits = ManagerRetraitSingl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendreUnArticlePhotoServlet() {
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
			CategorieModel categorieModel = new CategorieModel();
			List<Categorie> listCategories = managerCategories.getCategories();
			categorieModel.setLstCategories(listCategories);
			request.setAttribute("categorieModel", categorieModel);
			
			Retrait retrait = new Retrait(utilisateur_current.getRue(),utilisateur_current.getCodePostal(),utilisateur_current.getVille());
			request.setAttribute("retrait", retrait);
			
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			String libelleCategorie = request.getParameter("categorie");
			
			String miseAPrixString = request.getParameter("miseAPrix");
			String dateDebutEnchereString = request.getParameter("dateDebutEnchere");
			String dateFinEnchereString = request.getParameter("dateFinEnchere");
			
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			
			
			ArticleVendu articleVendu = new ArticleVendu();
			if(nomArticle !=null && description !=null &&libelleCategorie !=null &&miseAPrixString !=null &&dateDebutEnchereString !=null 
					&&dateFinEnchereString !=null &&dateFinEnchereString !=null &&codePostal !=null &&ville !=null) {
				Integer no_categorie = 0;
				if (listCategories!=null ) {
					for (Categorie categorie : listCategories) {
						if (categorie.getLibelle().equals(libelleCategorie)) {
							no_categorie = categorie.getNoCategorie();
						}

					}
				}
				articleVendu.setNomArticle(nomArticle);
				articleVendu.setDescription(description);
				articleVendu.setNoCategorie(no_categorie);
				
				if(dateDebutEnchereString.contains("-")) {
					dateDebutEnchereString = dateDebutEnchereString.substring(8) +"/"+dateDebutEnchereString.substring(5,7)+"/"+dateDebutEnchereString.substring(0,4);
					
				}
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate dateDebutEnchere = LocalDate.parse(dateDebutEnchereString,formatter);
				articleVendu.setDateDebutEncheres(dateDebutEnchere);
				
				if(dateFinEnchereString.contains("-")) {
					dateFinEnchereString = dateFinEnchereString.substring(8) +"/"+dateFinEnchereString.substring(5,7)+"/"+dateFinEnchereString.substring(0,4);
					
				}
				 
				LocalDate dateFinEnchere = LocalDate.parse(dateFinEnchereString,formatter);
				articleVendu.setDateFinEncheres(dateFinEnchere);
				
				articleVendu.setNoUtilisateur(utilisateur_current.getNoUtilisateur());
				
				Integer miseAPrix = Integer.parseInt(miseAPrixString);
				articleVendu.setMiseAprix(miseAPrix);
				
				retrait.setRue(rue);
				retrait.setCode_postal(codePostal);
				retrait.setVille(ville);
				 
				
				
				articleVendu.setLieuRetrait(retrait);
				
				//traitement l'image
				Part filePart = request.getPart("photo");
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
				if (fileName!=null) {
					//String dirUrl = request.getServletContext().getRealPath("images/")  + "Utilisateur"
						//	+ utilisateur_current.getNoUtilisateur();
					String dirUrl = System.getProperty("user.home") + "\\imagesEnchere\\" + "Utilisateur"
							+ utilisateur_current.getNoUtilisateur();
					File dir = new File(dirUrl);
					if (!dir.exists()) {
						 
						dir.mkdir();
					}
 
					int noPhoto=0;
					if(dir.list()!=null) {
						noPhoto= dir.list().length+1;
					}
					else {
						noPhoto=1;
					}
					//int noPhoto= dir.list().length+1;
					String refPhoto = "U"+ utilisateur_current.getNoUtilisateur()+"P"+ noPhoto+"_"+fileName ; //U2P1; U2P2....
//ajouter une colonne dans le table ARTICLES_VENDUS pour sauvegarder le refPhoto
					String fileImg = dirUrl + File.separator + refPhoto;
					File file = new File(fileImg);
					try {
						filePart.write(file.getAbsolutePath());
						//request.setAttribute("message", "Upload File Success!");
						System.out.println("ĐƯỜNG DẪN KIỂM TRA UPLOAD HÌNH ẢNH : \n" + dirUrl);
						
						articleVendu.setRefPhoto(fileName);

						articleVendu = managerArticles.addArticleVendu(articleVendu);
						
						//rename the photo in the disk
						if(file.exists()) { //si on peut pas ajouter l'article dans BD on supprime sa photo 
						       file.renameTo(new File(dirUrl + File.separator + articleVendu.getRefPhoto()));
							}
						 
						retrait.setNoArticle(articleVendu.getNoArticle());//System.out.println(retrait);
						managerRetraits.addRetrait(retrait);
						//TODO : si retrait n'est pas ajouté il faur supprimer l'article
						request.setAttribute("message", "Votre article est bien enregistré avec un lieu de retrait");
					} catch (BLLException e) {
						if(file.exists()) { //si on peut pas ajouter l'article dans BD on supprime sa photo 
					       file.delete();
						}
						request.setAttribute("message", e.getMessage());
					} catch (Exception e) {
						request.setAttribute("message", "Upload File Failed!");
						e.printStackTrace();
					}
					 
				}
				
			}
			
			//request.getRequestDispatcher("vendreUnArticlePhoto.jsp").forward(request, response);
			getServletContext().getRequestDispatcher("/vendreUnArticlePhoto.jsp").forward(request, response);	
			
		} else {
			//request.getRequestDispatcher("AccueilNonConnecteServlet").forward(request, response);
			getServletContext().getRequestDispatcher("/AccueilNonConnectePagination6Servlet").forward(request, response);	
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
