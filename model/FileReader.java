package ro.utcn.tp.Assig5.model;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class FileReader {
	public static List<String>readFile() throws IOException,ClassNotFoundException{
		List<String> lines = Collections.emptyList(); 
		try { lines = Files.readAllLines(Paths.get("Activities.txt"), StandardCharsets.UTF_8); } 
		catch (IOException e) { 
			e.printStackTrace();
		}
		return lines;

			
	}
}
