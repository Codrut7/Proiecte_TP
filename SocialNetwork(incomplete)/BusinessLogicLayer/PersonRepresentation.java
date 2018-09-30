package BusinessLogicLayer;

import ModelLayer.Person;

import java.io.*;
import java.util.*;

/*
     27/09/2018 - Fodor Bogdan Codrut
     Se foloseste un graph data structure pentru a reprezenta persoanele intr-o retea sociala
     Aici avem toate interactiunile din reteaua sociala
 */
public class PersonRepresentation {
    private static Map<Person, Set<Person>> graph;
    private int edgeCount = 0;

    public PersonRepresentation() throws IOException, ClassNotFoundException {
        loadData();
    }

    public int getNumarNoduri(){
        return graph.size();
    }

    public int getNumarMuchii(){
        return edgeCount;
    }

    public void validareNod(Person p){
        if(!graph.containsKey(p)){
            throw new IllegalArgumentException("Nu exista persoana");
        }
    }
    public int gradNod(Person p){  // echivalent cu -> NumarPrieteni persoana P
        validareNod(p);
        return graph.get(p).size();
    }

    public void adaugareMuchie(Person x,Person y) throws IOException { // echivalent cu -> adaugarePrieten
        validareNod(x);
        validareNod(y);
        graph.get(x).add(y);
        graph.get(y).add(x);
        saveData();
    }

    public boolean contineMuchie(Person x,Person y){  // echivalen cu -> persoana x prieten cu persoana y
        validareNod(x);
        validareNod(y);
        return graph.get(x).contains(y);
    }

    public boolean contineNod(Person p){    // echivalnet cu -> persoana x este inregistrata in database
        return graph.containsKey(p);
    }

    public void addNode(Person p) throws IOException {   // adaugam o persoana in strucutra de date dupa ce se inregistreaza
        if(!graph.containsKey(p)){
            graph.put(p,new HashSet<>());
            saveData();
        }
    }
    public void deleteNode(Person p) throws IOException { // sterge o persoana din structura de date
        if(graph.containsKey(p)){
            graph.remove(p);
            saveData();
        }
    }

    public void saveData() throws IOException {
        FileOutputStream file = new FileOutputStream("D:\\proiect_anul_2_TP_BANK4\\map.ser");
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(graph);
        output.close();
        file.close();
    }

    private void loadData() throws IOException,ClassNotFoundException {
        File filez = new File("D:\\SocialNetwork\\map.ser");
        FileInputStream file = new FileInputStream(filez);
        ObjectInputStream input = new ObjectInputStream(file);
        graph = (HashMap<Person, Set<Person>>) input.readObject();
        input.close();
        file.close();
    }
}
