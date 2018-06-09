package tests.mbt;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EnvironementContract;
import contracts.MapContract;
import contracts.PlayerContract;
import enumeration.Cell;
import enumeration.Dir;
import errors.PostConditionError;
import errors.PreConditionError;
import services.EditMap;
import services.Engine;
import services.Entity;
import services.Environement;
import services.Map;
import servicesImplementations.CowImpl;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EngineImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.MapImpl;
import servicesImplementations.PlayerImpl;

class EngineTest {
    
	 Engine engine;
	
	/**
	 * Objectif de Test: init(env) faillure
	 * 
	 * Cas de Test:  engine.init(env)
	 * 		 env=null
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  engine.init(null)
	 */
	@Test
    public void testInitFaillure()
    {
		Environement env =null;
		try {
			Engine engine = new EngineContract(new EngineImpl());
			engine.init(env);
			fail();
		} catch (PreConditionError e) {
			
		}
		
    }
	
	
	/**
	 * Objectif de Test: init(env) faillure
	 * 
	 * Cas de Test:  engine.init(env)
	 * 		 env!=null
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  engine.init(env)
	 */
	@Test
    public void testInitSuccess()
    {
		Environement env = new  EnvironementContract(new EnvironementImpl());
		try {
			Engine engine = new EngineContract(new EngineImpl());
			engine.init(env);
			
		} catch (Error e) {
			fail();
			
		}
		
    }
	/**
	 * Objectif de Test: removeEntity(entity) faillure
	 * 
	 * Cas de Test:  engine.removeEntity(entity)
	 * 		 entity < 0
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  engine.removeEntit(-2)
	 */
	@Test
    public void testremoveEntityFaillure()
    {
		Environement env = new  EnvironementContract(new EnvironementImpl());
		try {
			Engine engine = new EngineContract(new EngineImpl());
			engine.init(env);
			engine.removeEntity(-2);
			fail();
		} catch (PreConditionError e) {
		}
		
    }
	
	/**
	 * Objectif de Test: removeEntity(entity) faillure
	 * 
	 * Cas de Test:  engine.removeEntity(entity)
	 * 		 entity > entities.length
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  engine.removeEntit(-2)
	 */
	@Test
    public void testremoveEntityFaillure2()
    {
		Environement env = new  EnvironementContract(new EnvironementImpl());
		try {
			Engine engine = new EngineContract(new EngineImpl());
			engine.init(env);
			engine.removeEntity(3);
			fail();
		} catch (PreConditionError e) {
		}
		
    }
	/**
	 * Objectif de Test: addEntity(entity) faillure
	 * 
	 * Cas de Test:  engine.addEntity(entity)
	 * 		 entity = null
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  engine.addEntit(null)
	 */
	@Test
    public void testaddEntityFaillure()
    {
		Environement env = new  EnvironementContract(new EnvironementImpl());
		Entity entity=null;
		try {
			Engine engine = new EngineContract(new EngineImpl());
			engine.init(env);
			engine.addEntity(entity);
			fail();
		} catch (PreConditionError e) {
		}
		
    }
	
	/**
	 * Objectif de Test: addEntity(entity) Success
	 * 
	 * Cas de Test:  engine.addEntity(entity)
	 * 		 entity !=null
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  engine.addEntit(entity)
	 */
	@Test
    public void testaddEntitySucceess()
    {
		EditMap map = new EditMapContract(new EditMapImpl());
		map.init(5, 4);
		map.setNature(3, 2,Cell.IN);
		Environement env = new  EnvironementContract(new EnvironementImpl());
		env.init(map);
		
		Entity entity= new PlayerContract(new PlayerImpl());
		entity.init(env, 3, 2, Dir.E,10);
		
		try {
			Engine engine = new EngineContract(new EngineImpl());
			engine.init(env);
			engine.addEntity(entity);
		} catch (Error e) {
			fail();
		}
		
    }
	
	
	/**
	 * Objectif de Test: step()
	 * 
	 * Cas de Test:  step()
	 *            entity.hp =0;
	 * 		 
	 * 
	 * Condition initiale: Aucune
	 * 
	 * Operation:
	 *  engine.step()
	 */
	@Test
    public void testStepSuccess()
    {
		EditMap map = new EditMapContract(new EditMapImpl());
		map.init(5, 4);
		map.setNature(3, 2,Cell.IN);
		Environement env = new  EnvironementContract(new EnvironementImpl());
		env.init(map);
		Entity entity1= new PlayerContract(new PlayerImpl());
		entity1.init(env, 3, 2, Dir.E,5);
		Entity entity2= new CowContract(new CowImpl());
		entity2.init(env, 3, 0, Dir.N,3);
		Entity entity3= new CowContract(new CowImpl());
		entity3.init(env, 3, 3, Dir.W,4);
		
		try {
			Engine engine = new EngineContract(new EngineImpl());
			engine.init(env);
			engine.addEntity(entity1);
			engine.addEntity(entity2);
			engine.addEntity(entity3);
			engine.step();
		} catch (Error e) {
			fail();
		}
    }
}
