<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Absences</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="styleCompetence.css">


<%
	ArrayList<String> liste = (ArrayList<String>) request.getAttribute("tableauAbsences");
	int i = 0;
%>

</head>
<body>

			<%
			%>

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