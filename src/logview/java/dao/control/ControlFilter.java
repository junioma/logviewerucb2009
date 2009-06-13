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

public class ControlFilter {
	
	public static final List<Evento> gerarListaEventos(FiltroPesquisa filtro) throws IOException{
		List<Evento> listEventos = new ArrayList<Evento>();
		/*Deve ser configurada previamente com dados da interface web---------------------------*/
		//TODO Colocar a configuraï¿½ï¿½o da Configuration em seu devido lugar na interface do usuï¿½rio
		Configuration configuracao = Configuration.getInstance();
		configuracao.setLogsPath("C:/Lucene/JavaDotNet/data/new");
		configuracao.addFileExtension("txt");
		//TODO os caminhos da configuraço devem vir da interface grafica
		configuracao.setLog4JPatternLayoutPath("/log4j.properties");
		//configuracao.addFileExtension("log");
		//configuracao.addFileExtension("l4j");
		configuracao.addFileExtension(new String(""));
		/*--------------------------------------------------------------------------------------*/
		Directory diretorio = new Directory(configuracao.getLogsPath());//Directory used by data 
		diretorio.loadDataFromDirectory();//Read the data from directory, only the files descriptors
        Parse parse = new Parse(configuracao.getlog4JPatternLayout()); //Pega as mascaras do log4j
        System.out.println("google:Mascara do log4j:"+parse.getFullParseConversionString()+"\n Ela é:"+parse.validateFullParseConversionString());

        System.out.println(Evento.EVENTO.DATA.ordinal()+" "+Evento.EVENTO.DATA.name()+" "+Evento.EVENTO.DATA.getMascara()+" google:Quantidade total de regitros:");
        SequencialReader.readEventsCheckingByFilter(diretorio.getDirectoryFiles(), listEventos, parse, filtro, 1, 50);
        //CastEvento.manyFromString(arrayListDeEventos, listEventos,parse);;
		return listEventos;
	}
	
	public static final List<Evento> gerarListaEventosFiltrados(FiltroPesquisa filtro) throws IOException{
		List<Evento> listEventos = gerarListaEventos(filtro);
		return listEventos;
	}
}
