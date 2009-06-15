package logview.java.dao.control;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logview.java.dao.reader.Directory;
import logview.java.dao.reader.Parse;
import logview.java.dao.reader.SequencialReader;
import logview.java.entity.Configuration;
import logview.java.view.holders.Evento;
import logview.java.view.holders.FiltroPesquisa;
import logview.resources.util.constraints.Constantes;

public class ControlFilter {
	
	public static final List<Evento> gerarListaEventos(FiltroPesquisa filtro) throws IOException{
		//ArrayList que contem a lista de todos os Eventos lidos
		List<Evento> listEventos = new ArrayList<Evento>();
		/*Pega a instancia da classe de configura��o*/
		Configuration configuracao = Configuration.getInstance();
		/*Le os dados de configura��o do propertie de configura��o da aplica��o. msg de erro na interface precisa ser implementada*/
		if(configuracao.readDataFromConfigurationProperties())
		{
			System.out.println("********\n    Configuration properties read succeseful at: "+Constantes.PATH_CONFIGURATION_PROPERTIES+"\n********\n");
		}
		else//precisa lan�ar alguma exception
		{
			System.out.println("Configuration properties NOT READ at: "+Constantes.PATH_CONFIGURATION_PROPERTIES);
		}
		/*Isso ainda precisa ser estudado se ficar� no properties*/
		/*--------------------------------------------------------------------------------------*/
		Directory diretorio = new Directory(configuracao.getLogsPath());//Directory where data are stored. 
		diretorio.loadDataFromDirectory();//Read the data from directory, only the files descriptors
        Parse parse = new Parse(configuracao.getlog4JPatternLayout()); //Pega as mascaras do log4j
        if(parse.validateFullParseConversionString())
		{
			System.out.println("********\n    The log4j Mask is valid!\n    The mask value: "+parse.getFullParseConversionString()+"\n********\n");
		}
		else//precisa lan�ar alguma exception
		{
			System.out.println("********\n    The log4j Mask is NOT valid!\n    The mask value: "+parse.getFullParseConversionString()+"\n********\n");
		}
        System.out.println("====\n     Hosted at: Google Code\n     Quantidade total de regitros:"+SequencialReader.countEventsByParse(diretorio.getDirectoryFiles(), parse, filtro)+"\n====\n");
        /*Le os dados dos dir�torios informados, guardando os evento em uma lista de evento, com base em um parse, filtrando, sendo que traz os registros de x at� y */
        SequencialReader.readEventsCheckingByFilter(diretorio.getDirectoryFiles(), listEventos, parse, filtro, 1, 50);
		return listEventos;
	}
	
	public static final List<Evento> gerarListaEventosFiltrados(FiltroPesquisa filtro) throws IOException{
		List<Evento> listEventos = gerarListaEventos(filtro);
		return listEventos;
	}
}
