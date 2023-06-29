package it.unisa.request;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.AccountDAO;
import it.unisa.DAO.MetodiPagamentoDAO;
import it.unisa.bean.Account;
import it.unisa.bean.MetodiPagamento;
import it.unisa.utils.Validator;

/**
 * Servlet implementation class addMetodo
 */
@WebServlet("/addMetodo")
public class addMetodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addMetodo() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		AccountDAO conn=new AccountDAO();
		Account accountBean=null;
		String account=(String)request.getAttribute("accountBean");
		if (account != null) {
			try {
				accountBean = conn.doRetrieveByKey(account);
			} catch (SQLException e) {

				accountBean = null;
			}
		}
        
        String numerocarta=request.getParameter("numcarta");
        
        if(!Validator.isCardNumberValid(numerocarta)) {
       
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
        	date=null;
        }
        
        
        if(date!=null && accountBean!=null && accountBean.getUsername()!=null && numerocarta!=null && cvc!=null) {
        	MetodiPagamento pg=new MetodiPagamento();
        	pg.setUsername(accountBean);
        	pg.setCvc(cvc);
        	pg.setData_scadenza(date);
        	pg.setNumero_carta(numerocarta);
        	pg.setTipo_pagamento("null");
        	
        	try {
				new MetodiPagamentoDAO().doSave(pg);
			} catch (SQLException e) {
				
			}
        }
        
        response.sendRedirect("Profilo#linkMetodiPagamento");
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
