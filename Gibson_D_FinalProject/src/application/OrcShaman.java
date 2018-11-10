package application;

public class OrcShaman extends BaseCreature{

	//Declares variables.
	private int mana;
	private int spellBonus;
	
	public OrcShaman( //Constructor that has six int parameters.
			int mana,
			int spellBonus,
			int health,
			int armorClass,
			int attackBonus,
			int damage)
	{
		super(
				health,
				armorClass,
				attackBonus,
				damage);
		
		setMana(mana);
		setSpellBonus(spellBonus);
	}
	
	//Setter and getter methods.
	public void setMana(int newMana){
		mana = newMana;
	}
	
	public void setSpellBonus(int newSpellBonus){
		spellBonus = newSpellBonus;
	}
	
	public int getMana(){
		return mana;
	}
	
	public int getSpellBonus(){
		return spellBonus;
	}
}
