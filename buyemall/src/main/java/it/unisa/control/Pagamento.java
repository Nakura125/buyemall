package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.AccountDAO;
import it.unisa.DAO.MetodiPagamentoDAO;

import it.unisa.bean.Account;
import it.unisa.bean.MetodiPagamento;


/**
 * Servlet implementation class Pagamento
 */
@WebServlet("/Pagamento")
public class Pagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pagamento() {
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
		
		
		
		
		if(accountBean!=null && accountBean.getUsername()!=null) {
			
			//--------------MetodiPagamento--------------------------
			List<MetodiPagamento> mt=new LinkedList<>();
			try {
				mt=new MetodiPagamentoDAO().doRetrieveByUsername(accountBean.getUsername());
			} catch (SQLException e) {
				
				
			}
			
			request.setAttribute("MetodiPag", mt);
			
			//---------------Servlet Settings-------------------------
			String pageName=getServletConfig().getServletName();
			pageName = pageName.substring(pageName.lastIndexOf(".") + 1);
			request.setAttribute("pageName", pageName);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
            
            
		}else {
			response.sendRedirect("Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
