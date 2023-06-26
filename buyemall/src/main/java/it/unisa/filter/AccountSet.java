package it.unisa.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import it.unisa.DAO.AccountDAO;
import it.unisa.DAO.AdminDAO;
import it.unisa.bean.Account;

/**
 * Servlet Filter implementation class AccountSet
 */
@WebFilter("/AccountSet")
public class AccountSet extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AccountSet() {
        super();

    }

	/**
	 * @see Filter#destroy()
	 */
	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//AccountDAO conn=new AccountDAO();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		if (!requestURI.equals("FilterRequest.java")) {
			
			Cookie[] cookies = httpRequest.getCookies();
			Cookie account = null;
			String accountBean = null;

			//System.out.println("Richiamo");

			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("Account")) {
						// Il cookie desiderato è presente
						account = cookie;

						accountBean = account.getValue();

						break; // Esci dal ciclo, poiché hai trovato il cookie

					}
				}
			}
			String o = null;

			if (httpRequest.getSession().getAttribute("Account") != null)
				o = (String) httpRequest.getSession().getAttribute("Account");

			if (o != null && accountBean == null) {
				accountBean = o;
			}

			Boolean isAdmin = false;
			if (accountBean != null) {
				try {
					isAdmin = new AdminDAO().doRetrieveByKey(accountBean);
				} catch (SQLException e) {

					isAdmin = false;
				}
			}

			request.setAttribute("isAdmin", isAdmin);
			request.setAttribute("accountBean", accountBean);
		}
        
		chain.doFilter(request, response);

        
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	

}
