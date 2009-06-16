/*
 * Copyright 2003-2008 Universidade Católica de Brasília, Brasil
 *
 * Este arquivo é parte do JMBlogview.
 *
 * O JMBlogview é um software livre; você pode redistribui-lo e/ou modificá-lo
 * dentro dos termos da Licença Pública Geral GNU versão 2 como publicada
 * pela Fundação do Software Livre (FSF).
 *
 * Este programa é distribuido na esperança que possa ser util, mas SEM
 * NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU, sob o
 * título "Licenca.txt", junto com este programa, se não, escreva para a
 * Fundação do Software Livre(FSF) Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */
package logview.resources.util.constraints;

/**
 * @author matheus
 *
 */
public class Constantes {
	public static Integer NIVEL_ERROR = new Integer(5);
	public static Integer NIVEL_WARNING = new Integer(4);
	public static Integer NIVEL_FINE = new Integer(3);
	public static Integer NIVEL_INFO = new Integer(2);
	public static Integer NIVEL_DEBUG = new Integer(1);

	public static String CAMINHO_IMAGEM_NIVEL = "/Web/imagens/nivel/";
	
	public static String IMAGEM_ERROR = CAMINHO_IMAGEM_NIVEL+"nivel-error.gif";
	public static String IMAGEM_WARNING = CAMINHO_IMAGEM_NIVEL+"nivel-warning.gif";
	public static String IMAGEM_FINE = CAMINHO_IMAGEM_NIVEL+"nivel-fine.gif";
	public static String IMAGEM_INFO = CAMINHO_IMAGEM_NIVEL+"nivel-info.gif";
	public static String IMAGEM_DEBUG = CAMINHO_IMAGEM_NIVEL+"nivel-debug.gif";
	
	public static final String PATH_CONFIGURATION_PROPERTIES = "/configuration.properties";
	

}
