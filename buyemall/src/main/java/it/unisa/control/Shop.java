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

import it.unisa.DAO.ProdottoDAO;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Tipo;

/**
 * Servlet implementation class Shop
 */
@WebServlet("/Shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProdottoDAO conn=new ProdottoDAO();
		//-------------Generatotion Filter-----------------------
		List<Integer> generation;
		try {
			generation=conn.selectAllGeneration();
		} catch (SQLException e) {
			generation=null;
			e.printStackTrace();
		}
		List<Integer> countGeneration=new LinkedList<>();
		if(generation!=null) {
			for(int i=0; i< generation.size();i++)
				try {
					countGeneration.add(conn.countForFilter(null, generation.get(i), null, null, null));
				} catch (SQLException e) {
					countGeneration.add(0);
					e.printStackTrace();
				}
		}
		
		request.setAttribute("generation", generation);
		request.setAttribute("countGeneration", countGeneration);
		
		//-------------Type Filter-----------------------
		List<String> type;
		
		try {
			type=conn.selectAllType();
		} catch (SQLException e) {
			type=null;
			e.printStackTrace();
		}
		
		List<Integer> countType=new LinkedList<>();
		if(type!=null) {
			for(int i=0; i< type.size();i++)
				try {
					countType.add(conn.countForFilter(null, null, Tipo.valueOf(type.get(i)), null, null));
				} catch (SQLException e) {
					countType.add(0);
					e.printStackTrace();
				}
		}
		request.setAttribute("type", type);
		request.setAttribute("countType", countType);
		
		
		//---------------search Form-----------------------------
		
		
		//---------------Servlet Settings-------------------------
		Integer count;
		try {
			 count=conn.countForFilter(null, null, null, null, null);
		} catch (SQLException e) {
			count=0;
			e.printStackTrace();
		}
		
		request.setAttribute("count", count);
		
		
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
