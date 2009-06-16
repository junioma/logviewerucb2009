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
package logview.java.dao.reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import logview.java.entity.Evento;
import logview.java.entity.Evento.EVENTO;
import logview.resources.util.constraints.Constantes;

/**Class to represent a the format of parse from log4j PatternLayout .

 * @author Bruno / Junio / Matheus

 * @version 1.0

 * @since 1.0

 */
public class Parse {
	class Coordenada
	{
		private int primeiraCoordenada;
		private int segundaCoordenada;
		public Coordenada(int primeiraCoordenada,int segundaCoordenada)
		{
			this.primeiraCoordenada = primeiraCoordenada;
			this.segundaCoordenada = segundaCoordenada;
		}
		public Coordenada(){
			primeiraCoordenada = 0;
			segundaCoordenada = 0;
		}
		public int getPrimeiraCoordenada() {
			return primeiraCoordenada;
		}
		public void setPrimeiraCoordenada(int primeiraCoordenada) {
			this.primeiraCoordenada = primeiraCoordenada;
		}
		public int getSegundaCoordenada() {
			return segundaCoordenada;
		}
		public void setSegundaCoordenada(int segundaCoordenada) {
			this.segundaCoordenada = segundaCoordenada;
		}
		
	}

	private enum OPTIONS_RETURN_TYPES
	{
		VALID,
		VALID_DATE,
		VALID_ISO8601,
		VALID_ABSOLUTE,
		INVALID,
		INVALID_BUT_IGNORED,
		NOT_IS_A_OPTION,
		UNKNOWN_TYPE_OF_OPTION
	}
	private enum DIRECTION
	{
		LEFT,
		RIGHT
	}
    private String fullParseConversionString;
    private ArrayList<Token> tokenStack;	
	private int numberOfLineFeed;
    private Evento parsingEvent;
    
	private Evento getParsingEvent() {
		return parsingEvent;
	}
	private void setParsingEvent(Evento parsingEvent) {
		this.parsingEvent = parsingEvent;
	}
	public Parse()
	{
		tokenStack = new ArrayList<Token>();
		parsingEvent = new Evento();
		numberOfLineFeed = 0;
	}
	public Parse(String fullParseConversionString)
	{
		if(fullParseConversionString != null)
		{
			this.fullParseConversionString = fullParseConversionString;
		}
		else
		{
			this.fullParseConversionString="";
		}
		tokenStack = new ArrayList<Token>();
		parsingEvent = new Evento();
		//TODO
		numberOfLineFeed = 0;
	}

    public String getFullParseConversionString() {
        return fullParseConversionString;
    }

    public void setFullParseConversionString(String fullParseConversionString) {
        this.fullParseConversionString = fullParseConversionString;
    }
    
