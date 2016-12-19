package fonctions;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/modifierCompetence")
public class modifierCompetence extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/VueCompetence.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultSet result = connexionBDD();

		int a;
		float b;
		String ID_Competence, Nom_Competence, Module, Descriptif, Coefficient, Objectif;

		ArrayList<String> tableauCompetences = new ArrayList<String>();
		/*
		 * 
		 * on aura donc un tableau avec tous les attributs de la table. Il y
		 * aura tjrs 6 attributs pour une compétence (et ds le mm ordre). Même
		 * si un attribut est vide, il sera comptabilisé pour des raisons de
		 * compatibilité des requêtes.
		 * 
		 */
		try {
			while (result.next()) {
				a = result.getInt("ID_Competence");
				Nom_Competence = result.getString("Nom_Competence");
				Module = result.getString("Module");
				Descriptif = result.getString("Descriptif");
				b = result.getFloat("Coefficient");
				Objectif = result.getString("Objectif");
				ID_Competence = Integer.toString(a);
				Coefficient = Float.toString(b);

				// on ajoute tout dans le tableau les valeurs :
				tableauCompetences.add(ID_Competence);
				tableauCompetences.add(Nom_Competence);
				tableauCompetences.add(Module);
				tableauCompetences.add(Descriptif);
				tableauCompetences.add(Coefficient);
				tableauCompetences.add(Objectif);

				/*
				 * for (String elem : tableauCompetences) {
				 * System.out.println(elem); } // affichage du tableau pour les
				 * tests
				 */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// on envoie le tableau à la JSP
		request.setAttribute("tableauCompetences", tableauCompetences);

		// on affiche la JSP
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String valeur = request.getParameter("valeur");
		int numero = Integer.parseInt(request.getParameter("numero"));
		String ancienne = request.getParameter("ancienne");

		System.out.println("valeur   ----> " + valeur);
		System.out.println("numero   ----> " + numero);
		System.out.println("ancienne ----> " + ancienne);

		String DBURL = "jdbc:mysql://localhost/gnou?autoReconnect=true&useSSL=false";
		String DBLOGIN = "root";
		String DBPASSWORD = "root";

		Connection cn = null;
		Statement st = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			cn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);

			st = cn.createStatement();

			String sql = "";

			if (numero % 6 == 0)
				sql = "UPDATE competences SET ID_Competence='" + valeur + "' WHERE ID_Competence='" + ancienne + "'";
			else if (numero % 6 == 1)
				sql = "UPDATE competences SET Nom_Competence='" + valeur + "' WHERE Nom_Competence='" + ancienne + "'";
			else if (numero % 6 == 2)
				sql = "UPDATE competences SET Module='" + valeur + "' WHERE Module='" + ancienne + "'";
			else if (numero % 6 == 3)
				sql = "UPDATE competences SET Descriptif='" + valeur + "' WHERE Descriptif='" + ancienne + "'";
			else if (numero % 6 == 4)
				sql = "UPDATE competences SET Coefficient='" + valeur + "' WHERE Coefficient='" + ancienne + "'";
			else if (numero % 6 == 5)
				sql = "UPDATE competences SET Objectif='" + valeur + "' WHERE Objectif='" + ancienne + "'";

			st.executeUpdate(sql);
			// on execute la requete, quelle qu'elle soit

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static ResultSet connexionBDD() {
		String DBURL = "jdbc:mysql://localhost/gnou?autoReconnect=true&useSSL=false";
		String DBLOGIN = "root";
		String DBPASSWORD = "root";

		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			cn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);

			st = cn.createStatement();
			String sql = "SELECT * FROM competences";

			rs = st.executeQuery(sql);

			// System.out.print("Connexion à la table 'Compétences' réussie.");
			// System.out.println("");
			// System.out.println("");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rs;

	}

}