package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

//Controller for the winScreen.
public class WinController implements Initializable, ControlledScreen{

	ScreensController myController;
	
	//Declares items in scene.
	@FXML
	private Button leaveGameButton;
	
	@Override
	public void initialize(URL url, ResourceBundle rb){
		
	}
	
	//Sets parent screen.
	public void setScreenParent(ScreensController screenParent){
		myController = screenParent;
	}
	
	//Switches active screen to game screen.
	@FXML
	private void goToGameScreen(ActionEvent event){
		myController.setScreen(Game.gameScreenID);
	}
	
	//Switches active screen to intro screen.
	@FXML
	private void goToIntroScreen(ActionEvent event){
		myController.setScreen(Game.introScreenID);
	}
	
	//Quits the game.
	public void leaveGame(ActionEvent event){
		System.exit(0);
	}
}
