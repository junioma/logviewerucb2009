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
