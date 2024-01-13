import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.time.Duration;

import javax.print.DocFlavor.URL;
import javafx.application.*;

public class MainMenu extends Application{
    public static int grid_height = 15;
    public static int grid_width = 15;
    public static GamePanel game = new GamePanel();
    public static Leaderbord leaders = new Leaderbord();
    public static Speed setSpeed = new Speed();
    public static GridSize gridSize = new GridSize();
    // Unødvendig? public static MainMenu menu = new MainMenu();
    public static Multiplayer multi = new Multiplayer();
    public static long speed = 200000000;
    public static boolean isMulti = false;

    static String path = "Wii.mp3"; 
    static Media media = new Media(new File(path).toURI().toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(media);
        
    public static void main(String[] args) {
        mediaPlayer.setAutoPlay(true);
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);
        Font font = Font.font(30);
        
        Image icon = new Image(getClass().getResourceAsStream("snake.png"));
        stage.getIcons().add(icon);

        Button play = new Button("Play");
        play.setFont(font);
        play.setMinSize(400, 50);
        play.setOnAction(event -> {
            mediaPlayer.pause();
            if(isMulti){
                Multiplayer.speed = speed;
                Multiplayer.GRID_HEIGHT = grid_height;
                Multiplayer.GRID_WIDTH = grid_width;
                Multiplayer game = new Multiplayer();
                try {
                    game.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                GamePanel.speed = speed;
                GamePanel.GRID_HEIGHT = grid_height;
                GamePanel.GRID_WIDTH = grid_width;
                GamePanel game = new GamePanel();
                try {
                    game.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Button leaderbord = new Button("Leaderbord");
        leaderbord.setFont(font);
        leaderbord.setMinSize(400, 50);
        leaderbord.setOnAction(event -> leaders.start(stage));

        Button speedbtn = new Button("Speed");
        speedbtn.setMinSize(400, 50);
        speedbtn.setFont(font);
        speedbtn.setOnAction(event -> setSpeed.start(stage));

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


        Button multiplayer = new Button("Multiplayer: OFF");
        multiplayer.setFont(font);
        multiplayer.setMinSize(400, 50);
        multiplayer.setOnAction(event -> {
            isMulti = !isMulti;
            if(isMulti){
                multiplayer.setText("Multiplayer: ON");
            } else{
                multiplayer.setText("Multiplayer: OFF");
            }
        });


        VBox vBox = new VBox(50, play, leaderbord, speedbtn, setGrid, multiplayer);
        vBox.setTranslateX(200);
        vBox.setTranslateY(50);
        root.getChildren().addAll(rect, vBox);
        stage.setScene(scene);
        stage.show();
    }
}
