package logview.java.dao.reader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logview.java.view.holders.Evento;
import logview.java.view.holders.Evento.EVENTO;

/**Class to represent a the format of parse from log4j PatternLayout .

 * @author Bruno / Junio / Matheus

 * @version 1.0

 * @since 1.0

 */
public class Parse {

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
	//Those attributes are temporary
	@Deprecated
	private String filter;

    private String fullParseConversionString;
    
    private ArrayList<Token> tokenStack;
	@Deprecated
	private String idParse;
	@Deprecated
	private String dataHoraParse;
	@Deprecated
	private String classeParse;
	@Deprecated
	private String mensagemParse;
	@Deprecated
	private String arquivoParse;
	@Deprecated
	private String loggerParse;
	@Deprecated
	private String nivelParse;
	private List<Evento.EVENTO> orderOfParse;
    

	public Parse()
	{
		tokenStack = new ArrayList<Token>();
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
	}

    public String getFullParseConversionString() {
        return fullParseConversionString;
    }

    public void setFullParseConversionString(String fullParseConversionString) {
        this.fullParseConversionString = fullParseConversionString;
    }



    public void setArquivoParse(String arquivoParse) {
        this.arquivoParse = arquivoParse;
    }

    public void setClasseParse(String classeParse) {
        this.classeParse = classeParse;
    }

    public void setDataHoraParse(String dataHoraParse) {
        this.dataHoraParse = dataHoraParse;
    }

    public void setIdParse(String idParse) {
        this.idParse = idParse;
    }

    public void setLoggerParse(String loggerParse) {
        this.loggerParse = loggerParse;
    }

    public void setMensagemParse(String mensagemParse) {
        this.mensagemParse = mensagemParse;
    }

    public void setNivelParse(String nivelParse) {
        this.nivelParse = nivelParse;
    }
    
    public String getArquivoParse() {
        return arquivoParse;
    }

    public String getClasseParse() {
        return classeParse;
    }

    public String getDataHoraParse() {
        return dataHoraParse;
    }

    public String getIdParse() {
        return idParse;
    }

    public String getLoggerParse() {
        return loggerParse;
    }

    public String getMensagemParse() {
        return mensagemParse;
    }

    public String getNivelParse() {
        return nivelParse;
    }

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	public List<Evento.EVENTO> getOrderOfParse() {
		return orderOfParse;
	}
	public void addOrderOfParse(Evento.EVENTO orderOfParse) {
		if(this.orderOfParse == null)
		{
			this.orderOfParse = new ArrayList<Evento.EVENTO>();
		}
			this.orderOfParse.add(orderOfParse);
	}
	public void setOrderOfParse(List<Evento.EVENTO> orderOfParse) {
		this.orderOfParse = orderOfParse;
	}
	
	/**
	 *This method validate the received string with the declared filter.
	 *
	 * @param  event	A value of event to be validated.
	 * @return Boolean	Return if is a valid value	
	 */
	
	public Boolean validateEvent(String event)
	{
		//return event.matches(fullParse);
		boolean open = false;
		int 	amountOfMatch=0;
		for(int counter=0;counter<event.length();counter++)
		{			
			if(event.charAt(counter)=='[')
			{
				open= true;
			}
			else if (event.charAt(counter)==']') {
				if(open)
				{
					open=false;
					amountOfMatch++;
				}
			}
		}
		return amountOfMatch >= orderOfParse.size()? true:false;
	}
	
