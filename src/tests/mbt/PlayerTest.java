package tests.mbt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contracts.ArrowContract;
import contracts.CowContract;
import contracts.EditMapContract;
import contracts.EnvironementContract;
import contracts.FoodContract;
import contracts.KeyContract;
import contracts.MagicBeansContract;
import contracts.MonsterContract;
import enumeration.Cell;
import enumeration.Command;
import enumeration.Dir;
import errors.InvariantError;
import errors.PreConditionError;
import services.Arrow;
import services.Cow;
import services.EditMap;
import services.Environement;
import services.Food;
import services.Key;
import services.MagicBeans;
import services.Monster;
import services.Player;
import services.Weapon;
import servicesImplementations.ArrowImpl;
import servicesImplementations.CowImpl;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.FoodImpl;
import servicesImplementations.KeyImpl;
import servicesImplementations.MagicBeansImpl;
import servicesImplementations.MonsterImpl;
import servicesImplementations.PlayerImpl;
import contracts.PlayerContract;

public class PlayerTest {

	protected Player player;
	protected Environement env;
	protected int col;
	protected int row;
	EditMap map;
	
	@Before
	public void beforeTests() {
		env = new EnvironementContract(new EnvironementImpl());
		col=0;
		row=0;
		try {
			 map = new EditMapContract( new EditMapImpl() ) ;
			
			map.init(5,6);
			map.setNature(0, 0, Cell.IN);  map.setNature(1, 0, Cell.WLL);map.setNature(2, 0, Cell.EMP);map.setNature(3, 0, Cell.EMP);map.setNature(4, 0, Cell.WLL);
			map.setNature(0, 1, Cell.EMP); map.setNature(1, 1, Cell.DNO);map.setNature(2, 1, Cell.EMP);map.setNature(3, 1, Cell.EMP);map.setNature(4, 1, Cell.WLL);
			map.setNature(0, 2, Cell.WLL); map.setNature(1, 2, Cell.WLL);map.setNature(2, 2, Cell.WLL);map.setNature(3, 2, Cell.DWO);map.setNature(4, 2, Cell.WLL);
			map.setNature(0, 3, Cell.EMP); map.setNature(1, 3, Cell.EMP);map.setNature(2, 3, Cell.EMP);map.setNature(3, 3, Cell.EMP);map.setNature(4, 3, Cell.EMP);
			map.setNature(0, 4, Cell.EMP); map.setNature(1, 4, Cell.WLL);map.setNature(2, 4, Cell.DWO);map.setNature(3, 4, Cell.WLL);map.setNature(4, 4, Cell.WLL);
			map.setNature(0, 5, Cell.EMP); map.setNature(1, 5, Cell.WLL);map.setNature(2, 5, Cell.EMP);map.setNature(3, 5, Cell.EMP);map.setNature(4, 5, Cell.OUT);
			/*
			System.out.println("");
			for(int i=5;i>=0;i--) {
				System.out.println("");
				for(int j=0;j<5;j++) {
					
					System.out.print(map.cellNature(j, i)+" ");
			}
			}*/
			
			env.init(map);
		} catch (PreConditionError e) {
			assert(false);
		} catch (Exception e) {
			assert(false);
		}
	}; 

