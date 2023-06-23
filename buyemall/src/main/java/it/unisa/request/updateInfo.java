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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
AccountDAO conn=new AccountDAO();
		
Account accountBean=null;
try {
accountBean=(Account)request.getAttribute("accountBean");
}catch(NullPointerException e) {
	e.printStackTrace();
	accountBean=null;
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
				
				if(password!=null && password.trim()!="") {
					String pshs=PasswordHash.hashPassword(password);
					conn.UpdatePassword(pshs, accountBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        response.sendRedirect("Profilo");
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
