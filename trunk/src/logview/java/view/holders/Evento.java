package logview.java.view.holders;

import java.text.SimpleDateFormat;
import java.util.Date;

import logview.resources.util.Constantes;

public class Evento {
	private Long id;
	private Date dataHora;
	private String classe;
	private String mensagem;
	private String arquivo;
	private String thread;
	private String logger;
	private int nivel;
	private String localizacaoChamador;
	private int linhaEvento;
	private String metodoChamador;
	
	
	public enum EVENTO
	{
		ID(""),
		DATAHORA("%d"),
		CLASSE("%C"),
		MENSAGEM("%m"),
		ARQUIVO("%F"),
		THREAD("%t"),
		LOGGER(""),
		NIVEL("%p"),
		LOCALIZACAOCHAMADOR("%l"),
		LINHAEVENTO("%L"),
		METODOCHAMADOR("%M");
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
