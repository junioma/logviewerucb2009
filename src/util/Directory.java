package util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import entity.Configuration;

/**Class to represent a list of files inside a directory.

 * @author Bruno / Junio / Matheus

 * @version 1.0

 * @since 1.0

 */

class Directory {
	String 		directoryPath;
	File 		directory;
	ArrayList<File>	directoryFiles;
	
	@SuppressWarnings("unused")
	private Directory() {}
	/**
	 * This constructor will build a object with a string received in the first paramter.
	 *
	 * @param  directoryPath	A valid path where all files will be read.
	 */
	Directory(String directoryPath)
	{
		if(directoryPath!=null)
		{
			this.directoryPath = directoryPath;
			directory = new File(this.directoryPath);
		}
		else
		{
			directoryPath	= "";
			directory		= null;
		}
		directoryFiles  = null;
	}
	
	public ArrayList<File> getDirectoryFiles() {
		if (directoryFiles == null)
		{
			directoryFiles = new ArrayList<File>();
		}
		return directoryFiles;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}
	
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
	
	public File getDirectory() 
	{
		if (directory==null && directoryPath.length()>0)
		{
			directory = new File(directoryPath);
		}
		return directory;
	}
	
	/**
	 * This method will check each element from the first paramter and will creat a new reference to file in the second paramter when it is a file 
	 * or call itself when find a directory making a recursive cicle.
	 *
	 * @param  directoryToWalk	This paramter will be used to find all valid files in the path.
	 * @param  arrayListOfFiles	This paramter will be used to store all reference of valid files found in the first paramter.
	 * @return void
	 */
	private void recursiveDirectoryWalker(File directoryToWalk,ArrayList<File> arrayListOfFiles)
	{
		Configuration configuracao = Configuration.getInstance();
		File todosArquivosDoDiretorio[] = directoryToWalk.listFiles();
		for (int counter = 0; counter < todosArquivosDoDiretorio.length; counter++) {
            File f = todosArquivosDoDiretorio[counter];
            if (f.isDirectory()) {
            	recursiveDirectoryWalker(todosArquivosDoDiretorio[counter], arrayListOfFiles);
            } 
            else
            {
            	//TODO testar melhor extenção do arquivo
            	//verifica extenção do arquivo
            	int point = f.getName().lastIndexOf(".");
            	if(point>0&&point!=(f.getName().length()-1))
            	{
            		if(configuracao.getFileExtension().contains(f.getName().substring(point+1, f.getName().length())))
            			arrayListOfFiles.add(todosArquivosDoDiretorio[counter]);
            	}
            	else //must be treated
            	{
            		if(configuracao.getFileExtension().contains(""))
            			arrayListOfFiles.add(todosArquivosDoDiretorio[counter]);
            	}
            }
        }
	}
	
	/**
	 * This method will validate the object directory from this class and fill the object directoryFiles
	 * with all valid files found in path used in constructor method.
	 *
	 * @return void
	 */
	public void loadDataFromDirectory() throws IOException {
		if (directory == null)
		{
			throw new IOException("You must specify a path.");
		}
		if (!directory.exists() || !directory.isDirectory()) {
            throw new IOException(directory + " do not exist or not is a directory.");
        }
		recursiveDirectoryWalker(getDirectory(),getDirectoryFiles());
	}
}
