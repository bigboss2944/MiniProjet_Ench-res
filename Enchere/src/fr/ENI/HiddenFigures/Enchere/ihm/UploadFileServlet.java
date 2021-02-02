package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendus;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerArticleVendusSingl;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFileServlet")
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

public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerArticleVendus managerArticles = ManagerArticleVendusSingl.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Utilisateur utilisateur_current = (Utilisateur) request.getSession().getAttribute("user");
		String description = request.getParameter("description");
		String noArticleVenduStr = request.getParameter("noArticleVendu");
		if (noArticleVenduStr != null) {
			Integer noArticleVendu = Integer.parseInt(noArticleVenduStr);
			request.getSession().setAttribute("noArticleVenduModifPhoto", noArticleVendu);

		}

		if (description != null) {
			
//			Part filePart = request.getPart("photo");
//			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//			if (fileName!=null) {
//				String dirUrl = request.getServletContext().getRealPath("images/")  + "Utilisateur"
//						+ utilisateur_current.getNoUtilisateur();
//				File dir = new File(dirUrl);
//				if (!dir.exists()) {
//					dir.mkdir();
//				}
//				Integer noArticleVendu = (Integer) request.getSession().getAttribute("noArticleVenduModifPhoto");
//				int noPhoto= noArticleVendu;
//				String refPhoto = "U"+ utilisateur_current.getNoUtilisateur()+"P"+38 +"_"+fileName ; //U2P1; U2P2....
////ajouter une colonne dans le table ARTICLES_VENDUS pour sauvegarder le refPhoto
//				String fileImg = dirUrl + File.separator + refPhoto;
//				File file = new File(fileImg);
//				try {
//					
//					filePart.write(file.getAbsolutePath());
//					request.setAttribute("message", "Upload File Success!");
//				}
			Part filePart = request.getPart("photo");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			// InputStream fileContent = filePart.getInputStream();
			if (fileName!=null) {
				Integer noArticleVendu = (Integer) request.getSession().getAttribute("noArticleVenduModifPhoto");

				String dirUrl = request.getServletContext().getRealPath("images/") + "Utilisateur"
						+ utilisateur_current.getNoUtilisateur();
				File dir = new File(dirUrl);
				if (!dir.exists()) {
					dir.mkdir();
				}
//				String fileImg = dirUrl + File.separator + "U" + utilisateur_current.getNoUtilisateur() + "P"
//						+ noArticleVendu + "_" + fileName;
//				
//				File file = new File(fileImg);
				
				String fileImg = dirUrl + File.separator
						+ managerArticles.getArticleVendu(noArticleVendu).getRefPhoto();
				File file = new File(fileImg);
				
				try {
					if (file.exists()) {
						file.delete();
					}
					fileImg = dirUrl + File.separator + "U" + utilisateur_current.getNoUtilisateur() + "P"
							+ noArticleVendu + "_" + fileName;
					file = new File(fileImg);
					System.out.println("bien ici");
					filePart.write(file.getAbsolutePath());
					
					
					managerArticles.modifierRefPhoto(noArticleVendu, "U" + utilisateur_current.getNoUtilisateur() + "P"
							+ noArticleVendu + "_" + fileName );
					
					
					request.getSession().setAttribute("noArticleVenduModifPhoto", null);
					request.setAttribute("message", "Upload File Success!");
					System.out.println("ĐƯỜNG DẪN KIỂM TRA UPLOAD HÌNH ẢNH : \n" + dirUrl);
				}catch (Exception e) {
					request.setAttribute("message", "Upload File Failed!");
					e.printStackTrace();
				}

			} else {
				request.setAttribute("message", "Filename est null!");
			}

		}

		getServletContext().getRequestDispatcher("/uploadFile.jsp").forward(request, response);

	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
//	private String extractFileName(Part part) {
//		String contentDisp = part.getHeader("content-disposition");
//		String[] items = contentDisp.split(";");
//		for (String s : items) {
//			if (s.trim().startsWith("filename")) {
//				return s.substring(s.indexOf("=") + 2, s.length() - 1);
//			}
//		}
//		return "";
//	}

//	public File getFolderUpload(String nameimg) {
//
//		String fileImg = dirUrl + File.separator + nameimg;
//		File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
//		if (!folderUpload.exists()) {
//			folderUpload.mkdirs();
//		}
//		return folderUpload;
//	}

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
