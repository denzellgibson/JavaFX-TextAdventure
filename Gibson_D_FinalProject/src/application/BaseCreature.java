package application;

public class BaseCreature {
	private int health, armorClass, attackBonus, damage, maxHealth;
	
	public BaseCreature( //Constructor has four int parameters.
			int health,
			int armorClass,
			int attackBonus,
			int damage)
	{
		setHealth(health);
		setArmorClass(armorClass);
		setAttackBonus(attackBonus);
		setDamage(damage);
		setMaxHealth(health);
	}
	
	//Setter and getter methods.
	public void setHealth(int newHealth){
		health = newHealth;
	}
	
	public void setArmorClass(int newArmorClass){
		armorClass = newArmorClass;
	}
	
	public void setAttackBonus(int newAttackBonus){
		attackBonus = newAttackBonus;
	}
	
	public void setDamage(int newDamage){
		damage = newDamage;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getArmorClass(){
		return armorClass;
	}
	
	public int getAttackBonus(){
		return attackBonus;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getMana(){
		return 0;
	}
	
	public int getDamageReduction(){
		return 0;
	}
	
	public int getSpellBonus(){
		return 0;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public void setMaxHealth(int health){
		maxHealth = health;
	}
}

