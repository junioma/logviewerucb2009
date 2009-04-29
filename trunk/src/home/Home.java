package home;

import holders.Evento;
import holders.FiltroPesquisa;

import java.io.IOException;
import java.util.List;

import javax.faces.event.ActionEvent;

import util.ControlFilter;
import filtros.LogView;

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
		listaEventos = (List<Evento>) getFromSession("listaEvento");
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		storeOnSession("listaEvento", listaEventos);
		this.listaEventos = listaEventos;
	}

	public FiltroPesquisa getFiltro() {
		if(filtro == null)
			filtro = new FiltroPesquisa();
		return filtro;
	}

	public void setFiltro(FiltroPesquisa filtro) {
		this.filtro = filtro;
	}

}
