/*
 * Copyright 2003-2008 Universidade Católica de Brasília, Brasil
 *
 * Este arquivo é parte do Easy2C.
 *
 * O Easy2C é um software livre; você pode redistribui-lo e/ou modificá-lo
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
package logview.java.view.home;

import java.io.IOException;
import java.util.List;

import javax.faces.event.ActionEvent;

import logview.java.dao.control.ControlFilter;
import logview.java.view.LogView;
import logview.java.view.holders.Evento;
import logview.java.view.holders.FiltroPesquisa;


@SuppressWarnings("unchecked")
public class Home extends LogView{
	private FiltroPesquisa filtro;	
	private List<Evento> listaEventos;

	public Home() {
		
	}
	
	public void actionFiltrar(ActionEvent evento) throws IOException{
		setListaEventos(ControlFilter.gerarListaEventosFiltrados(getFiltro()));
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
