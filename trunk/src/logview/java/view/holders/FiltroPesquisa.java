package logview.java.view.holders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logview.resources.util.constraints.Constantes;

public class FiltroPesquisa {
	public Integer[] niveis;
	public Date periodoInicial;
	public Date periodoFinal;
	public String classe;
	public String mensagem;
	public String arquivo;
	public String thread;
	public String logger;
	public boolean caseMensagem;
	public boolean caseClasse;
	public boolean caseThread;
	public boolean caseLogger;
	public boolean caseArquivo;
	
	public boolean isCaseMensagem() {
		return caseMensagem;
	}
	public void setCaseMensagem(boolean caseMensagem) {
		this.caseMensagem = caseMensagem;
	}
	public boolean isCaseClasse() {
		return caseClasse;
	}
	public void setCaseClasse(boolean caseClasse) {
		this.caseClasse = caseClasse;
	}
	public Integer[] getNiveis() {
		return niveis;
	}
	public void setNiveis(Integer[] niveis) {
		this.niveis = niveis;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public Date getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	public Date getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public String getArquivo() {
		return arquivo;
	}
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	public String getThread() {
		return thread;
	}
	public void setThread(String thread) {
		this.thread = thread;
	}
	public String getLogger() {
		return logger;
	}
	public void setLogger(String logger) {
		this.logger = logger;
	}
	public boolean isCaseThread() {
		return caseThread;
	}
	public void setCaseThread(boolean caseThread) {
		this.caseThread = caseThread;
	}
	public boolean isCaseLogger() {
		return caseLogger;
	}
	public void setCaseLogger(boolean caseLogger) {
		this.caseLogger = caseLogger;
	}
	public boolean isCaseArquivo() {
		return caseArquivo;
	}
	public void setCaseArquivo(boolean caseArquivo) {
		this.caseArquivo = caseArquivo;
	}
	
	public boolean isFiltroNivelError(){
		return verificarExisteNivel(Constantes.NIVEL_ERROR);
	}
	
	public boolean isFiltroNivelWarning(){
		return verificarExisteNivel(Constantes.NIVEL_WARNING);
	}
	public boolean isFiltroNivelFine(){
		return verificarExisteNivel(Constantes.NIVEL_FINE);
	}
	public boolean isFiltroNivelInfo(){
		return verificarExisteNivel(Constantes.NIVEL_INFO);
	}
	public boolean isFiltroNivelDebug(){
		return verificarExisteNivel(Constantes.NIVEL_DEBUG);
	}
	
	/**
	 * @param i
	 * @return
	 */
	private boolean verificarExisteNivel(Integer i) {
		if(getNiveis() == null)
			return false;
		List<Integer> listaNiveis = new ArrayList<Integer>();
		for(Integer nivel : getNiveis()){
			listaNiveis.add(nivel);
		}
		return listaNiveis.contains(i);
	}
	
	/**
	 * 
	 */
	//TODO Adicionar filtro por data
	
	public boolean atendeFiltroPesquisa(Evento evento){
		boolean adicionar = true;
		if(adicionar){
			if(this.getNiveis() != null){
				for(Integer nivel : this.getNiveis()){
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
			if(evento.getMensagem() != null && this.getMensagem() != null && !this.getMensagem().trim().equals("")){
				String mensagemEvento = evento.getMensagem();
				String mensagemFiltro = this.getMensagem();
				if(!this.isCaseMensagem()){
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
			if(evento.getClasse() != null && this.getClasse() != null && !this.getClasse().trim().equals("")){
				String classeEvento = evento.getClasse();
				String classeFiltro = this.getClasse();
				if(!this.isCaseClasse()){
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
			if(evento.getArquivo() != null && this.getArquivo() != null && !this.getArquivo().trim().equals("")){
				String arquivoEvento = evento.getArquivo();
				String arquivoFiltro = this.getArquivo();
				if(!this.isCaseArquivo()){
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
			if(evento.getThread() != null && this.getThread() != null && !this.getThread().trim().equals("")){
				String threadEvento = evento.getThread();
				String threadFiltro = this.getThread();
				if(!this.isCaseThread()){
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
			if(evento.getLogger() != null && this.getLogger() != null && !this.getLogger().trim().equals("")){
				String loggerEvento = evento.getLogger();
				String loggerFiltro = this.getLogger();
				if(!this.isCaseLogger()){
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
