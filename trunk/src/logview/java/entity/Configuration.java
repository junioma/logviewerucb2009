
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
	        is = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constantes.PATH_CONFIGURATION_PROPERTIES);
	        properties.load(is); 
	        this.setLogsPath(properties.getProperty("easy2c.dataPath"));
	        this.setLog4JPatternLayoutPath(properties.getProperty("easy2c.log4jPath"));
	        
	        
	           
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
	        //TODO Deve usar o caminho passado pela interface grafica
	        //is = Thread.currentThread().getContextClassLoader().getResourceAsStream(log4JPatternLayoutPath);
	        properties.load(is); 
	        result = properties.getProperty("log4j.appender.stdout.layout.ConversionPattern");
	           
	    } catch (FileNotFoundException ex) {   
	        ex.printStackTrace();   
	    } catch (IOException ex) {   
	        ex.printStackTrace();   
	    }   
	       
	    return result; 
	}
	
}
