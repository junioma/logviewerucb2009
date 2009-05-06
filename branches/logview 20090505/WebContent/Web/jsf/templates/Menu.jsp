<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<f:verbatim>

	<script language="JavaScript"
		src="<%=request.getContextPath()%>/Web/template/jscookmenu/JSCookMenu.js"></script>
	<script>
    var myThemeOfficeBase = '<%=request.getContextPath()%>/Web/template/jscookmenu/ThemeOffice/'    
</script>
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/Web/template/jscookmenu/ThemeOffice/theme.css"
		TYPE="text/css">
	<script language="JavaScript"
		src="<%=request.getContextPath()%>/Web/template/jscookmenu/ThemeOffice/theme.js"></script>

	<script language="JavaScript"><!--

var myMenu =
[
    [null, 'Avaliação de Desempenho', '', '', '',
        [null, 'Programa', '', '', '',
        [null, 'Pesquisar Servidor Cadastrado', '<%=request.getContextPath()%>/faces/Web/jsf/manterprograma/PesquisarPrograma.jsp?_clr=1', '_self', ''],
        [null, 'Incluir Servidor', '<%=request.getContextPath()%>/faces/Web/jsf/manterprograma/IncluirPrograma.jsp?_clr=1', '_self', '']
        ],
        [null, 'Etapa/Período', '', '', '',
        [null, 'Pesquisar', '<%=request.getContextPath()%>/faces/Web/jsf/manterperiodo/PesquisarPeriodo.jsp?_clr=1', '_self', ''],
        [null, 'Incluir', '<%=request.getContextPath()%>/faces/Web/jsf/manterperiodo/IncluirPeriodo.jsp?_clr=1', '_self', '']
        ],
        [null, 'Avaliação', '', '', '',
        [null, 'Pesquisar', '<%=request.getContextPath()%>/faces/Web/jsf/manteravaliacao/PesquisarAvaliacao.jsp?_clr=1', '_self', ''],
        [null, 'Incluir', '<%=request.getContextPath()%>/faces/Web/jsf/manteravaliacao/IncluirAvaliacao.jsp?_clr=1', '_self', ''],
        _cmSplit,
        [null, 'Histórico', '<%=request.getContextPath()%>/faces/Web/jsf/manteravaliacao/PesquisarHistoricoServidor.jsp?_clr=1', '_self', '']
        ],
    ], 
   [null, 'Sair', '<%=request.getContextPath()%>/faces/Web/jsf/login/Logoff.jsp', '_self', '']
];
--></script>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr bgcolor="#EFEBDE">
			<td>
			<div id="Menu" style="width: 80%; background: #EFEBDE; padding-left: 60px"></div>
			</td>
		</tr>
	</table>

	<script language="JavaScript"><!--
	cmDraw ('Menu', myMenu, 'hbr', cmThemeOffice, 'ThemeOffice');
--></script>

</f:verbatim>