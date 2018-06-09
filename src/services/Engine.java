package services;

import errors.*;

public interface Engine {

	
	public Environement Envi();
	public Entity[] Entities();
	public Entity getEntity(int entity);
	
	/**
	 * \pre env != null
	 * @throws PreconditionError 
	 * @throws InvariantException 
	 * @throws PostconditionError 
	 */
	public void init(Environement env) throws PreConditionError, InvariantError, PostConditionError;
	
	/**
	 * \pre 0 ≤ entity < Entities().length 
	 * \post Entities().length == Entities().length @pré - 1 .
	 * 		 forall k in[0,i-1],getEntity(k) == getEntity(k) @pré.
	 * 		 forall k in[i,Entities().length -2],getEntity(k) == getEntity(k+1) @pré.
	 * @throws InvariantException 
	 * @throws PostconditionError 
	 * @throws PreconditionError 
	*/
	public void removeEntity(int entity) throws PreConditionError, PostConditionError, InvariantError;
	/**
	 *\pre  entity != null 
	 *\post Entities().length == Entities().length @pré + 1.
	 *  	 forall k in[0,Entities().length-2], getEntity(k) == getEntity(k) @pré.
	 *  	 getEntity(Entities().length-1) == entity
	 * @throws PreconditionError 
	 * @throws PostconditionError 
	 * @throws InvariantException 
	 */
	public void addEntity(Entity entity) throws InvariantError, PostConditionError, PreConditionError;
	
	/**
	 * \pre  forall i in[0;Entities().length-1], getEntity(i).Hp() > 0
	 * \post forall i in[0;Entities().length-1], getEntity(i).Hp() <= 0  if e instanceof cow then env.setCellContent(e.Col(), e.Row(), e.Drop_Spoil();); 
	 * @throws PostconditionError 
	 * @throws InvariantException 
	 * @throws PreconditionError 
	 * 
	 */
	public void step() throws InvariantError, PostConditionError, PreConditionError;
	
	
	
	/* Invariants
	 * forall i in[0;Entities().length-1], getEntity(i).Env() == Envi().
	 * forall i in[0;Entities().length-1], getEntity(i).Col() == x and getEntity(i).Raw() == y => Envi().CellContent(x,y) == getEntity(i).
	 * 
	 * 
	 * */
}
