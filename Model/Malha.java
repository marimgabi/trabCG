package Model;

import java.util.ArrayList;

public class Malha {
    ArrayList<Poligono> faces;
    ArrayList<Aresta> arestas;
    ArrayList<Vertice> vertices;
    Vertice centro;

    public Malha() {
        faces = new ArrayList<>();
        arestas = new ArrayList<>();
        vertices = new ArrayList<>();
        centro = new Vertice();
    }

    public ArrayList<Poligono> getFaces() {
        return faces;
    }

    public void setFaces(ArrayList<Poligono> faces) {
        this.faces = faces;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    public Vertice getCentro() {
        return centro;
    }

    public void setCentro(Vertice centro) {
        this.centro = centro;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public void addVertice(Vertice vertice){
        vertices.add(vertice);
    }

    public void addAresta(Aresta aresta){
        arestas.add(aresta);
    }
}
