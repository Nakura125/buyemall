package it.unisa.request;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.AccountDAO;
import it.unisa.bean.Account;
import it.unisa.utils.PasswordHash;
import it.unisa.utils.Validator;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO conn=new AccountDAO();
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		if (!Validator.isValidEmail(email) || email == null) {
		    request.setAttribute("errorMsg", "email non valida. Inserire email valida.");
		    request.setAttribute("pageName", "LgForward");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		} else if ( password == null) {
		    request.setAttribute("errorMsg", "password non valido. Inserire solo caratteri alfanumerici.");
		    request.setAttribute("pageName", "LgForward");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		} else {
			
			Account ac = null;
			try {
				
				ac=conn.LoginEmail(email, PasswordHash.hashPassword(password));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				request.setAttribute("errorMsg", "Server ha dato un errore riprova più tardi");
			    request.setAttribute("pageName", "LgForward");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			    dispatcher.forward(request, response);
			}
			
			if(ac==null || ac.getEmail()==null) {
				request.setAttribute("errorMsg", "Email e password sono sbagliate");
			    request.setAttribute("pageName", "LgForward");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			    dispatcher.forward(request, response);
			}
			
			String ricordami = request.getParameter("Ricordami");
			if (ricordami != null && ricordami.equals("on")) {
		        // Il checkbox è stato selezionato
				// Imposta un cookie con durata lunga (es. 7 giorni)
				request.getSession().setAttribute("Account", ac.getUsername());
				Cookie cookie = new Cookie("Account", ac.getUsername());
		        cookie.setMaxAge(7 * 24 * 60 * 60); // Durata in secondi
		        response.addCookie(cookie);

		    } else {
		        // Il checkbox non è stato selezionato
		    	request.getSession().setAttribute("Account", ac.getUsername());
		    }
			
			//request.setAttribute("pageName", "Home");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
			//dispatcher.forward(request, response);
			
			response.sendRedirect("Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
