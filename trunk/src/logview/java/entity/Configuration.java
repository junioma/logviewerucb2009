/*
 * Copyright 2003-2008 Universidade Cat�lica de Bras�lia, Brasil
 *
 * Este arquivo � parte do jmblogview.
 *
 * O jmblogview � um software livre; voc� pode redistribui-lo e/ou modific�-lo
 * dentro dos termos da Licen�a P�blica Geral GNU vers�o 2 como publicada
 * pela Funda��o do Software Livre (FSF).
 *
 * Este programa � distribuido na esperan�a que possa ser util, mas SEM
 * NENHUMA GARANTIA; sem uma garantia implicita de ADEQUA��O a qualquer
 * MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU
 * para maiores detalhes.
 *
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o
 * t�tulo "Licenca.txt", junto com este programa, se n�o, escreva para a
 * Funda��o do Software Livre(FSF) Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */

/**
 * 
 */
package logview.java.entity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logview.resources.util.constraints.Constantes;


/**This Singleton Class hold the basic configuration of the application.

 * @author Bruno / Junio / Matheus

 * @version 1.0

 * @since 1.0

 */
public class Configuration {
	private static Configuration uniqueInstance;
	private String logsPath;
	private String log4JPatternLayoutPath;
	private List<String> fileExtension;
	private Configuration(){
		logsPath = null;
		log4JPatternLayoutPath = null;
		fileExtension = new ArrayList<String>();
	}
	/**
	 * This method read the data from configuration.properties. 
	 * This propertie is saved at Constantes.PATH_CONFIGURATION_PROPERTIES. 
	 * @return boolean Return is the data from properties was read.
	 */
	public boolean readDataFromConfigurationProperties ()
	{
		boolean isSucess = true;
		
		InputStream is;   
	    Properties properties = new Properties();   
	       
	    try {
	    	String tempExtension,tempArrayExtension[];
	        is = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constantes.PATH_CONFIGURATION_PROPERTIES);
	        if(is!=null)
	        {
	        	properties.load(is);	
	        }
	        else
	        {
	        	return false;
	        }
	        this.setLogsPath(properties.getProperty("jmblogview.dataPath"));
	        this.setLog4JPatternLayoutPath(properties.getProperty("jmblogview.log4jPath"));
	        tempExtension = properties.getProperty("jmblogview.extensions");
	        if (tempExtension != null && tempExtension.length()>0)
	        {
	        		tempArrayExtension = tempExtension.split(",");
	        		for(int count = 0;count<tempArrayExtension.length;count++)
	        		{
	        			if(tempArrayExtension[count]!=null)
	        			{
	        				this.addFileExtension(tempArrayExtension[count]);
	        			}
	        		}
	        		tempExtension = properties.getProperty("jmblogview.extensionsEmpty");
	        		if(tempExtension != null)
	        		{
	        			if(tempExtension.toLowerCase().equals("yes"))
	        			{
	        				this.addFileExtension("");
	        			}
	        		}
	        }
	        else
	        {
	        	this.addFileExtension("txt");
        		this.addFileExtension("log");
        		this.addFileExtension("");
	        }
	           
	    } 
	    catch (FileNotFoundException ex) {   
	        ex.printStackTrace(); 
	        isSucess = false;
	    } 
	    catch (IOException ex) {   
	        ex.printStackTrace();   
	        isSucess = false;
	    }
	    catch (IllegalArgumentException  ex) {
			ex.printStackTrace(); 
			isSucess = false;
		}
	    catch (Exception e) {
			e.printStackTrace();
			isSucess = false;
		}
	    
		
		return isSucess;
	}
	
	
	
	public String getLogsPath() {
		return logsPath;
	}
	public void setLogsPath(String logsPath) {
		this.logsPath = logsPath;
		logsPath = null;
	}
	public String getLog4JPatternLayoutPath() {
		return log4JPatternLayoutPath;
	}
	public void setLog4JPatternLayoutPath(String log4JPatternLayoutPath) {
		this.log4JPatternLayoutPath = log4JPatternLayoutPath;
		log4JPatternLayoutPath = null;
	}
	
	public List<String> getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(List<String> fileExtension) {
		this.fileExtension = fileExtension;
	}
	/**
	 * This method adds new files extentions.
	 * @param fileExtension A string thats represent a file extension.
	 * @return int A positive number to sucesse os a negative number to a fail.
	 * If the negative number is -1: some value passed to mathoed is null
	 * If the negative number is -2: The collection failed to add the value.
	 */
	public int addFileExtension(String fileExtension){
		int indexAdded=-1;
		if(this.fileExtension!=null && fileExtension!=null){
			if(this.fileExtension.add(fileExtension)){
				indexAdded=fileExtension.indexOf(fileExtension);
			}
			else{
				indexAdded=-2;
			}
		}
		else{
			return -1;
		}
		return indexAdded;
	}
	/**
	 * This method implements the pattern Singleton
	 * @return Configuration return the instance of class
	 */
	public static Configuration getInstance(){
		if (uniqueInstance == null){
			uniqueInstance = new Configuration();
		}
		return uniqueInstance;
	}
	// TODO Rever a leitura de ,properties
	public String getlog4JPatternLayout() {
	    InputStream is;   
	    String result="";
	    Properties properties = new Properties();   
	       
	    try {      
	        is = Thread.currentThread().getContextClassLoader().getResourceAsStream(getLog4JPatternLayoutPath());
	        if(is!=null)
	        {
	        	properties.load(is);
	        }
	        else
	        {
	        	System.out.println("Log4j properties NOT READ at: "+getLog4JPatternLayoutPath());
				throw new RuntimeException("Log4j properties NOT READ at: "+getLog4JPatternLayoutPath());
	        }
	        result = properties.getProperty("log4j.appender.stdout.layout.ConversionPattern");
	           
	    } catch (FileNotFoundException ex) {   
	        ex.printStackTrace();   
	        System.out.println("Log4j properties NOT READ at: "+getLog4JPatternLayoutPath());
			throw new RuntimeException("Log4j properties NOT READ at: "+getLog4JPatternLayoutPath());
	    } catch (IOException ex) {   
	        ex.printStackTrace();   
	    }   
	    catch (Exception e) {
	    	System.out.println("Log4j properties NOT READ at: "+getLog4JPatternLayoutPath());
			throw new RuntimeException("Log4j properties NOT READ at: "+getLog4JPatternLayoutPath());
		}
	       
	    return result; 
	}
	
}
