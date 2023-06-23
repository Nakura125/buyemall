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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		AccountDAO conn=new AccountDAO();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Cookie[] cookies = httpRequest.getCookies();
        Cookie account=null;
        Account accountBean=null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Account")) {
                    // Il cookie desiderato è presente
                    account=cookie;
                    try {
						accountBean=conn.doRetrieveByKey(account.getValue());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						accountBean=null;
					}
                    break;  // Esci dal ciclo, poiché hai trovato il cookie
                    
                }
            }
        }
        System.out.println("Something");
        request.setAttribute("accountBean", accountBean);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
