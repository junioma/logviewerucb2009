package util.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class SelectUtil {
	public static List<SelectItem> getNiveisEvento(){
		SelectItem item = null;
		List<SelectItem> listaItens = new ArrayList<SelectItem>();

		for(int i =1; i <=5; i++){
			item = new SelectItem();
			String erro = "";
			switch (i) {
			case 1:
				erro = "DEBUG";
				break;
			case 2:
				erro = "INFO";
				break;
			case 3:
				erro = "FINE";
				break;
			case 4:
				erro = "WARNING";
				break;
			case 5:
				erro = "ERROR";
				break;
			default:
				break;
			}

			item.setLabel(erro);
			item.setValue(i);
			listaItens.add(item);
		}

		return listaItens;
	}
}