	/**
	 *This method validate a line read from the log file with the parse based on the log4j file.
	 *
	 * @param  eventString	A value of event to be validated.
	 * @param  numberOfLineInEvent	The value of the line used at moment.
	 * @param  tempEvent  The object that will receive the parse of a event	
	 * @return Boolean	Return if the value is a valid value.	
	 */
	//TODO: Implementar a validação com base na pilha 
	public Boolean validateLineOfEvent(String eventString,int numberOfLineInEvent,Evento tempEvent)
	{
		int intervaloInicio = 0;
		int intervaloFim = 0;
		int posicaoDoInicioDaMensagemNaString=-1;
		int posicaoDoFimDaMensagemNaString=-1;
		int posicaoAtualNaString=0;
		int posicaoDaMascaraMensagemNaPilha=-1;
		int tokenOfTheMoment = -1;
		Token tokenTemp = null;
		boolean isMatching = false; 
		Coordenada valorDeCoordenada = new Coordenada(intervaloInicio,intervaloFim);
		
		this.setParsingEvent(tempEvent);
		
		findLineRangeAtStack(valorDeCoordenada,numberOfLineInEvent);//Procura o intervalo de tokens referente a essa linha
		intervaloInicio = valorDeCoordenada.getPrimeiraCoordenada();
		intervaloFim	= valorDeCoordenada.getSegundaCoordenada();
		for(int counter=intervaloInicio;counter<=intervaloFim ;counter++)		//percorre a pilha da esquerda para a direita
		{
			//pega cada iten
			tokenOfTheMoment = counter;
			tokenTemp = tokenStack.get(tokenOfTheMoment);
			//
			if(tokenTemp.getTokenType() == EVENTO.LINEFEED)
			{
				break;
			}
			//se for uma msg pega a posição na pilha e sai para que a leitura tenha a ordem invetida
			else if(tokenTemp.getTokenType() == EVENTO.MENSAGEM)
			{
				posicaoDaMascaraMensagemNaPilha=counter;
				posicaoDoInicioDaMensagemNaString=posicaoAtualNaString;
				break;
			}
			else {
				valorDeCoordenada.setPrimeiraCoordenada(posicaoAtualNaString);
				valorDeCoordenada.setSegundaCoordenada(0);
				isMatching = matchMaskWithLine(eventString,valorDeCoordenada,tokenOfTheMoment,DIRECTION.RIGHT);
				posicaoAtualNaString = valorDeCoordenada.getPrimeiraCoordenada();
				if(!isMatching)
				{
					break;
				}
			}
		}
		posicaoAtualNaString = eventString.length();
		if((posicaoDoInicioDaMensagemNaString > -1 && isMatching) && tokenTemp.getTokenType() != EVENTO.LINEFEED) //Significa que há mensagem na string
		{
			//percorre a pilha da direita para a esquerda
			for(int counter = intervaloFim-1 ; counter >= posicaoDaMascaraMensagemNaPilha ; counter--)
			{
				//pega cada iten
				tokenTemp = tokenStack.get(counter);
				//se for uma msg pega a posição na pilha
				if(tokenTemp.getTokenType() == EVENTO.MENSAGEM)
				{
					posicaoDoFimDaMensagemNaString=posicaoAtualNaString;
					valorDeCoordenada.setPrimeiraCoordenada(posicaoDoInicioDaMensagemNaString);
					valorDeCoordenada.setSegundaCoordenada(posicaoDoFimDaMensagemNaString);
					isMatching = matchMaskWithLine(eventString,valorDeCoordenada,posicaoDaMascaraMensagemNaPilha,DIRECTION.RIGHT);
					break;
				}
				else {
					valorDeCoordenada.setPrimeiraCoordenada(posicaoAtualNaString);
					valorDeCoordenada.setSegundaCoordenada(0);
					isMatching = matchMaskWithLine(eventString,valorDeCoordenada,counter,DIRECTION.LEFT);
					posicaoAtualNaString = valorDeCoordenada.getPrimeiraCoordenada();
					if(!isMatching)
					{
						break;
					}
				}
			}
		}
		this.setParsingEvent(null);
		return isMatching;
	}
	
