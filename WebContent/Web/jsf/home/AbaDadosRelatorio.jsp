<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces" prefix="af"%>

<af:showDetailItem text="Relatório" immediate="true"
	inlineStyle="font-size: 8pt">
	<h:panelGrid>
		<af:objectImage source="#{relatorioGrafico.caminhoGrafico}" rendered="#{relatorioGrafico.caminhoGrafico != null && relatorioGrafico.eventos != null}" 
		shortDesc="Gráfico quantitativo das ocorrências"/>
		<af:commandButton text="Gerar gráfico"
			actionListener="#{relatorioGrafico.gerarGraficoEventos}" shortDesc="Gerar/Atualizar relatório gráfico quantitativo das ocorrências"/>
		<af:commandButton text="Gerar PDF" action="#{relatorioPDF.visualizarPDF}" shortDesc="Gerar lista de ocorrências em um arquivo no formato PDF."/>	
	</h:panelGrid>

</af:showDetailItem>