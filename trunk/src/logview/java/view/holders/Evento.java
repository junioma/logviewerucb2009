/*
 * Copyright 2003-2008 Universidade Católica de Brasília, Brasil
 *
 * Este arquivo é parte do jmblogview.
 *
 * O jmblogview é um software livre; você pode redistribui-lo e/ou modificá-lo
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

import java.text.SimpleDateFormat;
import java.util.Date;

import logview.resources.util.constraints.Constantes;

public class Evento {
	private Long id;
	private Date dataHora;
	//type of used date. This may used to display the date properly 
	private EVENTO dateType;
	private String classe;
	private String mensagem;
	private String arquivo;
	private String thread;
	private String logger;
	private int nivel;
	private String localizacaoChamador;
	private int linhaEvento;
	private String metodoChamador;
	private String tempoGasto;
	
	
	
	public enum EVENTO
	{
		ID(""),
		DATA("%d"),
		DATA_ABSOLUTE("%d{ABSOLUTE}"),
		DATA_ISO8601("%d{ISO8601}"),
		DATA_DATE("%d{DATE}"),
		CLASSE("%C"),
		MENSAGEM("%m"),
		ARQUIVO("%F"),
		THREAD("%t"),
		LOGGER("%c"),
		NIVEL("%p"),
		TEMPOGASTO("%r"),
		LOCALIZACAOCHAMADOR("%l"),
		LINHAEVENTO("%L"),
		METODOCHAMADOR("%M"),
		PADDING(""),
		LINEFEED("%n"),
		ESCAPE("%");
		private String mascara;
		private EVENTO(String mascara)
		{
			this.mascara = mascara;
		}
		public String getMascara()
		{
			return this.mascara;
		}
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	
	public EVENTO getDateType() {
		return dateType;
	}
	public void setDateType(EVENTO dateType) {
		this.dateType = dateType;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
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
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public String getTempoGasto() {
		return tempoGasto;
	}
	public void setTempoGasto(String tempoGasto) {
		this.tempoGasto = tempoGasto;
	}
	public boolean isNivelDebug(){
		return (getNivel() == Constantes.NIVEL_DEBUG.intValue());
	}
	public boolean isNivelInfo(){
		return (getNivel() == Constantes.NIVEL_INFO.intValue());		
	}
	public boolean isNivelFine(){
		return (getNivel() == Constantes.NIVEL_FINE.intValue());
	}
	public boolean isNivelWarning(){
		return (getNivel() == Constantes.NIVEL_WARNING.intValue());		
	}
	public boolean isNivelError(){
		return (getNivel() == Constantes.NIVEL_ERROR.intValue());		
	}
	
	public String getDescricaoNivel(){
		if(isNivelDebug())
			return "Debug";
		if(isNivelInfo())
			return "Info";
		if(isNivelFine())
			return "Fine";
		if(isNivelWarning())
			return "Warning";
		if(isNivelError())
			return "Error";
		return "";	
	}
	
	public String getData(){
		Date dataHora = getDataHora();		
		if(dataHora == null)
			return "";
		return new SimpleDateFormat("dd/MM/yyyy").format(dataHora);
	}
	
	public String getHora(){
		Date dataHora = getDataHora();
		if(dataHora == null)
			return "";
		return new SimpleDateFormat("HH:mm:ss").format(dataHora);
	}
	
	public String getImagemNivel(){
		if(isNivelDebug()){
			return Constantes.IMAGEM_DEBUG;
		}
		if(isNivelError()){
			return Constantes.IMAGEM_ERROR;
		}
		if(isNivelInfo()){
			return Constantes.IMAGEM_INFO;
		}
		if(isNivelFine()){
			return Constantes.IMAGEM_FINE;
		}
		if(isNivelWarning()){
			return Constantes.IMAGEM_WARNING;
		}

		return "";
	}
	public String getLocalizacaoChamador() {
		return localizacaoChamador;
	}
	public void setLocalizacaoChamador(String localizacaoChamador) {
		this.localizacaoChamador = localizacaoChamador;
	}
	public int getLinhaEvento() {
		return linhaEvento;
	}
	public void setLinhaEvento(int linhaEvento) {
		this.linhaEvento = linhaEvento;
	}
	public String getMetodoChamador() {
		return metodoChamador;
	}
	public void setMetodoChamador(String metodoChamador) {
		this.metodoChamador = metodoChamador;
	}
}
