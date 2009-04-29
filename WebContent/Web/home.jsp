<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces" prefix="af"%>
<f:view>
	<af:document title="LogView">
		<af:form>
			<af:page>
				<af:panelHeader text="Filtros">
					<af:showDetail disclosed="true" undisclosedText="Mostrar Filtros"
						disclosedText="Ocultar filtros">
						<h:panelGrid columns="2">
							<af:panelBox text="Nível" width="500px">
								<af:selectManyCheckbox layout="horizontal"
									value="#{home.filtro.niveis}">
									<f:selectItem itemValue="5" itemLabel="Error" />
									<f:selectItem itemValue="4" itemLabel="Warning" />
									<f:selectItem itemValue="3" itemLabel="Fine" />
									<f:selectItem itemValue="2" itemLabel="Info" />
									<f:selectItem itemValue="1" itemLabel="Debug" />
								</af:selectManyCheckbox>
							</af:panelBox>

							<af:panelBox text="Classe" width="500px">
								<af:panelGroup layout="horizontal">
									<af:inputText columns="30" maximumLength="100"
										value="#{home.filtro.classe}" />
									<af:selectBooleanCheckbox value="#{home.filtro.caseClasse}" />
									<af:outputLabel value="Case sensitive" />
								</af:panelGroup>
							</af:panelBox>

							<af:panelBox text="Mensagem" width="500px">
								<af:panelGroup layout="horizontal">
									<af:inputText columns="30" maximumLength="100"
										value="#{home.filtro.mensagem}" />
									<af:selectBooleanCheckbox value="#{home.filtro.caseMensagem}" />
									<af:outputLabel value="Case sensitive" />
								</af:panelGroup>
							</af:panelBox>
							<af:panelBox text="Arquivo" width="500px">
								<af:panelGroup layout="horizontal">
									<af:inputText columns="30" maximumLength="100"
										value="#{home.filtro.arquivo}" />
									<af:selectBooleanCheckbox value="#{home.filtro.caseArquivo}" />
									<af:outputLabel value="Case sensitive" />
								</af:panelGroup>
							</af:panelBox>


							<af:panelBox text="Thread" width="500px">
								<af:panelGroup layout="horizontal">
									<af:inputText columns="30" maximumLength="100"
										value="#{home.filtro.thread}" />
									<af:selectBooleanCheckbox value="#{home.filtro.caseThread}"/>
									<af:outputLabel value="Case sensitive" />
								</af:panelGroup>
							</af:panelBox>

							<af:panelBox text="Logger" width="500px">
								<af:panelGroup layout="horizontal">
									<af:inputText columns="30" maximumLength="100"
										value="#{home.filtro.logger}" />
									<af:selectBooleanCheckbox value="#{home.filtro.caseLogger}"/>
									<af:outputLabel value="Case sensitive" />
								</af:panelGroup>
							</af:panelBox>

						</h:panelGrid>
					</af:showDetail>
				</af:panelHeader>
				<af:panelButtonBar inlineStyle="text-align: center">
					<af:commandButton id="aplicarFiltro"
						actionListener="#{home.actionFiltrar}" text="Aplicar"
						partialSubmit="false" />
				</af:panelButtonBar>
				
				<af:objectSpacer height="15" />
				<af:objectSeparator />
				<af:objectSpacer height="15" />

				<af:panelHeader text="Resultados">
					<af:table partialTriggers="aplicarFiltro" width="100%"
						value="#{home.listaEventos}" var="evento" rows="10">
						<af:column headerText="ID" sortable="true" sortProperty="id">
							<af:outputText value="#{evento.id}" />
						</af:column>
						<af:column headerText="Nível" sortable="true" sortProperty="nivel">
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
						</af:column>
						<af:column headerText="Data - Hora" sortable="true"
							sortProperty="dataHora">
							<h:panelGroup>
								<af:outputFormatted value="#{evento.data}" />
								<af:outputLabel value=" - " />
								<af:outputFormatted value="#{evento.hora}" />
							</h:panelGroup>
						</af:column>
						<af:column headerText="Classe" sortable="true"
							sortProperty="classe">
							<af:outputText value="#{evento.classe}" />
						</af:column>
						<af:column headerText="Mensagem" sortable="true"
							sortProperty="mensagem" >
							<af:outputText value="#{evento.mensagem}" />
						</af:column>
						<af:column headerText="Arquivo" sortable="true"
							sortProperty="arquivo">
							<af:outputText value="#{evento.arquivo}" />
						</af:column>
						<af:column headerText="Thread" sortable="true"
							sortProperty="thread">
							<af:outputText value="#{evento.thread}" />
						</af:column>
						<af:column headerText="Logger" sortable="true"
							sortProperty="logger">
							<af:outputText value="#{evento.logger}" />
						</af:column>
						<f:facet name="footer">
							<h:outputText value="Total eventos: #{home.totalEventos}" />
						</f:facet>
					</af:table>
				</af:panelHeader>
			</af:page>


		</af:form>
	</af:document>
</f:view>