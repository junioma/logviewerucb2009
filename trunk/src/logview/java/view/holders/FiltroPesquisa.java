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
package logview.java.view.holders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logview.java.entity.Evento;
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

	public boolean atendeFiltroPesquisa(Evento evento){
		return (isAtendeFiltroNivel(evento.getNivel()) 
				&& isAtendeFiltroClasse(evento.getClasse()) 
				&& isAtendeFiltroMensagem(evento.getMensagem())
				&& isAtendeFiltroPeriodo(evento.getDataHora()));		
	}
	/**
	 * @param mensagem2
	 * @return
	 */
	private boolean isAtendeFiltroClasse(String classe) {
		return isAtendeFiltroTexto(classe,this.caseClasse,this.getClasse());
	}
	
	private boolean isAtendeFiltroMensagem(String mensagem) {
		return isAtendeFiltroTexto(mensagem,this.caseMensagem,this.getMensagem());
	}
	/**
	 * @param mensagem2
	 * @return
	 */
	private boolean isAtendeFiltroTexto(String mensagem,boolean caseSensitive,String comparador) {
		if(comparador == null || comparador.equals("")){
			return true;
		}
		else{
			if(mensagem == null)
				return false;
			String msgPesquisa = mensagem;
			String msgFiltro = comparador;
			if(!caseSensitive){
				msgPesquisa = msgPesquisa.toUpperCase();
				msgFiltro = msgFiltro.toUpperCase();
			}
			if(msgPesquisa.indexOf(msgFiltro) == -1)
				return false;
		}		
		return true;
	}
	/**
	 * @param dataHora
	 * @return
	 */
	private boolean isAtendeFiltroPeriodo(Date dataEvento) {
		Date dataInicial = this.getPeriodoInicial();
		Date dataFinal = this.getPeriodoFinal();

		//Validates verification		
		if((dataInicial == null)&&(dataFinal == null))
			return true;

		if(((dataInicial != null) || (dataFinal != null)) && dataEvento == null)
			return false;

		//Interval verification
		if(dataInicial != null && dataFinal != null){
			return ((dataEvento.equals(dataInicial) || dataEvento.after(dataInicial)) && (dataEvento.equals(dataFinal) || dataEvento.before(dataFinal))); 
		}

		if(dataInicial != null && dataFinal == null){
			return ((dataEvento.equals(dataInicial) || dataEvento.after(dataInicial))); 
		}

		if(dataInicial == null && dataFinal != null){
			return ((dataEvento.equals(dataFinal) || dataEvento.before(dataFinal))); 
		}
		return true;

	}

	private boolean isAtendeFiltroNivel(int nivel) {		
		if(this.getNiveis() == null || (this.getNiveis().length == 0)){
			return true;
		}
		if(this.getNiveis() != null && (this.getNiveis().length > 0) && nivel == 0){
			return false;
		}

		Integer[] niveis = this.getNiveis();
		for(int i = 0; i<niveis.length; i++){			
			if(niveis[i].intValue() == nivel){
				return true;
			}
		}
		return false;
	}
}