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
import it.unisa.DAO.MetodiPagamentoDAO;
import it.unisa.bean.Account;
import it.unisa.bean.MetodiPagamento;

/**
 * Servlet implementation class deletePag
 */
@WebServlet("/deletePag")
public class deletePag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletePag() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Account accountBean=null;
		try {
		accountBean=(Account)request.getAttribute("accountBean");
		}catch(NullPointerException e) {
			e.printStackTrace();
			accountBean=null;
		}
        
        //redirect if not logged
        if(accountBean==null) {
        	response.sendRedirect("Home");
        }
        
        String num=request.getParameter("num");
       
        if(accountBean!=null && accountBean.getUsername()!=null && num!=null) {
        	try {
				MetodiPagamento o=new MetodiPagamentoDAO().doRetrieveByKey(num, accountBean.getUsername());
				
				new MetodiPagamentoDAO().doDelete(o.getNumero_carta());
			} catch (SQLException e) {
				
				e.printStackTrace();
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
