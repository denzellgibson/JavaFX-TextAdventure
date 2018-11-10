package application;

public class GoblinWarrior extends BaseCreature{
	
	//Declares variables.
	private int damageReduction;
	
		public GoblinWarrior( //Constructor that has five int parameters.
			int damageReduction,
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
		
		setDamageReduction(damageReduction);
	}
	
	//Getter and setter methods.
	public void setDamageReduction(int newDamageReduction){
		damageReduction = newDamageReduction;
	}
	
	public int getDamageReduction(){
		return damageReduction;
	}
}
