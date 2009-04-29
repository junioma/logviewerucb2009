package filtros;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class LogView {
	protected HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	protected HttpServletResponse getHttpServletResponse(){
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
	
	protected void storeOnSession(String parametro, Object atributo){
		getHttpServletRequest().getSession().setAttribute(parametro, atributo);
	}
	protected Object getFromSession(String parametro){
		return getHttpServletRequest().getSession().getAttribute(parametro);
	}
	
	protected void storeOnRequest(String parametro, Object atributo){
		getHttpServletRequest().getSession().setAttribute(parametro, atributo);
	}
	protected Object getFromRequest(String parametro){
		return getHttpServletRequest().getSession().getAttribute(parametro);
	}
	
}
