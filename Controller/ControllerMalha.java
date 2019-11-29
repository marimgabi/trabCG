package Controller;

import Model.Aresta;
import Model.Malha;
import Model.Poligono;
import Model.Vertice;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class ControllerMalha {
    GraphicsContext gc1;
    Malha malha;
    Malha malhaVisualizacao;
    int numAmostraX;
    int numAmostraZ;
    int espacoX;
    int espacoZ;
    Vertice n,v,u,vrp,p;

    public ControllerMalha(GraphicsContext gc) {
        n =  new Vertice();
        v =  new Vertice();
        u =  new Vertice();
        vrp =  new Vertice();
        p =  new Vertice();
        gc1=gc;
    }

    public void criaMalha(int pontosX, int pontosZ, int espaco, int espaco1){
        numAmostraX=pontosX;
        numAmostraZ=pontosZ;
        malha = new Malha(pontosX,pontosZ);
        malhaVisualizacao = new Malha(pontosX,pontosZ);
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

        criaArestas(malha);
//        p.setCoordenadas(0,3000,Math.abs(4*malha.getVerticesMatrix()[pontosX-1][pontosZ-1].getZ()));
//        vrp.setCoordenadas(0,3000,Math.abs(2*malha.getVerticesMatrix()[pontosX-1][pontosZ-1].getZ()));
        setVrp(2,2500,2*malha.getVerticesMatrix()[pontosX-1][pontosZ-1].getZ());
//        setP(0,3000,4*malha.getVerticesMatrix()[pontosX-1][pontosZ-1].getZ());

    }

    public void criaArestas(Malha malha){

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

        //inicializa VRP e P




    }

    public String criaHeightmap(){
        String heightmap;
        heightmap=String.valueOf(malha.getVerticesMatrix()[0][0].getX())+"\n"+String.valueOf(malha.getVerticesMatrix()[numAmostraX-1][0].getX()+"\n"+String.valueOf(numAmostraX)+"\n");
        heightmap=heightmap+String.valueOf(malha.getVerticesMatrix()[0][0].getZ())+"\n"+String.valueOf(malha.getVerticesMatrix()[numAmostraX-1][numAmostraZ-1].getZ()+"\n"+String.valueOf(numAmostraZ)+"\n");
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
        System.out.println("zmax: "+zmax);
        numAmostraZ=Integer.valueOf(values[5]);
        int cont=6;
        espacoX= (int) ((xmax-xmin)/(numAmostraX-1));
        espacoZ= (int) ((zmax-zmin)/(numAmostraZ-1));
        malha= new Malha(numAmostraX,numAmostraZ);
        malhaVisualizacao= new Malha(numAmostraX,numAmostraZ);
        //malhaVisualizacao = new Malha();

        for(int i=0;i<numAmostraX;i++){
            for(int j=0;j<numAmostraZ;j++){
                Vertice aux = new Vertice(xmin+(espacoX*i),Double.valueOf(values[cont]),zmin+(espacoZ*j));
                malha.getVerticesMatrix()[i][j]=aux;
                cont++;
                //System.out.println("x: "+aux.getX()+"   y: "+aux.getY()+"   z:"+aux.getZ());
            }
        }
        criaArestas(malha);

//        vrp.setCoordenadas(0,3000,2*zmax);
//        p.setCoordenadas(0,3000,4*zmax);
        setVrp(200,8000,4*zmax);
        setP(0,1000,0);

    }

    public void suavizaAlturas(){
        for(int k=0;k<numAmostraX;k++){
            for(int m=0;m<numAmostraZ;m++){
                int cont=0;
                double media=0;
                for(int i=k-1;i<=k+1;i++){
                    for(int j=m-1;j<=m+1;j++){
                         if(((i==k-1)&&(i>=0))||(i==k)||((i==k+1)&&(i<numAmostraX))){
                             if(((j==m-1)&&(j>=0))||(j==m)||((j==m+1)&&(j<numAmostraZ))){
                                media+=malha.getVerticesMatrix()[i][j].getY();
                                cont++;
                             }
                         }
                    }
                }

                media/=cont;
                malha.getVerticesMatrix()[k][m].setY(media);
            }
        }
    }

    public double[][] multiplicaMatriz(double matriz1[][], double matriz2[][]){
        //descobrir as dimensões das matrizes
        int linhas1, colunas1, linhas2, colunas2;
            linhas1 = matriz1.length;
            colunas1 = matriz1[0].length;

            linhas2 = matriz2.length;
            colunas2 = matriz2[0].length;

        //O tamanho da matriz resultado é r[linhas1][colunas2]
        double resultado[][]= new double[colunas1][linhas2];

//        System.out.println("Linha: "+resultado.length+"  Coluna: "+resultado[0].length);
//        System.out.println("todos os resultados");
        for(int i=0;i<linhas1;i++){
            for(int j=0;j<colunas2;j++){
                resultado[i][j] =retornaValorProdutoMatriz(matriz1,matriz2,i,j);
//                System.out.println("função: "+retornaValorProdutoMatriz(matriz1,matriz2,i,j));
//                System.out.println("dentro da matriz: "+resultado[i][j]);
            }
        }

//        System.out.println("---------------------");
//        System.out.println(matriz1[0][0]+"   "+matriz1[1][0]+"   "+matriz1[2][0]+"   "+matriz1[3][0]);
//        System.out.println(matriz2[0][0]+"   "+matriz2[1][0]+"   "+matriz2[2][0]+"   "+matriz2[3][0]);
//        System.out.println("---------------------");
//        System.out.println(resultado[0][0]+"   "+resultado[1][0]+"   "+resultado[2][0]+"   "+resultado[3][0]);

        return resultado;

    }

    public double retornaValorProdutoMatriz(double m1[][],double m2[][], int linha, int coluna){

        double soma=0;

        for(int i=0;i<m1.length;i++){
            soma += m1[linha][i]*m2[i][coluna];
        }


        return soma;
    }

    public void setVrp(double x, double y, double z){
        vrp.setCoordenadas(x,y,z);
        //calcular de novo todos os vetores
        calculaN();
        calculaV();
        calculaU();

    }

    public void setP(double x, double y, double z){
        p.setCoordenadas(x,y,z);
        //calcular de novo todos os vetores
        calculaN();
        calculaV();
        calculaU();
    }

    public Vertice normalizaVetor(Vertice vetor){
        Vertice normalizado = new Vertice();
        double norma;
        norma = Math.sqrt((Math.pow(vetor.getX(),2))+(Math.pow(vetor.getY(),2))+(Math.pow(vetor.getZ(),2)));
        if(norma==0){
            normalizado.setCoordenadas(0,0,0);
            return normalizado;
        }
        normalizado.setCoordenadas((vetor.getX()/norma),(vetor.getY()/norma),(vetor.getZ()/norma));

        return normalizado;
    }

    public void calculaN(){
        n.setX(vrp.getX()-p.getX());
        n.setY(vrp.getY()-p.getY());
        n.setZ(vrp.getZ()-p.getZ());
        //System.out.println("N = "+n.getX()+"    "+n.getY()+"    "+n.getZ());
    }

    public void calculaV(){
        Vertice aux = new Vertice();
        aux.setCoordenadas(normalizaVetor(n).getY()*normalizaVetor(n).getX(),normalizaVetor(n).getY()*normalizaVetor(n).getY(),normalizaVetor(n).getY()*normalizaVetor(n).getZ());
        v.setCoordenadas(0-aux.getX(),1-aux.getY(),0-aux.getZ());
        //System.out.println("V = "+v.getX()+"    "+v.getY()+"    "+v.getZ());
    }

    public void calculaU(){
        //v=1 n=2
//        cout << "Produto Vetorial e dado por: " << endl
//                << "i = (y1 * z2)-(y2 * z1)" << endl
//                << "j = -(x1 * z2)-(x2 * z1)" << endl
//                << "k = (x1 * y2)-(x2 * y1)" << endl;
//
//        cout << '(' << (v1[1]*v2[2])-( v2[1]*v1[2]) << " ,"
//                << -((v1[0]*v2[2])-( v2[0]*v1[2])) << " ,"
//                << (v1[0]*v2[1])-( v2[0]*v1[1]) << ')' << endl;

        u.setX((v.getY()*n.getZ())-(n.getY()*v.getZ()));
        u.setY((n.getX()*v.getZ())-(v.getX()*n.getZ()));
        u.setZ((v.getX()*n.getY())-(n.getX()*v.getY()));

        //System.out.println("U = "+u.getX()+"    "+u.getY()+"    "+u.getZ());

    }

    public void sru2srtParallel(){
        //Construindo Msru,src
        double mSruSrc[][] = new double[4][4];
        mSruSrc[0][0] = normalizaVetor(u).getX();
        mSruSrc[0][1] = normalizaVetor(u).getY();
        mSruSrc[0][2] = normalizaVetor(u).getZ();
        mSruSrc[0][3] = (-vrp.getX()*normalizaVetor(u).getX())+(-vrp.getY()*normalizaVetor(u).getY())+(-vrp.getZ()*normalizaVetor(u).getZ());
        mSruSrc[1][0] = normalizaVetor(v).getX();
        mSruSrc[1][1] = normalizaVetor(v).getY();
        mSruSrc[1][2] = normalizaVetor(v).getZ();
        mSruSrc[1][3] = (-vrp.getX()*normalizaVetor(v).getX())+(-vrp.getY()*normalizaVetor(v).getY())+(-vrp.getZ()*normalizaVetor(v).getZ());
        mSruSrc[2][0] = normalizaVetor(n).getX();
        mSruSrc[2][1] = normalizaVetor(n).getY();
        mSruSrc[2][2] = normalizaVetor(n).getZ();
        mSruSrc[2][3] = (-vrp.getX()*normalizaVetor(n).getX())+(-vrp.getY()*normalizaVetor(n).getY())+(-vrp.getZ()*normalizaVetor(n).getZ());
        mSruSrc[3][0] = 0;
        mSruSrc[3][1] = 0;
        mSruSrc[3][2] = 0;
        mSruSrc[3][3] = 1;

//        for(int i=0;i<4;i++){
//            System.out.println(mSruSrc[i][0]+"  "+mSruSrc[i][1]+"  "+mSruSrc[i][2]+"  "+mSruSrc[i][3]+"  \n");
//        }

        //Construindo Mjp

        double ymax,ymin,xmax,xmin;

        xmin=malha.getVerticesMatrix()[0][0].getX();
        xmax=malha.getVerticesMatrix()[numAmostraX-1][0].getX();
        ymax=5000;
        ymin=-20;

        //System.out.println("ymax: "+ymax+"   ymin:"+ymin);

        double mjp[][] = new double[4][4];

        mjp[0][0] = (gc1.getCanvas().getWidth()-0)/(xmax-xmin);
        mjp[0][1] = 0;
        mjp[0][2] = 0;
        mjp[0][3] = (-xmin*((gc1.getCanvas().getWidth()-0)/(xmax-xmin)))+0;
        mjp[1][0] = 0;
        mjp[1][1] = (0-gc1.getCanvas().getHeight())/(ymax-ymin);
        mjp[1][2] = 0;
        mjp[1][3] = (ymin*((gc1.getCanvas().getHeight()-0)/(ymax-ymin)))+gc1.getCanvas().getHeight();
        mjp[2][0] = 0;
        mjp[2][1] = 0;
        mjp[2][2] = 1;
        mjp[2][3] = 0;
        mjp[3][0] = 0;
        mjp[3][1] = 0;
        mjp[3][2] = 0;
        mjp[3][3] = 1;

        //System.out.println("VRP: "+vrp.getX()+"   "+vrp.getY()+"   "+vrp.getZ());
        //System.out.println("P: "+p.getX()+"   "+p.getY()+"   "+p.getZ());

        System.out.println("---------------Msrusrc-------------");
        System.out.println(mSruSrc[0][0]+"   "+mSruSrc[0][1]+"   "+mSruSrc[0][2]+"   "+mSruSrc[0][3]);
        System.out.println(mSruSrc[1][0]+"   "+mSruSrc[1][1]+"   "+mSruSrc[1][2]+"   "+mSruSrc[1][3]);
        System.out.println(mSruSrc[2][0]+"   "+mSruSrc[2][1]+"   "+mSruSrc[2][2]+"   "+mSruSrc[2][3]);
        System.out.println(mSruSrc[3][0]+"   "+mSruSrc[3][1]+"   "+mSruSrc[3][2]+"   "+mSruSrc[3][3]);
        System.out.println("--------------------------------");

        System.out.println("---------------Mjp-------------");
        System.out.println(mjp[0][0]+"   "+mjp[0][1]+"   "+mjp[0][2]+"   "+mjp[0][3]);
        System.out.println(mjp[1][0]+"   "+mjp[1][1]+"   "+mjp[1][2]+"   "+mjp[1][3]);
        System.out.println(mjp[2][0]+"   "+mjp[2][1]+"   "+mjp[2][2]+"   "+mjp[2][3]);
        System.out.println(mjp[3][0]+"   "+mjp[3][1]+"   "+mjp[3][2]+"   "+mjp[3][3]);
        System.out.println("--------------------------------");

        System.out.println("W: "+gc1.getCanvas().getWidth()+"   H: "+gc1.getCanvas().getHeight());

        //Msru,src . Mjp
        double msrt[][];
        //double msrt[][]= new double[4][4];
        //System.out.println("PRIMERA MULTIPLICAÇÃO");
        msrt=multiplicaMatriz(mjp,mSruSrc);
        System.out.println("---------------MSRT-------------");
        System.out.println(msrt[0][0]+"   "+msrt[0][1]+"   "+msrt[0][2]+"   "+msrt[0][3]);
        System.out.println(msrt[1][0]+"   "+msrt[1][1]+"   "+msrt[1][2]+"   "+msrt[1][3]);
        System.out.println(msrt[2][0]+"   "+msrt[2][1]+"   "+msrt[2][2]+"   "+msrt[2][3]);
        System.out.println(msrt[3][0]+"   "+msrt[3][1]+"   "+msrt[3][2]+"   "+msrt[3][3]);
        System.out.println("--------------------------------");
        multiplicaPontos(msrt);

    }

    public void multiplicaPontos(double tg[][]){
        for(int i=0;i<numAmostraX;i++){
            for(int j=0;j<numAmostraZ;j++){
                double pontos[][] = new double[4][1];
                //double pontosResult[][] = new double[][];
                double pontosResult[][] = new double[4][1];
                //System.out.println( malha.getVerticesMatrix()[i][j].getX()+"    "+malha.getVerticesMatrix()[i][j].getY()+"    "+malha.getVerticesMatrix()[i][j].getZ());
                //System.out.println("------------------");
                pontos[0][0] = malha.getVerticesMatrix()[i][j].getX();
                pontos[1][0] = malha.getVerticesMatrix()[i][j].getY();
                pontos[2][0] = malha.getVerticesMatrix()[i][j].getZ();
                pontos[3][0] = 1;
                System.out.println(pontos[0][0]+"    "+pontos[1][0]+"    "+pontos[2][0]+"    "+pontos[3][0]);
                //System.out.println(tg[0][0]+"    "+tg[0][1]+"    "+tg[0][2]);
                pontosResult=multiplicaMatriz(tg,pontos);
                malhaVisualizacao.getVerticesMatrix()[i][j] = new Vertice(pontosResult[0][0],pontosResult[1][0], pontosResult[2][0]);
                //System.out.println(pontosResult[0][0]+"    "+pontosResult[1][0]+"    "+pontosResult[2][0]+"    "+pontosResult[3][0]);
            }
        }
        //Reconstruir arestas da malha de visualização
        malhaVisualizacao.getArestas().clear();
        criaArestas(malhaVisualizacao);
    }

//    public Vertice[][] transporMatriz(Vertice matriz[][]){
//        Vertice transposta[][] = new Vertice[matriz[0].length][matriz.length];
//
//        for(int linha=0;linha<matriz.length;linha++){
//            for(int coluna=0;coluna<matriz[linha].length;coluna++){
//                transposta[coluna][linha]=matriz[linha][coluna];
//            }
//        }
//
//        return transposta;
//    }
}
