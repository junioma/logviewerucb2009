/*
 * Copyright 2003-2008 Universidade Católica de Brasília, Brasil
 *
 * Este arquivo é parte do jmblogview.
 *
 * O jmblogview é um software livre; você pode redistribui-lo e/ou modificá-lo
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
