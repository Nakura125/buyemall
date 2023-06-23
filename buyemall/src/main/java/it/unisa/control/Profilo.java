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
import it.unisa.DAO.OrdineDAO;
import it.unisa.bean.Account;
import it.unisa.bean.MetodiPagamento;
import it.unisa.bean.Ordine;

/**
 * Servlet implementation class Profilo
 */
@WebServlet("/Profilo")
public class Profilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profilo() {
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
        
        //redirect if not logged
        if(accountBean==null) {
        	response.sendRedirect("Home");
        }
        
        
        //info
        request.setAttribute("info", accountBean);
        
        
        //metodi Pagamento
        MetodiPagamentoDAO connmet=new MetodiPagamentoDAO();
        List<MetodiPagamento> mt=new LinkedList<>();
        try {
			 mt=connmet.doRetrieveByUsername(accountBean.getUsername());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
        request.setAttribute("metodi", mt);
        
		//ordini
        OrdineDAO connord=new OrdineDAO();
        List<Ordine> ordini=new LinkedList<>();
        try {
			ordini=connord.doRetrieveByUsername(accountBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
                
        ordini.add(Ordine.nullOrdine());
        
        
        request.setAttribute("ordiniPr", ordini);
        
		// Servlet Config
		String pageName=getServletConfig().getServletName();
		pageName = pageName.substring(pageName.lastIndexOf(".") + 1);
		request.setAttribute("pageName", pageName);
		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