	private boolean matchMaskWithLine(String eventString,Coordenada coordenadaPosicaoAtualNaString, int tokenOfTheMoment, DIRECTION directionOfRead) {
		boolean retorno = false;
		Token tokenTemp = tokenStack.get(tokenOfTheMoment);
		EVENTO tokenTempType = tokenTemp.getTokenType();
		int posicaoAtualNaString = coordenadaPosicaoAtualNaString.getPrimeiraCoordenada();
		if(tokenTempType == EVENTO.PADDING)
		{
			if(directionOfRead == DIRECTION.RIGHT)
			{
				//se a string restante tem pelomenos o mesmo tamanho do padding
				if(eventString.substring(posicaoAtualNaString).length() >= tokenTemp.getTokenValue().length())
				{
					//se o padding é igual a substring dos proximos caracteres que vem apartir da posicaoAtualNaString
					int caractereFinalPadding = tokenTemp.getTokenValue().length()-1;
					String subStringTest = eventString.substring(posicaoAtualNaString,posicaoAtualNaString+caractereFinalPadding +1 );
					//TODO deletar essew linha abaixo
					if(tokenTemp.getTokenValue().equals(subStringTest))
					{
						posicaoAtualNaString += caractereFinalPadding +1 ;
						if(posicaoAtualNaString >= eventString.length())
						{
							posicaoAtualNaString = eventString.length()-1;
						}
						retorno = true;
					}
					else {
						retorno = false;
					}
				}
				else
				{
					retorno = false;
				}
			}
			else
			{
				//se a posição da string está dentro do intervalo da string
				if(posicaoAtualNaString >= 0)
				{
					//se o padding é igual a substring dos proximos caracteres que vem apartir da posicaoAtualNaString
					int tamanhoValorPadding = tokenTemp.getTokenValue().length();
					if(posicaoAtualNaString - tamanhoValorPadding < 0 )
					{
						return false;
					}
					String subStringTest = eventString.substring(posicaoAtualNaString - tamanhoValorPadding,posicaoAtualNaString);
					//TODO deletar essew linha abaixo
					if(tokenTemp.getTokenValue().equals(subStringTest))
					{
						posicaoAtualNaString -= tamanhoValorPadding ;
						if(posicaoAtualNaString<0)
						{
							posicaoAtualNaString = 0;
						}
						retorno = true;
					}
					else {
						retorno = false;
					}
				}
				else
				{
					retorno = false;
				}
			}
		}//fim padding
		else if(tokenTempType == EVENTO.ESCAPE)
		{
			if(directionOfRead == DIRECTION.RIGHT)
			{
				posicaoAtualNaString++;
			}
			else {
				posicaoAtualNaString--;
			}
			retorno = true;
		}
		else if(tokenTempType == EVENTO.DATA || tokenTempType == EVENTO.DATA_ABSOLUTE || 
				tokenTempType == EVENTO.DATA_DATE || tokenTempType == EVENTO.DATA_ISO8601)
		{
			/** 
			 * Reveice the substring of data
			 */
			String dateTempToMatch = "";
			int sizeOfDateValue = 0;
			GregorianCalendar calendar = null;
			
			if(tokenTempType == EVENTO.DATA || tokenTempType == EVENTO.DATA_ISO8601)
			{
				sizeOfDateValue = 23;
			}
			else if(tokenTempType == EVENTO.DATA_DATE)
			{
				sizeOfDateValue = 24;
			}
			else if(tokenTempType == EVENTO.DATA_ABSOLUTE)
			{
				sizeOfDateValue = 12;
			}
			else 
			{
				return false;
			}
			
			if(directionOfRead == DIRECTION.RIGHT)
			{	
				//TODO erro
				//Erro!
				if(posicaoAtualNaString + sizeOfDateValue<=eventString.length())
				{
					dateTempToMatch = eventString.substring(posicaoAtualNaString, posicaoAtualNaString + sizeOfDateValue );
				}
				posicaoAtualNaString+=sizeOfDateValue;
			}
			else {
				if(posicaoAtualNaString<sizeOfDateValue){
					retorno = false;
				}
				eventString.charAt(posicaoAtualNaString);
				eventString.charAt(posicaoAtualNaString - sizeOfDateValue);
				dateTempToMatch = eventString.substring(posicaoAtualNaString - sizeOfDateValue, posicaoAtualNaString);
				posicaoAtualNaString = (posicaoAtualNaString - sizeOfDateValue);
			}
			
			retorno = dateTempToMatch.matches(tokenTemp.getTokenValue());
			if(retorno)
			{
				int year;
				int month;
				int date;
				int hour;
				int minute;
				int second;
				if(tokenTempType == EVENTO.DATA || tokenTempType == EVENTO.DATA_ISO8601)
				{
					year = Integer.parseInt(dateTempToMatch.substring(0, 4));
					month = Integer.parseInt(dateTempToMatch.substring(5, 7))-1; //it is 0 based
					date = Integer.parseInt(dateTempToMatch.substring(8, 10));
					hour = Integer.parseInt(dateTempToMatch.substring(11, 13));
					minute = Integer.parseInt(dateTempToMatch.substring(14,16));
					second = Integer.parseInt(dateTempToMatch.substring(17,19));
					calendar = new GregorianCalendar(year , month , date , hour , minute , second);
				}
				else if(tokenTempType == EVENTO.DATA_DATE)
				{
					String monthString = dateTempToMatch.substring(3,6); 
					if(monthString.equals("Jan"))
					{
						month=0;
					}else if(monthString.equals("Fev"))
					{
						month=1;
					}
					else if(monthString.equals("Mar"))
					{
						month=2;
					}
					else if(monthString.equals("Apr"))
					{
						month=3;
					}
					else if(monthString.equals("May"))
					{
						month=4;
					}
					else if(monthString.equals("Jun"))
					{
						month=5;
					}
					else if(monthString.equals("Jul"))
					{
						month=6;
					}
					else if(monthString.equals("Aug"))
					{
						month=7;
					}
					else if(monthString.equals("Set"))
					{
						month=8;
					}
					else if(monthString.equals("Oct"))
					{
						month=9;
					}
					else if(monthString.equals("Nov"))
					{
						month=10;
					}
					else
					{
						month=11;
					}
					date = Integer.parseInt(dateTempToMatch.substring(0,2));
					year = Integer.parseInt(dateTempToMatch.substring(7,11));
					hour = Integer.parseInt(dateTempToMatch.substring(12,14));
					minute = Integer.parseInt(dateTempToMatch.substring(15,17));
					second = Integer.parseInt(dateTempToMatch.substring(18,20));
					calendar = new GregorianCalendar(year , month , date , hour , minute , second);
					
				}
				else if(tokenTempType == EVENTO.DATA_ABSOLUTE)
				{
					hour = Integer.parseInt(dateTempToMatch.substring(0,2));
					minute = Integer.parseInt(dateTempToMatch.substring(3,5));
					second = Integer.parseInt(dateTempToMatch.substring(6,8));
					
					calendar = new GregorianCalendar(0 , 0 , 0 , hour , minute , second);
				}
				else 
				{
					return false;
				}
				Date dateTemp = calendar.getTime();
				this.getParsingEvent().setDataHora(dateTemp);
				//type of used date. This may used to display the date properly  
				this.getParsingEvent().setDateType(tokenTempType);
				
			}
		}
		else if(tokenTempType == EVENTO.MENSAGEM)
		{
			int inicio = coordenadaPosicaoAtualNaString.getPrimeiraCoordenada();
			int fim = coordenadaPosicaoAtualNaString.getSegundaCoordenada();
			if(inicio<fim)
			{
				this.getParsingEvent().setMensagem(eventString.substring(inicio, fim));
				retorno = true;
			}
			else {
				retorno = false;
			}
			
		}
		else//Outras mascaras
		{
			String tempValue="";
			int savePosicaoAtualNaString = posicaoAtualNaString;
			if(directionOfRead == DIRECTION.RIGHT)
			{
				//Se esse token for o ultimo dos tokens
				if(tokenStack.get(tokenOfTheMoment+1).getTokenType() == EVENTO.LINEFEED)
				{
					tempValue = eventString.substring(posicaoAtualNaString,eventString.length()) ;
					posicaoAtualNaString = savePosicaoAtualNaString+(eventString.length()-1);						
					retorno = true;
				}
				else if(tokenStack.get(tokenOfTheMoment+1).getTokenType() == EVENTO.PADDING) {//se o proximo token for um padding
					for(int counter = posicaoAtualNaString ; counter < eventString.length() ; counter++)
					{
						coordenadaPosicaoAtualNaString.setPrimeiraCoordenada(counter);
						if(matchMaskWithLine(eventString,coordenadaPosicaoAtualNaString, tokenOfTheMoment+1,DIRECTION.RIGHT))
						{
							retorno = true;
							tempValue = eventString.substring(savePosicaoAtualNaString,savePosicaoAtualNaString+(counter-savePosicaoAtualNaString)) ;
							posicaoAtualNaString = savePosicaoAtualNaString+(counter-savePosicaoAtualNaString);						
							break;
						}
					}
				}
				else {
					retorno = false ;
				}

			}
			else{ //direita pra esquerda
				//Se esse token for o ultimo dos tokens
				if(tokenStack.get(tokenOfTheMoment-1).getTokenType() == EVENTO.PADDING) {//se o token anterior
					for(int counter = posicaoAtualNaString ; counter >= 0 ; counter--)
					{
						coordenadaPosicaoAtualNaString.setPrimeiraCoordenada(counter);
						if(matchMaskWithLine(eventString,coordenadaPosicaoAtualNaString, tokenOfTheMoment-1,DIRECTION.LEFT))
						{
							retorno = true;
							tempValue = eventString.substring(counter,posicaoAtualNaString) ;
							posicaoAtualNaString = savePosicaoAtualNaString+(counter-savePosicaoAtualNaString);						
							break;
						}
					}
				}
				else {
					retorno = false ;
				}
			}
			if(tokenTempType == EVENTO.CLASSE)
			{
				this.getParsingEvent().setClasse(tempValue);
			}
			else if(tokenTempType == EVENTO.METODOCHAMADOR)
			{
				this.getParsingEvent().setMetodoChamador(tempValue);
			}
			else if(tokenTempType == EVENTO.ARQUIVO)
			{
				this.getParsingEvent().setArquivo(tempValue);
			}
			else if(tokenTempType == EVENTO.THREAD)
			{
				this.getParsingEvent().setThread(tempValue);
			}
			else if(tokenTempType == EVENTO.LOGGER)
			{
				this.getParsingEvent().setLogger(tempValue);
			}
			else if(tokenTempType == EVENTO.NIVEL)
			{
				Integer valueOfNivel=null;
				if(tempValue.equals("ERROR")){
					valueOfNivel = Constantes.NIVEL_ERROR.intValue();
				}
				else if(tempValue.equals("WARN"))
				{
					valueOfNivel = Constantes.NIVEL_WARNING.intValue();
				}
				else if(tempValue.equals("FINE"))
				{
					valueOfNivel = Constantes.NIVEL_FINE.intValue();
				}
				else if(tempValue.equals("INFO"))
				{
					valueOfNivel = Constantes.NIVEL_INFO.intValue();
				}
				else if(tempValue.equals("DEBUG"))
				{
					valueOfNivel = Constantes.NIVEL_DEBUG.intValue();
				}
				else {
					valueOfNivel = -1;
				}
				
				this.getParsingEvent().setNivel(valueOfNivel);
			}
			else if(tokenTempType == EVENTO.TEMPOGASTO)
			{
				this.getParsingEvent().setTempoGasto(tempValue);
				//Não contemplado
			}
			else if(tokenTempType == EVENTO.LOCALIZACAOCHAMADOR)
			{
				this.getParsingEvent().setLocalizacaoChamador(tempValue);
			}
			else if(tokenTempType == EVENTO.LINHAEVENTO)
			{
				int lineOfEvent = 0;
				try
				{
					lineOfEvent = Integer.parseInt(tempValue);
				}
				catch (Exception e) {
					lineOfEvent = -1;
				}
				this.getParsingEvent().setLinhaEvento(lineOfEvent);
			}
			else if(tokenTempType == EVENTO.METODOCHAMADOR)
			{
				this.getParsingEvent().setMetodoChamador(tempValue);
			}
		}
		coordenadaPosicaoAtualNaString.setPrimeiraCoordenada(posicaoAtualNaString);
		return retorno;
	}
	/**
	 * Este método procura os tokens no intervalo informado, com base no numberOfTheLineAtMoment informado.
	 *
	 * @param	intervalo_inicio	Valor do primeiro token que será prenchido com base no numberOfTheLineAtMoment.
	 * @param	intervalo_fim	Valor do ultimo token da linha informada que será prenchido com base no numberOfTheLineAtMoment.
	 * @param	numberOfTheLineAtMoment		Linha lida do log no momento, pois os logs podem ser muitas linhas do arquivo.
	 * @return 	Boolean	Return		Retorna se houve sucesso ou não	
	 */
	private boolean findLineRangeAtStack(Coordenada valoresDeCoordenada,int  numberOfTheLineAtMoment) {
		boolean retorno = false;
		if(numberOfTheLineAtMoment > 0)
		{
			int pulaLinhasEncontrados = 0;
			valoresDeCoordenada.setSegundaCoordenada(-1);
			valoresDeCoordenada.setPrimeiraCoordenada(0);
			for(int counter=0;counter < tokenStack.size(); counter++)
			{
				if(tokenStack.get(counter).getTokenType() == EVENTO.LINEFEED)
				{
					pulaLinhasEncontrados++;
					if(pulaLinhasEncontrados == numberOfTheLineAtMoment)
					{
						valoresDeCoordenada.setSegundaCoordenada(counter);
						retorno = false;
						break;
					}
					else
					{
						valoresDeCoordenada.setPrimeiraCoordenada(counter + 1) ;
					}
				}
			}
		}
		return retorno;
		
	}
	/**
	 * Validate the log4j mask used at log4j properties.
	 * @return boolean If the log4j mask is valid
	 */
	public boolean validateFullParseConversionString()
	{
		boolean retorno=true;
		int afterLastKnownMask = 0;
		tokenStack = new ArrayList<Token>();
		for(int counter = 0 ; counter<fullParseConversionString.length() ; counter++)
		{
			if(fullParseConversionString.charAt(counter)=='%')
			{		
				if(counter>0)
				{
					String paddingTemp;
					//get the padding
					paddingTemp = fullParseConversionString.substring(afterLastKnownMask,counter);
					//Test if the mask have padding or is the begin of the parse string
					if(paddingTemp.length()>0)
					{
						//check if the padding have at last one char 
						//adiciona um elemento na lista de tokens
							tokenStack.add(new Token(EVENTO.PADDING,paddingTemp,false));
					}
					else {
						if(!(fullParseConversionString.charAt(counter+1)=='n') )
						{
							retorno = false;
							break;
						}
					}
				}
				afterLastKnownMask = checkPercent(counter);
				//something has fail
				if(afterLastKnownMask<=0)
				{
					retorno = false;
					break;
				}
				counter = afterLastKnownMask;
				afterLastKnownMask++;
			}
		}
		return retorno;
	}
	
	
	
