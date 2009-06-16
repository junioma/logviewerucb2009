/*
 * Copyright 2003-2008 Universidade Cat�lica de Bras�lia, Brasil
 *
 * Este arquivo � parte do JMBlogview.
 *
 * O JMBlogview � um software livre; voc� pode redistribui-lo e/ou modific�-lo
 * dentro dos termos da Licen�a P�blica Geral GNU vers�o 2 como publicada
 * pela Funda��o do Software Livre (FSF).
 *
 * Este programa � distribuido na esperan�a que possa ser util, mas SEM
 * NENHUMA GARANTIA; sem uma garantia implicita de ADEQUA��O a qualquer
 * MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU
 * para maiores detalhes.
 *
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o
 * t�tulo "Licenca.txt", junto com este programa, se n�o, escreva para a
 * Funda��o do Software Livre(FSF) Inc., 51 Franklin Street, Fifth Floor,
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
