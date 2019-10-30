package Controller;

import Model.Aresta;
import Model.Malha;
import Model.Poligono;
import Model.Vertice;

import java.util.Random;

public class ControllerMalha {
    Malha malha;
    Malha malhaVisualizacao;


    public void criaMalha(int pontosX, int pontosZ, int espaco, int espaco1){

        malha = new Malha(pontosX,pontosZ);
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

        //Cria tds os vértices e add na malha
        for(int i=0;i<pontosX;i++){
            for(int j=0;j<pontosZ;j++){
                Vertice aux = new Vertice(((((pontosX/2)*espaco)*-1)+deslocamento)+(espaco*i),Math.abs(gerador.nextInt(5000)),((((pontosZ/2)*espaco1)*-1)+deslocamento2)+(espaco1*j));
                malha.getVerticesMatrix()[i][j] = aux;
            }
        }

//        for(int i=0;i<pontosX;i++){
//            for(int j=0;j<pontosZ;j++){
//                System.out.println(malha.getVerticesMatrix()[i][j].getX()+"   "+malha.getVerticesMatrix()[i][j].getZ());
//            }
//        }

        //Cria arestas

        for(int i=0;i<pontosX;i++){
            for(int j=0;j<pontosZ;j++){
                boolean achou;
                Aresta a,b,c;
                a=new Aresta();
                b=new Aresta();
                c=new Aresta();

                //Polígono 1
                if((i<pontosX-1)&&(j<pontosZ-1)){
                    //aresta (i,j)->(i+1,j)
                    achou=false;
                    if((i+1)<pontosX){
                        if(malha.getArestas().size()!=0){
                            for(Aresta aux:malha.getArestas()){
                                if(aux.alreadyExist(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i+1][j])){
                                    a=aux;
                                    achou=true;
                                    break;
                                }
                            }
                        }
                        if(achou==false){
                            a=new Aresta(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i+1][j]);
                            malha.getArestas().add(a);
                        }

                    }

                    //aresta (i,j)->(i,j+1)
                    achou=false;
                    if((j+1)<pontosZ){
                        if(malha.getArestas().size()!=0){
                            for(Aresta aux:malha.getArestas()){
                                if(aux.alreadyExist(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i][j+1])){
                                    b=aux;
                                    achou=true;
                                    break;
                                }
                            }
                        }
                        if(achou==false){
                            b=new Aresta(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i][j+1]);
                            malha.getArestas().add(b);
                        }

                    }

                    //aresta (i+1,j)->(i,j+1)
                    achou=false;
                    if(((j+1)<pontosZ)&&((i+1)<pontosX)){
                        if(malha.getArestas().size()!=0){
                            for(Aresta aux:malha.getArestas()){
                                if(aux.alreadyExist(malha.getVerticesMatrix()[i+1][j],malha.getVerticesMatrix()[i][j+1])){
                                    c=aux;
                                    achou=true;
                                    break;
                                }
                            }
                        }
                        if(achou==false){
                            c=new Aresta(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i][j+1]);
                            malha.getArestas().add(c);
                        }

                    }
                    //Cria o polígono
                    Poligono poligono1 = new Poligono();
                    poligono1.getArestas().add(a);
                    poligono1.getArestas().add(b);
                    poligono1.getArestas().add(c);
                    poligono1.getVertices().add(malha.getVerticesMatrix()[i][j]);
                    poligono1.getVertices().add(malha.getVerticesMatrix()[i+1][j]);
                    poligono1.getVertices().add(malha.getVerticesMatrix()[i][j+1]);
                    malha.getFaces().add(poligono1);
                }

                //Polígono 2
                if((i>0)&&(j<pontosZ-1)){
                    //aresta (i,j)->(i-1,j+1)
                    achou=false;

                    if(malha.getArestas().size()!=0){
                        for(Aresta aux:malha.getArestas()){
                            if(aux.alreadyExist(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i-1][j+1])){
                                a=aux;
                                achou=true;
                                break;
                            }
                        }
                    }
                    if(achou==false){
                        a=new Aresta(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i-1][j+1]);
                        malha.getArestas().add(a);
                    }

                    //aresta (i,j)->(i,j+1)
                    achou=false;

                    if(malha.getArestas().size()!=0){
                        for(Aresta aux:malha.getArestas()){
                            if(aux.alreadyExist(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i][j+1])){
                                b=aux;
                                achou=true;
                                break;
                            }
                        }
                    }
                    if(achou==false){
                        b=new Aresta(malha.getVerticesMatrix()[i][j],malha.getVerticesMatrix()[i][j+1]);
                        malha.getArestas().add(b);
                    }

                    //aresta (i,j+1)->(i-1,j+1)
                    achou=false;
                    if(malha.getArestas().size()!=0){
                        for(Aresta aux:malha.getArestas()){
                            if(aux.alreadyExist(malha.getVerticesMatrix()[i][j+1],malha.getVerticesMatrix()[i-1][j+1])){
                                c=aux;
                                achou=true;
                                break;
                            }
                        }
                    }
                    if(achou==false){
                        c=new Aresta(malha.getVerticesMatrix()[i][j+1],malha.getVerticesMatrix()[i-1][j+1]);
                        malha.getArestas().add(c);
                    }


                    //Cria o polígono
                    Poligono poligono2 = new Poligono();
                    poligono2.getArestas().add(a);
                    poligono2.getArestas().add(b);
                    poligono2.getArestas().add(c);
                    poligono2.getVertices().add(malha.getVerticesMatrix()[i][j]);
                    poligono2.getVertices().add(malha.getVerticesMatrix()[i-1][j+1]);
                    poligono2.getVertices().add(malha.getVerticesMatrix()[i][j+1]);
                    malha.getFaces().add(poligono2);
                }

            }
        }

        for(Poligono p:malha.getFaces()){
            System.out.println("-----");
            System.out.println("("+p.getArestas().get(0).getIni().getX()+";"+p.getArestas().get(0).getIni().getZ()+")  ----  ("+p.getArestas().get(0).getFim().getX()+";"+p.getArestas().get(0).getFim().getZ()+")");
            System.out.println("("+p.getArestas().get(1).getIni().getX()+";"+p.getArestas().get(1).getIni().getZ()+")  ----  ("+p.getArestas().get(1).getFim().getX()+";"+p.getArestas().get(1).getFim().getZ()+")");
            System.out.println("("+p.getArestas().get(2).getIni().getX()+";"+p.getArestas().get(2).getIni().getZ()+")  ----  ("+p.getArestas().get(2).getFim().getX()+";"+p.getArestas().get(2).getFim().getZ()+")");
        }

        //Cria arestas que não cruzam o eixo Z
//        for(int i=0;i<malha.getVertices().size();i++){
//            if((i%pontosZ)<(pontosZ-1)){
//                Aresta nova = new Aresta(malha.getVertices().get(i),malha.getVertices().get(i+1));
//                malha.addAresta(nova);
//            }
//        }

//        for(Aresta a:malha.getArestas()){
//            System.out.println("---\nInicio: \n"+a.getIni().getX()+"   "+a.getIni().getZ()+"\n");
//            System.out.println("Fim: "+a.getFim().getX()+"   "+a.getFim().getZ()+"\n");
//        }

        //Cria arestas que não cruzam o eixo X
//        for(int i=0;i<malha.getVertices().size();i++){
//            if((i+pontosZ)<malha.getVertices().size()){
//                Aresta nova =new Aresta(malha.getVertices().get(i),malha.getVertices().get(i+pontosZ));
//                malha.addAresta(nova);
//            }
//        }

    }

}
