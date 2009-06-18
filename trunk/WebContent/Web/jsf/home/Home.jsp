<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces" prefix="af"%>
<f:view>
	<af:document title="LogView">
		<af:form>
			<af:page title="LogView">
				<af:showOneTab id="principal" position="above">
					<jsp:include page="AbaDadosFiltro.jsp" flush="true" />
					<jsp:include page="AbaDadosRelatorio.jsp" flush="true" />
				</af:showOneTab>				
			</af:page>
		</af:form>
	</af:document>
</f:view>