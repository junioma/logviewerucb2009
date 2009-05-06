package util.lucene;

import holders.Evento;
import holders.FiltroPesquisa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlFilter {
	
	public static final List<Evento> gerarListaEventos(FiltroPesquisa filtro) throws IOException{
		List<Evento> listEventos = new ArrayList<Evento>();
		Directory diretorio = new Directory("D:/DESENVOLVIMENTO/workspace/logview");//Directory used by data 
		diretorio.loadDataFromDirectory();//Read the data from directory, only the files descriptors
		//ArrayList<String>arrayListDeEventos = new ArrayList<String>();
        //Parse parse = new Parse("\\[[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9] [0-9][0-9]\\:[0-9][0-9]\\:[0-9][0-9]\\]\\[[0-9][0-9][0-9]\\] \\[[A-Z]*[a-z]*\\] \\[.*\\] \\[.*\\]");
        Parse parse = new Parse();
		parse.addOrderOfParse(Evento.EVENTO.DATAHORA);
        parse.addOrderOfParse(Evento.EVENTO.ID);
        parse.addOrderOfParse(Evento.EVENTO.NIVEL);
        parse.addOrderOfParse(Evento.EVENTO.CLASSE);
        parse.addOrderOfParse(Evento.EVENTO.MENSAGEM); 
        /*parse.setDataHoraParse("[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9] [0-9][0-9]\\:[0-9][0-9]\\:[0-9][0-9]");
        parse.setIdParse("[0-9][0-9][0-9]");
        parse.setNivelParse("[A-Z]*[a-z]*");
        parse.setClasseParse("\\[.*\\]");
        parse.setMensagemParse(".*");*/
        System.out.println("Quantidade total de regitros:"+SequencialReader.countEventsByParse(diretorio.getDirectoryFiles(), parse,filtro));
        //SequencialReader.readEventsAll(diretorio.getDirectoryFiles(), arrayListDeEventos,parse,500,600);
		//SequencialReader.readEventsCheckingByFilter(diretorio.getDirectoryFiles(), arrayListDeEventos, parse, filtro, 2, 5);
        SequencialReader.readEventsCheckingByFilter(diretorio.getDirectoryFiles(), listEventos, parse, filtro, 1, SequencialReader.countEventsByParse(diretorio.getDirectoryFiles(), parse,filtro));
        //CastEvento.manyFromString(arrayListDeEventos, listEventos,parse);
		//arrayListDeEventos = null;
		return listEventos;
	}
	
	public static final List<Evento> gerarListaEventosFiltrados(FiltroPesquisa filtro) throws IOException{
		List<Evento> listEventos = gerarListaEventos(filtro);
		return listEventos;
	}
}
