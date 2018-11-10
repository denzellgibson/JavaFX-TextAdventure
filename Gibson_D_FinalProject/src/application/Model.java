package application;

//Class that holds most of the data used by the game.

public class Model {

	//Declares and initializes variables to be used when instantiating objects.
	//Easy to adjust numbers for the purposes of game design.
	private static int playerHealth = 60, playerAC = 16,  playerAB = 6, playerDamage = 7, playerMana = 30, playerDR = 4, playerSB = 5;
	private static int goblinHealth = 25, goblinAC = 13, goblinAB = 4, goblinDamage = 4, goblinDR = 2;
	private static int orcHealth = 50, orcAC = 15, orcAB = 5, orcDamage = 8, orcMana = 30, orcSB = 4;
	
	//Declares variables.
	private static String storyState;
	private static boolean isCombat = true;
	private static BaseCreature currentEnemy;
	
	//Creates player object to be used in the game.
	static Player player = new Player(
			playerMana, 
			playerDR, 
			playerSB,
			playerHealth,
			playerAC,
			playerAB,
			playerDamage);
	
	//Creates GoblinWarrior object to be used in the game.
	static GoblinWarrior goblin = new GoblinWarrior(
			goblinDR,
			goblinHealth,
			goblinAC,
			goblinAB,
			goblinDamage);
	
	//Creates OrcShaman object to be used in the game.
	static OrcShaman orc = new OrcShaman(
			orcMana,
			orcSB,
			orcHealth,
			orcAC,
			orcAB,
			orcDamage);
	
	//Setter and getter methods.
	public static String getStoryState(){
		return storyState;
	}
	
	public static void setStoryState(String story){
		storyState = story;
	}
	
	public static BaseCreature getCurrentEnemy(){
		return currentEnemy;
	}
	
	public static void setCurrentEnemy(BaseCreature creature){
		currentEnemy = creature;
	}
	
	public static boolean getCombat(){
		return isCombat;
	}
	
	public static void setCombat(boolean combat){
		isCombat = combat;
	}
}
