<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"
	errorPage="/Web/jsf/templates/ErrorPage.jsp"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces" prefix="af"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://xmlns.oracle.com/adf/faces/html" prefix="afh"%>

<tiles:importAttribute name="windowTitle" scope="request" ignore="true" />
<tiles:importAttribute name="title" scope="request" ignore="true" />

<f:view>

	<html>

	<afh:head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title><tiles:getAsString name="windowTitle" /></title>
	</afh:head>

	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" rightmargin="0"
		style="margin-top: 0px">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><tiles:insert attribute="header" flush="false" /></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><f:subview id="Menu">
				<tiles:insert attribute="menu" flush="false" />
			</f:subview></td>
		</tr>
	</table>


	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%"><f:subview id="Messages">
				<tiles:insert attribute="messages" flush="false" />
			</f:subview></td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%"><tiles:useAttribute id="title" name="title"
				scope="request" classname="java.lang.String" ignore="true" /> <af:panelPage
				title="#{requestScope.title}">
				<af:objectSpacer width="10" height="15" />
				<tiles:insert attribute="body" flush="false" />
			</af:panelPage></td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%"><f:subview id="Footer">
				<tiles:insert attribute="footer" flush="false" />
			</f:subview></td>
		</tr>
	</table>
	</body>
	</html>


</f:view>