	private int checkPercent(int percentPosition) {
		// TODO Auto-generated method stub
		int retorno = 0;
		switch(fullParseConversionString.charAt(percentPosition+1))
		{
			case '%' ://the percent character
			{	
				tokenStack.add(new Token(EVENTO.ESCAPE,EVENTO.ESCAPE.getMascara(),false));
				retorno = percentPosition+1;
				break;
			}
			case 'd' ://date mask 
			{	
				OPTIONS_RETURN_TYPES valid=OPTIONS_RETURN_TYPES.INVALID;
				if(fullParseConversionString.charAt(percentPosition+2)=='{')
				{
					valid = validateOptions(percentPosition+2,'d');
					if(valid==OPTIONS_RETURN_TYPES.VALID || valid==OPTIONS_RETURN_TYPES.VALID_ABSOLUTE || valid==OPTIONS_RETURN_TYPES.VALID_ISO8601 || valid==OPTIONS_RETURN_TYPES.VALID_DATE)
					{
						retorno = fullParseConversionString.indexOf('}', percentPosition+2);
						if(valid == OPTIONS_RETURN_TYPES.VALID)
						{
							tokenStack.add(new Token(EVENTO.DATA,"Alguma EXP",true));
						}
						else if (valid == OPTIONS_RETURN_TYPES.VALID_ABSOLUTE) {
							tokenStack.add(new Token(EVENTO.DATA_ABSOLUTE,"[0-9][0-9]\\:[0-9][0-9]\\:[0-9][0-9]\\,[0-9][0-9][0-9]",true));
						}
						else if (valid == OPTIONS_RETURN_TYPES.VALID_DATE) {
							tokenStack.add(new Token(EVENTO.DATA_DATE,"[0-9][0-9] [a-zA-Z][a-zA-Z][a-zA-Z] [0-9][0-9][0-9][0-9] [0-9][0-9]\\:[0-9][0-9]\\:[0-9][0-9]\\,[0-9][0-9][0-9]",true));
						}
						else if (valid == OPTIONS_RETURN_TYPES.VALID_ISO8601) {
							tokenStack.add(new Token(EVENTO.DATA_ISO8601,"[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9] [0-9][0-9]\\:[0-9][0-9]\\:[0-9][0-9]\\,[0-9][0-9][0-9]",true));
						}
					}
					else if(valid == OPTIONS_RETURN_TYPES.NOT_IS_A_OPTION)
					{
						tokenStack.add(new Token(EVENTO.PADDING,"{",false));
						retorno = percentPosition+1;
					}
					else if(valid == OPTIONS_RETURN_TYPES.INVALID)
					{
						retorno = -1 ;
					}
				}
				else
				{
					tokenStack.add(new Token(EVENTO.DATA,"[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9] [0-9][0-9]\\:[0-9][0-9]\\:[0-9][0-9]\\,[0-9][0-9][0-9]",true));
					retorno = percentPosition+1;
				}
				break;
			}
			case 'C' : 
			case 'c' : 
			{	//class mask
				OPTIONS_RETURN_TYPES valid=OPTIONS_RETURN_TYPES.INVALID;
				if(fullParseConversionString.charAt(percentPosition+2)=='{')
				{
					valid = validateOptions(percentPosition+2,'c');
					if(valid==OPTIONS_RETURN_TYPES.VALID || valid==OPTIONS_RETURN_TYPES.INVALID_BUT_IGNORED)
					{
						retorno = fullParseConversionString.indexOf('}', percentPosition+2);
						if(fullParseConversionString.charAt(percentPosition+1)=='C')
						{
							tokenStack.add(new Token(EVENTO.CLASSE,"",false));	
						}
						else 
						{
							tokenStack.add(new Token(EVENTO.LOGGER,"",false));
						}
					}
					else if(valid == OPTIONS_RETURN_TYPES.NOT_IS_A_OPTION)
					{
						tokenStack.add(new Token(EVENTO.PADDING,"{",false));
						retorno = percentPosition+1;
					}
				}
				else
				{
					if(fullParseConversionString.charAt(percentPosition+1)=='C')
					{
						tokenStack.add(new Token(EVENTO.CLASSE,"",false));	
					}
					else 
					{
						tokenStack.add(new Token(EVENTO.LOGGER,"",false));
					}
					retorno = percentPosition+1;
				}
				break;
			}
			case 'F' : 
			{	//File
				tokenStack.add(new Token(EVENTO.ARQUIVO,"",false));
				retorno = percentPosition+1;
				break;
			}
			case 'l' : 
			{	//
				tokenStack.add(new Token(EVENTO.LOCALIZACAOCHAMADOR,"",false));
				retorno = percentPosition+1;
				break;
			}
			case 'L' : 
			{	//Line
				tokenStack.add(new Token(EVENTO.LINHAEVENTO,"",false));
				retorno = percentPosition+1;
				break;
			}case 'M' : 
			{	//method
				tokenStack.add(new Token(EVENTO.METODOCHAMADOR,"",false));
				retorno = percentPosition+1;
				break;
			}
			case 'p' : 
			{	//priority
				tokenStack.add(new Token(EVENTO.NIVEL,"",false));
				retorno = percentPosition+1;
				break;
			}
			case 'r' : 
			{	//milliseconds
				tokenStack.add(new Token(EVENTO.TEMPOGASTO,"",false));
				retorno = percentPosition+1;
				break;
			}
			case 't' : 
			{	//thread
				tokenStack.add(new Token(EVENTO.THREAD,"",false));
				retorno = percentPosition+1;
				break;
			}
			case 'm' : 
			{	//message
				tokenStack.add(new Token(EVENTO.MENSAGEM,"",false));
				retorno = percentPosition+1;
				break;
			}
			case 'n' : 
			{
				tokenStack.add(new Token(EVENTO.LINEFEED,"",false));
				numberOfLineFeed++;
				retorno = percentPosition+1;
				break;
			}
			default :
			{
				retorno = -1; //nao eh um tipo conhecido , nao suportaremos tipos desconhecidos
			}
			
		}
		return retorno;
	}
	private OPTIONS_RETURN_TYPES validateOptions(int firstPosition, char typeOfOption) {
		// TODO Auto-generated method stub
		OPTIONS_RETURN_TYPES retorno = OPTIONS_RETURN_TYPES.INVALID;
		int lastPosition = fullParseConversionString.indexOf('}', firstPosition);
		if(lastPosition>firstPosition)
		{
			switch(typeOfOption)
			{
				case 'C':	//Test when is a %c or %C the mask
				case 'c':	
				{
					boolean isDigit =  true;
					String substring = fullParseConversionString.substring(firstPosition+1, lastPosition);
					for(int counter = 0;counter<substring.length(); counter ++)
					{
						if(!Character.isDigit(substring.charAt(counter)))
						{
							isDigit=false;
						}
					}
					if(isDigit && substring.length()>0)
					{
						if(Integer.parseInt(substring)>0)
						{
							retorno = OPTIONS_RETURN_TYPES.VALID;	//the only way to the return be TRUE
						}
					}
					else {
						retorno = OPTIONS_RETURN_TYPES.INVALID_BUT_IGNORED; //if the value is invalid, log4j use the default class
					}
					break;
				}
				case 'd':
				{
					String substring = fullParseConversionString.substring(firstPosition+1, lastPosition);
					if(substring.length()>0)
					{
						if(substring.toUpperCase().equals("ABSOLUTE"))
						{
							retorno = OPTIONS_RETURN_TYPES.VALID_ABSOLUTE;	//the only way to the return be TRUE
						}
						else if(substring.toUpperCase().equals("ISO8601"))
						{
							retorno = OPTIONS_RETURN_TYPES.VALID_ISO8601;
						}
						else if(substring.toUpperCase().equals("DATE"))
						{
							retorno = OPTIONS_RETURN_TYPES.VALID_DATE;
						}
						else {
							retorno = OPTIONS_RETURN_TYPES.INVALID;
						}
					}
					else {
						retorno = OPTIONS_RETURN_TYPES.INVALID; //if the value is invalid, log4j use the default class
					}
					break;
				}
				default :
				{
					retorno = OPTIONS_RETURN_TYPES.UNKNOWN_TYPE_OF_OPTION;
				}
			}
		}
		else {
			retorno = OPTIONS_RETURN_TYPES.NOT_IS_A_OPTION; // the bracer is a common character
		}
		return retorno;
	}
	public int getNumberOfLineFeed() {
		return numberOfLineFeed;
	}
	public void setNumberOfLineFeed(int numberOfLineFeed) {
		this.numberOfLineFeed = numberOfLineFeed;
	}

	


}
