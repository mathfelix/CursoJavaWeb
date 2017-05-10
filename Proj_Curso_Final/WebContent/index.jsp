<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	
	<h2>App Proj_Curso</h2>
	<a href="cadastro.jsp">Novo Usuario</a>
	<hr /><br />

	${msg}

	<form action="acessologin" method="post">
	<br/>
		<fieldset>
			<legend>Entre com seus Dados.</legend>
				
			Email:<br/>
			<input type="text" name="email" size="25" required="required" />
			<br/><br/>

			Senha:<br/>
			<input type="password" name="senha" size="25" required="required" />			
			<br/><a href="recuperasenha.jsp">Esqueci minha senha</a><br/><br/>	
			
			<input type="submit" value="Entrar" />
				
		</fieldset>
	</form>
	<br><br>

</body>
</html>