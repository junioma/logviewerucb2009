/*
 * Copyright 2003-2008 Universidade Cat�lica de Bras�lia, Brasil
 *
 * Este arquivo � parte do Easy2C.
 *
 * O Easy2C � um software livre; voc� pode redistribui-lo e/ou modific�-lo
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
package logview.resources.util.chart;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * Classe utilit�ria para gera��o de gr�ficos
 */
public class GraficoUtil{
	public static final String DIRETORIO_TEMPORARIO = "/Web/data";
	
	public static final String DIRETORIO_TEMPORARIO_USUARIOS = DIRETORIO_TEMPORARIO+"/users";
	
	private static final String GRAFICO_PROPERTIES = "grafico.properties";

	private static Properties properties = null;
	
	private static Color[] objetosCores = new Color[] {
		Color.BLUE,
		Color.BLACK,
		Color.GREEN,
		Color.YELLOW,
		Color.RED
	};

	/**
	 * Gera um gr�fico de barras Vertical
	 * 
	 * @param Grafico - objeto que encapsula as propriedades para gera��o do gr�fico.
	 * 
	 * @return Objeto representando o gr�fico.
	 */
	public static JFreeChart gerarGraficoBarraVertical(Grafico grafico) {

		JFreeChart chart = null;
		DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();

		// Gerar conjunto de itens a serem apresentados no gr�fico
		int maximoGrafico =0;
		for (ItemGrafico item : grafico.getArrayValores()) {
			if(item.getValor().intValue() > maximoGrafico)
				maximoGrafico = item.getValor();
			defaultCategoryDataset.addValue(item.getValor(), item.getGrupo(),item.getLabel());	
		}
		
		chart = ChartFactory.createBarChart3D(grafico.getTituloGrafico(), grafico.getTituloEixoX(),
				grafico.getTituloEixoY(), defaultCategoryDataset, PlotOrientation.VERTICAL,
				true, false, false);		
		chart.setBorderVisible(true);
		chart.setBorderPaint(Color.black);
		chart.setAntiAlias(true);
		chart.setTextAntiAlias(true);
		
		CategoryPlot plot = chart.getCategoryPlot(); //Pega o plot para pintar as barras
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(new Color(150,150,200));
		plot.setRangeGridlinePaint(new Color(150,150,200));
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);
		
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(true); //Desenha a linha externa nas barras (bordas das barras)
		renderer.setItemMargin(0);		
		renderer.setMaximumBarWidth(0.02);
		renderer.setMinimumBarLength(0.02);		
		
		CategoryAxis eixoX = plot.getDomainAxis();		
		eixoX.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);	
		
		ValueAxis eixoY = plot.getRangeAxis();
		eixoY.setRange(0, maximoGrafico*2);
		
		
		// Customiza as cores apresentadas no gr�fico
		int j = 0;
		for (int i = 0; i < grafico.getQuantidadeSeries(); i++) 
		{ 
			renderer.setSeriesPaint(i, 
				new GradientPaint(0.0f, 0.0f, objetosCores[j++], 0.0f, 0.0f, Color.DARK_GRAY));  
			if (j >= objetosCores.length)
				j = 0;
		} 
		
		return chart;
	}

	/**
	 * Gera gr�fico 
	 * @param tituloGrafico
	 * @param tituloEixoX
	 * @param tituloEixoY
	 * @param arrayValores
	 * @param realPath
	 * @param width
	 * @param height
	 * @param quantidadeSeries
	 * @return Nome do arquivo gerado com dados do gr�fico, com caminho relativo incluso
	 */
	public static String gerarGraficoBarraVerticalJPEG(Grafico grafico, String realPath) {
		String pathArquivo = "";
		String nomeArquivo = "";
		try {
			if (properties == null) {
				properties = new Properties();		
				properties.load(Grafico.class.getResourceAsStream(GRAFICO_PROPERTIES));			
			}				
		
			verificaExistenciaDiretorioTemporario(realPath, grafico.getIdentificadorSessao());
			
			String nomePath = (String) properties.get(grafico.getPrefixoNomeArquivo());			
			nomeArquivo = buildNomeArquivo( 
					nomePath,
					grafico.getIdentificadorSessao());
			
			pathArquivo = realPath + nomeArquivo;


			JFreeChart chart = gerarGraficoBarraVertical(grafico);
			File file = new File(pathArquivo);
			grafico.setArquivo(file);
			grafico.setCaminho(nomeArquivo);
			ChartUtilities.saveChartAsJPEG(file, chart, grafico.getWidth(), grafico.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nomeArquivo;
	}
	
	/**
	 * M�todo verifica se existe diret�rio tempor�rio para gera��o de arquivos 
	 * associados a sess�o do usu�rio.
	 * 
	 * Caso n�o exista, cria o diret�rio.
	 * 
	 * @param realPath
	 * @param diretorioTemporarioUsuarios
	 * @param identificadorSessao
	 */
	private static void verificaExistenciaDiretorioTemporario(String realPath,
			String identificadorSessao) {
		File dataDir = new File(realPath + DIRETORIO_TEMPORARIO);
		// Cria diretorio temporario para armazenar imagens
		if (!dataDir.exists()) {
			dataDir.mkdir();
			
			File usersDir = new File(realPath + DIRETORIO_TEMPORARIO_USUARIOS);
			usersDir.mkdir();
		}
		
		File f = new File(realPath + DIRETORIO_TEMPORARIO_USUARIOS + "/" + identificadorSessao);
		
		if (!f.exists()) {
			f.mkdir();
		}
		
	}

	/**
	 * Constr�i nome do arquivo a partir do ID da sess�o e o nome do arquivo
	 * @param mascaraArquivo
	 * @param identificadorSessao
	 * @return
	 */
	public static String buildNomeArquivo(String mascaraArquivo, String identificadorSessao) {
		String extensao = ".jpg";
		return DIRETORIO_TEMPORARIO_USUARIOS + "/" + identificadorSessao + "/" + mascaraArquivo + extensao;
	}

}
