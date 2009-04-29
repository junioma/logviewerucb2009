package holders;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

public class Evento {
	private Long id;
	private Date dataHora;
	private String classe;
	private String mensagem;
	private String arquivo;
	private String thread;
	private String logger;
	private int nivel;
	
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
		return (getNivel() == 1);
	}
	public boolean isNivelInfo(){
		return (getNivel() == 2);		
	}
	public boolean isNivelFine(){
		return (getNivel() == 3);
	}
	public boolean isNivelWarning(){
		return (getNivel() == 4);		
	}
	public boolean isNivelError(){
		return (getNivel() == 5);		
	}
	
	@Transactional
	public String getData(){
		Date dataHora = getDataHora();		
		if(dataHora == null)
			return "";
		return new SimpleDateFormat("dd/MM/yyyy").format(dataHora);
	}
	
	@Transactional
	public String getHora(){
		Date dataHora = getDataHora();
		if(dataHora == null)
			return "";
		return new SimpleDateFormat("HH:mm:ss").format(dataHora);
	}
}
