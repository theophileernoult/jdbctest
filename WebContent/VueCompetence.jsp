<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier compétence</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="styleCompetence.css">


<%
	ArrayList<String> liste = (ArrayList<String>) request.getAttribute("tableauCompetences");
	int i = 0;
%>

</head>
<body>

<i>Pour ajouter une compétence, cliquer <a href="ajouterCompetence">ici</a>.</i>

	<table id="tableau">
		<thead>
			<tr>
				<th><center>ID_Compétence</center></th>
				<th><center>Nom_Compétence</center></th>
				<th><center>Module</center></th>
				<th><center>Descriptif</center></th>
				<th><center>Coefficient</center></th>
				<th><center>Objectif</center></th>
			</tr>
		</thead>

		<tr>
			<%
				for (String temp : liste) {
					if (i % 6 == 0 && i != 0) {
			%>
		</tr>
		</tr>
		<%
			}
		%>
		<td onclick="ff(<%=i%>)">
			<form>
				<input type="hidden" id="numero+<%=i%>" value="<%=i%>" /> <input
					type="text" id="valeur<%=i%>" value="<%=temp%>"
					onclick="f('<%=temp + "'"%>)" /> <input type="submit"
					id="submit<%=i%>" value="Modifier" />
			</form>
		</td>
		<%
			i++;
			}
		%>
		</tr>
	</table>


	<script language="javascript">
	var ancienne;
	var num;
	var idDuInput;
	var idDuSubmit;
	
function f(lalala){
	ancienne=lalala;
}
function ff(lalala){
	num=lalala;
//alert(num);
}

$('input:text').click(function() {
	  idDuInput = $(this).attr('id');
	  //alert(idDuInput);
	});

$('input:submit').click(function() {
	  idDuSubmit = $(this).attr('id');
	  	$.ajax({
			type: 'POST',
			url: 'modifierCompetence', 
			data: {
			valeur : $("#"+idDuInput).val(),
			numero : num,
			ancienne : ancienne
			}
		});
	});
/*		$("#"+idDuSubmit).click(function() {
			//alert(idDuInput);
			$.ajax({
				type: 'POST',
				url: 'modifierCompetence', 
				data: {
				valeur : $("#"+idDuInput).val(),
				numero : num,
				ancienne : ancienne
				}
			});
		});
*/


	</script>

</body>
</html>