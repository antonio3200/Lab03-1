package it.polito.tdp.spellchecker.model;

public class Richword {
  String parola;
  boolean corretta;
public Richword(String parola) {
	super();
	this.parola = parola;
}
public String getParola() {
	return parola;
}
public void setParola(String parola) {
	this.parola = parola;
}
public boolean isCorretta() {
	return corretta;
}
public void setCorretta(boolean corretta) {
	this.corretta = corretta;
}
@Override
public String toString() {
	return "Richword [parola=" + parola + ", corretta=" + corretta + "]";
}
  
}
