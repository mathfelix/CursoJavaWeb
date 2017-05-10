<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	
	<form method="post" action="editoracad">
		<fieldset>
			<legend>Dados da Editora</legend>
			
		Nome:<br />
		<input type="text" required="required" name="nome" size="25"
			pattern="[a-zA-Zá-úÁ-Ú ]{4,60}" />
		<br /><br />
	
		Origem:<br />
		<input type="text" required="required" name="origem" size="25"/>
		<br /><br />
		
		<input type="submit" value="Salvar" class="btn btn-primary" />
		</fieldset>
	</form>
	<br><br>
	
	<table border="1" style="background-color: #DCDCDC; width: 75%;">
		<tr>
			<th colspan="5">Relação de Editoras</th>
		</tr>
		
		<tr>
			<th>Codigo</th>
			<th>Nome</th>
			<th>Origem</th>
			<th>Opções</th>
		</tr>
		
		<c:forEach items="${listaEditoras}" var="edt">
			<tr>
				<td>${edt.codigo}</td>
				<td>${edt.nome}</td>
				<td>${edt.origem}</td>
				
				<td>
					<a href="editorapesq?cod=${edt.codigo}">Editar</a>
						&nbsp; | &nbsp;
					<a href="editoradel?cod=${edt.codigo}">Excluir</a>
				</td>
			</tr>
		</c:forEach>
			
	</table>

</body>
</html>