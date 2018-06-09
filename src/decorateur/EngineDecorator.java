package decorateur;

import errors.*;
import services.Engine;
import services.Entity;
import services.Environement;

public class EngineDecorator implements Engine{

	
	protected Engine delegate;
	
	public EngineDecorator(Engine delegate) {
		this.delegate=delegate;
	}
	
	@Override
	public Environement Envi() {
		return delegate.Envi();
	}

	@Override
	public Entity[] Entities() {
		return delegate.Entities();
	}

	@Override
	public Entity getEntity(int entity) {
	
		return delegate.getEntity(entity);
	}

	@Override
	public void init(Environement env) throws PreConditionError, InvariantError, PostConditionError {
		delegate.init(env);
	}

	@Override
	public void removeEntity(int entity) throws PreConditionError, PostConditionError, InvariantError {
		delegate.removeEntity(entity);
		
	}

	@Override
	public void addEntity(Entity entity) throws InvariantError, PostConditionError, PreConditionError {
		delegate.addEntity(entity);
		
	}

	@Override
	public void step() throws InvariantError, PostConditionError, PreConditionError {
		delegate.step();
		
	}

}
