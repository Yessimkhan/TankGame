import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class TankGame extends Application {
    static Pane pane = new Pane();
    Tank tank = new Tank();
    @Override
    public void start(Stage stage) throws Exception {
        pane.setStyle("-fx-background-color: #000000");
        Player player = new MyPlayer();
        Game game;
        game = new Game(new Map());
        game.addPlayer(player);
        for (int i = 0; i < game.map.size; i++) {
            for (int j = 0; j < game.map.size; j++) {
                if(game.map.getValueAt(i,j)=='W'){
                    addWater(i,j);
                }
                else if(game.map.getValueAt(i,j)=='S'){
                    addStone(i,j);
                }
                else if(game.map.getValueAt(i,j)=='T'){
                    addTree(i,j);
                }
                else if(game.map.getValueAt(i,j)=='B'){
                    addBrick(i,j);
                }else if(game.map.getValueAt(i,j) =='P'){
                    tank.add(i,j);
                }
            }
        }


        tank.getImageTank().setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DOWN:
                    player.moveDown();
                    tank.getImageTank().setY(player.getPosition().getY()*30);
                    tank.getImageTank().setRotate(180);
                    break;
                case UP:
                    player.moveUp();
                    tank.getImageTank().setY(player.getPosition().getY()*30);
                    tank.getImageTank().setRotate(0);
                    break;
                case LEFT:
                    player.moveLeft();
                    tank.getImageTank().setX(player.getPosition().getX()*30);
                    tank.getImageTank().setRotate(270);
                    break;
                case RIGHT:
                    player.moveRight();
                    tank.getImageTank().setX(player.getPosition().getX()*30);
                    tank.getImageTank().setRotate(90);
                    break;
                case SPACE:
                    shoot();
            }
        });

        stage.setHeight(420);
        stage.setWidth(390);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Tank Game");
        stage.show();
        tank.getImageTank().requestFocus();
    }
    public static void addWater(int i,int j) throws FileNotFoundException {
        FileInputStream f1 = new FileInputStream("water.jpg");
        Image im = new Image(f1);
        javafx.scene.image.ImageView image = new ImageView(im);
        image.setFitWidth(30);
        image.setFitHeight(30);
        image.setY(i*30);
        image.setX(j*30);
        pane.getChildren().add(image);
        image.requestFocus();

    }
    public static void addStone(int i,int j) throws FileNotFoundException {
        FileInputStream f1 = new FileInputStream("steel.jpg");
        Image im = new Image(f1);
        javafx.scene.image.ImageView image = new ImageView(im);
        image.setFitWidth(30);
        image.setFitHeight(30);
        image.setY(i*30);
        image.setX(j*30);
        pane.getChildren().add(image);
        image.requestFocus();
    }
    public static void addTree(int i,int j) throws FileNotFoundException {
        FileInputStream f1 = new FileInputStream("tree.jpg");
        Image im = new Image(f1);
        javafx.scene.image.ImageView image = new ImageView(im);
        image.setFitWidth(30);
        image.setFitHeight(30);
        image.setY(i*30);
        image.setX(j*30);
        pane.getChildren().add(image);
        image.requestFocus();
    }
    public static void addBrick(int i,int j) throws FileNotFoundException {
        FileInputStream f1 = new FileInputStream("brick.jpg");
        Image im = new Image(f1);
        javafx.scene.image.ImageView image = new ImageView(im);
        image.setFitWidth(30);
        image.setFitHeight(30);
        image.setY(i*30);
        image.setX(j*30);
        pane.getChildren().add(image);
        image.requestFocus();
    }
    public void shoot() {
        Rectangle btn = new Rectangle();
        btn.setWidth(5);
        btn.setHeight(5);
        btn.setFill(Color.ORANGE);
        Group group = new Group(btn);
        Duration duration = Duration.millis(1000);
        TranslateTransition transition = new TranslateTransition(duration, btn);
        transition.setAutoReverse(true);
        pane.getChildren().add(group);

        if(tank.getRotate()==0){
            transition.setFromY(tank.getImageTank().getY()-5);
            transition.setFromX(tank.getImageTank().getX()+13);
            transition.setToY(-20);
            transition.setToX(tank.getImageTank().getX()+13);

        }
        if(tank.getImageTank().getRotate()==90){
            transition.setFromY(tank.getImageTank().getY()+13);
            transition.setFromX(tank.getImageTank().getX()+35);
            transition.setToY(tank.getImageTank().getY()+13);
            transition.setToX(400);
        }
        if(tank.getImageTank().getRotate()==180){
            transition.setFromY(tank.getImageTank().getY()+35);
            transition.setFromX(tank.getImageTank().getX()+13);
            transition.setToY(400);
            transition.setToX(tank.getImageTank().getX()+13);
        }
        if(tank.getImageTank().getRotate()==270){
            transition.setFromY(tank.getImageTank().getY()+13);
            transition.setFromX(tank.getImageTank().getX()-5);
            transition.setToY(tank.getImageTank().getY()+13);
            transition.setToX(-20);
        }
        transition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
class Tank extends MyPlayer{
    static FileInputStream f1;
    static {
        try {
            f1 = new FileInputStream("/Users/yessimkhan/IdeaProjects/Project_FX/yellow-tank-up.gif");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    Image im = new Image(f1);
    javafx.scene.image.ImageView imageTank = new ImageView(im);
    public void add(int i, int j) {
        imageTank.setFitWidth(30);
        imageTank.setFitHeight(30);
        imageTank.setY(i * 30);
        imageTank.setX(j * 30);
        TankGame.pane.getChildren().addAll(imageTank);
        imageTank.requestFocus();
    }

    public ImageView getImageTank() {
        return imageTank;
    }

    public double getRotate() {
        return imageTank.getRotate();
    }
}
class Bullet extends TankGame{
    public void shoot() {
        Rectangle btn = new Rectangle();
        btn.setWidth(5);
        btn.setHeight(5);
        btn.setFill(Color.ORANGE);
        Group group = new Group(btn);
        Duration duration = Duration.millis(1000);
        TranslateTransition transition = new TranslateTransition(duration, btn);
        transition.setAutoReverse(true);
        pane.getChildren().add(group);

        if(tank.getRotate()==0){
            transition.setFromY(tank.getImageTank().getY()-5);
            transition.setFromX(tank.getImageTank().getX()+13);
            transition.setToY(-20);
            transition.setToX(tank.getImageTank().getX()+13);

        }
        if(tank.getImageTank().getRotate()==90){
            transition.setFromY(tank.getImageTank().getY()+13);
            transition.setFromX(tank.getImageTank().getX()+35);
            transition.setToY(tank.getImageTank().getY()+13);
            transition.setToX(400);
        }
        if(tank.getImageTank().getRotate()==180){
            transition.setFromY(tank.getImageTank().getY()+35);
            transition.setFromX(tank.getImageTank().getX()+13);
            transition.setToY(400);
            transition.setToX(tank.getImageTank().getX()+13);
        }
        if(tank.getImageTank().getRotate()==270){
            transition.setFromY(tank.getImageTank().getY()+13);
            transition.setFromX(tank.getImageTank().getX()-5);
            transition.setToY(tank.getImageTank().getY()+13);
            transition.setToX(-20);
        }
        transition.play();
    }
}


class Position {
    int Y;
    int X;
    public Position()
    {}
    Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        String obj_class = obj.getClass().toString();
        if (!(obj_class.contains("Position"))) {
            return false;
        }
        Position position = (Position) obj;
        return X == position.X && Y == position.Y;
    }

    public String toString() {
        return String.format("(%d,%d)", getX(), getY());
    }
}
class Map {
    int size;
    char karta[][];
    int px,py;
    File file = new File("map.txt");
    Scanner input = new Scanner(file);
    Map() throws FileNotFoundException {
        size = input.nextInt();
        karta = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String element = input.next();
                this.karta[i][j] = element.charAt(0);

            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(karta[i][j]=='P'){
                    py = i;
                    px = j;
                }
            }
        }

    }

    public int getPy() {
        return py;
    }

    public int getPx() {
        return px;
    }

    public int getSize() {
        return size;
    }
    char getValueAt(int y, int x) {
        return karta[y][x];
    }

    public void print() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                System.out.print(karta[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
class MyPlayer implements Player {
    Position position = new Position();
    Map map;
    @Override
    public void setMap(Map map) {
        this.map = map;
    }
    @Override
    public void moveRight() {
        int X = position.getX() + 1;
        if (X < map.size) {
            char ch = map.getValueAt(position.getY(), X);
            if (ch != 'S' && ch != 'W' && ch!= 'B') {
                position.setX(X);
            }
        }
    }

    @Override
    public void moveLeft() {
        int X = position.getX() - 1;
        if (X >= 0) {
            char ch = map.getValueAt(position.getY(), X);
            if (ch != 'S' && ch != 'W' && ch!= 'B') {
                position.setX(X);
            }
        }
    }

    @Override
    public void moveUp() {
        int Y = position.getY() - 1;
        if (Y >= 0) {
            char ch = map.getValueAt(Y, position.getX());
            if (ch != 'S' && ch != 'W' && ch!= 'B') {
                position.setY(Y);
            }
        }
    }

    @Override
    public void moveDown() {
        int Y = position.getY() + 1;
        if (Y < map.size) {
            char ch = map.getValueAt(Y, position.getX());
            if (ch != 'S' && ch != 'W' && ch!= 'B') {
                position.setY(Y);
            }
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

}

interface Player {

    void moveRight();

    void moveLeft();

    void moveUp();

    void moveDown();

    Position getPosition();

    void setMap(Map map);

}
class Game {
    Map map;
    Player player;
    Game(Map map) {
        this.map = map;
    }
    public void setMap(Map map) {
        this.map = map;
    }
    public void addPlayer(Player player) {
        this.player = player;
        this.player.setMap(map);
        player.getPosition().setX(map.getPx());
        player.getPosition().setY(map.getPy());
    }
}