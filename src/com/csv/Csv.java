package com.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Csv{

	String fileName;
	ArrayList<String[]> data;
	
	public Csv(String fileName){
		this.fileName = fileName;
	}
	
	public static void main(String[] args) throws IOException {
		Csv csvFile = new Csv("data");
		csvFile.add(new String[]{"1"});
		csvFile.add(new String[]{"2"});
		csvFile.add(new String[]{"3"});
		csvFile.add(new String[]{"4"});
		csvFile.add(new String[]{"5"});
		csvFile.add(new String[]{"6"});
		csvFile.remove(3);
	}
	
	public void add(String[] data) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
		bw.append(String.join(",", data)+"\n");
		bw.close();
		bw = null;
	}
	
	public void remove(int lineNumber) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String currentLine;
		String newFile = "";
		int cont = 1;
		while((currentLine = reader.readLine()) != null) {
		    if(cont++ == lineNumber) continue;
		    newFile += currentLine+"\n";
		}
		reader.close();
		reader = null;
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(newFile);
		writer.flush();
		writer.close();
		writer = null;
	}
	
	public ArrayList<String[]> read() throws IOException{
		if(data != null){
			return data;
		}
		data = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(tokenize(line));
        }
        reader.close();
        reader = null;
        return data;
	}
	
	public String[] tokenize(String line){
		return line.split(",");
	}
}