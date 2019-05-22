package analisiProject;
import java.util.*;
import java.io.*;
import java.net.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
import org.jgrapht.traverse.*;

public class Live{
	
	private Graph<Vertexgraph,DefaultEdge> g;
	private Set<Vertexgraph> generated;
	private Set<Vertexgraph> killed;
	private int righe;
	private String results ="";
	
	private List<Set<String>> LiveOutCorr;
	
	
	public Live(int num_nodi, Set<Vertexgraph> Kill, Set<Vertexgraph> Gen, Graph<Vertexgraph,DefaultEdge> g) {
		
		righe = num_nodi;
		
		killed = new HashSet<Vertexgraph>(Kill);
		generated = new HashSet<Vertexgraph>(Gen);
		this.g= new DefaultDirectedGraph<Vertexgraph,DefaultEdge>(DefaultEdge.class);
		this.g = g;
		
		LiveOutCorr = new ArrayList<Set<String>>(righe);
				
		Set<String> tmp = new HashSet<String>();
		
		for(int i = 0; i<righe; i++) {
			this.LiveOutCorr.add(tmp);
		}
		results = "LIVE 0:\n";
		for(int i= this.LiveOutCorr.size()-1; i>=0; i--) {
			results= results+i+" "+LiveOutCorr.get(i)+"\n";
			
		}
		
	}//fine costruttore
	
	
	public String compute_LiveOut(int iterazioni) {
				
		for(int rep = 1; rep < iterazioni; rep++) {
		
			List<Set<String>> LiveOutNext = new ArrayList<Set<String>>(righe); //colonna succ
			
			Set<String> tmp = new HashSet<String>();
			
			for(int i = 0; i<righe; i++) {
				LiveOutNext.add(tmp);
			}
			
			Set<Vertexgraph> nodi = g.vertexSet();// tutti i nodi del grafo
			Iterator itNodi = nodi.iterator();
			
			while(itNodi.hasNext()) {//itero tutti i nodi
				
				Vertexgraph curr = (Vertexgraph) itNodi.next();
								
				List<Vertexgraph> uscenti = new ArrayList<Vertexgraph>();
				uscenti=Graphs.successorListOf(g, curr);//nodi uscenti dal nodo corrente			
				
				Iterator itSucc = uscenti.iterator();
				
				int indiceSucc = 0;
				
				Set<String> liveOutSucc = new HashSet<String>();//live out del succ
				
				while(itSucc.hasNext()) {
					
					Set<String> genSucc = new HashSet<String>();// generate dal succ
					Set<String> killSucc = new HashSet<String>(); //killate dal succ
					
					Vertexgraph succ = (Vertexgraph) itSucc.next();
														
					indiceSucc = succ.getIndex();
					Iterator gen = generated.iterator();
					
					while(gen.hasNext()) {
						Vertexgraph nodo = (Vertexgraph) gen.next();
						
						if(nodo.getIndex()==indiceSucc) {
							genSucc.add(nodo.getIstruzione());//salvo i gen dei successori
						}
					}
		
					Iterator kill = killed.iterator();
					
					while(kill.hasNext()) {
						Vertexgraph nodo = (Vertexgraph) kill.next();
						if(nodo.getIndex()==indiceSucc) {
							killSucc.add(nodo.getIstruzione());
						}
					}
					//HO GEN E KILL PER i-esimo successore di CURR	
					
					liveOutSucc.addAll(this.LiveOutCorr.get(indiceSucc));
					
					liveOutSucc.removeAll(killSucc);
					
					liveOutSucc.addAll(genSucc);

					
				}//fine while sui nodi uscenti
								
				Set<String> union = new HashSet<String>( LiveOutNext.get(curr.getIndex()) );
								
				union.addAll(liveOutSucc);
				
				LiveOutNext.remove( curr.getIndex() );
				LiveOutNext.add( curr.getIndex(), union );			
				
			}//fine iterazione sui nodi
			
			results = results+"\n\nLIVE "+(rep)+":";
			results=results+"\n";
				
			for(int i= LiveOutNext.size()-1; i>=0; i--) {
				results = results+i+" "+LiveOutNext.get(i)+"\n";
			}
						
			Collections.copy(this.LiveOutCorr,LiveOutNext);
		}//fine for
		
		return results;
	}
}