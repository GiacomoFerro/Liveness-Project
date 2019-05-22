package analisiProject;
import java.util.*;
import java.io.*;

public class Vertexgraph {
	public String nodo =""; //istruzione
	public int index= 0; 

	public Vertexgraph() {
		nodo="NULL";
		index=-1;
	}
	
	public Vertexgraph(String istruzione, int indice) {
		nodo = istruzione;
		index = indice;
	}
	
	public String getIstruzione() {
		return nodo;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setNodo(String nodo) {
		this.nodo = nodo;
	}
	
	public void setIndex(int indice) {
		this.index = indice;
	}
	
	@Override
	public String toString() {
		return "["+index+","+nodo+"]";
	}
	
	public boolean equals(Vertexgraph e) {
		
		return this.getIndex()==e.getIndex() && 
				this.getIstruzione().equals(e.getIstruzione());		
	}
	
	
	
}
