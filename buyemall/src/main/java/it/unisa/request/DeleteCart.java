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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO conn=new AccountDAO();
		Cookie account=null;
		Account accountBean=null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Account")) {
                    // Il cookie desiderato è presente
                    account=cookie;
                    try {
						accountBean=conn.doRetrieveByKey(account.getValue());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						accountBean=null;
					}
                    break;  // Esci dal ciclo, poiché hai trovato il cookie
                    
                }
            }
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
				//System.out.println("Query fatta");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
