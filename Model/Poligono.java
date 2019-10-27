package Model;

import java.util.ArrayList;

public class Poligono {
    ArrayList<Aresta> arestas;
    ArrayList<Vertice> vertices;

    public Poligono() {
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }
}
