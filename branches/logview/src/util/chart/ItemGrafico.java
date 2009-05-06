/**
 * 
 */
package util.chart;


/**
 * @author fmluna
 *
 */
public class ItemGrafico {
	private String label;
	
	private String grupo;
	
	private Integer valor;

	
	
	public ItemGrafico(String label, String grupo, Integer valor) {
		super();
		this.label = label;
		this.valor = valor;
		this.grupo = grupo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
}
