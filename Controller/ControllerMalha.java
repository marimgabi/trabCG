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
        int deslocamento,deslocamento2;

        if(pontosX%2==0){
            deslocamento=espaco/2;
        }else{
            deslocamento=0;
        }
        if(pontosZ%2==0){
            deslocamento2=espaco/2;
        }else{
            deslocamento2=0;
        }
        malha.setCentro(new Vertice((((pontosX/2)*espaco)*-1)+((((pontosX/2)*espaco)-(((pontosX/2)*espaco)*-1))/2),0,(((pontosZ/2)*espaco1)*-1)+(((((pontosZ/2)*espaco1)-(((pontosZ/2)*espaco1)*-1))/2))));
        System.out.println("CENTRO: "+malha.getCentro().getX()+" "+malha.getCentro().getY()+" "+malha.getCentro().getZ());
        //Cria tds os vértices
        for(int i =(((pontosX/2)*espaco)*-1)+deslocamento; i<=((pontosX/2)*espaco)-deslocamento; i+=espaco){
            for(int j=(((pontosZ/2)*espaco1)*-1)+deslocamento2;j<=((pontosZ/2)*espaco1)-deslocamento2;j+=espaco1){
                Vertice aux = new Vertice(i,Math.abs(gerador.nextInt(5000)),j);
                System.out.println("X = "+aux.getX()+" Y = "+aux.getY()+" Z = "+aux.getZ());

            }
        }
    }

}
