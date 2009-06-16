/*
 * Copyright 2003-2008 Universidade Cat�lica de Bras�lia, Brasil
 *
 * Este arquivo � parte do jmblogview.
 *
 * O jmblogview � um software livre; voc� pode redistribui-lo e/ou modific�-lo
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
package logview.java.dao.reader;

import logview.java.view.holders.Evento;
import logview.java.view.holders.Evento.EVENTO;

/**
 * @author junioma
 *This class is the representation of a part of the event;
 *The event is a collection of tokens
 */
public class Token {
	private Evento.EVENTO tokenType;
	private String tokenValue;
	private boolean regularExpression;
	
	public Token(EVENTO tokenType, String tokenValue, boolean isRegularExpression) {
		this.tokenType = tokenType;
		this.tokenValue = tokenValue;
		this.regularExpression = isRegularExpression;
	}
	public boolean isRegularExpression() {
		return this.regularExpression;
	}
	public void setRegularExpression(boolean regularExpression) {
		this.regularExpression = regularExpression;
	}
	public Evento.EVENTO getTokenType() {
		return tokenType;
	}
	public void setTokenType(Evento.EVENTO tokenType) {
		this.tokenType = tokenType;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
}
