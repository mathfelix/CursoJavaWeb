<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Livros (ADMIN)</title>
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
		<br><br>

	${msg}
	
	<form method="post" action="livrocad">
		<fieldset>
			<legend>Dados do Livro</legend>
			
		Titulo:<br />
		<input type="text" required="required" name="titulo" size="25"
			pattern="[a-zA-Zá-ú ]{4,60}" />
		<br /><br />
	
		Ano:<br />
		<input type="text" required="required" name="ano" size="25" pattern="{1,2016}" />
		<br /><br />
		
		Categoria:<br />
		<input type="text" required="required" name="categoria" size="25" />
		<br /><br /> 
		
		Preço:<br />
		<input type="text" required="required" name="preco" size="25" pattern="{1,9999}" />
		<br /><br />
		
		Autor:<br />
		<select name="autor">
			<c:forEach items="${Autores}" var="autor" >
				<option value="${autor.codigo}">${autor.nome}</option>
			</c:forEach>
		</select><br /><br /> 
		
		Editora:<br />
		<select name="editora">
			<c:forEach items="${Editoras}" var="editora" >
				<option value="${editora.codigo}">${editora.nome}</option>
			</c:forEach>
		</select><br /><br /> 
	
		<input type="submit" value="Salvar" class="btn btn-primary" />
		</fieldset>
	</form>
	<br><br>
	
	
	<table border="1" style="background-color: #DCDCDC; width: 75%;">
		<tr>
			<th colspan="11">Relação de Livros</th>
		</tr>
		
		<tr>
			<th>Codigo</th>
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
		
		<c:forEach items="${listaLivros}" var="liv">
			<tr>
				<td>${liv.codigo}</td>
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
					<a href="livropesq?cod=${liv.codigo}">Editar</a>
						&nbsp; | &nbsp;
					<a href="livrodel?cod=${liv.codigo}">Excluir</a>
				</td>
			</tr>
		</c:forEach>
			
	</table>

</body>
</html>