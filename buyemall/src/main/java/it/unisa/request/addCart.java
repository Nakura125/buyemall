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
import it.unisa.DAO.ProdottoDAO;
import it.unisa.bean.Account;
import it.unisa.bean.Prodotto;

/**
 * Servlet implementation class addCart
 */
@WebServlet("/addCart")
public class addCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCart() {
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
		String parameter=request.getParameter("idProdotto");
		Integer idProdotto=null;
		if(parameter!=null) {
			try {
				idProdotto=Integer.parseInt(parameter);
			}catch(NumberFormatException e){
				e.printStackTrace();
				idProdotto=null;
			}
		}
		
		ProdottoDAO connPr=new ProdottoDAO();
		Prodotto pd=null;
		if(idProdotto!=null) {
			try {
				pd=connPr.doRetrieveByKey(idProdotto);
			} catch (SQLException e) {
				pd=null;
				e.printStackTrace();
			}
		}
		//System.out.println(pd);
		//System.out.println(accountBean);
		if(pd!=null && pd.getTipo() != null && accountBean!=null && accountBean.getUsername()!=null) {
			try {
				conn.doSaveCart(accountBean,pd );
				//System.out.println("Query fatta");
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
            
		}
		
		//request.setAttribute("pageName", "Shop");
		//RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/index.jsp");
		//dispatcher.forward(request, response);
		
		response.sendRedirect("Detail?idProdotto="+parameter);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
