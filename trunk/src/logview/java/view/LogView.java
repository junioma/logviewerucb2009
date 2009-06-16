/*
 * Copyright 2003-2008 Universidade Católica de Brasília, Brasil
 *
 * Este arquivo é parte do jmblogview.
 *
 * O jmblogview é um software livre; você pode redistribui-lo e/ou modificá-lo
 * dentro dos termos da Licença Pública Geral GNU versão 2 como publicada
 * pela Fundação do Software Livre (FSF).
 *
 * Este programa é distribuido na esperança que possa ser util, mas SEM
 * NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o
 * título "Licenca.txt", junto com este programa, se não, escreva para a
 * Fundação do Software Livre(FSF) Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */
package logview.java.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logview.java.view.holders.Evento;
import logview.java.view.holders.FiltroPesquisa;
import logview.resources.util.constraints.Constantes;

@SuppressWarnings({"unchecked","deprecation"})
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
	
	protected String getPath(String path){
		return getHttpServletRequest().getRealPath(path);
	}
	
	protected void setEventos(List<Evento> eventos){
		storeOnSession("listaEvento", eventos);
	}
	protected List<Evento> getEventos(){
		List<Evento> lista = new ArrayList<Evento>();
		List<Evento> listaSession = (List<Evento>) getFromSession("listaEvento");
		if(listaSession != null)
			lista.addAll(listaSession);
		return lista;
	}
	
	protected void setFiltroPesquisa(FiltroPesquisa filtro){
		storeOnSession("filtroPesquisa", filtro);
	}
	protected FiltroPesquisa getFiltroPesquisa() {
		FiltroPesquisa filtro = (FiltroPesquisa) getFromSession("filtroPesquisa");
		if(filtro != null)
			return filtro;
		else
			setFiltroPesquisa(new FiltroPesquisa());
		return getFiltroPesquisa();
	}
	
	public String getIMG_ERROR(){
		return Constantes.IMAGEM_ERROR;
	}
	public String getIMG_WARNING(){
		return Constantes.IMAGEM_WARNING;
	}
	public String getIMG_FINE(){
		return Constantes.IMAGEM_FINE;
	}
	public String getIMG_INFO(){
		return Constantes.IMAGEM_INFO;
	}
	public String getIMG_DEBUG(){
		return Constantes.IMAGEM_DEBUG;
	}
}
