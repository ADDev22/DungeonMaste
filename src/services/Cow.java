package services;

public interface Cow extends Entity{

	/*pré et post init*/
	
	/** 
	 * \pre  e != null && d !=null && 0 ≤ col < e.Width() && 0 ≤ y < e.Height() && 4 >= hp >= 3
	 * \post Col()==col && Row()==row && Env()==e && Face()==d 	&& Hp()==hp && Drop_Spoil()==null.
	 * */
	
	/* pré et post Step*/
	
	/**
	 * \pre true
	 * \post Col() @pré -1 ≤ Col() ≤ Col() @pré + 1 && Row() @pré - 1 ≤ Row() ≤ Row() @pré + 1. 
	 */
	
	/**
	 * \pre spoil != null 
	 * @param spoil 
	 * \post Drop_Spoil() == spoil
	 */
	public void Set_Spoil(IMob spoil);
	public IMob Drop_Spoil();
}
