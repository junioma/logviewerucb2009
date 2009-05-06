package holders;

import java.text.SimpleDateFormat;
import java.util.Date;

import util.Constantes;

public class Evento {
	public Long id;
	public Date dataHora;
	public String classe;
	public String mensagem;
	public String arquivo;
	public String thread;
	public String logger;
	public int nivel;
	
	public enum EVENTO
	{
		ID,
		DATAHORA,
		CLASSE,
		MENSAGEM,
		ARQUIVO,
		THREAD,
		LOGGER,
		NIVEL
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
}
