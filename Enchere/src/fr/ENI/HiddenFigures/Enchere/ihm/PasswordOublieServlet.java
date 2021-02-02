package fr.ENI.HiddenFigures.Enchere.ihm;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.factory.SendMailFactory;

import fr.ENI.HiddenFigures.Enchere.bll.BLLException;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateurs;
import fr.ENI.HiddenFigures.Enchere.bll.ManagerUtilisateursSingl;
import fr.ENI.HiddenFigures.Enchere.bo.Utilisateur;

/**
 * Servlet implementation class PasswordOublieServlet
 */
@WebServlet("/PasswordOublieServlet")
public class PasswordOublieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerUtilisateurs managerUtilisateurs = ManagerUtilisateursSingl.getInstance();
	private static final String username ="thuybt@donga.edu.vn";
	private static final String password ="donga33xvnt";
	//Regarde le lien en-dessous pour cacher le username et password
	//https://www.codejava.net/java-ee/jsp/sending-e-mail-with-jsp-servlet-and-javamail 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordOublieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8") ;
		String email = request.getParameter("email");
		Utilisateur utilisateur = new Utilisateur();
		if (email != null ) {
			try {
				utilisateur =managerUtilisateurs.rechercherUtilisateurParEmail(email);
				 
				Properties props = new Properties();
			      
		        props.put("mail.smtp.host", "smtp.gmail.com");
		        // below mentioned mail.smtp.port is optional 
		        props.put("mail.smtp.port", "587");		
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.starttls.enable", "true");
		        

		        Session session = Session.getInstance(props,new javax.mail.Authenticator()
		        {
		            protected PasswordAuthentication getPasswordAuthentication()
		            {
		  	 	         return new PasswordAuthentication(username,password); 
		            }
		        });
		        String emailTo = email;
		        String emailSubject = "Retire le mot de passe";
		        String emailContent = "<h1>Bonjour Thuy</h1>" 
		        		+"<h5>Veillez cliquer sur le lien en-dessous pour saisir un nouveau mot de passe: </h5>"
		        		+ "<a href=\"http://localhost:8082/Enchere/NouveauPasswordServlet\">Saisir un nouveau mot de passe</a> ";

		        try {
		  
		            MimeMessage message = new MimeMessage(session);
		            message.setFrom(new InternetAddress(username));
		            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
		            message.setSubject(emailSubject);
		            message.setContent(emailContent,"text/html");
		          		             
		           
		            Transport.send(message);
		            System.out.println("Send email : done");
		            request.setAttribute("message","Vérifier votre boîte d'email");
		        }
		        catch(Exception e) {
		    	     e.printStackTrace();
		    	     request.setAttribute("message", "Send email : failed");
		        }
		        
			
				
			} catch (BLLException e) {
				request.setAttribute("message", e.getMessage());

			}
		}
		
		request.getRequestDispatcher("email.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
