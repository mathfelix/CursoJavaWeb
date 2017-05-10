<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mudar Senha</title>
</head>
<body>
	
	${msg}
	<form action="alterasenha" method="post">
		<fieldset>
		
		<legend>Informe novamente seu Email e uma nova Senha!</legend>
		<br>
		Email:<br>
		<input name="email" type="text" size="30"/>
		<br><br>
		
		Senha:<br>
		<input name="senha" type="password" size="20"/>
		<br>
		
		Confirme a senha:<br>
		<input name="confsenha" type="password" size="20"/>
		<br><br>
		
		<input type="submit" value="Confirmar">
		
		</fieldset>
	
	</form>

	

</body>
</html>