	@After
	public final void afterTests() {
		player=null;
	}
	
	
	@Test  // tester les pre, post et inv.  
	public void TestInitPositif(){
		
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());
		try {
			player.init(env, 0, 0, d,5);
		
			
		} catch (Error e) {
			//if(!(e instanceof PreConditionError || e instanceof PostConditionError || e instanceof InvariantError))
				assertTrue(false); 
		}
		assertTrue(player.Row()==0);
		assertTrue(player.Col()==0);
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Hp()==5);
		assertTrue(env.cellNature(0, 0)==Cell.IN);
		return;
	}
	

	@Test
	public void TestInitNegatif(){
	
		col=1;
		row=1;
	
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());
		Player player_bis = new PlayerContract(new PlayerImpl());
		
		// Briser les precond en metant un player dans un case IN occupé par un autre player. 
		try {
			player_bis.init(env, 0, 0, d,5);
			player.init(env, 0, 0, d,10);
		} catch (Error e) {
			if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
		
		env.setCellContent(0, 0, null); 
		// Briser l'invariant en mettant le player sur une case autre que la case IN. 
		
		try {
			player.init(env, 1, 0, d,4);
		} catch (Error e) {
			if(e instanceof InvariantError || e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
		
		// Briser les pré cond en mettant le player dans une case hors MAP 
		try {
			player.init(env, -1, -1, d,4);
		} catch (Error e) {
			if(e instanceof PreConditionError ) {assertTrue(true);} else assertTrue(false);  
		}
	
		// Briser les pré cond en passant un env et/ou direction null
		try {
			player.init(null, 0, 0, null,4);
		} catch (Error e) {
			//if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
		}
		
		// Briser les pré cond en passant hp inférieur ou égal à 0
				try {
					player.init(env, 0, 0, d,0);
				} catch (Error e) {
					if(e instanceof PreConditionError) {assertTrue(true);} else assertTrue(false);  
				}
				
		return;
		
	}
	
	
	
		   // pre : true (rien a tester)
	@Test  // post conds (+inv).
	public void TestTurnL(){
		
		col=0;
		row=0;
	
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());
		player.init(env, col, row, d,5);
		
		player.TurnL();
		assertTrue(player.Face()==Dir.W);
		player.TurnL();
		assertTrue(player.Face()==Dir.S);
		player.TurnL();
		assertTrue(player.Face()==Dir.E);
		player.TurnL();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==col && player.Row()==row); 
		assertTrue(player.Hp()==5);
		
	}
	
	
	
	
		// pre : true (rien a tester)
	@Test  // post conds (+inv).
	public void TestTurnR(){
		
		col=0;
		row=0;
	
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());
		player.init(env, col, row, d,3);
		
		player.TurnR();
		assertTrue(player.Face()==Dir.E);
		player.TurnR();
		assertTrue(player.Face()==Dir.S);
		player.TurnR();
		assertTrue(player.Face()==Dir.W);
		player.TurnR();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==col && player.Row()==row); 
		assertTrue(player.Hp()==3);
	}
	
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestForward(){
		
		col=0;
		row=0;
	
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());;
		player.init(env, col, row, d,3);
		
		player.Forward();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==1);

		player.Forward();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==1);
		
		player.TurnR();
		player.Forward();
		
		
		assertTrue(player.Face()==Dir.E);
		assertTrue(player.Col()==1);	
		assertTrue(player.Row()==1);
		assertTrue(player.Hp()==3);
		
		
	}
	
	

		// ce test a pour but de tester le mouvenent d'un player dans une case occupé par un mob quelconque.
		// pre : true (rien a tester)
		@Test  // post conds (+inv)
	public void TestCMD(){
		
		col=0;
		row=1;
	
		Dir d = Dir.N;
		
		Player player_bis= new PlayerContract(new PlayerImpl());
		player_bis.init(env, 0, 0, d,3);
		player_bis.Forward();
		
		player = new PlayerContract(new PlayerImpl());
		player.init(env, 0, 0, d,4);
		
		player.Forward();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==0);
	
		player.TurnR();
		player.StrafeL();
		player.TurnR();
		player.Backward();
		player.TurnR();
		player.StrafeR();
		player.TurnR();
		player.TurnR();
		
		
		assertTrue(player.Face()==Dir.E);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==0);
		assertTrue(player.Hp()==4);
		
		assertTrue(player_bis.Face()==Dir.N);
		assertTrue(player_bis.Col()==0);	
		assertTrue(player_bis.Row()==1);
		assertTrue(player_bis.Hp()==3);
		
		
	}	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestBackward(){
		
		col=0;
		row=0;
	
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());
		player.init(env, col, row, d,4);
		
		player.Backward();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==0);
	
		player.Forward();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==1);
		player.Backward();
		
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==0);
		
		player.Forward();
		player.TurnL();
		player.Backward();
		
		assertTrue(player.Face()==Dir.W);
		assertTrue(player.Col()==1);	
		assertTrue(player.Row()==1);
		assertTrue(player.Hp()==4);
	}
	
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestStrafeL(){
		
		col=0;
		row=0;
	
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());
		player.init(env, col, row, d,4);
		
		player.StrafeL();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==0);
	
		player.Forward();
		player.StrafeL();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==1);
		
		player.TurnR();
		player.TurnR();
		player.StrafeL();
		
		assertTrue(player.Face()==Dir.S);
		assertTrue(player.Col()==1);	
		assertTrue(player.Row()==1);
		
		player.StrafeL();
		player.TurnR();
		player.StrafeL();
		
		assertTrue(player.Face()==Dir.W);
		assertTrue(player.Col()==2);	
		assertTrue(player.Row()==0);
		assertTrue(player.Hp()==4);
	}
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestSrafeR(){
		
		
		col=0;
		row=0;
	
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());
		player.init(env, col, row, d,4);
		
		player.StrafeR();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==0);
	
		player.Forward();
		player.StrafeR();
		assertTrue(player.Face()==Dir.N);
		assertTrue(player.Col()==1);	
		assertTrue(player.Row()==1);
		
		player.TurnR();
		player.StrafeR();
		
		assertTrue(player.Face()==Dir.E);
		assertTrue(player.Col()==1);	
		assertTrue(player.Row()==1);
		
		player.TurnR();
		player.StrafeR();
		
		assertTrue(player.Face()==Dir.S);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==1);
		assertTrue(player.Hp()==4);
		player.TurnL();
		Player player_2 = new PlayerContract(new PlayerImpl());
		player_2.init(env, col, row, d,4);
		player.StrafeR();
		assertTrue(player.Face()==Dir.E);
		assertTrue(player.Col()==0);	
		assertTrue(player.Row()==1);
		assertTrue(player.Hp()==4);
	}
	
	
	
		// pre : true (rien a tester)
		@Test  // post conds (+inv).
	public void TestTransitionsetEtats(){
		col=0;
		row=0;
	
		Dir d = Dir.N;
		player = new PlayerContract(new PlayerImpl());
		player.init(env, col, row, d,4);
		
		assertTrue(env.cellNature(col, row)==Cell.IN); 
		player.Forward();
		player.StrafeR();
		player.TurnR();
		player.Forward();
		player.Forward();
		player.TurnR();
		player.Forward();
		player.StrafeR();
		player.Backward();
		player.TurnL();
		player.Forward();
		player.StrafeL();
		player.StrafeL();
		player.Forward();
		player.Forward();
		player.Backward();
		player.TurnL();
		player.StrafeL();
		player.StrafeL();
		player.StrafeL();
		player.StrafeL();
		player.Forward();
		player.Forward();
		player.Forward();
		player.Backward();
		player.Backward();
		player.StrafeR();
		player.StrafeR();
		player.Forward();
		player.Forward();
		player.StrafeR();
		player.TurnR();
		player.Forward();
		
		assertTrue(env==player.Env());
		assertTrue(player.Face()==Dir.E);
		assertTrue(player.Col()==4);	
		assertTrue(player.Row()==5);		
		assertTrue(player.Hp()==4);
		assertTrue(env.cellNature(4, 5)==Cell.OUT); 
		
		
		
	}
	
		@Test
		public void TestStep(){
	
			// faire avancer le player aléatoirement jusqu'a atteindre la sortie. 
			
			col=0;
			row=0;
		
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,4);
			Random r = new Random();
			while(player.Col() != 4 || player.Row() != 5) {
				
				int action = r.nextInt(6);
				Command c = null ;
				if(action == 0) { c = Command.FF ;}else
				if(action == 1) { c = Command.BB ;}else
				if(action == 2) { c = Command.LL ;}else
				if(action == 3) { c = Command.RR ;}else
				if(action == 4) { c = Command.TL ;}else
				if(action == 5) { c = Command.TR ;}
				
				player.AddCommad(c);
				player.Step();
			}
			
			assertTrue(player.Col()==4);
			assertTrue(player.Row()==5);
			assertTrue(player.Hp()==4);

		}
		
		@Test // pre post inv
		public void TestContent(){
			
			col=0;
			row=0;
		
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);

			assertTrue(	player.Content(-1, -1)==null);
			
			try {
				player.Content(-2, 0);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Content(-1, -2);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Content(2, 0);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Content(1, 4);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			
			assertTrue(player.Content(0, 0)== env.CellContent(player.Col()+0, player.Row()+0));
			assertTrue(player.Content(0, 1)== env.CellContent(player.Col()+0, player.Row()+1));
			assertTrue(player.Content(-1, 1) == null); // test hors bornes de la map
			
		
		}
		
		
		
		@Test
		public void Nature() {
			

			col=0;
			row=0;
		
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);

			assertTrue(	player.Nature(-1, -1)==Cell.WLL);
			
			try {
				player.Nature(-2, 0);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Nature(-1, -2);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Nature(2, 0);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Nature(1, 4);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			
			assertTrue(player.Nature(0, 0)== env.cellNature(player.Col()+0, player.Row()+0));
			assertTrue(player.Nature(0, 1)== env.cellNature(player.Col()+0, player.Row()+1));
			assertTrue(player.Nature(-1, 1) == Cell.WLL); // test hors bornes de la map
			
			
			
			
			
			
			
			
			
		}
		
		
		@Test
		public void Viewable() {
			
			
			

			col=0;
			row=0;
		
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
			
			assertFalse(player.Viewable(0, 0));
			
			try {
				player.Viewable(-2, 0);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Viewable(-1, -2);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Viewable(2, 0);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			try {
				player.Viewable(1, 4);
				assertTrue(false);
			}catch(PreConditionError e){
				assertTrue(true);
			}
			
			
			assertTrue(player.Viewable(0, 1));
			assertTrue(player.Viewable(0, 2));
			assertFalse(player.Viewable(0, 3));
			assertFalse(player.Viewable(-1, 0));
			assertTrue(player.Viewable(-1, 1));
			assertFalse(player.Viewable(-1, 2));
			assertFalse(player.Viewable(-1, 3));
			assertTrue(player.Viewable(1, 1));
			assertTrue(player.Viewable(1, 2));
			assertFalse(player.Viewable(1, 3));
			
			
		}
		
		
		
		
		@Test
		public void TestAttack() {
			

			col=0;
			row=0;
		
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
			assertTrue(player.Striking_Power()==3);
			
			Cow c = new CowContract(new CowImpl());
			c.init(env, 0, 1, d,3);

			assertTrue(c.Hp()==3);
			player.Attack();
			assertTrue(c.Hp()==0);
			
			
			
		}
		
		
		@Test
		public void TestIncreaseStrikingPower() {
			
			col=0;
			row=0;
		
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
			
			assertTrue(player.Striking_Power()==3);
			player.Increase_Striking_Power(2);
			assertTrue(player.Striking_Power()==5);			
			
			
		}
		
		
		
		@Test 
		public void TestArrow() {
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);

			Arrow a = new ArrowContract(new ArrowImpl());
			a.init();
			player.Add_Weapon(a);
			
			assertTrue(player.Nb_Arrows()==1);
			player.Arrow();
			assertTrue(player.Nb_Arrows()==1);
			
			Monster m =new MonsterContract(new MonsterImpl());
			m.init(env, 0, 1, Dir.N, 4, 4);

			assertTrue(player.Nb_Arrows()==1);
			player.Arrow();
			assertTrue(m.Hp()==0);
			assertTrue(player.Nb_Arrows()==0);
			
			
		}
		
		@Test 
		public void TestEat() {
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
			
			Food f = new FoodContract(new FoodImpl());
			f.init();
			player.Add_Food(f);
			
			assertTrue(player.Foods().size()==1);
			player.Eat();
			assertTrue(player.Foods().size()==1);
			
			Monster m =new MonsterContract(new MonsterImpl());
			m.init(env, 0, 1, Dir.S, 4, 10);
			m.Step();
			
			assertTrue(player.Hp()==90);
			player.Eat();
			assertTrue(player.Hp()==91);
			assertTrue(player.Foods().size()==0);
			 
			
			
			
		}
		
		@Test 
		public void TestOpenDoor() {
			
			Key k = new KeyContract(new KeyImpl());
			k.init(1, 1, env);
			
			// ouvrir une porte sans clé 
			
			map.setNature(1, 1, Cell.DNC);
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
			player.Forward();
			player.TurnR();
			
			assertTrue(player.Nature(0, 1)==Cell.DNC);
			player.OpenDoor();
			assertTrue(player.Nature(0, 1)==Cell.DNO);
			player.OpenDoor();
			assertTrue(player.Nature(0, 1)==Cell.DNO);
			
			// ouvrir une porte avec une clé 
			
			map.setNature(1, 1, Cell.DNC);
			env.setCellContent(1, 1, k);
	
			
			assertTrue(player.Nature(0, 1)==Cell.DNC);
			player.OpenDoor();
			assertTrue(player.Nature(0, 1)==Cell.DNC);
			
			player.Add_Key(k);
			
			assertTrue(player.Nature(0, 1)==Cell.DNC);
			player.OpenDoor();
			assertTrue(player.Nature(0, 1)==Cell.DNO);
			player.OpenDoor();
			assertTrue(player.Nature(0, 1)==Cell.DNO);
			
		}
		
		
		@Test 
		public void TestCloseDoor() {
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
			player.Forward();
			player.TurnR();
			
			assertTrue(player.Nature(0, 1)==Cell.DNO);
			player.CloseDoor();
			assertTrue(player.Nature(0, 1)==Cell.DNC);
			player.CloseDoor();
			assertTrue(player.Nature(0, 1)==Cell.DNC);
			
			
			
		}
		
		
		@Test 
		public void TestTake() {
			
			// prendre une clé et ouvrir une porte
			
			Key k_impl = new KeyImpl();
			Key k = new KeyContract(k_impl);
			k.init(1, 1,0,1, env);
			
			map.setNature(1, 1, Cell.DNC);
			env.setCellContent(1, 1, k); 
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
			
			assertTrue(player.Keys().size()==0);
			player.Take();
			assertTrue(player.Keys().size()==1);
			assertTrue(player.Keys().contains(k_impl));
			player.Forward();
			player.TurnR();
			assertTrue(player.Has_Key());
			assertTrue(player.Nature(0, 1)==Cell.DNC);
			player.OpenDoor();
			assertTrue(player.Nature(0, 1)==Cell.DNO);
			
			
			
		}
		
		
		@Test
		public void Add_Key() {
			
		
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
		
			Key k_impl = new KeyImpl();
			Key k = new KeyContract(k_impl);
			
			player.Add_Key(k);
			assertTrue(player.Keys().contains(k));
			
		};
		
		@Test
		public void Add_Food() {
			
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
		
			Food f_impl = new FoodImpl();
			Food f = new FoodContract(f_impl);
			
			player.Add_Food(f);
			assertTrue(player.Foods().contains(f));
			
			
		};
		
		@Test
		public void Add_Weapon() {
			
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
		
			Arrow w_impl = new ArrowImpl();
			Weapon w = new ArrowContract(w_impl);
			
			player.Add_Weapon(w);
			assertTrue(player.Weapons().contains(w));
			
			
		};
		
		
		@Test
		public void Add_Beans() {
			
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
		
			MagicBeans bean = new MagicBeansContract(new MagicBeansImpl());
			bean.init();
			
			player.Add_Beans(bean);
			assertTrue(player.Beans().contains(bean));
			
			
		};
		
		@Test
		public void TestUseBeans() {
			
			
			Dir d = Dir.N;
			player = new PlayerContract(new PlayerImpl());
			player.init(env, col, row, d,100);
		
			MagicBeans bean = new MagicBeansContract(new MagicBeansImpl());
			bean.init();
			
			assertTrue(player.Beans().size()==0);
			player.Add_Beans(bean);
			assertTrue(player.Beans().size()==1);
			assertTrue(player.Striking_Power()==3);
			player.UseMagicBeans();
			assertTrue(player.Beans().size()==0);
			assertTrue(player.Striking_Power()==4);
			
			
			
			
			
		}
		
		
		
		
		
	
}
