<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
  <head>
    <title>
      Erro de acesso
    </title>
    <link media="screen" rel="stylesheet"
          href="<%=request.getContextPath()%>/estilos/default.css"></link>
    <link rel="stylesheet" charset="UTF-8" type="text/css" 
          href="<%=request.getContextPath()%>/estilos/oracle-desktop.css">          
    <script language="JavaScript" type="text/javascript"
            src="<%=request.getContextPath()%>/scripts/default.js"></script>
  </head>
  
  <body  leftmargin="0" topmargin="0" rightmargin="0" style="margin-top: 0px">

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <%@ include file="/jsf/templates/Header.jsp"%>
        </td>
      </tr>
      <tr bgcolor="#EFEBDE">
        <td width="100%" colspan="2" align="center" style="font-size: 14" height="19">
          Erro de acesso
        </td>
      </tr>
    </table>    
    <br>

    <!--table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="57">
          <img src="<%=request.getContextPath()%>/imagens/logo_srh.gif"
               alt="Sistema de Gestão de Recursos Humanos"
               name="pagina_principal" width="57" height="77" border="0"></img>
        </td>
        <td background="<%=request.getContextPath()%>/imagens/fundo_logo.gif">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="51" align="right" colspan="2">
                <img src="<%=request.getContextPath()%>/imagens/logo_srh_texto.gif" alt="Sistema de Gestão de Recursos Humanos" width="284" height="51" border="0" align="left"><img src="<%=request.getContextPath()%>/imagens/titulo_modulo.gif" width="409" height="51" border="0">
              </td>
            </tr>
            <tr>
              <td width="13" height="26"></td>
              <td height="26">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="center" class="ThemeOfficeMenu">Erro de acesso</td>
                    <td width="119">&nbsp;</td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table-->
    
    <!--br>
    
    <table cellpadding="0" cellspacing="0" style="text-align: center;"
           width="759">
      <tbody>
        <tr>
          <td>
            <table>
              <tr>
                <td>
                  <span class="ErrorMessage" style="text-align: left">
                    <b>Permissão de acesso negada.</b>
                  </span>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    
    <table>
      <tr>
        <td>
          <img src="<%=request.getContextPath()%>/imagens/blank.gif" height="1" width="1"
               class="spacer" alt=""></img>
        </td>
      </tr>
    </table>
    
    <br>
    
    <table align="center">
      <tr>
        <td>
          <input type="button" value=" Fechar " onclick="self.close()"/>
        </td>
      </tr>
    </table-->

    <div id="Messages:_id1__xc_">
      <div style="margin-top:5px"></div>
    </div>
    <table id="Messages:_id1" cellpadding="0" cellspacing="0" border="0" width="100%" summary="">
      <tr>
        <td style="background-image:url(<%=request.getContextPath()%>/adf/images/cache/cmbts.gif);background-repeat:no-repeat" width="11" height="11">
          <div style="margin-top:11px;padding-left:11px"></div>
        </td>
        <td width="100%" class="x3m" style="padding-right:10px">
          <table cellpadding="0" cellspacing="0" border="0" width="100%" summary="">
            <tr>
              <td rowspan="3">
                <img src="<%=request.getContextPath()%>/imagens/error.gif" width="18" height="18" border="0" alt="">
              </td>
              <td rowspan="3" width="3">
                <img src="<%=request.getContextPath()%>/adf/images/t.gif" alt="" width="3" height="18">
              </td>
              <td width="100%" valign="bottom">
                <table cellpadding="0" cellspacing="0" border="0" width="100%" summary="">
                  <tr>
                    <td class="x21">Erro</td>
                  </tr>
                  <tr>
                    <td height="1" class="x3e"></td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td class="x3m">
          <img src="<%=request.getContextPath()%>/adf/images/t.gif" alt="" width="1" height="1">
        </td>
        <td width="100%" class="x3m" style="padding:0px 10px 5px 0px">
          <div style="margin-left:21px"> 
            <span class="x3u"></span>
            <div class="x8o">
              <span class="x3u">
                <b>Acesso negado!</b>
              </span>
              <br>
            </div>
          </div>
        </td>
      </tr>
    </table>
    
    <table>
      <tr>
        <td>
          <img src="<%=request.getContextPath()%>/imagens/blank.gif" height="1" width="1"
               class="spacer" alt=""></img>
        </td>
      </tr>
    </table>
    
    <table align="center">
      <tr>
        <td>
          <button style="border: none; background: transparent; cursor: hand;" onclick="execute()">
            <img id="voltar" src="<%=request.getContextPath()%>/imagens/button_back.gif" title="Voltar" alt="Voltar" border="0" align="middle" style="display: none" onclick="alert('t')">
            <img id="fechar" src="<%=request.getContextPath()%>/imagens/button_close.gif" title="Fechar" alt="Fechar" border="0" align="middle" style="display: none">
            <script>
              if (opener == undefined || opener.document.forms[0] == undefined) {
                //document.write('<img src="<%=request.getContextPath()%>/imagens/button_back.gif" title="Voltar" alt="Voltar" border="0" align="middle" onclick="alert(opener)">')
                document.getElementById('voltar').style.display = '';
              } else {
                //document.write('<img src="<%=request.getContextPath()%>/imagens/button_close.gif" title="Fechar" alt="Fechar" border="0" align="middle">')
                document.getElementById('fechar').style.display = '';
              }

              function execute() {
                if (document.getElementById('voltar').style.display == '') {
                  history.back();
                }
                if (document.getElementById('fechar').style.display == '') {
                  self.close();
                }
              }

            </script>
          </button>
        </td>
      </tr>
    </table>
    
  </body>
</html>