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

/**
 * 
 */
package logview.resources.util.chart;


/**
 * @author fmluna
 *
 */
public class ItemGrafico {
	private String label;
	
	private String grupo;
	
	private Integer valor;

	
	
	public ItemGrafico(String label, String grupo, Integer valor) {
		super();
		this.label = label;
		this.valor = valor;
		this.grupo = grupo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
}
