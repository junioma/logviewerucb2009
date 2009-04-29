package util;

import holders.Evento;
import holders.Parse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CastEvento {
	private CastEvento(){}
	@Deprecated
	public static int manyFromString(ArrayList<String> arrayStringEvento,List<Evento> arrayEvento,Parse parse)
	{
		int counterExtern;
		Evento eventoTemp = null;
		for(counterExtern = 0;counterExtern < arrayStringEvento.size();counterExtern ++)
		{	
			
			StringBuilder stringBuilderTemp = new StringBuilder(arrayStringEvento.get(counterExtern));
			eventoTemp = new Evento();
			List<Evento.EVENTO> ordemDoParse = parse.getOrderOfParse();
			for(int counter = 0 ; counter< ordemDoParse.size();counter++)
			{
				if (ordemDoParse.get(counter)==Evento.EVENTO.ID) 
				{
					eventoTemp.setId(parse.doParseId(stringBuilderTemp));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.DATAHORA)
				{
					//TODO
					Date temp = parse.doParseData(stringBuilderTemp);
					eventoTemp.setDataHora(temp);
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.CLASSE)
				{
					eventoTemp.setClasse(parse.doParseClasse(stringBuilderTemp));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.MENSAGEM)
				{
					eventoTemp.setMensagem(parse.doParseMensagem(stringBuilderTemp));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.NIVEL)
				{
					eventoTemp.setNivel(parse.doParseNivel(stringBuilderTemp));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.ARQUIVO)
				{
					eventoTemp.setArquivo("");
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.THREAD)
				{
					eventoTemp.setThread("");
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.LOGGER)
				{
					eventoTemp.setLogger("");
				}
			}
		}
		return counterExtern;
	}
	
	public static Evento oneFromString(String eventStringForm, Parse parse)
	{
		Evento eventoTemp = null;
		if(eventStringForm!=null)
		{
			StringBuilder stringBuilderTemp = new StringBuilder(eventStringForm);
			eventoTemp = new Evento();
			List<Evento.EVENTO> ordemDoParse = parse.getOrderOfParse();
			for(int counter = 0 ; counter< ordemDoParse.size();counter++)
			{
				if (ordemDoParse.get(counter)==Evento.EVENTO.ID) 
				{
					eventoTemp.setId(parse.doParseId(stringBuilderTemp));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.DATAHORA)
				{
					//TODO
					Date temp = parse.doParseData(stringBuilderTemp);
					eventoTemp.setDataHora(temp);
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.CLASSE)
				{
					eventoTemp.setClasse(parse.doParseClasse(stringBuilderTemp));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.MENSAGEM)
				{
					eventoTemp.setMensagem(parse.doParseMensagem(stringBuilderTemp));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.NIVEL)
				{
					eventoTemp.setNivel(parse.doParseNivel(stringBuilderTemp));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.ARQUIVO)
				{
					eventoTemp.setArquivo(new String(""));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.THREAD)
				{
					eventoTemp.setThread(new String(""));
				}
				else if(ordemDoParse.get(counter)==Evento.EVENTO.LOGGER)
				{
					eventoTemp.setLogger(new String(""));
				}
			}
		}
		return eventoTemp;
	}
	
}
