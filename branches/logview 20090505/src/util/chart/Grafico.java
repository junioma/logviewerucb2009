/**
 * 
 */
package util.chart;

import java.io.File;
import java.util.List;

/**
 * Classe que encapsula propriedades para geração de um gráfico.
 *  
 * @author fmluna
 */
public class Grafico {
	private String tituloGrafico;
	
	private String tituloEixoX; 
	
	private String tituloEixoY;
	
	private List<ItemGrafico> arrayValores; 
	
	private int width;
	
	private int height;
	
	private int quantidadeSeries;
	
	private String prefixoNomeArquivo;
	
	private String identificadorSessao;

	private String caminho;
	
	private File arquivo;
	
	/**
	 * Construtor padrão de objeto gráfico para 
	 * agrupar atributos necessários para geração de um gráfico.
	 * 
	 * @param tituloGrafico
	 * @param tituloEixoX
	 * @param tituloEixoY
	 * @param arrayValores
	 * @param width
	 * @param height
	 * @param quantidadeSeries
	 * @param prefixoNomeArquivo
	 * @param identificadorGrafico 
	 */	
	public Grafico(String tituloGrafico, String tituloEixoX,
			String tituloEixoY, List<ItemGrafico> arrayValores, int width,
			int height, int quantidadeSeries, String prefixoNomeArquivo, 
			String identificadorSessao) {
		super();
		this.tituloGrafico = tituloGrafico;
		this.tituloEixoX = tituloEixoX;
		this.tituloEixoY = tituloEixoY;
		this.arrayValores = arrayValores;
		this.width = width;
		this.height = height;
		this.quantidadeSeries = quantidadeSeries;
		this.prefixoNomeArquivo = prefixoNomeArquivo;
		
		this.identificadorSessao = identificadorSessao;
	}

	public String getTituloGrafico() {
		return tituloGrafico;
	}

	public void setTituloGrafico(String tituloGrafico) {
		this.tituloGrafico = tituloGrafico;
	}

	public String getTituloEixoX() {
		return tituloEixoX;
	}

	public void setTituloEixoX(String tituloEixoX) {
		this.tituloEixoX = tituloEixoX;
	}

	public String getTituloEixoY() {
		return tituloEixoY;
	}

	public void setTituloEixoY(String tituloEixoY) {
		this.tituloEixoY = tituloEixoY;
	}

	public List<ItemGrafico> getArrayValores() {
		return arrayValores;
	}

	public void setArrayValores(List<ItemGrafico> arrayValores) {
		this.arrayValores = arrayValores;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getQuantidadeSeries() {
		return quantidadeSeries;
	}

	public void setQuantidadeSeries(int quantidadeSeries) {
		this.quantidadeSeries = quantidadeSeries;
	}

	public String getPrefixoNomeArquivo() {
		return prefixoNomeArquivo;
	}

	public void setPrefixoNomeArquivo(String prefixoNomeArquivo) {
		this.prefixoNomeArquivo = prefixoNomeArquivo;
	}

	public String getIdentificadorSessao() {
		return identificadorSessao;
	}

	public void setIdentificadorSessao(String identificadorSessao) {
		this.identificadorSessao = identificadorSessao;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
}
