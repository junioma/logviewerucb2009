package logview.java.dao.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logview.java.view.holders.Evento;
import logview.java.view.holders.FiltroPesquisa;


/**Class to do all the sequential read work without use index.

 * @author Bruno / Junio / Matheus

 * @version 1.0

 * @since 1.0

 */

public class SequencialReader {

	/**
	 * This method read all files received by first parameter and write all lines from these files into the second parameter without criteria.
	 *
	 * @param  arrayListDoDiretorio	This parameter must have a list of all files that will be read.
	 * @param  arrayListDeEventos	This parameter will receive all lines reads from files.
	 * @return void
	 */
	//TODO Mudar para usando o readEventsCheckingByFilter passando o valor m�ximo e valor minimo
	public static int readEventsAll(ArrayList<File> arrayListDoDiretorio,ArrayList<String> arrayListDeEventos,Parse parse,int begin,int end) throws IOException{
		return 0;
	}
	
	/**
	 *This method read all files received by first parameter and write all lines from these files into the second 
	 *parameter using the validation of the parse object that was received in the third parameter and checking by 
	 *filters of the fourth parameter. The values mus be at the range of begin and end.
	 *
	 * @param  arrayListDoDiretorio	This parameter must have a list of all files that will be read.
	 * @param  arrayListDeEventos	This parameter will receive all lines reads from files.
	 * @param  parse	This parameter will be used to validate all lines of file.
	 * @param  filtro	This parameter will be used to validade the user search condition.
	 * @param  begin	This parameter will be used to control the user pagination.
	 * @param  end	This parameter will be used to control the user pagination.
	 * @return void
	 */
	public static void readEventsCheckingByFilter(ArrayList<File> arrayListDoDiretorio,List<Evento> arrayListDeEventos,Parse parse,FiltroPesquisa filtro, int begin,int end) throws IOException{
		String eventoStringTemp="";
		int numberMaxOfLineFeed = parse.getNumberOfLineFeed();
		int numberOfLineInEvent = 1;
		Evento tempEvent = new Evento() ;
		
		for(int counter =0,counterOfEvents=1 ; counter <arrayListDoDiretorio.size()&&counterOfEvents<=end;counter++ ){
			FileReader arquivo = new FileReader(arrayListDoDiretorio.get(counter));
			BufferedReader buffer = new BufferedReader(arquivo);
			while((eventoStringTemp=buffer.readLine())!=null && counterOfEvents<=end)
			{
				//TODO Terminar classes envolvidas ness processo
				if(parse.validateLineOfEvent(eventoStringTemp,numberOfLineInEvent,tempEvent))//an event have got many lines
                {
					if(numberOfLineInEvent>=numberMaxOfLineFeed)// Se o numero necess�rios de linhas j� foi lido e aprovado
					{
						if(tempEvent != null)//Se o evento lido � v�lido
						{
		                	counterOfEvents++;	               
		                	if(counterOfEvents>=begin && filtro.atendeFiltroPesquisa(tempEvent))
		                	{
		                		arrayListDeEventos.add(tempEvent);
		                	}
						}
						tempEvent = new Evento();
					}
					numberOfLineInEvent++;
				}
				
				if(numberOfLineInEvent>=numberMaxOfLineFeed)
                {
					numberOfLineInEvent=1;
					tempEvent = new Evento();
                }
			}//end while
			arquivo.close();
		}//end for

	}
	//TODO Mudar isso pois � utilizado para a pagina��o
	public static int countEventsByParse(ArrayList<File> arrayListDoDiretorio,Parse parse,FiltroPesquisa filtro) throws IOException 
	{
		String eventoStringTemp="";
		int counterOfSearchEvents = 0;
		//TODO configurar para o novo formato
		for(int counter =0; counter <arrayListDoDiretorio.size();counter++ ){
			FileReader arquivo = new FileReader(arrayListDoDiretorio.get(counter));
			BufferedReader buffer = new BufferedReader(arquivo);
			while((eventoStringTemp=buffer.readLine())!=null)
			{
				//TODO configurar para o novo formato
				if(parse.validateLineOfEvent(eventoStringTemp,0,null))
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
				//TODO configurar para o novo formato
				if(parse.validateLineOfEvent(eventoTemp,0,null))
				{
					counterOfEvents++;
				}
			}
		}
		return counterOfEvents;
	}
}
