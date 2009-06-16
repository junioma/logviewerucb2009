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
package logview.java.view.relatorio;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import logview.java.entity.Evento;
import logview.java.view.LogView;
import logview.java.view.holders.FiltroPesquisa;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class RelatorioPDF extends LogView{

	public String visualizarPDF(){
		List<Evento> listaEventos = getEventos();
		FiltroPesquisa filtro = getFiltroPesquisa();
		if(listaEventos.isEmpty()){
			return null;
		}
		JRPdfExporter exporter = new JRPdfExporter();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SUBREPORT_DIR", getPath("/WEB-INF/classes/logview/resources/util/reports"));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		parametros.put("listaEvento", listaEventos);
		if(filtro != null){
			parametros.put("cArquivo", filtro.getArquivo());
			parametros.put("cClasse", filtro.getClasse());
			parametros.put("cLogger", filtro.getLogger());
			parametros.put("cMensagem", filtro.getMensagem());
			parametros.put("cPeriodoInicial", filtro.getPeriodoInicial());
			parametros.put("cPeriodoFinal", filtro.getPeriodoFinal());
			parametros.put("cThread", filtro.getThread());
			parametros.put("cNivelError", filtro.isFiltroNivelError());
			parametros.put("cNivelWarning", filtro.isFiltroNivelWarning());
			parametros.put("cNivelFine", filtro.isFiltroNivelFine());
			parametros.put("cNivelInfo", filtro.isFiltroNivelInfo());
			parametros.put("cNivelDebug", filtro.isFiltroNivelDebug());
			parametros.put("totalEvento", listaEventos.size()+"");
		}
		JRDataSource ds = new JREmptyDataSource();

		InputStream fileInputStream =  getClass().getResourceAsStream("/logview/resources/util/reports/filtroPesquisa.jasper");
		try{
			JasperPrint jp = JasperFillManager.fillReport(fileInputStream,parametros, ds);

			if (jp != null) {
				try {
					HttpServletResponse response = (HttpServletResponse) getHttpServletResponse();
					response.setContentType("application/force-download");
					response.setHeader("Content-disposition","attachment; filename=RelatorioLogViewr.pdf");			
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);

					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());

					exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"iso-8859-1");
					exporter.exportReport();
					response.getOutputStream().flush();
				} catch (IOException e) {
					
				}
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}

}
