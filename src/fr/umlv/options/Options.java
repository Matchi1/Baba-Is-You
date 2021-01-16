package fr.umlv.options;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Manages the options from the run command
 */
public class Options {
	static final String defaultFile = "worlds/default-file.txt";
	
	/**
	 * Browse all the files in the specified String representation
	 * of the directory path
	 * @param dir the specified String representation of the directory path
	 * @return a List of the path of the files in the specified directory
	 * @throws IOException
	 */
	public static List<Path> levels(String dir) throws IOException{
		Objects.requireNonNull(dir);
		Path loadFilesPath = FileSystems.getDefault().getPath(dir);
		var lstPath = Files.walk(loadFilesPath, 1).collect(Collectors.toList());
		if(lstPath.contains(loadFilesPath))
			lstPath.remove(loadFilesPath);
		return lstPath;
	}
	
	/**
	 * Browse the file with the specified string representation of its path
	 * @param path the specified string representation of its path 
	 * @return a List of the path of the file
	 */
	public static List<Path> level(String path){
		Objects.requireNonNull(path);
		return List.of(FileSystems.getDefault().getPath(path));
	}
	
	/**
	 * Manages the option in the specified String array
	 * @param args the specified String array
	 * @return a List of path of files
	 * @throws IOException
	 */
	public static List<Path> option(String[] args) throws IOException {
		Objects.requireNonNull(args);
		var lstPath = List.of(FileSystems.getDefault().getPath(defaultFile));
		if(args.length >= 2) {
			switch(args[0]) {
			case "--levels":
				lstPath = levels(args[1]);
				return lstPath;
			case "--level":
				lstPath = level(args[1]);
				return lstPath;
			}
		}
		return lstPath;
	}	
}
