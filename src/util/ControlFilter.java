package util;


import holders.Evento;
import holders.FiltroPesquisa;
import holders.Parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlFilter {
	
	public static final List<Evento> gerarListaEventos(FiltroPesquisa filtro) throws IOException{
		List<Evento> listEventos = new ArrayList<Evento>();
		Directory diretorio = new Directory("C:/Lucene/JavaDotNet/data");//Directory used by data 
		diretorio.loadDataFromDirectory();//Read the data from directory, only the files descriptors
        Parse parse = new Parse();
		parse.addOrderOfParse(Evento.EVENTO.DATAHORA);
        parse.addOrderOfParse(Evento.EVENTO.ID);
        parse.addOrderOfParse(Evento.EVENTO.NIVEL);
        parse.addOrderOfParse(Evento.EVENTO.CLASSE);
        parse.addOrderOfParse(Evento.EVENTO.MENSAGEM); 
        System.out.println("google:Quantidade total de regitros:"+SequencialReader.countEventsByParse(diretorio.getDirectoryFiles(), parse,filtro));
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
