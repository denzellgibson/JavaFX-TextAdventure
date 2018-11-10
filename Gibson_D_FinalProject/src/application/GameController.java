package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

//Controller for the MainGame screen.
public class GameController implements Initializable, ControlledScreen{
	
	ScreensController myController;

	//All items used in the scene.
	@FXML
	private Button attackButton;
	@FXML
	private Button defendButton;
	@FXML
	private Button fleeButton;
	@FXML
	private Button startGameButton;
	@FXML
	private Button exitGameButton;
	@FXML
	private Button nextEnemyButton;
	@FXML
	private Button winButton;
	
	
	@FXML
	private TextField playerHealthTextField;
	@FXML
	private TextField playerManaTextField;
	@FXML
	private TextField playerACTextField;
	@FXML
	private TextField playerDRTextField;
	@FXML
	private TextField playerABTextField;
	@FXML
	private TextField playerDBTextField;
	@FXML
	private TextField playerSBTextField;
	
	@FXML
	private TextField enemyHealthTextField;
	@FXML
	private TextField enemyManaTextField;
	@FXML
	private TextField enemyACTextField;
	@FXML
	private TextField enemyDRTextField;
	@FXML
	private TextField enemyABTextField;
	@FXML
	private TextField enemyDBTextField;
	@FXML
	private TextField enemySBTextField;
	
	@FXML
	private TextArea storyTextArea;
	
	@FXML
	private Label playerLabel;
	@FXML
	private Label enemyLabel;
	
	//Declares and initializes variables.
	public int enemyCounter = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		
		//Upon start.
		attackButton.setDisable(true);
		defendButton.setDisable(true);
		fleeButton.setDisable(true);
		nextEnemyButton.setDisable(true);
		winButton.setDisable(true);
		
