package comm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int sal = Integer.parseInt(req.getParameter("salary"));

		// database logic
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nithya", "root", "password");
			PreparedStatement ps = con.prepareStatement("insert into Employee values(?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, sal);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter pw = res.getWriter();
		pw.write("<h2 style='color:red'>Registration successful</h2>");
		pw.close();

	}
}
