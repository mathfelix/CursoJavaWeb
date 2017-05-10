<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

		<h2>Área Restrita (ADMIN)</h2>
		<h3>Bem vindo - ${usu.nome}</h3>
		<a href="logout_adm">Sair</a>
		&nbsp; | &nbsp;
		<a href="autorcons">Gerenciar autores</a>
		&nbsp; | &nbsp;
		<a href="editoracons">Gerenciar editoras</a> 
		&nbsp; | &nbsp;
		<a href="livrocons">Gerenciar livros</a> 
		<hr /><br />

		<b>--Dados do ADMIN--</b>
		<br />
		
		<br />Codigo: ${usu.codigo}
		<br />Nome: ${usu.nome}
		<br />Email: ${usu.email}
		
		
	

</body>
</html>