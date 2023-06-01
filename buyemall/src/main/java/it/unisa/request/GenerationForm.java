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
 * Servlet implementation class GenerationForm
 */
@WebServlet("/GenerationForm")
public class GenerationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerationForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//---------------Form Generation--------------------------
		ProdottoDAO conn=new ProdottoDAO();
				String[] gen=request.getParameterValues("generazioneForm");
				Integer genr;
				List<Prodotto> generationForm=new LinkedList<>();;
				if(gen!=null) {
					try {
						genr=Integer.parseInt( gen[gen.length-1]);
					}catch(NumberFormatException e) {
						genr=null;
					}
					
					try {
						generationForm=(List<Prodotto>) conn.RetrieveByFilters(null, genr, null, null, null);
					} catch (SQLException e) {
						generationForm=new LinkedList<>();
						e.printStackTrace();
					}
				}
				else
				{
					try {
						generationForm=(List<Prodotto>) conn.RetrieveByFilters(null, null, null, null, null);
					} catch (SQLException e) {
						generationForm=new LinkedList<>();
						e.printStackTrace();
					}
				}
				
				generationForm.add(Prodotto.nullProduct());
				
				response.setContentType("application/json");
				PrintWriter out=response.getWriter();
				String result="[";
				for(int i=0; i< generationForm.size();i++) {
					result+="{"+"idProdotto:"+generationForm.get(i).getIdProdotto()+
							"Quantita:"+generationForm.get(i).getQuantita()+
							"Generazione:"+generationForm.get(i).getGenerazione()+
							"Visitato:"+generationForm.get(i).getVisitato()+
							"Prezzo:"+generationForm.get(i).getPrezzo()+
							"Nome:"+generationForm.get(i).getNome()+
							"Descrizione:"+generationForm.get(i).getDescrizione()+
							"Tipo:"+generationForm.get(i).getTipo()+
							"Nazionalita:"+generationForm.get(i).getNazionalita()+
							"Sprites:"+"["
							;
					
					for(int j=0;j< generationForm.get(i).getSprites().size();j++) {
						result+="{"+
								"idSprites:"+generationForm.get(i).getSprites().get(j).getIdSprites()+
								"link:"+generationForm.get(i).getSprites().get(j).getLink()+
								"link_small:"+generationForm.get(i).getSprites().get(j).getLink_small()
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
