package logview.java.view.holders;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**Class to represent a the format of parse from log4j PatternLayout .

 * @author Bruno / Junio / Matheus

 * @version 1.0

 * @since 1.0

 */
public class Parse {

	//Those attributes are temporary
	@Deprecated
	private String filter;

    private String fullParseConversionString;
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
