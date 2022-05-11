package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
	ArrayList<String> dizionario;
	String lingua;
	
	public boolean loadDictionary(String language) {
		if(dizionario!=null && this.lingua.equals(language))
			return true;
		this.lingua=language;
		dizionario= new ArrayList<String>();
		try {
			FileReader fr = new FileReader("src/main/resources/"+this.lingua+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				this.dizionario.add(word.toLowerCase());
				}
				Collections.sort(dizionario);
				br.close();
				return true;
			}
		catch(IOException e) {
			System.err.println("ERRORE IN LETTURA DEL FILE");
			return false;
		}
	}
	
	public List<Richword> spellCheckText(List<String> inputTest){
		List<Richword> result= new ArrayList<Richword>();
		for(String s : inputTest) {
			Richword rich= new Richword(s);
			rich.setCorretta(false);
			if(dizionario.contains(s))
				rich.setCorretta(true);
			result.add(rich);
		}
		return result;
	}

}
