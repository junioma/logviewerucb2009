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
		configuracao.setLogsPath("C:/Lucene/JavaDotNet/data");
		configuracao.addFileExtension("txt");
		//TODO os caminhos da configuraï¿½ï¿½o devem vir da interface grafica
		configuracao.setLog4JPatternLayoutPath("/log4j.properties");
		//configuracao.addFileExtension("log");
		//configuracao.addFileExtension("l4j");
		configuracao.addFileExtension(new String(""));
		/*--------------------------------------------------------------------------------------*/
		Directory diretorio = new Directory(configuracao.getLogsPath());//Directory used by data 
		diretorio.loadDataFromDirectory();//Read the data from directory, only the files descriptors
        Parse parse = new Parse(configuracao.getlog4JPatternLayout()); //Pega as mascaras do log4j
        System.out.println("google:Mascara do log4j:"+parse.getFullParseConversionString()+"\n Ela é:"+parse.validateFullParseConversionString());
		parse.addOrderOfParse(Evento.EVENTO.DATAHORA);
        parse.addOrderOfParse(Evento.EVENTO.ID);
        parse.addOrderOfParse(Evento.EVENTO.NIVEL);
        parse.addOrderOfParse(Evento.EVENTO.CLASSE);
        parse.addOrderOfParse(Evento.EVENTO.MENSAGEM); 
        System.out.println(Evento.EVENTO.DATAHORA.ordinal()+" "+Evento.EVENTO.DATAHORA.name()+" "+Evento.EVENTO.DATAHORA.getMascara()+" google:Quantidade total de regitros:"+SequencialReader.countEventsByParse(diretorio.getDirectoryFiles(), parse,filtro));
        //SequencialReader.readEventsAll(diretorio.getDirectoryFiles(), arrayListDeEventos,parse,500,600);
		//SequencialReader.readEventsCheckingByFilter(diretorio.getDirectoryFiles(), arrayListDeEventos, parse, filtro, 2, 5);
        SequencialReader.readEventsCheckingByFilter(diretorio.getDirectoryFiles(), listEventos, parse, filtro, 1, SequencialReader.countEventsByParse(diretorio.getDirectoryFiles(), parse,filtro));
        //CastEvento.manyFromString(arrayListDeEventos, listEventos,parse);;
		return listEventos;
	}
	
	public static final List<Evento> gerarListaEventosFiltrados(FiltroPesquisa filtro) throws IOException{
		List<Evento> listEventos = gerarListaEventos(filtro);
		return listEventos;
	}
}
