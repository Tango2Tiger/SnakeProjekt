import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Leaderbord extends Application{

    public static void main(String[] args){
        launch(args);
    }

    //Refers to the MainMenu
    MainMenu menu = new MainMenu();

    //This is the Leaderboards override method for the leaderboard menu
    @Override
    public void start(Stage stage) throws FileNotFoundException{
        //Group to hold all the UI elements
        Group root = new Group();
        Scene scene = new Scene(root);
        Rectangle rect = new Rectangle(600, 800);
        Font font = Font.font(40);
        
        //Button to come back to the Main menu
        Button exit = new Button("Back");
        exit.setFont(Font.font(30));
        exit.setMinSize(100, 25);
        exit.setLayoutX(10);
        exit.setLayoutY(720);
        exit.setOnAction(event -> {
            try {
                menu.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Text header = new Text("Leaderboard");
        header.setFill(Color.WHITE);
        header.setFont(font);
        stage.setTitle("Snake");

        String path = "C:\\Users\\magnu\\Dropbox\\MagnusDropbox\\Skole\\DTU\\1.semester\\SnakeProjekt-1\\ranking.txt";
        File ranking = new File(path);
        Scanner reader = new Scanner(ranking);

        font = Font.font(20);
        
        ArrayList<Text> nameArr = new ArrayList<>();
        while (reader.hasNextLine()) {
            nameArr.add(new Text(reader.nextLine()));
            nameArr.get(nameArr.size()-1).setFont(font);
            nameArr.get(nameArr.size()-1).setFill(Color.WHITE);;
        }

        //VBox to organize the UI elements
        VBox vBox = new VBox(30, header);
        for(int i=0; i < Math.min(10, nameArr.size()); i++){
            vBox.getChildren().add(nameArr.get(i));
        }
        vBox.setTranslateX(200);
        vBox.setTranslateY(50);

        root.getChildren().addAll(rect, vBox, exit);
        stage.setScene(scene);
        stage.show();
    }
    
}
