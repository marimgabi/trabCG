package Controller;

import Model.Aresta;
import Model.Malha;
import Model.Poligono;
import Model.Vertice;

import java.util.Random;

public class ControllerMalha {
    Malha malha;
    Malha malhaVisualizacao;
    int numAmostraX;
    int numAmostraZ;
    int espacoX;
    int espacoZ;


    public void criaMalha(int pontosX, int pontosZ, int espaco, int espaco1){
        numAmostraX=pontosX;
        numAmostraZ=pontosZ;
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

        criaArestas();

    }

    public void criaArestas(){

        //Cria arestas

        for(int i=0;i<numAmostraX;i++){
            for(int j=0;j<numAmostraZ;j++){
                boolean achou;
                Aresta a,b,c;
                a=new Aresta();
                b=new Aresta();
                c=new Aresta();

                //Polígono 1
                if((i<numAmostraX-1)&&(j<numAmostraZ-1)){
                    //aresta (i,j)->(i+1,j)
                    achou=false;
                    if((i+1)<numAmostraX){
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
                    if((j+1)<numAmostraZ){
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
                    if(((j+1)<numAmostraZ)&&((i+1)<numAmostraX)){
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
                            c=new Aresta(malha.getVerticesMatrix()[i+1][j],malha.getVerticesMatrix()[i][j+1]);
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
//                    System.out.println(malha.getFaces().size()+" poligono 1");
                }

                //Polígono 2
                if((i>0)&&(j<numAmostraZ-1)){
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
//                    System.out.println(malha.getFaces().size()+" poligono 2");
                }

            }
        }

//        int cont=0;
//        for(Poligono p:malha.getFaces()){
//            cont++;
//            System.out.println(cont+") -----");
//            System.out.println("("+p.getArestas().get(0).getIni().getX()+";"+p.getArestas().get(0).getIni().getZ()+")  ----  ("+p.getArestas().get(0).getFim().getX()+";"+p.getArestas().get(0).getFim().getZ()+")");
//            System.out.println("("+p.getArestas().get(1).getIni().getX()+";"+p.getArestas().get(1).getIni().getZ()+")  ----  ("+p.getArestas().get(1).getFim().getX()+";"+p.getArestas().get(1).getFim().getZ()+")");
//            System.out.println("("+p.getArestas().get(2).getIni().getX()+";"+p.getArestas().get(2).getIni().getZ()+")  ----  ("+p.getArestas().get(2).getFim().getX()+";"+p.getArestas().get(2).getFim().getZ()+")");
//
//        }


    }

    public String criaHeightmap(){
        String heightmap;
        heightmap=String.valueOf(malha.getVerticesMatrix()[0][0].getX())+"\n"+String.valueOf(malha.getVerticesMatrix()[numAmostraX-1][0].getX()+"\n"+String.valueOf(numAmostraX)+"\n");
        heightmap=heightmap+String.valueOf(malha.getVerticesMatrix()[0][0].getZ())+"\n"+String.valueOf(malha.getVerticesMatrix()[numAmostraZ-1][0].getZ()+"\n"+String.valueOf(numAmostraZ)+"\n");
        for(int i=0;i<numAmostraX;i++){
            for(int j=0;j<numAmostraZ;j++){
                heightmap=heightmap+String.valueOf(malha.getVerticesMatrix()[i][j].getY()+"\n");
            }
        }

        return heightmap;
    }

    public void leHeightmap(String heightmap){
        String[] values = heightmap.split(";");
        double xmin, xmax, zmin, zmax;
        xmin=Double.valueOf(values[0]);
        xmax=Double.valueOf(values[1]);
        numAmostraX=Integer.valueOf(values[2]);
        zmin=Double.valueOf(values[3]);
        zmax=Double.valueOf(values[4]);
        numAmostraZ=Integer.valueOf(values[5]);
        int cont=6;
        espacoX= (int) ((xmax-xmin)/(numAmostraX-1));
        espacoZ= (int) ((zmax-zmin)/(numAmostraZ-1));
        malha= new Malha(numAmostraX,numAmostraZ);
        //malhaVisualizacao = new Malha();

        for(int i=0;i<numAmostraX;i++){
            for(int j=0;j<numAmostraZ;j++){
                Vertice aux = new Vertice(xmin+(espacoX*i),Double.valueOf(values[cont]),zmin+(espacoZ*i));
                malha.getVerticesMatrix()[i][j]=aux;
                cont++;
            }
        }

        criaArestas();

    }

    public void suavizaAlturas(){

    }

}
