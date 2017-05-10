<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrinho de Compras</title>
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
		
		
		<form action="finalizar?codigo=${usu.codigo}" method="post">
		
			
			<br />Nome: ${usu.nome}
			<br />Email: <input type="text" value="${usu.email}" readonly="readonly" name="email">
		
			<br><br>
			${msg}<br>
			
			<table border="1" style="background-color: #DCDCDC; width: 75%; border: thin;">
				<tr>
					<th colspan="11">Carrinho de Compras</th>
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
				
				<c:forEach items="${livrosCarrinho}" var="li">
					<tr>
						
						<td>${li.titulo}</td>
						<td>${li.ano}</td>
						<td>${li.categoria}</td>
						<td>${li.preco}</td>
						<td>${li.autor.nome}</td>
						<td>${li.autor.nacionalidade}</td>
						<td>${li.autor.nascimento}</td>
						<td>${li.editora.nome}</td>
						<td>${li.editora.origem}</td>
						<td>
							<a href="excluir?codigo=${li.codigo}">Excluir</a>
						</td>
						
					</tr>
				</c:forEach>
					
			</table>
			<br><br>
		
			<input type="submit" value="Finalizar Pedido">
		</form>

</body>
</html>