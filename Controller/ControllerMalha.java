package Controller;

import Model.Malha;
import Model.Vertice;

import java.util.Random;

public class ControllerMalha {
    Malha malha;
    Malha malhaVisualizacao;

    public void criaMalha(int pontosX, int pontosZ, int espaco, int espaco1){
        malha = new Malha();
        Random gerador = new Random();

        malha.setCentro(new Vertice((((pontosX/2)*espaco)*-1)+((((pontosX/2)*espaco)-(((pontosX/2)*espaco)*-1))/2),0,(((pontosZ/2)*espaco1)*-1)+(((((pontosZ/2)*espaco1)-(((pontosZ/2)*espaco1)*-1))/2))));

        //Cria tds os vértices
        for(int i=((pontosX/2)*espaco)*-1;i<=(pontosX/2)*espaco;i+=espaco){
            for(int j=((pontosZ/2)*espaco1)*-1;j<=(pontosZ/2)*espaco1;j+=espaco1){
                Vertice aux = new Vertice(i,Math.abs(gerador.nextInt(5000)),j);


            }
        }
    }

}
