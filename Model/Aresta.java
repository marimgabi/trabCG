package Model;

import java.io.Serializable;

public class Aresta implements Serializable {
    public Vertice ini;
    public Vertice fim;
    public Poligono pai1;
    public Poligono pai2;

    public Aresta() {
    }

    public Aresta(Vertice ini, Vertice fim) {
        this.ini = ini;
        this.fim = fim;
    }

    public Vertice getIni() {
        return ini;
    }

    public void setIni(Vertice ini) {
        this.ini = ini;
    }

    public Vertice getFim() {
        return fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }

    public Poligono getPai1() {
        return pai1;
    }

    public void setPai1(Poligono pai1) {
        this.pai1 = pai1;
    }

    public Poligono getPai2() {
        return pai2;
    }

    public void setPai2(Poligono pai2) {
        this.pai2 = pai2;
    }
}
