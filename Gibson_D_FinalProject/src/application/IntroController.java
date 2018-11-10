package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

//Controller for the IntroScreen.

public class IntroController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    //Declarations for items in scene.
    @FXML
	private Button startGameButton;
    
    @FXML
    private TextArea instructionsTextArea;
    
    //Declares variables.
    String instructions, toWin, details;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    	//Initializes string variables with instructions and details.
    	instructions = "Instructions: On the main game screen, press 'Start Game' to start."
    			+ "\nStory and combat information appears in the text area"
    			+ "\nPress the 'Attack' button to attack the enemy for the turn"
    			+ "\nPress the 'Defend' button to heal and reduce incoming damage for the turn."
    			+ "\nPress the 'Flee' button to run from battle."
    			+ "\nIf 'Attack or 'Defend' is chosen, enemy will also decide and act out their choice."
    			+ "\nWhen the enemy is dead, press 'Next Enemy' to fight the next enemy after defeating the current enemy."
    			+ "\nWhen all enemies are dead, press the 'Winner!' button to see the win screen."
    			+ "\nYou lose when you flee or lose all your health."
    			+ "\nPress the 'Exit Game' button to exit the game at anytime."
    			+ "\n";
    	
    	toWin = "\nTo Win: Progress through the story, defeat all enemies, and survive to win."
    			+ "\n";
    	
    	details = "\nHealth: Amount left before you die."
    			+ "\nMana: Needed to heal when defending."
    			+ "\nArmor Class: Attack Roll needed to hit."
    			+ "\nDamage Reduction: Reduces the amount of damage taken."
    			+ "\nAttack Bonus: A bonus to the attack roll."
    			+ "\nSpell Bonus: A bonus to spell effects.";
    	
    	//Populates textArea with string variables.
    	instructionsTextArea.appendText(instructions);
    	instructionsTextArea.appendText(toWin);
    	instructionsTextArea.appendText(details);
    	instructionsTextArea.setScrollTop(0);
    }    
    
    //Set screen parent.
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    //Switches active screen to the gameScreen.
    @FXML
    private void goToGameScreen(ActionEvent event){
       myController.setScreen(Game.gameScreenID);
    }
    
    //Switches active screen to the winScreen.
    @FXML
    private void goToWinScreen(ActionEvent event){
       myController.setScreen(Game.winScreenID);
    }
    
    //Button event that loads the gameScreen.
    public void startGame(ActionEvent event){
    	goToGameScreen(event);
    }
}
