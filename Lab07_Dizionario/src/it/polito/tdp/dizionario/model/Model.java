package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	UndirectedGraph<String, DefaultEdge> graph;
	List<String> listaVertici;

	public List<String> createGraph(int numeroLettere) {
		WordDAO dao= new WordDAO();
		graph = new SimpleGraph<>(DefaultEdge.class) ;
		
		List<String> risultato=dao.getAllWordsFixedLength(numeroLettere);
		
		for(String s: risultato){
			graph.addVertex(s);
		}
		listaVertici=new ArrayList<String>(graph.vertexSet());

		for (String s: graph.vertexSet()){
			aggiungiArchi(s);
		}
		
		return listaVertici;
	}
	

	private void aggiungiArchi(String s) {
		
		for (String temp: listaVertici){
			int count=0;
			for(int i=0; i<s.length(); i++){
				if(s.charAt(i)!=temp.charAt(i))
					count++;
			}
			if (count==1){
				graph.addEdge(s, temp);
			}
		}
	}


	public List<String> displayNeighbours(String parolaInserita) {
		if(listaVertici.contains(parolaInserita)){
			return Graphs.neighborListOf(graph, parolaInserita);
		}
		return new ArrayList<String>();
	}

	public String findMaxDegree() {
		int maxDegree=-1;
		String wordWithMaxDegree="";
		for (String stemp: graph.vertexSet()){
			if(graph.degreeOf(stemp)>maxDegree){
				maxDegree=graph.degreeOf(stemp);
				wordWithMaxDegree=stemp;
			}
		}
		return "Grado massimo:\n"+wordWithMaxDegree+": grado "+maxDegree+"\nVicini: "+displayNeighbours(wordWithMaxDegree);
	}
	
	public void doReset(){
		listaVertici.clear();
		graph=new SimpleGraph<>(DefaultEdge.class);
	}
}
