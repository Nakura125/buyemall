package it.unisa.request;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.AccountDAO;
import it.unisa.DAO.IndirizzoDAO;
import it.unisa.bean.Account;
import it.unisa.bean.Indirizzo;
import it.unisa.utils.PasswordHash;
import it.unisa.utils.Validator;

/**
 * Servlet implementation class updateIndirizzo
 */
@WebServlet("/updateIndirizzo")
public class updateIndirizzo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateIndirizzo() {
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
		
		String provincia=request.getParameter("provincia");
        
        if(!Validator.hasOnlyAlphanumeric(provincia)) {
        	provincia=null;
        }
        
        String via=request.getParameter("via");
        
        if(!Validator.hasOnlyAlphanumeric(via)) {
        	via=null;
        }
        
        String citta=request.getParameter("citta");
        
        if(!Validator.hasOnlyAlphanumeric(citta)) {
        	citta=null;
        }
        
        String n_civico=request.getParameter("n_civico");
        
        if(!Validator.hasOnlyAlphanumeric(n_civico)) {
        	n_civico=null;
        }
        
        if(provincia != null && via!=null  &&n_civico!=null && citta!=null && accountBean!=null && accountBean.getUsername()!=null) {
        	try {
        		
        		Indirizzo i=new Indirizzo();
        		i.setCitta(citta);
        		i.setN_civico(n_civico);
        		i.setProvincia(provincia);
        		i.setVia(via);
        		
        		Integer id=new IndirizzoDAO().doSaveGenerator(i);
        		
        		i.setIdIndirizzo(id);
        		
				conn.UpdateIndirizzo(i,accountBean);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        
        response.sendRedirect("Home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
