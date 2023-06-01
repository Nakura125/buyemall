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
import it.unisa.bean.Tipo;

/**
 * Servlet implementation class FormType
 */
@WebServlet("/FormType")
public class FormType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//---------------Form Type-------------------------------
		ProdottoDAO conn=new ProdottoDAO();
		
				String[] typ=request.getParameterValues("typeForm");
				String typr = null;
				List<Prodotto> typeForm=new LinkedList<>();;
				if(typ!=null) {
					try {
						typr=typ[typ.length-1];
					}catch(NumberFormatException e) {
						typr=null;
					}
					
					try {
						typeForm=(List<Prodotto>) conn.RetrieveByFilters(null, null, Tipo.valueOf(typr), null, null);
					} catch (SQLException e) {
						typeForm=new LinkedList<>();
						e.printStackTrace();
					}
					catch(IllegalArgumentException e) {
						try {
							typeForm=(List<Prodotto>) conn.RetrieveByFilters(null, null, null, null, null);
						} catch (SQLException e2) {
							typeForm=new LinkedList<>();
							e2.printStackTrace();
						}
					}
				}
				else
				{
					try {
						typeForm=(List<Prodotto>) conn.RetrieveByFilters(null, null, null, null, null);
					} catch (SQLException e) {
						typeForm=new LinkedList<>();
						e.printStackTrace();
					}
				}
				
				typeForm.add(Prodotto.nullProduct());
				
				response.setContentType("application/json");
				PrintWriter out=response.getWriter();
				String result="[";
				for(int i=0; i< typeForm.size();i++) {
					result+="{"+"idProdotto:"+typeForm.get(i).getIdProdotto()+
							"Quantita:"+typeForm.get(i).getQuantita()+
							"Generazione:"+typeForm.get(i).getGenerazione()+
							"Visitato:"+typeForm.get(i).getVisitato()+
							"Prezzo:"+typeForm.get(i).getPrezzo()+
							"Nome:"+typeForm.get(i).getNome()+
							"Descrizione:"+typeForm.get(i).getDescrizione()+
							"Tipo:"+typeForm.get(i).getTipo()+
							"Nazionalita:"+typeForm.get(i).getNazionalita()+
							"Sprites:"+"["
							;
					
					for(int j=0;j< typeForm.get(i).getSprites().size();j++) {
						result+="{"+
								"idSprites:"+typeForm.get(i).getSprites().get(j).getIdSprites()+
								"link:"+typeForm.get(i).getSprites().get(j).getLink()+
								"link_small:"+typeForm.get(i).getSprites().get(j).getLink_small()
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
