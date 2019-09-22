package com.codenation.demo.utils;

import java.io.File;
import java.io.PrintWriter;

import com.codenation.demo.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileWriter {

	private static final String FILE_URI = "./src/main/resources/answer.json";
	
	public static String objectToJson(Response response) {
		
		ObjectMapper obj = new ObjectMapper();
		String json = null;
		try {
			json = obj.writeValueAsString(response); 
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return json;
	}
	
	public static File getFile() {
		File file = new File(FILE_URI);
		return file;
	}
	
	public static void writerFile(String response) {
		File file = new File(FILE_URI);
		try {
			if (!file.exists()) {
				file.delete();				
			}
			file.createNewFile();
			java.io.FileWriter arq = new java.io.FileWriter(file);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(response);
			arq.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
