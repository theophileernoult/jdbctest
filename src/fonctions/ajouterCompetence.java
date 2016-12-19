package fonctions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajouterCompetence")
public class ajouterCompetence extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/VueAjoutCompetence.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		modifierCompetence.connexionBDD();
		// on se connecte en utilisant la fonction de l'autre fichier

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom_comp = request.getParameter("nom_comp");
		String module_comp = request.getParameter("module_comp");
		String desc_comp = request.getParameter("desc_comp");
		String coeff_comp = request.getParameter("coeff_comp");
		String obj_comp = request.getParameter("obj_comp");

		System.out.println(nom_comp);

		String DBURL = "jdbc:mysql://localhost/gnou?autoReconnect=true&useSSL=false";
		String DBLOGIN = "root";
		String DBPASSWORD = "root";

		Connection cn = null;
		Statement st = null;
		
		response.setContentType("text/html");
		PrintWriter onAfficheLeSucces = response.getWriter();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			cn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);
			st = cn.createStatement();

			if (nom_comp.equals("") || module_comp.equals("") || desc_comp.equals("") || coeff_comp.equals("")
					|| obj_comp.equals("")){
				onAfficheLeSucces.println("<b>Erreur, un ou plusieurs champs n'ont pas été remplis.</b><br>");
				onAfficheLeSucces.println("<a href=ajouterCompetence>Revenir au formulaire</a>");
				
			}

			else{
			String sql = "INSERT INTO competences(Nom_competence, Module, Descriptif, Coefficient, Objectif) VALUES ('"
					+ nom_comp + "','" + module_comp + "','" + desc_comp + "','" + coeff_comp + "','" + obj_comp + "')";

			st.executeUpdate(sql);
			// on execute la requete, quelle qu'elle soit

			onAfficheLeSucces.println("<b>La compétence a bien été ajoutée.</b><br>");
			onAfficheLeSucces.println("<a href=modifierCompetence>Revenir à la liste des compétences</a>");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
