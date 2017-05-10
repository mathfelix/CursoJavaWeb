package controle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidade.Usuario;

@WebFilter(urlPatterns = {"/restrito/*"}, servletNames = {"editconta", "carrinho"})
public class FiltroAcesso implements Filter {

    public FiltroAcesso() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		Usuario u = (Usuario) session.getAttribute("usu");
		if (u == null) {
			HttpServletResponse httpResponse = (HttpServletResponse) response; 
			httpResponse.sendRedirect("../../erro.jsp");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