	public boolean validateFullParseConversionString()
	{
		boolean retorno=true;
		int afterLastKnownMask = 0;
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
						retorno = false;
						break;
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
							tokenStack.add(new Token(EVENTO.DATA_ABSOLUTE,"Alguma EXP",true));
						}
						else if (valid == OPTIONS_RETURN_TYPES.VALID_DATE) {
							tokenStack.add(new Token(EVENTO.DATA_DATE,"Alguma EXP",true));
						}
						else if (valid == OPTIONS_RETURN_TYPES.VALID_ISO8601) {
							tokenStack.add(new Token(EVENTO.DATA_ISO8601,"Alguma EXP",true));
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
						if(valid == OPTIONS_RETURN_TYPES.VALID)
						{
							if(fullParseConversionString.charAt(percentPosition+1)=='C')
							{
								tokenStack.add(new Token(EVENTO.CLASSE,"",false));	
							}
							else 
							{
								tokenStack.add(new Token(EVENTO.LOGGER,"",false));
							}
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
	public Long doParseId(StringBuilder eventoString)
	{
		boolean valido=true;
		String retorno="";
		retorno=findData(eventoString);
		for(int counter=0;counter<retorno.length();counter++)
		{
			if (!Character.isDigit(retorno.charAt(counter)))
			{
				valido=false;
			}
		}
		if(valido)
		{
			return Long.parseLong(retorno);
		}
		else
		{
			return Long.parseLong("0");
		}
		
	}
	
	public Date doParseData(StringBuilder eventoString) 
	{
		//TODO Concertar parse de data
		Date dataTemp = new Date(); 
		String tempString = findData(eventoString);
		dataTemp.setYear(Integer.parseInt(tempString.substring(0,4)));
		dataTemp.setMonth(Integer.parseInt(tempString.substring(5,7)));
		dataTemp.setDate(Integer.parseInt(tempString.substring(8,10)));
		dataTemp.setHours(Integer.parseInt(tempString.substring(11,13)));
		dataTemp.setMinutes(Integer.parseInt(tempString.substring(14,16)));
		dataTemp.setSeconds(Integer.parseInt(tempString.substring(17,19)));
		return dataTemp;
		
	}
	public String doParseClasse(StringBuilder eventoString)
	{
		String retorno="";
		retorno=findData(eventoString);
		if(retorno!=null)
		{
			return retorno;
		}
		else
		{
			return "";
		}
	}
	
	public String doParseMensagem(StringBuilder eventoString)
	{
		//TODO
		String retorno="";
		int abreColchete,fechaColchete;
		abreColchete = 0; //colchete da classe, inicializando com a posição mais proxima
		while (abreColchete<eventoString.length())
		{
			
			if(eventoString.charAt(abreColchete)=='[')
			{
				break;
			}
			abreColchete++;
		}
		fechaColchete = abreColchete; //inicializa a busca após o primeiro colchete
		for(int counter = abreColchete;counter < eventoString.length() ; counter++)
		{
			if(eventoString.charAt(counter)==']')
			{
				fechaColchete=counter;
			}
		}
		retorno=eventoString.substring(abreColchete+1, fechaColchete);
		eventoString.replace(abreColchete, fechaColchete+1, "");
		
		if(retorno!=null)
		{
			return retorno;
		}
		else
		{
			return "";
		}
	}
	
	public String doParseArquivo(StringBuilder eventoString)
	{
		String retorno="";
		retorno=findData(eventoString);
		if(retorno!=null)
		{
			return retorno;
		}
		else
		{
			return "";
		}
	}
	
	public String doParseLogger(StringBuilder eventoString)
	{
		String retorno="";
		retorno=findData(eventoString);
		if(retorno!=null)
		{
			return retorno;
		}
		else
		{
			return "";
		}
	}
	
	public int doParseNivel(StringBuilder eventoString)
	{
		String stringTemp="";
		int nivel=0;
		stringTemp=findData(eventoString);
		if(stringTemp.toUpperCase().contains("DEBUG")) 
		{
			nivel= 1;
		}
		else if(stringTemp.toUpperCase().contains("INFO")) 
		{
			nivel= 2;
		}
		else if(stringTemp.toUpperCase().contains("FINE")) 
		{
			nivel= 3;
		}
		else if(stringTemp.toUpperCase().contains("WARNING")) 
		{
			nivel= 4;
		}
		else if(stringTemp.toUpperCase().contains("ERROR")) 
		{
			nivel= 5;
		}
		return nivel;
	}
	private String findData(StringBuilder stringToSearch)
	{
		int abreColchete,fechaColchete;
		String retorno="";
		abreColchete = 0; //colchete da classe, inicializando com a posição mais proxima
		while (abreColchete<stringToSearch.length())
		{
			
			if(stringToSearch.charAt(abreColchete)=='[')
			{
				break;
			}
			abreColchete++;
		}
		fechaColchete = abreColchete; //inicializa a busca após o primeiro colchete
		while (fechaColchete<stringToSearch.length())
		{
			
			if(stringToSearch.charAt(fechaColchete)==']')
			{
				break;
			}
			fechaColchete++;
		}
		retorno=stringToSearch.substring(abreColchete+1, fechaColchete);
		stringToSearch.replace(abreColchete, fechaColchete+1, "");
		//System.out.println(stringToSearch);
		return retorno;
	}
	


}
