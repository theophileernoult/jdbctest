<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier notes</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="styleCompetence.css">

<%
	ArrayList<String> liste = (ArrayList<String>) request.getAttribute("tableauNotes");
	int i = 0;
	String valeurCode = "";
%>

</head>
<body>

	<table id="tableau">
		<thead>
			<tr>
				<th><center>Code Isep</center></th>
				<th><center>Note</center></th>
			</tr>
		</thead>

		<tr>
			<%
				for (String temp : liste) {		
					if (i % 2 == 0) {
		%>

		<td>
			<%
			out.print(temp);
			valeurCode=temp ;
			%>
		</td>
				<%
			}
		
					else if (i % 2 == 1) {
			%>

		<td onclick="ff(<%=i%>)">
			<form>
				<input type="hidden" id="numero+<%=i%>" value="<%=i%>" /> 
				
<INPUT type= "radio" name="note" value="1" <%if (temp.equals("1"))	out.println("checked"); %> onclick="f(<%=temp%>,<%=valeurCode%>)">1
<INPUT type= "radio" name="note" value="2" <%if (temp.equals("2"))	out.println("checked"); %> onclick="f(<%=temp%>,<%=valeurCode%>)">2
<INPUT type= "radio" name="note" value="3" <%if (temp.equals("3"))	out.println("checked"); %> onclick="f(<%=temp%>,<%=valeurCode%>)">3
<INPUT type= "radio" name="note" value="4" <%if (temp.equals("4"))	out.println("checked"); %> onclick="f(<%=temp%>,<%=valeurCode%>)">4
<INPUT type= "radio" name="note" value="5" <%if (temp.equals("5"))	out.println("checked"); %> onclick="f(<%=temp%>,<%=valeurCode%>)">5
				
<!-- <input type="submit" id="submit<%=i%>" value="Modifier" />  -->
			</form>
		</td>
				</tr>
				<%
			}
		%>		
		<%
			i++;
			}
		%>
		
		</tr>
	</table>


	<script language="javascript">
	var ancienne;
	var num;
	var valeurDuInput;
	var code;
	
function f(lalala,codecode){
	ancienne=lalala;
	code=codecode;
	//alert(ancienne);
}
function ff(lalala){
	num=lalala;
//alert(num);
}
/*function recupCode(lalala){
	code=lalala;
	alert(code);
}
*/

$('input:radio').click(function() {
	valeurDuRadio = $(this).attr('value');
	  	$.ajax({
			type: 'POST',
			url: 'modifierNotes', 
			data: {
			valeur : valeurDuRadio,
			ancienne : ancienne,
			code : code
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