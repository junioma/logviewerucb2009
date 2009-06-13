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
