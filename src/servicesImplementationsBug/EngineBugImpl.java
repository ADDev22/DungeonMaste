package servicesImplementationsBug;

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

public class EngineBugImpl implements Engine{

	private Environement env;
	private List<Entity> entities; 
	
	
	@Override
	public Environement Envi() {
		return env;
	}

	@Override
	public Entity[] Entities() {
		Entity[] e = new Entity[entities.size()];
		for(int i=0;i<entities.size()-1;i++)
			e[i] = entities.get(i);
		
		return e;
	}

	@Override
	public Entity getEntity(int entity) {
		if(entity+1 >= 0 && entity+1 < entities.size()) return entities.get(entity+1); 
		return null;
	}

	@Override
	public void init(Environement env) {
		this.env=env;
		entities = new ArrayList<>(); 
	}

	@Override
	public void removeEntity(int entity) throws PreConditionError, PostConditionError {
		if(entity+1 >= 0 && entity+1 < entities.size()) { 
			Entity e = entities.remove(entity+1); 
			env.setCellContent(e.Col(), e.Row()+1, null); 
		}
		return ;
	}

	@Override
	public void addEntity(Entity entity) {
		entities.set(0,entity);
	}

	@Override
	public void step() throws InvariantError, PostConditionError, PreConditionError {
		
		IMob spoil=null;
		Cow c;
		List<Entity> copy = new ArrayList<>(entities);
		List<Entity> to_delete = new ArrayList<>();
		
		
		
		
		for(Entity eee : copy) {
			
			for(Entity e : entities) if(e.Hp()==1) {
				
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
