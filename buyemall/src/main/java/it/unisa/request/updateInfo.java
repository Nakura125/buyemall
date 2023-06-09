package it.unisa.request;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class updateInfo
 */
@WebServlet("/updateInfo")
public class updateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateInfo() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO conn=new AccountDAO();
		Account accountBean=null;
		String account=(String)request.getAttribute("accountBean");
		if (account != null) {
			try {
				accountBean = conn.doRetrieveByKey(account);
			} catch (SQLException e) {

				accountBean = null;
			}
		}
        
        String nome=request.getParameter("nome");
        
        if(!Validator.hasOnlyAlphanumeric(nome)) {
        	nome=null;
        }
        
        String cognome=request.getParameter("cognome");
        
        if(!Validator.hasOnlyAlphanumeric(cognome)) {
        	cognome=null;
        }
        
        String password=request.getParameter("password");
        
        if(nome != null && cognome!=null  && accountBean!=null && accountBean.getUsername()!=null) {
        	try {
				conn.UpdateInfo(nome, cognome, accountBean.getEmail(), accountBean);
				
				if(password!=null && password.equals("")) {
					String pshs=PasswordHash.hashPassword(password);
					conn.UpdatePassword(pshs, accountBean);
				}
			} catch (SQLException e) {

				
			}
        }
        
        response.sendRedirect("Profilo");
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
