package fonctions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirection")
public class redirection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String valeur = request.getParameter("valeur");
		String numero = request.getParameter("numero");
		String ancienne = request.getParameter("ancienne");

		System.out.println("---->" + valeur);
		System.out.println("---->" + numero);
		System.out.println("---->" + ancienne);

		String DBURL = "jdbc:mysql://localhost/gnou?autoReconnect=true&useSSL=false";
		String DBLOGIN = "root";
		String DBPASSWORD = "root";

		Connection cn = null;
		Statement st = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			cn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);

			st = cn.createStatement();

			String sql = "UPDATE competences SET ID_Competence='" + valeur + "' WHERE ID_Competence='" + ancienne + "'";

			st.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// doGet(request, response);
	}

}
