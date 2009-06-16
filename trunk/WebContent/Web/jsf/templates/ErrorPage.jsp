<%@ page contentType="text/html;charset=windows-1252" isErrorPage="true"
         import="java.io.CharArrayWriter, java.io.PrintWriter"%>

<html>

  <head>
    <title>
      Erro
    </title>
  </head>
  
  <body  leftmargin="0" topmargin="0" rightmargin="0" style="margin-top: 0px">
  
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <%@ include file="/Web/jsf/templates/Header.jsp"%>
        </td>
      </tr>
      <tr bgcolor="#EFEBDE">
        <td width="100%" colspan="2" align="center" style="font-size: 14" height="19">
          Ocorreu um erro ao processar a sua operação, favor entrar em contato com o administrador.
        </td>
      </tr>
      <tr bgcolor="#EFEBDE">
        <td style="text-align: center">
        	<input type="button" value="Voltar" onclick="javascript: history.go(-1)"/>
        	<input type="button" value="Fechar" onclick="javascript: window.close()"/>
        </td>
      </tr>
  </body>
  
</html>