package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;

public class Game extends Application{
	
	//Declares and initalizes String variables to be used to load screens.
	public static String gameScreenID = "main";
	public static String gameScreenFile = "/application/MainGame.fxml";
	public static String winScreenID = "winScreen";
	public static String winScreenFile = "/application/WinScreen.fxml";
	public static String introScreenID = "introScreen";
	public static String introScreenFile = "/application/IntroScreen.fxml";
	
	@Override
    public void start(Stage primaryStage) {
        try {
        	
        	//Loads all three screens in the game application.
        	ScreensController mainContainer = new ScreensController();
            mainContainer.loadScreen(Game.gameScreenID, Game.gameScreenFile);
            mainContainer.loadScreen(Game.winScreenID, Game.winScreenFile);
            mainContainer.loadScreen(Game.introScreenID, Game.introScreenFile);
            
            //Sets the intro screen as the one to appear when program starts.
            mainContainer.setScreen(Game.introScreenID);
            
            Group root = new Group();
            root.getChildren().addAll(mainContainer);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
         
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
    public static void main(String[] args) {
        launch(args);
        
	}
}
