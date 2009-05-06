/**
 * TableSelectMany
 * componenete com algumas funcionalidades para TableSelectMany
 * parametro construtor obj = objeto do tipo table
 */

//Construtor 
function TableSelectMany(obj){
    this.table = obj;
}

//properties
TableSelectMany.prototype.table = ""; 

//functions

/**
 * cheked
 * verificar se existe algum checkbox marcado na tabela
 */
TableSelectMany.prototype.checked = 
    function checked() {
        var inputs = this.table.getElementsByTagName("input");
        var marcado = false;
        for (var i=0; i < inputs.length; i++){
             if(inputs[i].type == "checkbox"){                            
                if(inputs[i].checked){
                    marcado = true;
                    break;
                }
            }
        }
        return marcado
    }

/**
 * size
 * retorna o tamanho da tabela
 */
TableSelectMany.prototype.size = 
    function size() {
        var inputs = this.table.getElementsByTagName("input");
        var size = 0;
        for (var i=0; i < inputs.length; i++){
             if(inputs[i].type == "checkbox"){                            
                size++;
            }
        }
        return size;
    }

// ---- fim componente table select many ----

// fun��es de uso global    

/**
 * getObject
 * fun��o para recuperar objetos da pagina passando o id simples("como se escreve na jsp")
 * parametro objectId String - id do objeto
 */
function getObject(objectId) {
    var objectList = document.getElementsByTagName("*");

    for(var i=0; i < objectList.length; i++){
        var arrayObjectName = objectList[i].id.split(":");
        var objectName = arrayObjectName[arrayObjectName.length - 1];
        
        if(String(objectName) == String(objectId)){
            return objectList[i];	
        }
        
    }
}

/**
 * excluir
 * Padr�o para exclus�o em tabelas
 * parametro tableName String - nome da tabela contendo os itens para exclus�o
 */
function excluir(tableName) {
    var table = new TableSelectMany(getObject(tableName));
    if (!table.checked()) {
        alert("Não existem itens marcados para exclusão!");
        return false;
    }   
    return confirm("Tem certeza de que deseja excluir permanentemente o(s) item(ns) selecionado(s)?"); 
}

/**
 * excluiritem
 * Padr�o para exclus�o de item em tabelas que n�o exigem marca��o
 */
function excluirItem() {       
    return (confirm("Tem certeza de que deseja excluir esse item?") == true); 
}

/**
 * reabrirItem
 * Padr�o para encerramento em tabelas
 * parametro tableName String - nome da tabela contendo os itens para exclus�o
 */
function reabrirItem() {  
    return (confirm("Tem certeza de que deseja reabrir esse item?") == true); 
}

/**
 * encerrarItem
 * Padr�o para encerramento em tabelas
 * parametro tableName String - nome da tabela contendo os itens para exclus�o
 */
function encerrarItem() {  
    return (confirm("Tem certeza de que deseja encerrar esse item?") == true); 
}

/**
 * encerrar
 * Padr�o para encerramento em tabelas
 * parametro tableName String - nome da tabela contendo os itens para exclus�o
 */
function encerrar(tableName) {
    var table = new TableSelectMany(getObject(tableName));
    if (!table.checked()) {
        alert("Não existem itens marcados para encerrar!");
        return false;
    }   
    return confirm("Tem certeza de que deseja encerrar o(s) item(ns) selecionado(s)?"); 
}

/**
 * cancelar
 * Emite uma confirma��o ao cancelar uma opera��o
 */
function cancelar() {
    return confirm("Tem certeza de que deseja cancelar a opera��o? As informa��es atuais ser�o perdidas!"); 
}

/**
 * newWindow
 * Submete os dados para uma nova janela (para abrir relat�rios)
 */
function newWindow(target) {
    if (target == null || target == undefined) {
        target = '_blank';
    }
    var form = document.forms[0];
    form.target = target;
    form.submit;
    window.setTimeout("form.target = '_self'", 200);
}

/**
 * Submete o formul�rio quando a tecla <ENTER> for pressionada dentro do campo
 * que chama essa fun��o.
 * Colocar a chamada para essa fun��o no evento "onkeypress" do campo.
 */
function submitOnEnter(element, e, buttonId) {
    var keyCode;
    var form = element.form;
    if (e != null && e != undefined && e.which != undefined) {
        keyCode = e.which;
    } else {
        keyCode = event.keyCode;
    }
    // Verifica se a tecla pressionada foi <ENTER>
    if (keyCode == 13) {
        submitForm(form.name, 1, {source: form.name + ':' + buttonId});
    }
}

/**
 *  Permitir somente a digita��o de n�meros inteiro positivo.
 * Colocar a chamada para essa fun��o no evento "onkeypress" do campo. 
 */
function formataNumeroInteiroPositivo(e) {
	if (document.all) // Internet Explorer
		var tecla = event.keyCode;
	else if(document.layers) // Nestcape
		var tecla = e.which;
		if (tecla > 47 && tecla < 58) // numeros de 0 a 9
			return true;
		else
		{
			if (tecla != 8) // backspace
				event.keyCode = 0;
			else
				return true;
		}
}

function submitFormOnEnter(evento, formId, buttonId, partial) {
  if (window.event != (void 0))
    evento = window.event;
  var a3;
  if (evento.srcElement == undefined)
    a3 = evento.target;
  else
    a3 = evento.srcElement;
  if (!a3)
    return true;
  if (a3.tagName=='A')
    return true;
  if ((a3.tagName == 'INPUT') && (a3.type != 'submit') && (a3.type != 'reset')) {
    if (getKeyCode(evento) == 13) {
      if (buttonId != (void 0)) {
        var a4 = new Object();
        a4[buttonId] = buttonId;
        a4['source'] = buttonId;
        var form = document.forms[formId];
        var field = document.getElementById(buttonId);
        if (partial != undefined && partial == true)
          partialSubmit(field, true, form);
        else
          submitForm(formId, 0, a4);
      }
      return false;
    }
  }
  return true;
}

function getKeyCode(evento) {
  if (window.event)
    return window.event.keyCode;
  else if (evento)
    return evento.which;
  return -1;
}

/**
 * Submete parcialmente o formul�rio (AJAX).
 * Faz uma chamada para uma fun��o do ADF Faces.
 */
function partialSubmit(field, validate, form) {
    var formId;

    // indica se vai validar ou n�o o formul�rio
    var valid;
    if (validate != undefined && validate) {
        valid = 1;
    } else {
        valid = 0;
    }
    
    if (form != undefined) {
        formId = form.id;
    } else {
        formId = field.form.id;
    }
    var fieldId = field.id;
    try {
        _adfspu(formId, valid, fieldId, fieldId);
    } catch (e) {
        try {
             //TODO
             // verficar qual 1 ou qual 0 equivale a 'ligar' a valida��o javascript
            _uixspu(formId,1,0,fieldId,0,1,0)
        } catch (e1) {
            _submitPartialChange(formId, valid, fieldId);
        }
    }
    return true;
}

function focusOnFirstField() {
    elements = document.getElementsByTagName("INPUT");
    if (elements != 'undefined' && elements.length > 0) {
        if (!elements[0].disabled) {
            try {
                elements[0].focus();
            } catch (e) {
            }
        }
    }
}

window.onload = focusOnFirstField;