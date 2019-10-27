package Model;

import java.util.ArrayList;

public class Malha {
    ArrayList<Poligono> faces;
    ArrayList<Aresta> arestas;
    Vertice centro;

    public Malha() {
        faces = new ArrayList<>();
        arestas = new ArrayList<>();
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
}
