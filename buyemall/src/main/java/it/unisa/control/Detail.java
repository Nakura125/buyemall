package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.ProdottoDAO;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Tipo;

/**
 * Servlet implementation class Detail
 */
@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Detail() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//---------------------ProductDetail-------------------------
		Integer idProdotto;
		try {
		idProdotto= Integer.parseInt(request.getParameter("idProdotto"));
		}catch(NumberFormatException e) {
			idProdotto=0;
		}
		Prodotto p;
		if(idProdotto>0 && idProdotto!=null) {
			try {
				p=new ProdottoDAO().doRetrieveByKey(idProdotto);
				
				new ProdottoDAO().UpdateVisit(p.getVisitato()+1, p);
			} catch (SQLException e) {
				p=Prodotto.nullProduct();
				e.printStackTrace();
			}
		}else {
			try {
				p=((List<Prodotto>) new ProdottoDAO().doRetrieveAllRAND()).get(0);
			} catch (SQLException e) {
				p=Prodotto.nullProduct();
				e.printStackTrace();
			}
		}

		
		request.setAttribute("ProductDetail",p);
		
		//------------------productAdvice---------------------------
		
		Collection<Prodotto> prAdvice;
		try {
			prAdvice=new ProdottoDAO().RetrieveByFilters(null, p.getGenerazione(), p.getTipo(), null, null);
			
			
		} catch (SQLException e) {
			prAdvice=new LinkedList<>();
			prAdvice.add(Prodotto.nullProduct());
			
			e.printStackTrace();
		}
		
		if(prAdvice.size()==0)
			prAdvice.add(Prodotto.nullProduct());

		request.setAttribute("ProductAdvice",prAdvice);

		//---------------Servlet Settings-------------------------
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
		
		doGet(request, response);
	}

}
