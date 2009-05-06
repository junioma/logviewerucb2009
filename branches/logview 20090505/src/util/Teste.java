package util;

import holders.Evento;
import holders.FiltroPesquisa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Deprecated
public class Teste {
	public static final int NUMERO_EVENTOS = 4000;
	
	public static final List<Evento> gerarListaEventos(){
		List<Evento> listEventos = new ArrayList<Evento>();
//		try {
//			List<Evento> teste = ControlFilter.gerarListaEventos(null);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Evento evento = null;
		int nivel = 1;
		//DEBUG
		//INFO
		//FINE
		//WARNING
		//ERROR
		
		for(int i=0;i<NUMERO_EVENTOS;i++){
			evento = new Evento();
			evento.setArquivo("C:/teste/evento"+i+".log");
			evento.setClasse("java.util.Teste.log"+i);
			//Carga básica de horario
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(new Date(System.currentTimeMillis()));
			calendario.add(Calendar.DAY_OF_MONTH, i);			
			evento.setDataHora(calendario.getTime());
			
			evento.setId(new Long(i));
			evento.setMensagem("Teste "+i+" exceção do número " + new Date(System.currentTimeMillis()));
			evento.setThread("Thread"+i);
			evento.setNivel(nivel);
			nivel++;
			if(nivel == 6)
				nivel = 1;
			listEventos.add(evento);
		}
		return listEventos;
	}
	
	public static final List<Evento> gerarListaEventosFiltrados(FiltroPesquisa filtro){
		List<Evento> listEventos = gerarListaEventos();
		List<Evento> listEventosFiltrados = new ArrayList<Evento>();
		if(listEventos != null && filtro != null){			
			for(Evento evento : listEventos) {
							
				if(atendeFiltroPesquisa(evento,filtro))
					listEventosFiltrados.add(evento);
			}
		}
		
		return listEventosFiltrados;
	}

	private static boolean atendeFiltroPesquisa(Evento evento,FiltroPesquisa filtro){
		boolean adicionar = true;
		if(adicionar){
			if(filtro.getNiveis() != null){
				for(Integer nivel : filtro.getNiveis()){
					if(nivel.intValue() == evento.getNivel()){
						adicionar = true;
						break;
					}
					else{
						adicionar = false;
					}
				}
			}
		}
		
		if(adicionar){
			if(evento.getMensagem() != null && filtro.getMensagem() != null && !filtro.getMensagem().trim().equals("")){
				String mensagemEvento = evento.getMensagem();
				String mensagemFiltro = filtro.getMensagem();
				if(!filtro.isCaseMensagem()){
					mensagemEvento = mensagemEvento.toUpperCase();
					mensagemFiltro = mensagemFiltro.toUpperCase();
				}
				if(mensagemEvento.indexOf(mensagemFiltro) == -1)
					adicionar = false;
				else
					adicionar = true;
				
			}
		}
		
		if(adicionar){
			if(evento.getClasse() != null && filtro.getClasse() != null && !filtro.getClasse().trim().equals("")){
				String classeEvento = evento.getClasse();
				String classeFiltro = filtro.getClasse();
				if(!filtro.isCaseClasse()){
					classeEvento = classeEvento.toUpperCase();
					classeFiltro = classeFiltro.toUpperCase();
				}
				if(classeEvento.indexOf(classeFiltro) == -1)
					adicionar = false;
				else
					adicionar = true;
				
			}
		}
		
		if(adicionar){
			if(evento.getArquivo() != null && filtro.getArquivo() != null && !filtro.getArquivo().trim().equals("")){
				String arquivoEvento = evento.getArquivo();
				String arquivoFiltro = filtro.getArquivo();
				if(!filtro.isCaseArquivo()){
					arquivoEvento = arquivoEvento.toUpperCase();
					arquivoFiltro = arquivoFiltro.toUpperCase();
				}
				if(arquivoEvento.indexOf(arquivoFiltro) == -1)
					adicionar = false;
				else
					adicionar = true;
				
			}
		}
		
		if(adicionar){
			if(evento.getThread() != null && filtro.getThread() != null && !filtro.getThread().trim().equals("")){
				String threadEvento = evento.getThread();
				String threadFiltro = filtro.getThread();
				if(!filtro.isCaseThread()){
					threadEvento = threadEvento.toUpperCase();
					threadFiltro = threadFiltro.toUpperCase();
				}
				if(threadEvento.indexOf(threadFiltro) == -1)
					adicionar = false;
				else
					adicionar = true;
				
			}
		}
		
		if(adicionar){
			if(evento.getLogger() != null && filtro.getLogger() != null && !filtro.getLogger().trim().equals("")){
				String loggerEvento = evento.getLogger();
				String loggerFiltro = filtro.getLogger();
				if(!filtro.isCaseLogger()){
					loggerEvento = loggerEvento.toUpperCase();
					loggerFiltro = loggerFiltro.toUpperCase();
				}
				if(loggerEvento.indexOf(loggerFiltro) == -1)
					adicionar = false;
				else
					adicionar = true;
				
			}
		}
		
		
		return adicionar;
	}
	
	
}
