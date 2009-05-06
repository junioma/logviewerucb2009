<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<f:subview id="FooterForm">

  <h:panelGrid cellpadding="0" cellspacing="0">
    <h:outputText value="Os campos marcados com um asterisco <font color=#F68622>*</font> são de preenchimento obrigatório."
                  escape="false" styleClass="SmallMessage"/>
  </h:panelGrid>
  
</f:subview>