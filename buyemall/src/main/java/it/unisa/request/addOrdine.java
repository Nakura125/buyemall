package it.unisa.request;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.AccountDAO;
import it.unisa.DAO.OrdineDAO;
import it.unisa.DAO.PagamentoOrdineDAO;
import it.unisa.DAO.ProdottoDAO;
import it.unisa.bean.Account;
import it.unisa.bean.Ordine;
import it.unisa.bean.PagamentoOrdine;
import it.unisa.bean.Prodotto;
import it.unisa.bean.Stato;
import it.unisa.utils.UtilityFunction;
import it.unisa.utils.Validator;

/**
 * Servlet implementation class addOrdine
 */
@WebServlet("/addOrdine")
public class addOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addOrdine() {
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
        
        String numerocarta=request.getParameter("numcarta");
        
        if(numerocarta==null && !Validator.isCardNumberValid(numerocarta)) {
        	
        	numerocarta=null;
        }
        String cvc=request.getParameter("cvc");
        if(!Validator.isCvcValid(cvc)) {
        	
        	cvc=null;
        }
        
        String dataString=request.getParameter("data");
        Date date=null;
        try {
        	date = Date.valueOf(dataString);
        	
        }catch(IllegalArgumentException e) {
        	//System.out.println("Pesce2");
        	date=null;
        }
        //System.out.println(date+"\n"+accountBean+"\n"+numerocarta+"\n"+cvc);
        if(date!=null && accountBean!=null && accountBean.getUsername()!=null && numerocarta!=null && cvc!=null) {
			try {
				
				List<Prodotto> p=AccountDAO.recoverCart(accountBean.getUsername());
				
				Float prezzo=(float)p.stream().mapToDouble(Prodotto::getPrezzo).sum();
				
				Ordine o=new Ordine();
				o.setIndirizzo(accountBean.getI());
				o.setSpedizione(accountBean.getI());
				o.setPrezzo(prezzo);
				o.setData(UtilityFunction.getToday());
				o.setStato(Stato.attesa);
				o.setU(accountBean);
				Integer key=new OrdineDAO().doSaveGenerator(o);
				
				Ordine generato=new OrdineDAO().doRetrieveByKey(key);
				for (Prodotto pr : p) {
					new OrdineDAO().doSaveComposto(accountBean, generato, pr);
					new ProdottoDAO().UpdateQuantita(pr.getQuantita()-1, pr);
				}
				PagamentoOrdine ord=new PagamentoOrdine();
				ord.setNumero_carta(numerocarta);
				ord.setOrdine(generato);//l'ordine appena creato è null
				ord.setCvc(cvc);
				ord.setData(date);
				ord.setTipo("null");
				ord.setUsername(accountBean);
				PagamentoOrdineDAO connOrd=new PagamentoOrdineDAO();
				connOrd.doSave(ord);
				
				new AccountDAO().DeleteCart(accountBean);
				response.sendRedirect("Home");
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", "l'ordine ha un errore");
			    request.setAttribute("pageName", "Pagamento");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			    dispatcher.forward(request, response);
			}
            
            
		}else {
			request.setAttribute("errorMsg", "l'ordine ha un errore");
		    request.setAttribute("pageName", "Pagamento");
		    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		    dispatcher.forward(request, response);
		}
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
