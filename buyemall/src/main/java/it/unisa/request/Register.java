package it.unisa.request;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.AccountDAO;
import it.unisa.bean.Account;
import it.unisa.bean.Indirizzo;
import it.unisa.utils.PasswordHash;
import it.unisa.utils.Validator;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("Username");
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String passwordCheck=request.getParameter("passwordCheck");
		
		AccountDAO conn=new AccountDAO();
		
		if (!Validator.hasOnlyAlphanumeric(username) || username == null) {
		    request.setAttribute("errorMsg", "Username non valido. Inserire solo caratteri alfanumerici.");
		    request.setAttribute("pageName", "RgForward");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		} else if (!Validator.hasOnlyAlphanumeric(nome)|| nome == null) {
		    request.setAttribute("errorMsg", "Nome non valido. Inserire solo caratteri alfanumerici.");
		    request.setAttribute("pageName", "RgForward");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		} else if (!Validator.hasOnlyAlphanumeric(cognome) || cognome == null) {
		    request.setAttribute("errorMsg", "Cognome non valido. Inserire solo caratteri alfanumerici.");
		    request.setAttribute("pageName", "RgForward");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		} else if ( password == null) {
		    request.setAttribute("errorMsg", "Password non valida. Inserire solo caratteri alfanumerici.");
		    request.setAttribute("pageName", "RgForward");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		} else if (!Validator.hasOnlyAlphanumeric(passwordCheck)|| passwordCheck == null || !passwordCheck.equals(password)) {
		    request.setAttribute("errorMsg", "Conferma password non valida. Inserire solo caratteri alfanumerici.");
		    request.setAttribute("pageName", "RgForward");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		} else if (!Validator.isValidEmail(email)|| email == null) {
		    request.setAttribute("errorMsg", "L'email non valida. Inserire un email valida.");
		    request.setAttribute("pageName", "RgForward");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		}else {
		    // Tutti i parametri sono validi, procedi con la registrazione
			
			try {
				Account ac=conn.doRetrieveByKey(username);
				System.out.println(ac);
				if(ac!=null && ac.getUsername()!=null) {
					request.setAttribute("errorMsg", "Lo username è già usato");
					request.setAttribute("pageName", "RgForward");
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				request.setAttribute("errorMsg", "è avvenuto un errore nel server riprova più tardi");
				request.setAttribute("pageName", "RgForward");
				e.printStackTrace();
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
			
			try {
				Account ac=conn.doRetrieveByEmail(email);
				if(ac!=null && ac.getUsername()!=null) {
					request.setAttribute("errorMsg", "L' email è già usata");
					request.setAttribute("pageName", "RgForward");
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				request.setAttribute("errorMsg", "è avvenuto un errore nel server riprova più tardi");
				request.setAttribute("pageName", "RgForward");
				e.printStackTrace();
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
			
			Account daSalvare=new Account();
			
			daSalvare.setNome(nome);

			daSalvare.setCognome(cognome);
			
			daSalvare.setEmail(email);
			daSalvare.setPassword(PasswordHash.hashPassword(password));
			daSalvare.setUsername(username);
			daSalvare.setI(Indirizzo.nullIndirizzo());
			
			try {
				conn.doSave(daSalvare);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", "è avvenuto un errore nel server riprova più tardi");
				e.printStackTrace();
				request.setAttribute("pageName", "RgForward");
				RequestDispatcher dispatcher = request.getRequestDispatcher("RgForward");
				dispatcher.forward(request, response);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("LgForward");
			request.setAttribute("pageName", "LgForward");
			dispatcher.forward(request, response);
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
