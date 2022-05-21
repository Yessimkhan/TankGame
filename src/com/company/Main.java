package com.company;
import java.util.*;
import java.io.*;
class MyPoint{
    double x;
    double y;
    public MyPoint(double x , double y ){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return this.x;
    }
    public void setX(double x){
        this.x = x;
    }
    public double getY(){
        return this.y;
    }
    public void setY(double y){
        this.y = y;
    }
}
class Shape {
    ArrayList<MyPoint> points;
    public Shape() {
        points = new ArrayList<>();
    }
    public void addPoints(double x , double y){
        MyPoint object = new MyPoint(x , y);
        this.points.add(object);
    }
    public ArrayList<MyPoint> getList() {
        return this.points;
    }
    public double getBigLine(){
        double v = 0;
        double max = 0;
        for (int i = 0; i < this.points.size(); i++) {
            double a = this.points.get(i).getX();
            double b = this.points.get(i).getY();
            double c = this.points.get(i + 1 == this.points.size()? 0 : i + 1).getX();
            double d = this.points.get(i + 1 == this.points.size() ? 0 : i + 1).getY();
            v = Math.sqrt(Math.abs(a - c) * Math.abs(a - c) + Math.abs(d - b) * Math.abs(d - b));
            if(max<v){
                max = v;
            }
        }
        return max;
    }
    public double getPerimetr(){
        double perim = 0;
        double v = 0;
        double max = 0;
        for (int i = 0; i < this.points.size(); i++) {
            double a = this.points.get(i).getX();
            double b = this.points.get(i).getY();
            double c = this.points.get(i+1==this.points.size()?0:i+1).getX();
            double d = this.points.get(i+1==this.points.size()?0:i+1).getY();
            v = Math.sqrt(Math.abs(a-c)*Math.abs(a-c)+Math.abs(d-b)*Math.abs(d-b));
            if(max<v){
                max = v;
            }
            perim +=v;
        }return perim;

    }
}
public class Main{
    public static void main(String[] args) throws Exception{

        Scanner scan = new Scanner(System.in);
        Shape shape = new Shape();
        System.out.println("qansha byrushtu figura");
        int count = scan.nextInt();
        System.out.println("Perimetr tabu ushin koordinattaryn engiziniz: ");
        while(count>0){
            double a = scan.nextDouble();
            double b = scan.nextDouble();
            shape.addPoints(a , b);
            count--;
        }

        ArrayList<MyPoint> temp = shape.getList();
        for (int i = 0 ; i < temp.size(); i++){
            System.out.println(temp.get(i).getX() + " " + temp.get(i).getY());

        }
        System.out.println("Perimetr is ");
        System.out.println(shape.getPerimetr());
        System.out.println("Big Line is");
        System.out.println(shape.getBigLine());
    }
}