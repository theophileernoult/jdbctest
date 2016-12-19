package fonctions;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/modifierNotes")
public class modifierNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/VueNotes.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultSet result = connexionBDD();

		int a=0;
		String Note, Code_ISEP;

		ArrayList<String> tableauNotes = new ArrayList<String>();

		try {
			while (result.next()) {
				a = result.getInt("Code_ISEP");
				Note = result.getString("Note");
				Code_ISEP = Integer.toString(a);

				// on ajoute tout dans le tableau les valeurs :
				tableauNotes.add(Code_ISEP);
				tableauNotes.add(Note);

//				
//				 for (String elem : tableauNotes) {
//				 System.out.println(elem); } // affichage du tableau pour les tests
//				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// on envoie le tableau à la JSP
		request.setAttribute("tableauNotes", tableauNotes);

		// on affiche la JSP
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String valeur = request.getParameter("valeur");
		int code = Integer.parseInt(request.getParameter("code"));
		String ancienne = request.getParameter("ancienne");

		System.out.println("code isep   ----> " + code);
		System.out.println("nouvele note   ----> " + valeur);
		System.out.println("ancienne note ----> " + ancienne);

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

			sql = "UPDATE fiche_competences_eleve SET Note='" + valeur + "' WHERE Note='" + ancienne + "' AND Code_ISEP='"+ code +"'";

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
			String sql = "SELECT * FROM fiche_competences_eleve";

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
