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

import org.json.JSONArray;
import org.json.JSONObject;

import it.unisa.DAO.ProdottoDAO;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Tipo;

/**
 * Servlet implementation class FilterRequest
 */
@WebServlet("/FilterRequest")
public class FilterRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDAO conn=new ProdottoDAO();
		String search=request.getParameter("searchForm");
		String gen=request.getParameter("generazioneForm");
		String type=request.getParameter("typeForm");
		String page=request.getParameter("pageForm");
		Integer pg=0;
		try {
			if(page!=null)
				pg=Integer.parseInt(page)-1;
			else
				pg=0;
		}catch(NumberFormatException e){
			pg=0;
		}
		Tipo tipo = null;
		Integer generazione = null;
		List<Prodotto> resultFilter=new LinkedList<>();
		try {
			if(gen!=null)
			generazione=Integer.parseInt(gen);
		}catch(NumberFormatException e){
			generazione=null;
		}
		
		try {
			if(type!=null)
			tipo=Tipo.valueOf(type);
		}catch(IllegalArgumentException e) {
			tipo=null;
		}
		if(search!=null && search.trim()=="") {
			search=null;
		}
		
		try {
			resultFilter=(List<Prodotto>) conn.RetrieveByFiltersPaged(search, generazione, tipo, null, null,8,pg*8);
		} catch (SQLException e) {
			resultFilter=new LinkedList<>();
			e.printStackTrace();
		}
		
		resultFilter.add(Prodotto.nullProduct());
		
		
		Integer count=0;
		try {
			count=conn.countForFilter(search, generazione, tipo, null, null);
		} catch (SQLException e) {
			count=0;
			e.printStackTrace();
		}
		if(count==null)  count=0;
		
		
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		//String result="{\"result\":[";
		//int count=0;
		String result;
		
		JSONArray allresult=new JSONArray();
		for(int i=0;i<resultFilter.size();i++) {
			JSONObject singleobj=new JSONObject();
			singleobj.put("idProdotto", resultFilter.get(i).getIdProdotto());
			singleobj.put("Quantita", resultFilter.get(i).getQuantita());
			singleobj.put("Generazione", resultFilter.get(i).getGenerazione());
			singleobj.put("Visitato", resultFilter.get(i).getVisitato());
			singleobj.put("Prezzo", resultFilter.get(i).getPrezzo());
			singleobj.put("Nome", resultFilter.get(i).getNome());
			singleobj.put("Descrizione", resultFilter.get(i).getDescrizione());
			singleobj.put("Tipo", resultFilter.get(i).getTipo().toString());
			singleobj.put("Nazionalita", resultFilter.get(i).getNazionalita());
			
			JSONArray allsprites=new JSONArray();
			for(int j=0;j< resultFilter.get(i).getSprites().size();j++) {
				JSONObject singlesprites=new JSONObject();
				singlesprites.put("idSprites", resultFilter.get(i).getSprites().get(j).getIdSprites());
				singlesprites.put("link",resultFilter.get(i).getSprites().get(j).getLink());
				singlesprites.put("link_small", resultFilter.get(i).getSprites().get(j).getLink_small());
				allsprites.put(singlesprites);
			}
			singleobj.put("sprites", allsprites);
			
			allresult.put(singleobj);
			
		}
		
		JSONObject rs=new JSONObject();
		rs.put("count", count);
		rs.put("page", pg);
		rs.put("result", allresult);
		/*for(int i=0; i< resultFilter.size();i++) {
			result+="{"+"\"idProdotto\":"+"\""+resultFilter.get(i).getIdProdotto()+"\""+","+
					"\"Quantita\":"+"\""+resultFilter.get(i).getQuantita()+"\""+","+
					"\"Generazione\":"+"\""+resultFilter.get(i).getGenerazione()+"\""+","+
					"\"Visitato\":"+"\""+resultFilter.get(i).getVisitato()+"\""+","+
					"\"Prezzo\":"+"\""+resultFilter.get(i).getPrezzo()+"\""+","+
					"\"Nome\":"+"\""+resultFilter.get(i).getNome()+"\""+","+
					"\"Descrizione\":"+"\""+resultFilter.get(i).getDescrizione()+"\""+","+
					"\"Tipo\":"+"\""+resultFilter.get(i).getTipo()+"\""+","+
					"\"Nazionalita\":"+"\""+resultFilter.get(i).getNazionalita()+"\""+","+
					"\"Sprites\":["
					;
			
			for(int j=0;j< resultFilter.get(i).getSprites().size();j++) {
				result+="{"+
						"\"idSprites\":"+"\""+resultFilter.get(i).getSprites().get(j).getIdSprites()+"\""+","+
						"\"link\":"+"\""+resultFilter.get(i).getSprites().get(j).getLink()+"\""+","+
						"\"link_small\":"+"\""+resultFilter.get(i).getSprites().get(j).getLink_small()+"\""+","
						;
				
				result+="}";
				if(j<resultFilter.get(i).getSprites().size())result+=",";
			}
			result+="]";
			result+="}";
			if(i<resultFilter.size()-1)result+=",";
		count++;
		}
		result+="]}";*/
		result=rs.toString();
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
