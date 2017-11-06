package src.main.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@WebFilter(urlPatterns = "/*")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccesFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Access Filter init");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//HttpServletRequest req = (HttpServletRequest) request;
		//req.getSession();
		//if(req.getParameter("idUserSession").equals(null)){
		return;	
	//	}
		
		
	}

	@Override
	public void destroy() {
		System.out.println("Access Filter destroy");
		
	}

}
