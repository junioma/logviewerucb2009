<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces" prefix="af"%>

<af:showDetailItem text="Relatório" immediate="true"
	inlineStyle="font-size: 8pt">
	<h:panelGrid>
		<af:objectImage source="#{relatorioGrafico.caminhoGrafico}" rendered="#{relatorioGrafico.caminhoGrafico != null && logview.eventos != null}"/>
		<af:commandButton text="Gerar gráfico"
			actionListener="#{relatorioGrafico.gerarGraficoEventos}" />
		<af:commandButton text="Gerar PDF" action="#{relatorioPDF.visualizarPDF}" />	
	</h:panelGrid>

</af:showDetailItem>