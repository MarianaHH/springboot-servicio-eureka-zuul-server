package com.mariana.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

	private Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
	
	//aqui van las validaciones de nuestro filtro, si retorna true ejecuta el metodo run
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("entrando a post filter");
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos %s seg.", tiempoTranscurrido/1000.00));
		log.info(String.format("Tiempo transcurrido en milisegundos %s ms.", tiempoTranscurrido));
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
		
		return "post";
	}

	@Override
	public int filterOrder() {
		
		return 1;
	}

}
