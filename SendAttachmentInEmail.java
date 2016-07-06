
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;  
import java.util.Scanner;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class SendAttachmentInEmail {  
	
		 public static void main(String[] args)
		 {  
			  Scanner scan=new Scanner(System.in);
			// String to="";//change accordingly  
			  String to="RecieverEmail@gmail.com";
			  //Get the session object  
			  Properties props = System.getProperties();  
			  props.put("mail.smtp.host", "smtp.gmail.com");  
			  props.put("mail.smtp.socketFactory.port", "465");  
			  props.put("mail.smtp.socketFactory.class",  
			            "javax.net.ssl.SSLSocketFactory");  
			  props.put("mail.smtp.auth", "true");  
			  props.put("mail.smtp.port", "465");  
			  final String pass;
			  final String msg;
			   System.out.println("enter your password : ");
			   password = scan.nextLine();
			   System.out.println("Enter Your message : ");
			   msg=scan.nextLine();
			  Session session = Session.getDefaultInstance(props,  
			   new javax.mail.Authenticator() 
			  {  
			   protected PasswordAuthentication getPasswordAuthentication() 
			   {  
				   return new PasswordAuthentication("SenderEmail@gmail.com",password);//change accordingly  
			   }  
			  });  
			   
			  //compose message  
			  try {  
			   MimeMessage message = new MimeMessage(session);  
			   message.setFrom(new InternetAddress("SenderEmail@gmail.com"));//change accordingly  
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
			   message.setSubject("attached file sent from java code:: ");
			 		   
			   MimeBodyPart part2 = new MimeBodyPart();
			   String attachFile = "D:/FileName.xls"; //write your file name with an extension
			   
			   DataSource source = new FileDataSource(attachFile);
			   part2.setDataHandler(new DataHandler(source));
			   part2.setFileName(new File(attachFile).getName());
			   
			   BodyPart _msg = new MimeBodyPart();
			   _msg.setText(msg); 
			   
			   Multipart multipart = new MimeMultipart();
			   multipart.addBodyPart(part2);
			   multipart.addBodyPart(_msg);
			  
			   message.setContent(multipart);
				
				   Transport.send(message);
				   System.out.println("message sent successfully");
			   
			  } catch (MessagingException e) {throw new RuntimeException(e);}  
			   
		 }  
}  
