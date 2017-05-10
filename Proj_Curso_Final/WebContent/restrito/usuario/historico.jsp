<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compras ja feitas!</title>
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
			
	
	
			${msg}<br>
			<br>
			
			<table border="1" style="background-color: #DCDCDC; width: 75%; border: thin;">
				<tr>
					<th colspan="11">Historico de compras</th>
				</tr>
				
				<tr>
					
					<th>Titulo</th>
					<th>Ano</th>
					<th>Categoria</th>
					<th>Preço</th>
					<!-- Autor -->
					<th>Autor</th>
					<th>Origem</th>
					<th>Nascimento</th>
					<!-- Editora -->
					<th>Editora</th>
					<th>Pais</th>
					<th>Opções</th>
					
				</tr>
				
				<c:forEach items="${listHistorico}" var="liv">
					<tr>
						
						<td>${liv.titulo}</td>
						<td>${liv.ano}</td>
						<td>${liv.categoria}</td>
						<td>${liv.preco}</td>
						<td>${liv.autor.nome}</td>
						<td>${liv.autor.nacionalidade}</td>
						<td>${liv.autor.nascimento}</td>
						<td>${liv.editora.nome}</td>
						<td>${liv.editora.origem}</td>
						<td>
							<a href="excluir?codigo=${liv.codigo}">Detalhes</a>
						</td>
						
					</tr>
				</c:forEach>
					
			</table>
			<br><br>
		
		

</body>
</html>