<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>

<%
	String result;
	String email="",host="",port="";
	final String password=request.getParameter("password");
	final String userName=request.getParameter("userName");
	String error="";
	try {
// 		userName=request.getParameter("userName");
// 		password=request.getParameter("password");
		email=request.getParameter("email");
		host=request.getParameter("host");
		port=request.getParameter("port");
		
		
		final Properties props = new Properties();
	    props.setProperty("mail.transport.protocol", "smtp");
	    //props.setProperty("mail.host", "smtp.tawuniya.com.sa");  
	    props.setProperty("mail.host", host);  
	    
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", port);  
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	   
	    
	    Session session1 = Session.getInstance(props,  
	    	    new javax.mail.Authenticator() {
	    	      protected PasswordAuthentication getPasswordAuthentication() {
	    	    return new PasswordAuthentication(userName,password);  
	    	      }  
	    	    });  
	    
		final Message msg = new MimeMessage(session1);
		//final InternetAddress addressFrom = new InternetAddress("ecargo@tawuniya.com.sa","ecargo@tawuniya.com.sa");
		final InternetAddress addressFrom = new InternetAddress(userName,userName);
		msg.setFrom(addressFrom);
		
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	
		Multipart multipart = new MimeMultipart();
		BodyPart messageBodyPart1 = new MimeBodyPart();
	    messageBodyPart1.setContent("Test Email","text/html");
	    multipart.addBodyPart(messageBodyPart1);
	    
		msg.setContent(multipart);
		
		msg.setSubject("Test Subject");
		Transport.send(msg);
		
		result = "Success";
	} catch(Exception e){
		result = "Failed";
		e.printStackTrace();
		error = e.toString();
	}
   
   System.out.println("Status===>"+result);
%>
<html>
	<head>
		<title>Send Email using JSP</title>
	</head>
	<body>
		<center>
			<h1>Send Email using JSP</h1>
		</center>
		<p align="center">
		<% 
			out.println("Result: " + result + "\n");
		%>
		</p>
		
		User Name: <b><% out.println(userName);%></b><br>
		Password: <b><% out.println(password);%></b><br>
		To Email Address: <b><% out.println(email);%></b><br>
		Host: <b><% out.println(host);%></b><br>
		Port: <b><% out.println(port);%></b><br>
		<br>
		<br>
		
		Error: <b><% out.println(error);%></b>
	</body>
</html>