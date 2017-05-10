package email;

import org.apache.commons.mail.SimpleEmail;

public class EmailConfig {

	private static final String HOSTNAME = "smtp.gmail.com";
	private static final String USERNAME = "cotiinformatica1@gmail.com";
	private static final String PASSWORD = "aulaJava";
	
	
	public static void recuperaSenha(String emailDestino, String mensagem) throws Exception {
		
		SimpleEmail email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.addTo(emailDestino);
		email.setFrom(USERNAME, "Proj Curso");
		email.setSubject("Proj Curso - Nova Senha.");
		email.setMsg(mensagem);
		email.setAuthentication(USERNAME, PASSWORD);
		email.setSmtpPort(587);
		email.setTLS(true);
		email.setSSL(true);
		email.send();
		
	}
	
	public static void confirmaCompra(String emailDestino, String mensagem) throws Exception {
		
		SimpleEmail email = new SimpleEmail();
		email.setHostName(HOSTNAME);
		email.addTo(emailDestino);
		email.setFrom(USERNAME, "Proj Curso");
		email.setSubject("Proj Curso - Compra Efetuada!");
		email.setMsg(mensagem);
		email.setAuthentication(USERNAME, PASSWORD);
		email.setSmtpPort(587);
		email.setTLS(true);
		email.setSSL(true);
		email.send();
		
	}
	
}
