/*
 * Copyright 2003-2008 Universidade Católica de Brasília, Brasil
 *
 * Este arquivo é parte do Easy2C.
 *
 * O Easy2C é um software livre; você pode redistribui-lo e/ou modificá-lo
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
	//TODO Mudar para usando o readEventsCheckingByFilter passando o valor máximo e valor minimo
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
					if(numberOfLineInEvent>=numberMaxOfLineFeed)// Se o numero necessários de linhas já foi lido e aprovado
					{
						if(tempEvent != null)//Se o evento lido é válido
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
				else {
					numberOfLineInEvent=1;
					tempEvent = new Evento();
				}
				
				if(numberOfLineInEvent>numberMaxOfLineFeed)
                {
					numberOfLineInEvent=1;
					tempEvent = new Evento();
                }
			}//end while
			arquivo.close();
		}//end for

	}
	//TODO Mudar isso pois é utilizado para a paginação
	public static int countEventsByParse(ArrayList<File> arrayListDoDiretorio,Parse parse,FiltroPesquisa filtro) throws IOException 
	{
		int counterOfEvents = 0;
		
		String eventoStringTemp="";
		int numberMaxOfLineFeed = parse.getNumberOfLineFeed();
		int numberOfLineInEvent = 1;
		Evento tempEvent = new Evento() ;
		
		for(int counter =0; counter <arrayListDoDiretorio.size();counter++){
			FileReader arquivo = new FileReader(arrayListDoDiretorio.get(counter));
			BufferedReader buffer = new BufferedReader(arquivo);
			while((eventoStringTemp=buffer.readLine())!=null)
			{
				//TODO Terminar classes envolvidas ness processo
				if(parse.validateLineOfEvent(eventoStringTemp,numberOfLineInEvent,tempEvent))//an event have got many lines
                {
					if(numberOfLineInEvent>=numberMaxOfLineFeed)// Se o numero necessários de linhas já foi lido e aprovado
					{
						if(tempEvent != null)//Se o evento lido é válido
						{	               
		                	if(filtro.atendeFiltroPesquisa(tempEvent))
		                	{
		                		counterOfEvents++;;
		                	}
						}
						tempEvent = new Evento();
					}
					numberOfLineInEvent++;
				}
				else {
					numberOfLineInEvent=1;
					tempEvent = new Evento();
				}
				
				if(numberOfLineInEvent>numberMaxOfLineFeed)
                {
					numberOfLineInEvent=1;
					tempEvent = new Evento();
                }
			}//end while
			arquivo.close();
		}//end for
		
		return counterOfEvents;
	}
	
}
