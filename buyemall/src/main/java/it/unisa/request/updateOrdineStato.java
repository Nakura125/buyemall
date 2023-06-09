package it.unisa.request;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.DAO.AccountDAO;
import it.unisa.DAO.OrdineDAO;
import it.unisa.bean.Account;
import it.unisa.bean.Ordine;
import it.unisa.bean.Stato;

/**
 * Servlet implementation class updateOrdineStato
 */
@WebServlet("/updateOrdineStato")
public class updateOrdineStato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateOrdineStato() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		if (accountBean == null)
			response.sendRedirect("Home");

		String idstr = request.getParameter("idordine");
		Integer id = null;
		try {
			id = Integer.parseInt(idstr);
		} catch (IllegalArgumentException e) {
			id = null;
		}
		Ordine o = null;
		try {
			if (id != null)
				o = new OrdineDAO().doRetrieveByKey(id);
		} catch (SQLException e) {

		}
		if (o != null) {
			try {
				new OrdineDAO().UpdateStato(Stato.rimborsato, o);
				response.sendRedirect("Profilo");
			} catch (SQLException e) {
				response.sendRedirect("Home");

			}

		} else {
			response.sendRedirect("Home");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
