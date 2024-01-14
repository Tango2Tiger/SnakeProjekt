import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.*;

//Magnus
public class MainMenu extends Application{
    //The Main menu which contains the initial grid size, speed, leaderboard, the multiplayer option and the mute
    public static int grid_height = 15;
    public static int grid_width = 15;
    public static GamePanel game = new GamePanel();
    public static Leaderbord leaders = new Leaderbord();
    public static Speed setSpeed = new Speed();
    public static GridSize gridSize = new GridSize();
    public static Multiplayer multi = new Multiplayer();
    public static long speed = 100000000;
    public static boolean isMulti = false;
    public static boolean soundOn = true;

    //The background music
    static String path = "Wii.mp3"; 
    static Media media = new Media(new File(path).toURI().toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(media);
    
    //Main method to launch the application
    public static void main(String[] args) {
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.2);
        launch(args);
    }

    //Override method to set the MainMenu menu up 
    @Override
    public void start(Stage stage) throws Exception{
        //Group to contain the UI elements
        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);
        Font font = Font.font(30);
        
        //Our snake icon, which shows when running the program
        Image icon = new Image(getClass().getResourceAsStream("snake.png"));
        stage.getIcons().add(icon);

        //Button for starting the game
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

        //The leaderboard button
        Button leaderbord = new Button("Leaderboard");
        leaderbord.setFont(font);
        leaderbord.setMinSize(400, 50);
        leaderbord.setOnAction(event -> {
            Leaderbord leaders = new Leaderbord();
            try {
                leaders.start(stage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        //The speed buttons
        Button speedbtn = new Button("Speed");
        speedbtn.setMinSize(400, 50);
        speedbtn.setFont(font);
        speedbtn.setOnAction(event -> {
            Speed setSpeed = new Speed();
            setSpeed.start(stage);
        });

        //The grid size button
        Button setGrid = new Button("Grid Size");
        setGrid.setMinSize(400, 50);
        setGrid.setFont(font);
        setGrid.setOnAction(event -> {
            GridSize gridSize = new GridSize();
            try {
                gridSize.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Mute button, to turn off the music
        Image on = new Image(getClass().getResourceAsStream("soundon.png"));
        Image off = new Image(getClass().getResourceAsStream("soundoff.png"));
        ImageView soundon = new ImageView(on);
        ImageView soundoff = new ImageView(off);

        Button mute = new Button("",soundon);


        mute.setFont(font);
        
        mute.setMinSize(30, 30);
        mute.setOnAction(event -> {
            soundOn = !soundOn;
            if(soundOn){
                mute.setGraphic(soundon);
                mediaPlayer.play();
            } else{
                mute.setGraphic(soundoff);
                mediaPlayer.pause();
            }
        });

        //Multiplayer button, to enable or disable multiplayer
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

        //VBox to organize all the UI elements
        VBox vBox = new VBox(50, play, leaderbord, speedbtn, setGrid, multiplayer, mute);
        vBox.setTranslateX(200);
        vBox.setTranslateY(30);

        //VBox to organize sound UI-elements
        VBox soundBox = new VBox(mute);
        soundBox.setTranslateX(20);
        soundBox.setTranslateY(500);

        root.getChildren().addAll(rect, vBox, soundBox);
        stage.setScene(scene);
        stage.show();
    }
}
