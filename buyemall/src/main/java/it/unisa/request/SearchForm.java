package it.unisa.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.ProdottoDAO;
import it.unisa.bean.Prodotto;

/**
 * Servlet implementation class SearchForm
 */
@WebServlet("/SearchForm")
public class SearchForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDAO conn=new ProdottoDAO();
		String search=request.getParameter("SearchForm");
		List<Prodotto> searchForm=new LinkedList<>();;
		if(search==null) {
			try {
				searchForm=(List<Prodotto>) conn.RetrieveByFilters(search, null, null, null, null);
			} catch (SQLException e) {
				searchForm=new LinkedList<>();
				e.printStackTrace();
			}
		}else {
			try {
				searchForm=(List<Prodotto>) conn.RetrieveByFilters(null, null, null, null, null);
			} catch (SQLException e) {
				searchForm=new LinkedList<>();
				e.printStackTrace();
			}
		}
		
		searchForm.add(Prodotto.nullProduct());
		
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		String result="[";
		for(int i=0; i< searchForm.size();i++) {
			result+="{"+"idProdotto:"+searchForm.get(i).getIdProdotto()+
					"Quantita:"+searchForm.get(i).getQuantita()+
					"Generazione:"+searchForm.get(i).getGenerazione()+
					"Visitato:"+searchForm.get(i).getVisitato()+
					"Prezzo:"+searchForm.get(i).getPrezzo()+
					"Nome:"+searchForm.get(i).getNome()+
					"Descrizione:"+searchForm.get(i).getDescrizione()+
					"Tipo:"+searchForm.get(i).getTipo()+
					"Nazionalita:"+searchForm.get(i).getNazionalita()+
					"Sprites:"+"["
					;
			
			for(int j=0;j< searchForm.get(i).getSprites().size();j++) {
				result+="{"+
						"idSprites:"+searchForm.get(i).getSprites().get(j).getIdSprites()+
						"link:"+searchForm.get(i).getSprites().get(j).getLink()+
						"link_small:"+searchForm.get(i).getSprites().get(j).getLink_small()
						;
				result+="}";
			}
			result+="]";
			result+="}";
		}
		result+="]";
		
		out.print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
