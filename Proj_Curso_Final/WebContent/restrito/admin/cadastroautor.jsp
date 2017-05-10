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
	
	<form method="post" action="autorcad">
		<fieldset>
			<legend>Dados do Autor</legend>
			
		Nome:<br />
		<input type="text" required="required" name="nome" size="25"
			pattern="[a-zA-Zá-ú ]{4,60}" />
		<br /><br />
	
		Nascionalidade:<br />
		<input type="text" required="required" name="nacionalidade" size="25"/>
		<br /><br />
		
		Nascimento:<br />
		<input type="date" required="required" name="nascimento" size="25"/>
		<br /><br /> 
		
		<input type="submit" value="Salvar" class="btn btn-primary" />
		</fieldset>
	</form>
	<br><br>
	
	<table border="1" style="background-color: #DCDCDC; width: 75%;">
		<tr>
			<th colspan="5">Relação de Autores</th>
		</tr>
		
		<tr>
			<th>Codigo</th>
			<th>Nome</th>
			<th>Nacionalidade</th>
			<th>Nascimento</th>
			<th>Opções</th>
		</tr>
		
		<c:forEach items="${listaAutores}" var="aut">
			<tr>
				<td>${aut.codigo}</td>
				<td>${aut.nome}</td>
				<td>${aut.nacionalidade}</td>
				<td>${aut.nascimento}</td>
				<td>
					<a href="autorpesq?cod=${aut.codigo}">Editar</a>
						&nbsp; | &nbsp;
					<a href="autordel?cod=${aut.codigo}">Excluir</a>
				</td>
			</tr>
		</c:forEach>
			
	</table>
	

</body>
</html>