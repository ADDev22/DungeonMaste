package contracts;

import decorateur.EngineDecorator;
import errors.*;
import services.Engine;
import services.Entity;
import services.Environement;

public class EngineContract extends EngineDecorator {

	public EngineContract(Engine delegate) {
		super(delegate);
	}
	
	public void checkInvariants() throws InvariantError, PreConditionError {
		
		Entity[] entities = Entities();
		for(int i=0;i<entities.length;i++) {
			if( entities[i].Env() != Envi() ) { throw new InvariantError("Engine's Env doesn't match with entities Env") ; }
			//if(((Entity)Envi().CellContent(entities[i].Col(), entities[i].Row())) != entities[i]) { throw new InvariantError("cell content doesn't contain the specified entity") ; }
		}
		
		
	
	}

	@Override
	public Environement Envi() {
	
		return super.Envi();
	}

	@Override
	public Entity[] Entities() {
	
		return super.Entities();
	}

	@Override
	public Entity getEntity(int entity) {
		
		return super.getEntity(entity);
	}

	@Override
	public void init(Environement env) throws PreConditionError, InvariantError, PostConditionError {
		if(env == null) { throw new PreConditionError("env == null");}
		super.init(env);
		this.checkInvariants();
		if(this.Envi() != env) { throw new PostConditionError("Envi() doesn't return the initial env"); }
	}


	
	@Override
	public void removeEntity(int entity) throws PreConditionError,PostConditionError,InvariantError {
		if(entity<0 || entity>= Entities().length) { throw new PreConditionError("index out of bound");}
		int size = Entities().length;
		Entity[] copy = new Entity[size];
		for(int i=0;i<size;i++) { copy[i] = Entities()[i];}
		checkInvariants();
		super.removeEntity(entity);
		checkInvariants();
		if(size != Entities().length +1) { throw new PostConditionError("size was supposed to decrease by 1");}
		for(int i=0;i<entity;i++) { if ( getEntity(i) !=copy[i]) { throw new PostConditionError("index of entity has changed");} }
		for(int i=entity;i<size-1;i++) { if (getEntity(i) != copy[i+1]){ throw new PostConditionError("index of entity was supposed to decrease by 1"); }}
		
		
	}

	
	
	@Override
	public void addEntity(Entity entity) throws InvariantError,PostConditionError,PreConditionError {
		if(entity == null) {throw new PreConditionError("null entity");}
		int size = Entities().length;
		Entity[] copy = new Entity[size];
		for(int i=0;i<size;i++) { copy[i] = Entities()[i];}
		checkInvariants();
		super.addEntity(entity);
		checkInvariants();
		if(size +1 != Entities().length ) { throw new PostConditionError("size was supposed to increase by 1");}
		for(int i=0;i<size;i++) { if ( getEntity(i) !=copy[i]) { throw new PostConditionError("index of entity has changed");} }
	    if (getEntity(size) != entity){ throw new PostConditionError("last entity doesn't match with added entity"); }
		
	}

	@Override
	public void step() throws InvariantError, PostConditionError, PreConditionError {
		int i=0;
		while(i<Entities().length){ if (Entities()[i].Hp()<=0) {removeEntity(i);}else {i++;} }
		checkInvariants();
		super.step();
		checkInvariants();
	}

}
