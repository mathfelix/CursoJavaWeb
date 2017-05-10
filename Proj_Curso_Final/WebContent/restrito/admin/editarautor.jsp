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
	<form action="autoralt?codigo=${autor.codigo}" method="post">
		<fieldset>
			<legend>Dados do Autor</legend>
			
		Nome:<br />
		<input type="text" required="required" name="nome" size="25" value="${autor.nome}"
			pattern="[a-zA-Zá-ú ]{4,60}" />
		<br /><br />
	
		Nascionalidade:<br />
		<input type="text" required="required" name="nacionalidade" size="25" value="${autor.nacionalidade}"/>
		<br /><br />
		
		Nascimento:<br />
		<input type="date" required="required" name="nascimento" size="25" value="${autor.nascimento}"/>
		<br /><br /> 
		
		<input type="submit" value="Salvar" class="btn btn-primary" />
		</fieldset>
	</form>
	<br><br>
	

	

</body>
</html>