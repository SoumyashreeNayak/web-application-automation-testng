
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.com.DAO;
import app.com.User;

public class RegisterCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		user.setFirstName(request.getParameter("user-first-name"));
		user.setLastName(request.getParameter("user-last-name"));
		user.setEmail(request.getParameter("user-email"));
		user.setPassword(request.getParameter("user-pwd"));
		DAO dao = new DAO();
		try {
			if (dao.addUser(user) == 0) {
				String errorMessage = "User with Email " + request.getParameter("user-email")
						+ " already exists. Use a different Email or login to continue.";
				response.sendRedirect("register.jsp?error=" + errorMessage);
			} else {
				String successMessage = "Registered Successfully!";
				response.sendRedirect("index.jsp?success="+successMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
