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

public class Update extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");

		String empSalaryParam = req.getParameter("salary");
		String empIdParam = req.getParameter("id");

		if (empSalaryParam == null || empIdParam == null) {
			// handle null parameter values
			res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter values");
			return;
		}

		int sal = Integer.parseInt(empSalaryParam);
		int id = Integer.parseInt(empIdParam);

		// database logic
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nithya", "root", "password");
			PreparedStatement ps = con.prepareStatement("update Employee set EmpSalary=? where EmpID=?");
			ps.setInt(1, sal);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter pw = res.getWriter();
		pw.write("<h2 style='color:red'>Updated successfully</h2>");
		pw.close();

	}

}
