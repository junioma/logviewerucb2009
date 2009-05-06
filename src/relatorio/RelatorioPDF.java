package relatorio;

import holders.Evento;
import holders.FiltroPesquisa;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import util.LogView;

public class RelatorioPDF extends LogView{

	public String visualizarPDF(){
		List<Evento> listaEventos = getEventos();
		FiltroPesquisa filtro = getFiltroPesquisa();
		if(listaEventos.isEmpty()){
			return null;
		}
		JRPdfExporter exporter = new JRPdfExporter();
		
		try {
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR", getPath("/WEB-INF/classes/util/reports"));
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

			InputStream fileInputStream =  getClass().getResourceAsStream("/util/reports/filtroPesquisa.jasper");

			JasperPrint jp = JasperFillManager.fillReport(fileInputStream,parametros, ds);
						
			if (jp != null) {
				HttpServletResponse response = (HttpServletResponse) getHttpServletResponse();
				response.setContentType("application/force-download");
				response.setHeader("Content-disposition","attachment; filename=RelatorioLogViewr.pdf");			
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"iso-8859-1");
				exporter.exportReport();
				response.getOutputStream().flush();
			}
		} catch (Exception e) {
		}
		
		return null;
	}
	
}
