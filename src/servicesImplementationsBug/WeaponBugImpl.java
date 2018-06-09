package servicesImplementationsBug;


import services.Weapon;

public class WeaponBugImpl extends IMobBugImpl implements  Weapon{

	@Override
	public String toString() {
		return "Weapon";
	}	
	
}
