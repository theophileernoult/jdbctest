<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter compétence</title>
<link rel="stylesheet" type="text/css" href="styleAjouterCompetence.css">

</head>
<body>
	<form method="POST" action="ajouterCompetence">
		<table>
		<th>Ajouter une compétence</th>
		<th><i>Tous les champs sont obligatoires</i></th>
		
			<tr>
				<td>Nom de la compétence :</td>
				<td><input type="text" id="nom_comp" name="nom_comp"></td>
			</tr>
			<tr>
				<td>Nom du module :</td>
				<td><select id="module_comp" name="module_comp">
						<option value="Communication">Communication</option>
						<option value="Travail en équipe">Travail en équipe</option>
						<option value="Conduite de projet">Conduite de projet</option>
						<option value="Professionnel responsable">Professionnel
							responsable</option>
						<option value="Electronique">Electronique</option>
						<option value="Informatique">Informatique</option>
						<option value="Télécommunications">Télécommunications</option>
						<option value="Traitement du signal">Traitement du signal</option>
				</select></td>
			</tr>
			<tr>
				<td>Description :</td>
				<td><input type="text" id="desc_comp" name="desc_comp"></td>
			</tr>
			<tr>
				<td>Coefficient :</td>
				<td><input type="number" step="0.1" id="coeff_comp"
					value="1.00" name="coeff_comp"></td>
			</tr>
			<tr>
				<td>Objectif :</td>
				<td><input type="text" id="obj_comp" name="obj_comp"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Ajouter"></td>
			</tr>
		</table>

	</form>

</body>
</html>