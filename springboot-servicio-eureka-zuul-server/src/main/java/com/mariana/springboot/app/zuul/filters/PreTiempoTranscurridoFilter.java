package com.mariana.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

	private Logger Log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
	
	//aqui van las validaciones de nuestro filtro, si retorna true ejecuta el metodo run
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		Log.info(String.format("%s request enrutado a %s",request.getMethod(),request.getRequestURL().toString()));
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		
		
		
		return true;
	}
	
	//metodo que implementa la logica de nuestro filtro
	@Override
	public Object run() throws ZuulException {
		
		return null;
	}

	//define el tipo de filtro puede ser pre, post y route
	@Override
	public String filterType() {
		
		return "pre";
	}

	@Override
	public int filterOrder() {
		
		return 1;
	}

}
