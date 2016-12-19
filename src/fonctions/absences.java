package fonctions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/absences")
public class absences extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/VueAbsences.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String DBURL = "jdbc:mysql://localhost/gnou?autoReconnect=true&useSSL=false";
		String DBLOGIN = "root";
		String DBPASSWORD = "root";

		Connection cn = null;
		Statement st = null;
		ResultSet result = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			cn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);

			st = cn.createStatement();
			String sql = "SELECT * FROM profil";

			result = st.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		int a;
		String codeIsep, Nom, Prenom, dossier;
		ArrayList<String> tableauAbsences = new ArrayList<String>();
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
				a = result.getInt("Code_Isep");
				Nom = result.getString("Nom");
				Prenom = result.getString("Prenom");
				codeIsep = Integer.toString(a);
				dossier = codeIsep.substring(0, 2);

				// on ajoute tout dans le tableau les valeurs :
				tableauAbsences.add(codeIsep);
				tableauAbsences.add(Nom);
				tableauAbsences.add(Prenom);
				tableauAbsences.add(dossier);

				
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
		request.setAttribute("tableauAbsences", tableauAbsences);

		// on affiche la JSP
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}