package servicesImplementations;

import java.util.ArrayList;
import java.util.List;

import errors.InvariantError;
import errors.PostConditionError;
import errors.PreConditionError;
import services.Cow;
import services.Engine;
import services.Entity;
import services.Environement;
import services.IMob;

public class EngineImpl implements Engine{

	private Environement env;
	private List<Entity> entities; 
	
	
	@Override
	public Environement Envi() {
		return env;
	}

	@Override
	public Entity[] Entities() {
		Entity[] e = new Entity[entities.size()];
		for(int i=0;i<entities.size();i++)
			e[i] = entities.get(i);
		
		return e;
	}

	@Override
	public Entity getEntity(int entity) {
		if(entity >= 0 && entity < entities.size()) return entities.get(entity); 
		return null;
	}

	@Override
	public void init(Environement env) {
		this.env=env;
		entities = new ArrayList<>(); 
	}

	@Override
	public void removeEntity(int entity) throws PreConditionError, PostConditionError {
		if(entity >= 0 && entity < entities.size()) { 
			Entity e = entities.remove(entity); 
			env.setCellContent(e.Col(), e.Row(), null); 
		}
		return ;
	}

	@Override
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	@Override
	public void step() throws InvariantError, PostConditionError, PreConditionError {
		
		IMob spoil=null;
		Cow c;
		List<Entity> copy = new ArrayList<>(entities);
		List<Entity> to_delete = new ArrayList<>();
		
		
		
		
		for(Entity eee : copy) {
			
			for(Entity e : entities) if(e.Hp()<=0) {
				
				spoil=null;
				
				if(e instanceof Cow) {
					c=(Cow)e; 	
					spoil = c.Drop_Spoil();
				}
				
				env.setCellContent(e.Col(), e.Row(), null);
				env.setCellContent(e.Col(), e.Row(), spoil); 
				to_delete.add(e);
			}
				
			
			
			for(Entity e : to_delete) { entities.remove(e); }
			if(entities.contains(eee)) eee .Step();
			
		
		
			}
		
	}

}
