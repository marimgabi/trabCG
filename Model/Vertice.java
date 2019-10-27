package Model;

import java.io.Serializable;

import static java.lang.StrictMath.sqrt;

public class Vertice implements Serializable {
    private double x;
    private double y;
    private double z;

    public Vertice(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertice() {

    }

    public double distancia(Vertice V){
        double resp = sqrt(Math.pow(V.x-this.x,2)+Math.pow(V.y-this.y,2));
        return resp;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
