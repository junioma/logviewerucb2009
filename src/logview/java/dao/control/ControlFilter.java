/*
 * Copyright 2003-2008 Universidade Católica de Brasília, Brasil
 *
 * Este arquivo é parte do JMBlogview.
 *
 * O JMBlogview é um software livre; você pode redistribui-lo e/ou modificá-lo
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
package logview.java.dao.control;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logview.java.dao.reader.Directory;
import logview.java.dao.reader.Parse;
import logview.java.dao.reader.SequencialReader;
import logview.java.entity.Evento;
import logview.java.service.Configuration;
import logview.java.view.holders.FiltroPesquisa;
import logview.resources.util.constraints.Constantes;

public class ControlFilter {
	
	public static final List<Evento> gerarListaEventos(FiltroPesquisa filtro) throws IOException{
		//ArrayList que contem a lista de todos os Eventos lidos
		List<Evento> listEventos = new ArrayList<Evento>();
		/*Pega a instancia da classe de configuração*/
		Configuration configuracao = Configuration.getInstance();
		/*Le os dados de configuração do propertie de configuração da aplicação. msg de erro na interface precisa ser implementada*/
		if(configuracao.readDataFromConfigurationProperties())
		{
			System.out.println("********\n    Configuration properties read succeseful at: "+Constantes.PATH_CONFIGURATION_PROPERTIES+"\n********\n");
		}
		else//precisa lançar alguma exception
		{
			System.out.println("Configuration properties NOT READ at: "+Constantes.PATH_CONFIGURATION_PROPERTIES);
			throw new RuntimeException("Configuration properties NOT READ at: "+Constantes.PATH_CONFIGURATION_PROPERTIES);
		}
		/*Isso ainda precisa ser estudado se ficará no properties*/
		/*--------------------------------------------------------------------------------------*/
		Directory diretorio = new Directory(configuracao.getLogsPath());//Directory where data are stored. 
		diretorio.loadDataFromDirectory();//Read the data from directory, only the files descriptors
        Parse parse = new Parse(configuracao.getlog4JPatternLayout()); //Pega as mascaras do log4j
        if(parse.validateFullParseConversionString())
		{
			System.out.println("********\n    The log4j Mask is valid!\n    The mask value: "+parse.getFullParseConversionString()+"\n********\n");
		}
		else//precisa lançar alguma exception
		{
			System.out.println("********\n    The log4j Mask is NOT a valid mask!\n    The mask value: "+parse.getFullParseConversionString()+"\n********\n");
			throw new RuntimeException("The log4j Mask is NOT a valid mask!\n    The mask value: "+parse.getFullParseConversionString());
		}
        System.out.println("====\n     Hosted at: Google Code\n     Quantidade total de regitros:"+SequencialReader.countEventsByParse(diretorio.getDirectoryFiles(), parse, filtro)+"\n====\n");
        /*Le os dados dos dirétorios informados, guardando os evento em uma lista de evento, com base em um parse, filtrando, sendo que traz os registros de x até y */
        SequencialReader.readEventsCheckingByFilter(diretorio.getDirectoryFiles(), listEventos, parse, filtro, 1, 50);
		return listEventos;
	}
	
	public static final List<Evento> gerarListaEventosFiltrados(FiltroPesquisa filtro) throws IOException{
		List<Evento> listEventos = gerarListaEventos(filtro);
		return listEventos;
	}
}
