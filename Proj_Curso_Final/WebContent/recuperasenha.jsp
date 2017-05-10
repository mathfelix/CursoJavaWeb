<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nova senha</title>
</head>
<body>
	
	<h3><a href="index.jsp">Voltar</a></h3>
	<br>
	
	${msg}
	<form action="mudarsenha" method="post">
	<br/>
		<fieldset>
			<legend>Informe seu email.</legend>
				
			Email:<br/>
			<input type="text" name="email" size="25" required="required" />
			<br/><br/>
			
			<input type="submit" value="Enviar" />
				
		</fieldset>
	</form>

</body>
</html>