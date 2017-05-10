<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	${msg}
	
	<form method="post" action="livroalt?codigo=${livro.codigo}">
		<fieldset>
			<legend>Dados do Livro</legend>
			
		Titulo:<br />
		<input type="text" name="titulo" size="25" value="${livro.titulo}" pattern="[a-zA-Zá-ú ]{4,60}" />
		<br /><br />
	
		Ano:<br />
		<input type="text" name="ano" size="25" pattern="{1,2016}" value="${livro.ano}"/>
		<br /><br />
		
		Categoria:<br />
		<input type="text" name="categoria" size="25" value="${livro.categoria}"/>
		<br /><br /> 
		
		Preço:<br />
		<input type="text" name="preco" size="25" pattern="{1,9999}" value="${livro.preco}"/>
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
	
</body>
</html>