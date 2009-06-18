<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces" prefix="af"%>

<af:showDetailItem text="Pesquisa" immediate="true"
	inlineStyle="font-size: 8pt">
	<af:panelHeader text="Filtros">
		
		<h:panelGrid columns="2">
				<af:outputLabel value="NÍVEL" />
				<af:selectManyCheckbox layout="horizontal"
					value="#{home.filtro.niveis}">
					<f:selectItem itemValue="5" itemLabel="Error" />
					<f:selectItem itemValue="4" itemLabel="Warning" />
					<f:selectItem itemValue="3" itemLabel="Fine" />
					<f:selectItem itemValue="2" itemLabel="Info" />
					<f:selectItem itemValue="1" itemLabel="Debug" />
				</af:selectManyCheckbox>
	
	
				<af:outputLabel value="CLASSE" />
				<af:panelGroup layout="horizontal">
					<af:inputText columns="30" maximumLength="100"
						value="#{home.filtro.classe}" />
					<af:selectBooleanCheckbox value="#{home.filtro.caseClasse}" />
					<af:outputLabel value="Case sensitive" />
				</af:panelGroup>
	
				<af:outputLabel value="MENSAGEM" />
				<af:panelGroup layout="horizontal">
					<af:inputText columns="30" maximumLength="100"
						value="#{home.filtro.mensagem}" />
					<af:selectBooleanCheckbox value="#{home.filtro.caseMensagem}" />
					<af:outputLabel value="Case sensitive" />
				</af:panelGroup>
	
				<%--<af:outputLabel value="ARQUIVO" />
				<af:panelGroup layout="horizontal">
					<af:inputText columns="30" maximumLength="100"
						value="#{home.filtro.arquivo}" />
					<af:selectBooleanCheckbox value="#{home.filtro.caseArquivo}" />
					<af:outputLabel value="Case sensitive" />
				</af:panelGroup>
	
				<af:outputLabel value="THREAD" />
				<af:panelGroup layout="horizontal">
					<af:inputText columns="30" maximumLength="100"
						value="#{home.filtro.thread}" />
					<af:selectBooleanCheckbox value="#{home.filtro.caseThread}" />
					<af:outputLabel value="Case sensitive" />
				</af:panelGroup>
	
				<af:outputLabel value="LOGGER" />
				<af:panelGroup layout="horizontal">
					<af:inputText columns="30" maximumLength="100"
						value="#{home.filtro.logger}" />
					<af:selectBooleanCheckbox value="#{home.filtro.caseLogger}" />
					<af:outputLabel value="Case sensitive" />
				</af:panelGroup>--%>
	
				<af:outputLabel value="PERÍODO" />
				<af:panelGroup layout="horizontal">
					<af:selectInputDate value="#{home.filtro.periodoInicial}" />
					<af:outputLabel value=" até " />
					<af:selectInputDate value="#{home.filtro.periodoFinal}" />
				</af:panelGroup>
			
		</h:panelGrid>
	</af:panelHeader>
	<af:panelButtonBar inlineStyle="text-align: center">
		<af:commandButton id="aplicarFiltro"
			actionListener="#{home.actionFiltrar}" text="Aplicar"
			partialSubmit="false" />
		<af:commandButton textAndAccessKey="Limpar" action="#{home.limpar}" />		
	</af:panelButtonBar>
	<af:objectSeparator />
	<af:panelHeader text="Legenda" inlineStyle="width: 100%">
		<h:panelGrid columns="10">
			<af:objectImage source="#{logview.IMG_ERROR}" />
			<af:outputLabel value="Nível ERROR" />
			<af:objectImage source="#{logview.IMG_WARNING}" />
			<af:outputLabel value="Nível WARNING" />
			<af:objectImage source="#{logview.IMG_FINE}" />
			<af:outputLabel value="Nível FINE" />
			<af:objectImage source="#{logview.IMG_INFO}" />
			<af:outputLabel value="Nível INFO" />
			<af:objectImage source="#{logview.IMG_DEBUG}" />
			<af:outputLabel value="Nível DEBUG" />
		</h:panelGrid>
	</af:panelHeader>
	<af:panelHeader text="Resultados">	
		<af:table partialTriggers="aplicarFiltro" width="100%"
			value="#{home.listaEventos}" var="evento" rows="10" emptyText="Nenhum resultado encontrado.">
			<af:column headerText="ID" sortable="true" sortProperty="id">
				<af:outputText value="#{evento.id}" />
			</af:column>
			<af:column headerText="Nível" sortable="true" sortProperty="nivel">
				<af:objectImage source="#{evento.imagemNivel}" />
			</af:column>
			<%--af:column headerText="Nível" sortable="true" sortProperty="nivel">
				<af:outputText value="ERROR"
					inlineStyle="COLOR: RED;font-weight: bold"
					rendered="#{evento.nivelError}" />
				<af:outputText value="WARNING"
					inlineStyle="COLOR: YELLOW;font-weight: bold"
					rendered="#{evento.nivelWarning}" />
				<af:outputText value="FINE"
					inlineStyle="COLOR: GREEN;font-weight: bold"
					rendered="#{evento.nivelFine}" />
				<af:outputText value="INFO"
					inlineStyle="COLOR: BLACK;font-weight: bold"
					rendered="#{evento.nivelInfo}" />
				<af:outputText value="DEBUG"
					inlineStyle="COLOR: BLUE;font-weight: bold"
					rendered="#{evento.nivelDebug}" />
			</af:column--%>
			<af:column headerText="Data - Hora" sortable="true"
				sortProperty="dataHora">
				<h:panelGroup>
					<af:outputFormatted value="#{evento.data}" />
					<af:outputLabel value=" - " />
					<af:outputFormatted value="#{evento.hora}" />
				</h:panelGroup>
			</af:column>
			<af:column headerText="Classe" sortable="true" sortProperty="classe">
				<af:outputText value="#{evento.classe}" />
			</af:column>
			<af:column headerText="Mensagem" sortable="true"
				sortProperty="mensagem">
				<af:outputText value="#{evento.mensagem}" />
			</af:column>
			<af:column headerText="Arquivo" sortable="true"
				sortProperty="arquivo">
				<af:outputText value="#{evento.arquivo}" />
			</af:column>
			<af:column headerText="Thread" sortable="true" sortProperty="thread">
				<af:outputText value="#{evento.thread}" />
			</af:column>
			<af:column headerText="Logger" sortable="true" sortProperty="logger">
				<af:outputText value="#{evento.logger}" />
			</af:column>
			<f:facet name="footer">
				<h:outputText value="Total eventos: #{home.totalEventos}" />
			</f:facet>
		</af:table>
	</af:panelHeader>
	<af:objectSeparator />

</af:showDetailItem>