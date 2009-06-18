/*
 * Copyright 2003-2008 Universidade Cat�lica de Bras�lia, Brasil
 *
 * Este arquivo � parte do JMBlogview.
 *
 * O JMBlogview � um software livre; voc� pode redistribui-lo e/ou modific�-lo
 * dentro dos termos da Licen�a P�blica Geral GNU vers�o 2 como publicada
 * pela Funda��o do Software Livre (FSF).
 *
 * Este programa � distribuido na esperan�a que possa ser util, mas SEM
 * NENHUMA GARANTIA; sem uma garantia implicita de ADEQUA��O a qualquer
 * MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU
 * para maiores detalhes.
 *
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o
 * t�tulo "Licenca.txt", junto com este programa, se n�o, escreva para a
 * Funda��o do Software Livre(FSF) Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */
package logview.java.view.home;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import logview.java.dao.control.ControlFilter;
import logview.java.dao.reader.Directory;
import logview.java.entity.Evento;
import logview.java.view.LogView;
import logview.java.view.holders.FiltroPesquisa;
import logview.resources.util.chart.GraficoUtil;


@SuppressWarnings("unchecked")
public class Home extends LogView{
	private FiltroPesquisa filtro;	
	private List<Evento> listaEventos;

	public Home() {
		
	}
	
	public String limpar(){
		setEventos(new ArrayList<Evento>());
		setFiltroPesquisa(new FiltroPesquisa());
		return "";
	}
	
	public void actionFiltrar(ActionEvent evento) throws IOException{
		limparGraficosTemporarios();
		setListaEventos(ControlFilter.gerarListaEventosFiltrados(getFiltro()));
	}
	
	
	/**
	 * 
	 */
	private void limparGraficosTemporarios() {
		String path = getHttpServletRequest().getSession().getServletContext().getRealPath("."); 
		String sessionId = getHttpServletRequest().getSession().getId();
		File f = new File(path + GraficoUtil.DIRETORIO_TEMPORARIO + GraficoUtil.DIRETORIO_TEMPORARIO_USUARIOS + "/" + sessionId);
		
		if (f.exists()) {
			// caso diret�rio exista, remove arquivos internos para depois
			// remover diretorio
			File[] files = f.listFiles();
			for(int i=0; i<files.length; i++) {
				files[i].delete();
			}
			f.delete();
		}
	}

	public String getTotalEventos() {
		if(getListaEventos() != null)
			return listaEventos.size()+"";
		return 0+"";
	}

	public List<Evento> getListaEventos() {
		listaEventos = getEventos();
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		setEventos(listaEventos);
		this.listaEventos = listaEventos;
	}

	public FiltroPesquisa getFiltro() {
		filtro=super.getFiltroPesquisa();
		return filtro;
	}

	public void setFiltro(FiltroPesquisa filtro) {
		setFiltro(filtro);
		this.filtro = filtro;
	}

}