		Model.setCurrentEnemy(Model.goblin);
		enemyCounter += 1;
	}
	
	//Sets the screen that is passed in as the parent screen.
	public void setScreenParent(ScreensController screenParent){
		myController = screenParent;
	}
	
	//Changes screen to the win screen.
	@FXML
	public void goToWinScreen(ActionEvent event){
		myController.setScreen(Game.winScreenID);
	}
	
	//Changes screen to the intro screen.
	@FXML
	public void goToIntroScreen(ActionEvent event){
		myController.setScreen(Game.introScreenID);
	}
	
	//Button ActionEvent that allows the player to attack and does the necessary calculations for both player and enemy attack.
	public void playerAttack(ActionEvent event){
		System.out.println("Button Clicked!");
		
		//If enemy is still alive.
		if (Model.getCurrentEnemy().getHealth() > 0 && Model.getCombat()==true){
			int attackRoll, damageRoll;
			Random rand = new Random();
			
			storyTextArea.appendText("\nYou attack!");
			//Rolls attack roll, adding attack bonus to roll.
			attackRoll = (rand.nextInt(20) + 1) + Model.player.getAttackBonus();
			if (attackRoll >= Model.getCurrentEnemy().getArmorClass()){ //If attack roll is higher than enemy's armor class, roll damage.
				storyTextArea.appendText("\nYou hit!");
				//Rolls damage roll, adding player bonus damage and subtracting using enemy damage reduction.
				damageRoll = ((rand.nextInt(10) + 1) + Model.player.getDamage()) - Model.getCurrentEnemy().getDamageReduction();
				//If damageRoll is 0 or less, does no damage.
				if (damageRoll <= 0){
					storyTextArea.appendText("\nBut the monster shrugs off the hit!");
					updatePlayerInformation();
					updateMonsterInformation(Model.getCurrentEnemy());
				}
				else{
					//Applies damage to enemies health.
					Model.getCurrentEnemy().setHealth(Model.getCurrentEnemy().getHealth() - damageRoll);
					storyTextArea.appendText("\nYou deal "+damageRoll+" points of damage!");
					updatePlayerInformation();
					updateMonsterInformation(Model.getCurrentEnemy());
				}
			}
			else {
				storyTextArea.appendText("\nBut you missed.");
			}
			
			//Same as player but with enemy.
			storyTextArea.appendText("\nThe monster attacks!");
			attackRoll = (rand.nextInt(20)+1) + Model.getCurrentEnemy().getAttackBonus();
			if (attackRoll >= Model.player.getArmorClass()){
				storyTextArea.appendText("\nAnd you were hit!");
				damageRoll = ((rand.nextInt(10) + 1) + Model.getCurrentEnemy().getDamage()) - (Model.player.getDamageReduction() + 3);
				if (damageRoll < 0 || damageRoll == 0){
					storyTextArea.appendText("\nBut the hit did not pierce through!");
					updatePlayerInformation();
					updateMonsterInformation(Model.getCurrentEnemy());
				}
				else{
					Model.player.setHealth(Model.player.getHealth() - damageRoll);
					storyTextArea.appendText("\nYou have taken "+damageRoll+" points of damage!");
					updatePlayerInformation();
					updateMonsterInformation(Model.getCurrentEnemy());
				}
			}
			else{
				storyTextArea.appendText("\nThe monster takes a swing at you but blocked it with your shield!");
				
				updatePlayerInformation();
				updateMonsterInformation(Model.getCurrentEnemy());
			}
		}
		
		//Checks if player is dead, and makes player lose the game if they are.
		if (Model.player.getHealth() <= 0){
			
			attackButton.setDisable(true);
			defendButton.setDisable(true);
			fleeButton.setDisable(true);
			nextEnemyButton.setDisable(true);
			
			Model.setStoryState("\nYou were knocked down by the enemy's attack."
					+ "\nBefore you can get back up, the enemy swings and hits your head."
					+ "\nYour vision becomes blurry."
					+ "\nThe light is fading from the world."
					+ "\nYou are consumed by darkness."
					+ "\nYou are dead.");
			appendTextArea(Model.getStoryState());
		}
		
		//Checks if the current enemy is dead, enabling 'next enemy' button and disabling others.
		if (Model.getCurrentEnemy().getHealth() <= 0){
			
			enemyCounter += 1;
			attackButton.setDisable(true);
			defendButton.setDisable(true);
			fleeButton.setDisable(true);
			nextEnemyButton.setDisable(false);
		}
		
		//Same as before but with orc enemy and player wins if true.
		if (Model.orc.getHealth() <= 0){
			
			attackButton.setDisable(true);
			defendButton.setDisable(true);
			fleeButton.setDisable(true);
			nextEnemyButton.setDisable(true);
			winButton.setDisable(false);
			
			Model.setStoryState("\nYou defeated the Orc Shaman"
					+ "\n"
					+ "\nYou take a few moments to recuperate and catch your breath."
					+ "\nLooking around, you see that you are at the end of the cave."
					+ "\nConfident that you took care of all the monsters that the bounty requires, \nyou preceed to collect the heads of the two monsters."
					+ "\nYou put out the fire and leave the cave."
					+ "\nWith the heads bouncing round in your bag, you feel happy to do a good deed and earned your pay."
					+ "\nYou head home."
					+ "\n"
					+ "\nYou win!");
			appendTextArea(Model.getStoryState());
		}
	}
	
	//Reduces damage and heals player, while enemy attacks.
	public void playerDefend(ActionEvent event){
		System.out.println("Button Clicked!");
		
		//Declares variables.
		int attackRoll, damageRoll;
		Random rand = new Random();
		
		storyTextArea.appendText("\nYou defend!");
		//Checks if player has enough mana to heal and if is already at full health. Still defends if false, but with no healing.
		if (Model.player.getMana() >= 10 && Model.player.getHealth() < Model.player.getMaxHealth()){
			Model.player.setMana(Model.player.getMana() - 10);
			//If player's health is within a certain range that would allow overhealing, automatically set to max health.
			if (60 - Model.player.getHealth() < 5){
				Model.player.setHealth(60);
				storyTextArea.appendText("\nYou healed to max health!");
				updatePlayerInformation();
			}
			//Otherwise add spell bonus to health.
			else{
				Model.player.setHealth(Model.player.getHealth() + Model.player.getSpellBonus());
				storyTextArea.appendText("\nYou healed "+Model.player.getSpellBonus()+" hit points!");
				updatePlayerInformation();
			}
			
		}
		//If previous condition is false, gives the appropriate reason for being unable to heal.
		else {
			if (Model.player.getMana() < 10){
				storyTextArea.appendText("\nInsufficient Mana!");
			}
			else {
				storyTextArea.appendText("\nAlready at full Health");
			}
		}
		
		//Same as player attack, but with enemy.
		storyTextArea.appendText("\nThe monster swings!");
		attackRoll = (rand.nextInt(20)+1) + Model.getCurrentEnemy().getAttackBonus();
		if (attackRoll >= Model.player.getArmorClass()){
			storyTextArea.appendText("\nAnd you were hit!");
			damageRoll = ((rand.nextInt(10) + 1) + Model.getCurrentEnemy().getDamage()) - Model.player.getDamageReduction();
			if (damageRoll < 0 || damageRoll == 0){
				storyTextArea.appendText("\nBut the hit did not pierce through!");
				updatePlayerInformation();
				updateMonsterInformation(Model.getCurrentEnemy());
			}
			else{
				Model.player.setHealth(Model.player.getHealth() - damageRoll);
				storyTextArea.appendText("\nYou have taken "+damageRoll+" points of damage!");
				updatePlayerInformation();
				updateMonsterInformation(Model.getCurrentEnemy());
			}
		}
		else{
			storyTextArea.appendText("\nThe monster takes a swing at you but blocked it with your shield!");
			
			updatePlayerInformation();
			updateMonsterInformation(Model.getCurrentEnemy());
		}
		
		if (Model.player.getHealth() <= 0){
			
			attackButton.setDisable(true);
			defendButton.setDisable(true);
			fleeButton.setDisable(true);
			nextEnemyButton.setDisable(true);
			
			Model.setStoryState("\nYou were knocked down by the enemy's attack."
					+ "\nBefore you can get back up, the enemy swings and hits your head."
					+ "\nYour vision becomes blurry."
					+ "\nThe light is fading from the world."
					+ "\nYou are consumed by darkness."
					+ "\nYou are dead.");
			appendTextArea(Model.getStoryState());
		}
	}
	
	//Makes the player lose the game, but with flavor text.
	public void playerFlee(ActionEvent event){
		System.out.println("Button Clicked!");
		
		attackButton.setDisable(true);
		defendButton.setDisable(true);
		fleeButton.setDisable(true);
		nextEnemyButton.setDisable(true);
		
		Model.setStoryState("\nYou flee in terror as the enemy chases after you."
				+ "\nAfter several minutes of running, you lose the enemy."
				+ "\nCatching your breath, you realize that you are not cut out to be an adventurerer."
				+ "\nYou decide to head home."
				+ "\n"
				+ "\nYou lose.");
		appendTextArea(Model.getStoryState());
	}
	
	//Enables buttons, starts the story in the text area, and populates the textfields for both player and enemy.
	public void startGame(ActionEvent event){
		System.out.println("Button Clicked!");
		
		updatePlayerInformation();
		updateMonsterInformation(Model.goblin);
		
		attackButton.setDisable(false);
		defendButton.setDisable(false);
		fleeButton.setDisable(false);
		
		Model.setStoryState(
				"There has been trouble recently in the farmlands."
				+ "\nMonsters have been seen roaming and terrorizing farmers."
				+ "\nA bounty has been put out for anyone who can kill and \nbring back the heads of the these monsters."
				+ "\nThrough your skills of gathering information, \nyou have a strong lead that the monsters are holding up in a cave up in the mountains."
				+ "\nWith your spells prepared, weapons sharpened, and armor polished, you set out."
				+ "\n"
				+ "\nA few days later.\n "
				+ "\nYou approach a dark and eerie cave."
				+ "\nStanding at the entrance is a small, gross, mean goblin!"
				);
		appendTextArea(Model.getStoryState());
	}
	
	//Quits the game.
	public void exitGame(ActionEvent event){
		System.out.println("Button Clicked!");
		
		System.exit(0);
	}
	
	//Changes the current enemy to the next enemy, and updates the enemy textfields.
	public void nextEnemy(ActionEvent event){
		System.out.println("Button Clicked!");
		
		Model.setCurrentEnemy(Model.orc);
		updateMonsterInformation(Model.getCurrentEnemy());
		Model.setStoryState("\nYou have defeated the goblin!"
				+ "\n"
				+ "\nYou continue on and enter the cave."
				+ "\nThe cave quickly gets darker and darker, so you light a torch."
				+ "\nAfter a few minutes of delving deeper and deeper into the cave, you see fire off into the distance."
				+ "\nYou take a few steps forward to get a closer look."
				+ "\nStanding next to the fire is a Orc Shaman!"
				+ "\nAfter stepping closer a few steps, the Orc Shaman notices you!"
				+ "\n"
				+ "\nCombat Start!");
		appendTextArea(Model.getStoryState());
		
		attackButton.setDisable(false);
		defendButton.setDisable(false);
		fleeButton.setDisable(false);
	}
	
	//Loads the win screen.
	public void finishGame(ActionEvent event){
		System.out.println("Button Clicked!");
		
		goToWinScreen(event);
	}
	
	//Changes the appropriate textfield text.
	public void updateHealth(TextField textfield, BaseCreature creature){
		textfield.setText(Integer.toString(creature.getHealth()));
	}
	
	public void updateMana(TextField textfield, BaseCreature creature){
		textfield.setText(Integer.toString(creature.getMana()));
	}
	
	public void updateAC(TextField textfield, BaseCreature creature){
		textfield.setText(Integer.toString(creature.getArmorClass()));
	}
	
	public void updateDR(TextField textfield, BaseCreature creature){
		textfield.setText(Integer.toString(creature.getDamageReduction()));
	}
	
	public void updateAB(TextField textfield, BaseCreature creature){
		textfield.setText(Integer.toString(creature.getAttackBonus()));
	}
	
	public void updateDB(TextField textfield, BaseCreature creature){
		textfield.setText(Integer.toString(creature.getDamage()));
	}
	
	public void updateSB(TextField textfield, BaseCreature creature){
		textfield.setText(Integer.toString(creature.getSpellBonus()));
	}
	
	public void updatePlayerInformation(){
		updateHealth(playerHealthTextField, Model.player);
		updateMana(playerManaTextField, Model.player);
		updateAC(playerACTextField, Model.player);
		updateDR(playerDRTextField, Model.player);
		updateAB(playerABTextField, Model.player);
		updateDB(playerDBTextField, Model.player);
		updateSB(playerSBTextField, Model.player);
	}
	
	public void updateMonsterInformation(BaseCreature creature){
		updateHealth(enemyHealthTextField, creature);
		updateAC(enemyACTextField, creature);
		updateDR(enemyDRTextField, creature);
		updateAB(enemyABTextField, creature);
		updateDB(enemyDBTextField, creature);
		updateMana(enemyManaTextField, creature);
		updateSB(enemySBTextField, creature);
	}
	
	//Appends the storyTextArea.
	public void appendTextArea(String story) {
		storyTextArea.appendText(story);
	}
}
