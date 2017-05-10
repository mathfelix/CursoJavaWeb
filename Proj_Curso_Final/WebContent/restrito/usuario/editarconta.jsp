<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<h2>Área Restrita</h2>
		<h3>Bem vindo - ${usu.nome}</h3>
		<a href="logout">Sair</a>
		&nbsp; | &nbsp;
		<a href="editconta">Editar Dados</a>
		&nbsp; | &nbsp;
		<a href="ofertas">Ofertas</a> 
		&nbsp; | &nbsp;
		<a href="carrinho">Carrinho</a>
		&nbsp; | &nbsp;
		<a href="historico?codigo=${usu.codigo}">Historico de Compras</a> 
		<hr /><br /><br />

		<b>--Dados do Usuario--</b>
		<br><br>
	
		${msg}
		<br>	
		<form action="salveditusuario?cod=${usu.codigo}" method="post">
			<fieldset>
				<legend>Dados de ${usu.nome}</legend>
				
			Nome:<br />
			<input type="text" required="required" name="nome" size="25" value="${usu.nome}" pattern="[a-zA-Zá-ú ]{4,60}" />
			<br /><br />
		
			Genero:<br />
			<input type="radio" checked="checked" required="required" name="genero" value="Feminino"/>Feminino
			<input type="radio" required="required" name="genero" value="Masculino"/>Masculino
			<br /><br />
		
			Nascimento:<br />
			<input type="date" required="required" name="nascimento" size="25" value="${usu.nascimento}"/>
			<br /><br />
			
			Endereco:<br />
			<input type="text" required="required" name="endereco" size="25" value="${usu.endereco}"/>
			<br /><br /> 
			
			Email:<br />
			<input type="email" required="required" name="email" size="25" value="${usu.email}"/>
			<br /><br /> 
		
			<input type="submit" value="Salvar" class="btn btn-primary" />
			</fieldset>
		</form>
		<br><br>

</body>
</html>