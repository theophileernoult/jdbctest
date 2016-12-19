package fonctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoJdbc {

	public static void main(String[] args) {
		//sauverEnBase();
		lireEnBase();

	}

	public static void sauverEnBase() {
		String DBURL = "jdbc:mysql://localhost/gnou?autoReconnect=true&useSSL=false";
		String DBLOGIN = "root";
		String DBPASSWORD = "root";
		
		Connection cn =null;
		Statement st=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			cn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);

			st = cn.createStatement();
			String sql= "INSERT INTO absences(Date,Présence,Code_ISEP) VALUES ('2016-11-25','P',9644)";

			st.executeUpdate(sql);
			System.out.println("La ligne a bien été ajoutée !");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
}}
	
	public static void lireEnBase() {
		String DBURL = "jdbc:mysql://localhost/gnou?autoReconnect=true&useSSL=false";
		String DBLOGIN = "root";
		String DBPASSWORD = "root";
		
		Connection cn =null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			cn = DriverManager.getConnection(DBURL, DBLOGIN, DBPASSWORD);

			st = cn.createStatement();
			String sql= "SELECT * FROM absences";

			rs= st.executeQuery(sql);
			
			System.out.print("Présences");
			System.out.println("");
			System.out.println("");

			
			while (rs.next()) {
				System.out.print(rs.getString("Date"));
				System.out.print("  - ");
				System.out.print(rs.getInt("Code_Isep"));
				System.out.print("  : ");
				System.out.print(rs.getString("Présence"));
				System.out.println("");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
}}}