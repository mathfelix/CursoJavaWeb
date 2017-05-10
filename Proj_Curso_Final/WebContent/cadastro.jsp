<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link type="text/css" rel="stylesheet"
	href="bootstrap/css/bootstrap.css" >

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro</title>
</head>
<body>

	<div class="container">

	<h2>App Proj_Curso</h2>
	<a href="index.jsp">Login</a>
	<hr /><br />

	${msg}
	
	<form method="post" action="usuariocontrolcad">
		<fieldset>
			<legend>Dados do Usuario.</legend>
			
		Nome:<br />
		<input type="text" required="required" name="nome" size="25"
			pattern="[a-zA-Zá-ú ]{4,60}" />
		<br /><br />
	
		Genero:<br />
		<input type="radio" checked="checked" required="required" name="genero" value="Feminino"/>Feminino
		<input type="radio" required="required" name="genero" value="Masculino"/>Masculino
		<br /><br />
	
		Nascimento:<br />
		<input type="date" required="required" name="nascimento" size="25" />
		<br /><br />
		
		Endereco:<br />
		<input type="text" required="required" name="endereco" size="25" />
		<br /><br /> 
		
		Email:<br />
		<input type="email" required="required" name="email" size="25"/>
		<br /><br /> 
	
		Senha:<br />
		<input type="password" required="required" name="senha" size="15"
			pattern="[a-zA-Z0-9]{4,16}" />
		<br /><br />
	
		Confirme a Senha:<br />
		<input type="password" required="required" name="confsenha" size="15" />
		<br /><br />
	
		<input type="submit" value="Salvar" class="btn btn-primary" />
		</fieldset>
	</form>

	</div>

</body>
</html>