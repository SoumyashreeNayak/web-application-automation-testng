
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.com.DAO;
import app.com.Item;

public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("user-email");
		String password = request.getParameter("user-pwd");
		DAO dao = new DAO();
		HttpSession session=request.getSession();
		try {
			if (!dao.validateLogin(email, password)) {
				String errorMessage = "Invalid email or password. Please try again.";
				response.sendRedirect("login.jsp?error=" + errorMessage);
			} else {
				session.setAttribute("user", email);
				List<Item> items=dao.getItems();
				request.setAttribute("items", items);
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
