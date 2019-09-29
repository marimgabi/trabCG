package Controller;

import Model.Malha;
import Model.Vertice;

import java.util.Random;

public class ControllerMalha {
    Malha malha;
    Malha malhaVisualizacao;

    public void criaMalha(int pontosX, int pontosZ, int espaco){
        Random gerador = new Random(System.currentTimeMillis());

        //Cria tds os vértices
        for(int i=0;i<pontosX;i+=espaco){
            for(int j=0;j<pontosZ;j+=espaco){
                Vertice aux = new Vertice(i,Math.abs(gerador.nextDouble()),j);
                //inserir os vertices

            }
        }
    }

}
