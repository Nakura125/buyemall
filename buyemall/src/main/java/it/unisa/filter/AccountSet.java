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
	public void destroy() {

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
						e.printStackTrace();
						accountBean=null;
					}
                    break;  // Esci dal ciclo, poiché hai trovato il cookie
                    
                }
            }
        }
        String o=null;
        
        if(httpRequest.getSession().getAttribute("Account")!=null)
        	o=(String) httpRequest.getSession().getAttribute("Account");
        
        if(o!=null && accountBean==null) {
        	try {
				accountBean=conn.doRetrieveByKey(o);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				accountBean=null;
			}
        }
        
        Boolean isAdmin=false;
        if(accountBean!=null) {
        	try {
				isAdmin=new AdminDAO().doRetrieveByKey(accountBean.getUsername());
			} catch (SQLException e) {

				e.printStackTrace();
				isAdmin=false;
			}
        }
        
        request.setAttribute("isAdmin", isAdmin);
        request.setAttribute("accountBean", accountBean);
		chain.doFilter(request, response);

        
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	

}
