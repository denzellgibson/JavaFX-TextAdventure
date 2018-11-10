package application;

public class Player extends BaseCreature{
	
	//Declares variables.
	private int mana;
	private int damageReduction;
	private int spellBonus;
	
	public Player ( //Constructor that has seven int parameters.
			int mana,
			int damageReduction,
			int spellBonus,
			int health,
			int armorClass,
			int attackBonus,
			int damage)
	{
		super (
			health,
			armorClass,
			attackBonus,
			damage);
		
		setMana(mana);
		setDamageReduction(damageReduction);
		setSpellBonus(spellBonus);
	}
	
	
	//Setters and getters methods.
	public void setMana(int newMana){
		mana = newMana;
	}
	
	public void setDamageReduction(int newDR){
		damageReduction = newDR;
	}
	
	public void setSpellBonus(int newSB){
		spellBonus = newSB;
	}
	
	public int getMana(){
		return mana;
	}
	
	public int getDamageReduction(){
		return damageReduction;
	}
	
	public int getSpellBonus(){
		return spellBonus;
	}
}
