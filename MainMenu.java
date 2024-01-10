import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.application.*;

public class MainMenu extends Application{
    public static long speed = 200000000;
    public static int gridSize = 15;
    public static GamePanel game = new GamePanel();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);
        Font font = Font.font(40);
        Leaderbord leaders = new Leaderbord();
        Speed setSpeed = new Speed();
        GridSize gridSize = new GridSize();

        Button play = new Button("Play");
        play.setFont(font);
        play.setMinSize(400, 50);
        play.setOnAction(event -> {
            try {
                game.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button leaderbord = new Button("Leaderbord");
        leaderbord.setFont(font);
        leaderbord.setMinSize(400, 50);
        leaderbord.setOnAction(event -> leaders.start(stage));

        Button speed = new Button("Speed");
        speed.setMinSize(400, 50);
        speed.setFont(font);
        speed.setOnAction(event -> setSpeed.start(stage));

        Button setGrid = new Button("Grid Size");
        setGrid.setMinSize(400, 50);
        setGrid.setFont(font);
        setGrid.setOnAction(event -> {
            try {
                gridSize.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        VBox vBox = new VBox(50, play, leaderbord, speed, setGrid);
        vBox.setTranslateX(200);
        vBox.setTranslateY(50);
        root.getChildren().addAll(rect, vBox);
        stage.show();
    }

    
}
