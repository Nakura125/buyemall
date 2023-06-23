package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.AccountDAO;
import it.unisa.DAO.MetodiPagamentoDAO;
import it.unisa.DAO.ProdottoDAO;
import it.unisa.bean.Account;
import it.unisa.bean.MetodiPagamento;
import it.unisa.bean.Prodotto;

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
		
		
		
		
		if(accountBean!=null && accountBean.getUsername()!=null) {
			
			//--------------MetodiPagamento--------------------------
			List<MetodiPagamento> mt=new LinkedList<>();
			try {
				mt=new MetodiPagamentoDAO().doRetrieveByUsername(accountBean.getUsername());
			} catch (SQLException e) {
				
				e.printStackTrace();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
