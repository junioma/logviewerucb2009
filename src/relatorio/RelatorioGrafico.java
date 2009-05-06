package relatorio;

import holders.Evento;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import util.LogView;
import util.chart.Grafico;
import util.chart.GraficoUtil;
import util.chart.ItemGrafico;

@SuppressWarnings({"deprecation","unchecked"})
public class RelatorioGrafico extends LogView {

	private static final int GRAFICO_LARGURA = 800;
	private static final int GRAFICO_ALTURA = 300;
	
	private String caminhoGrafico;
	
	public void gerarGraficoEventos(ActionEvent evt){
		List<Evento> listaEventos = (List<Evento>) getFromSession("listaEvento");
		if(listaEventos == null)
			return;
		setCaminhoGrafico(gerarGraficoEventos(gerarListaItemGrafico(listaEventos)));
	}
	
	private List<ItemGrafico> gerarListaItemGrafico(List<Evento> listaEventos) {
		List<ItemGrafico> listaIG = new ArrayList<ItemGrafico>();	
		
		List<Object[]> valorOcorrencia = contabilizarOcorrencias(listaEventos);
		
		for(Object[] obj : valorOcorrencia){
			listaIG.add(new ItemGrafico((String)obj[0],(String)obj[0],(Integer)obj[1]));
		}
		return listaIG;
	}

	private List<Object[]> contabilizarOcorrencias(List<Evento> listaEventos) {
		int qtdDebug = 0;
		int qtdInfo = 0;
		int qtdFine = 0;
		int qtdWarning = 0;
		int qtdError = 0;
		
		for(Evento evento : listaEventos){
			if(evento.isNivelDebug())
				qtdDebug++;
			if(evento.isNivelError())
				qtdError++;
			if(evento.isNivelFine())
				qtdFine++;
			if(evento.isNivelInfo())
				qtdInfo++;
			if(evento.isNivelWarning())
				qtdWarning++;
		}
		
		List<Object[]> listaValores = new ArrayList<Object[]>();
		listaValores.add(new Object[]{"Debug",new Integer(qtdDebug)});
		listaValores.add(new Object[]{"Info",new Integer(qtdInfo)});
		listaValores.add(new Object[]{"Fine",new Integer(qtdFine)});
		listaValores.add(new Object[]{"Warning",new Integer(qtdWarning)});
		listaValores.add(new Object[]{"Error",new Integer(qtdError)});
		return listaValores;
	}

	public String gerarGraficoEventos(List<ItemGrafico> itensGrafico) {	
		Grafico grafico = new Grafico("", "Nível", "Qtd. Ocorrência", 
				itensGrafico, GRAFICO_LARGURA, GRAFICO_ALTURA, itensGrafico.size(), 
				"nome.arquivo.relatorio.barras", getHttpServletRequest().getSession().getId());
		return GraficoUtil.gerarGraficoBarraVerticalJPEG(grafico,getHttpServletRequest().getRealPath("."));
	}
	
	public String getCaminhoGrafico() {
		return caminhoGrafico;
	}

	public void setCaminhoGrafico(String caminhoGrafico) {
		this.caminhoGrafico = caminhoGrafico;
	}

}
