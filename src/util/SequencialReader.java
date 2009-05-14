package util;
import holders.Evento;
import holders.FiltroPesquisa;
import holders.Parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**Class to do all the sequencial read work without use index.

 * @author Bruno / Junio / Matheus

 * @version 1.0

 * @since 1.0

 */

class SequencialReader {

	/**
	 * This method read all files received by first parameter and write all lines from these files into the second parameter without criteria.
	 *
	 * @param  arrayListDoDiretorio	This parameter must have a list of all files that will be read.
	 * @param  arrayListDeEventos	This parameter will receive all lines reads from files.
	 * @return void
	 */

	public static int readEventsAll(ArrayList<File> arrayListDoDiretorio,ArrayList<String> arrayListDeEventos,Parse parse,int begin,int end) throws IOException{
		String eventoTemp="";
		int counterOfMaxEvents=-1;
		if(begin==1)//se for o inicio, testa quantos elementos existem
		{
			counterOfMaxEvents=countEvents(arrayListDoDiretorio,parse);
		}
		for(int counter =0,counterOfEvents=1 ; counter <arrayListDoDiretorio.size()&&counterOfEvents<end;counter++ ){
			FileReader arquivo = new FileReader(arrayListDoDiretorio.get(counter));
			BufferedReader buffer = new BufferedReader(arquivo);
			while(((eventoTemp=buffer.readLine())!=null)&&counterOfEvents<end)
			{
                if(parse.validateEvent(eventoTemp))
                {
                	counterOfEvents++;
                	if(counterOfEvents>=begin)
                	{
                		arrayListDeEventos.add(new String(eventoTemp));
                	}
                }
			}
		}
		return counterOfMaxEvents;
	}
	
	/**
	 *This method read all files received by first parameter and write all lines from these files into the second 
	 *parameter using the validation the parse object that was received in the third parameter .
	 *
	 * @param  arrayListDoDiretorio	This parameter must have a list of all files that will be read.
	 * @param  arrayListDeEventos	This parameter will receive all lines reads from files.
	 * @return void
	 */

	public static void readEventsCheckingByFilter(ArrayList<File> arrayListDoDiretorio,List<Evento> arrayListDeEventos,Parse parse,FiltroPesquisa filtro, int begin,int end) throws IOException{
		String eventoStringTemp="";
		//TODO
		for(int counter =0,counterOfEvents=1 ; counter <arrayListDoDiretorio.size()&&counterOfEvents<=end;counter++ ){
			FileReader arquivo = new FileReader(arrayListDoDiretorio.get(counter));
			BufferedReader buffer = new BufferedReader(arquivo);
			while((eventoStringTemp=buffer.readLine())!=null && counterOfEvents<=end)
			{
				//TODO
				if(parse.validateEvent(eventoStringTemp))
                {
                	counterOfEvents++;
                	Evento tempEvent = CastEvento.oneFromString(eventoStringTemp,parse);
                	if(tempEvent!=null)
                	{
	                	if(counterOfEvents>=begin && filtro.atendeFiltroPesquisa(tempEvent))
	                	{
	                		//arrayListDeEventos.add(new String(eventoStringTemp));
	                		arrayListDeEventos.add(tempEvent);
	                	}
                	}
				}
			}
			arquivo.close();
		}

	}
	
	static int countEventsByParse(ArrayList<File> arrayListDoDiretorio,Parse parse,FiltroPesquisa filtro) throws IOException 
	{
		String eventoStringTemp="";
		int counterOfSearchEvents = 0;
		//TODO
		for(int counter =0; counter <arrayListDoDiretorio.size();counter++ ){
			FileReader arquivo = new FileReader(arrayListDoDiretorio.get(counter));
			BufferedReader buffer = new BufferedReader(arquivo);
			while((eventoStringTemp=buffer.readLine())!=null)
			{
				if(parse.validateEvent(eventoStringTemp))
                {
                	Evento tempEvent = CastEvento.oneFromString(eventoStringTemp,parse);
                	if(tempEvent!=null)
                	{
	                	if(filtro.atendeFiltroPesquisa(tempEvent))
	                	{
	                		counterOfSearchEvents++;
	                	}
                	}
				}
			}
			arquivo.close();
		}
		return counterOfSearchEvents;
	}
	
	public static int countEvents(ArrayList<File> arrayListDoDiretorio,Parse parse) throws IOException
	{
		int counterOfEvents=0;
		String eventoTemp="";
		for(int counter =0 ; counter <arrayListDoDiretorio.size();counter++ ){
			FileReader arquivo = new FileReader(arrayListDoDiretorio.get(counter));
			BufferedReader buffer = new BufferedReader(arquivo);
			while((eventoTemp=buffer.readLine())!=null)
			{
				if(parse.validateEvent(eventoTemp))
				{
					counterOfEvents++;
				}
			}
		}
		return counterOfEvents;
	}
}
