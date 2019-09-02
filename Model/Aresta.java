package Model;

import java.io.Serializable;

public class Aresta implements Serializable {
    public Vertice ini;
    public Vertice fim;

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
}
