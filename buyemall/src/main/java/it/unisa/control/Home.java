package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
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
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		//----------------Carousel--------------------------------
		
		try {
			List<Prodotto> carousel=(List<Prodotto>) new ProdottoDAO().doRetrieveAllRAND();
			request.setAttribute("carousel", carousel);
			
			//carousel.stream().map(Prodotto::getNome).forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//----------------Prodotti più visti--------------------------
		try {
			List<Prodotto> visitato=(List<Prodotto>) new ProdottoDAO().doRetrieveAllClient("visitato");
			request.setAttribute("visitato", visitato);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//---------------------Categories----------------------------
		try {
			Collection<Prodotto> prCategoriesCard=new ProdottoDAO().RetrieveByFilters(null, null, Tipo.carta, null, null);

			Collection<Prodotto> prCategoriesSet=new ProdottoDAO().RetrieveByFilters(null, null, Tipo.set, null, null);
			

			Collection<Prodotto> prCategoriesRom=new ProdottoDAO().RetrieveByFilters(null, null, Tipo.rom, null, null);

			Collection<Prodotto> prCategoriesBox=new ProdottoDAO().RetrieveByFilters(null, null, Tipo.box, null, null);
			
			request.setAttribute("prCategoriesCard", prCategoriesCard);
			request.setAttribute("prCategoriesSet", prCategoriesSet);
			request.setAttribute("prCategoriesRom", prCategoriesRom);
			request.setAttribute("prCategoriesBox", prCategoriesBox);
		} catch (SQLException e) {
			System.out.println("iao");
			e.printStackTrace();
		}
		
		
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
