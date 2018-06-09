package contracts;

import services.IMob;

public class WeaponContract  extends IMobContract{

	@Override
	public String toString() {
		return "Weapon";
	}
	
	public WeaponContract(IMob delegate) {
		super(delegate);
		
	}
	
	

}
