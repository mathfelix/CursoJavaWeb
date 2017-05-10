<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	${msg}	
	<form action="editoraalt?codigo=${editora.codigo}" method="post">
		<fieldset>
			<legend>Dados da Editora</legend>
		
		Nome:<br />
		<input type="text" required="required" name="nome" size="25" value="${editora.nome}"
			pattern="[a-zA-Zá-ú ]{4,60}" />
		<br /><br />
	
		Origem:<br />
		<input type="text" required="required" name="origem" size="25" value="${editora.origem}"/>
		<br /><br />
		
		<input type="submit" value="Salvar" class="btn btn-primary" />
		</fieldset>
	</form>
	<br><br>
	

	

</body>
</html>