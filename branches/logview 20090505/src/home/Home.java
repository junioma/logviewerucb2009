package home;

import holders.Evento;
import holders.FiltroPesquisa;

import java.util.List;

import javax.faces.event.ActionEvent;

import util.LogView;
import util.Teste;

@SuppressWarnings("unchecked")
public class Home extends LogView{
	private FiltroPesquisa filtro;	
	private List<Evento> listaEventos;

	public Home() {
		
	}
	
	public void actionFiltrar(ActionEvent evento){
		setListaEventos(Teste.gerarListaEventosFiltrados(getFiltro()));
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
