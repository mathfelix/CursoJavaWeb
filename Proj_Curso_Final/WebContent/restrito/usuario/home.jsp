<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
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
		<hr /><br />

		<b>--Dados do Usuario--</b>
		<br />
		
			
			<br />Nome: ${usu.nome}
			<br />Email: ${usu.email}
		
		
	

</body>
</html>