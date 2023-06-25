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
import it.unisa.DAO.ProdottoDAO;
import it.unisa.bean.Account;
import it.unisa.bean.Prodotto;

/**
 * Servlet implementation class DeleteCart
 */
@WebServlet("/DeleteCart")
public class DeleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCart() {
        super();

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
		
		if(pd!=null && pd.getTipo() != null && accountBean!=null && accountBean.getUsername()!=null) {
			try {
				conn.DeleteCart(pd, accountBean);
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
            
		}
		
		response.sendRedirect("Cart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
