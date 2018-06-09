package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import contracts.ArrowContract;
import contracts.EditMapContract;
import contracts.EngineContract;
import contracts.EnvironementContract;
import contracts.FoodContract;
import contracts.JeuContract;
import contracts.KeyContract;
import contracts.MagicBeansContract;
import contracts.MonsterContract;
import contracts.PlayerContract;
import contracts.TreasureContract;
import enumeration.Cell;
import enumeration.Command;
import enumeration.Dir;
import services.Arrow;
import services.Containable;
import services.Cow;
import services.EditMap;
import services.Engine;
import services.Environement;
import services.Food;
import services.Jeu;
import services.Key;
import services.MagicBeans;
import services.Monster;
import services.Player;
import services.Treasure;
import servicesImplementations.ArrowImpl;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EngineImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.FoodImpl;
import servicesImplementations.KeyImpl;
import servicesImplementations.MagicBeansImpl;
import servicesImplementations.PlayerImpl;
import servicesImplementations.TreasureImpl;
import servicesImplementations.MonsterImpl;

public class FindTheExit implements Jeu{

	public static void main(String args[]) throws Exception {
		
		
			new JeuContract(new FindTheExit()).InitAndStartGame();
			
			
		}
		
		
		
		
	Treasure t_impl ; 
	Player p1;
	

	@Override
	public void InitAndStartGame() throws Exception {
	
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in)); 
		
		Command c = null;
		// Create the Map
		
		EditMap map = new EditMapContract( new EditMapImpl() ) ;
		
		map.init(5,6);
		map.setNature(0, 0, Cell.IN);  map.setNature(1, 0, Cell.WLL);map.setNature(2, 0, Cell.EMP);map.setNature(3, 0, Cell.EMP);map.setNature(4, 0, Cell.WLL);
		map.setNature(0, 1, Cell.EMP); map.setNature(1, 1, Cell.DNC);map.setNature(2, 1, Cell.EMP);map.setNature(3, 1, Cell.EMP);map.setNature(4, 1, Cell.WLL);
		map.setNature(0, 2, Cell.WLL); map.setNature(1, 2, Cell.WLL);map.setNature(2, 2, Cell.WLL);map.setNature(3, 2, Cell.DWC);map.setNature(4, 2, Cell.WLL);
		map.setNature(0, 3, Cell.EMP); map.setNature(1, 3, Cell.EMP);map.setNature(2, 3, Cell.EMP);map.setNature(3, 3, Cell.EMP);map.setNature(4, 3, Cell.EMP);
		map.setNature(0, 4, Cell.EMP); map.setNature(1, 4, Cell.WLL);map.setNature(2, 4, Cell.DWO);map.setNature(3, 4, Cell.WLL);map.setNature(4, 4, Cell.WLL);
		map.setNature(0, 5, Cell.EMP); map.setNature(1, 5, Cell.WLL);map.setNature(2, 5, Cell.EMP);map.setNature(3, 5, Cell.EMP);map.setNature(4, 5, Cell.OUT);
		
		
		//Create the Env
		
		
		Environement env = new EnvironementContract(new EnvironementImpl());
		env.init(map);
		
		t_impl = new TreasureImpl(); 
		Treasure t = new TreasureContract(t_impl);
		t.init(env, 0, 4);
		
		Key k = new KeyContract(new KeyImpl());
		k.init(3, 2, env);
	
		env.setCellContent(3, 2, k);
		
		Dir d = Dir.N;
		
		// Create a Cow
		
		Monster cow = new MonsterContract(new MonsterImpl());;
		cow.init(env, 2, 0, d,4);
		cow.Set_Spoil(k);
		
		// Create a player 
		
		p1 = new PlayerContract(new PlayerImpl());
		p1.init(env, 0, 0, d, 10);
		
		// fill player's bag
		
		Arrow a = new ArrowContract(new ArrowImpl());
		a.init();
		
		MagicBeans bean = new MagicBeansContract(new MagicBeansImpl());
		bean.init();
		
		Food f = new FoodContract(new FoodImpl());
		f.init();
		
		p1.Add_Weapon(a);
		p1.Add_Food(f);
		p1.Add_Beans(bean);
		
		
		// Create Engine
		
		Engine game_engine = new EngineContract(new EngineImpl());
		game_engine.init(env);
		
		// add entities to game_engine

		game_engine.addEntity(p1);
		game_engine.addEntity(cow);
		
		while(true) {
			
			if(p1.Hp()<=0) { System.out.println("You Died. GAME OVER !"); return; }
			
			// on peut itérer sur l'env pour trouver dynamiquement la position de la sortie. ici on suppose qu'on la connais
			
			if(p1.Col()==4 && p1.Row()==5) {
				
				if(p1.Treasures().contains(t_impl)) {
				System.out.println("Good Job. You've found the Exit !"); return;}
				System.out.println("You don't have the Treasure yet. You can not finish this level!");
			}
			
			//(−1,3)(0,3)(1,3)
			//(−1,2)(0,2)(1,2)
			//(−1,1)(0,1)(1,1)
			
			Containable b=null;
			
		/*	if ( p1.Face()==Dir.E || p1.Face()==Dir.W) {
			if(p1.Viewable(-1, 3)){	System.out.print(p1.Nature(3, -1)+":"+(((b=p1.Content(3, -1))==null)?"Noc":b)+"  ");  }else { System.out.print("??????  ");}
			if(p1.Viewable(0, 3)) {	System.out.print(p1.Nature(3, 0)+":"+(((b=p1.Content(3, 0))==null)?"Noc":b)+"  ");  }else { System.out.print("??????  ");}
			if(p1.Viewable(1, 3)) {	System.out.print(p1.Nature(3, 1)+":"+(((b=p1.Content(3, 1))==null)?"Noc":b)+"  ");  }else { System.out.print("??????  ");}
			
			System.out.println("");
			
			if(p1.Viewable(-1, 2)){	System.out.print(p1.Nature(2, -1)+":"+(((b=p1.Content(2, -1))==null)?"Noc":b)+"  ");  }else { System.out.print("??????  ");}
			if(p1.Viewable(0, 2)) {	System.out.print(p1.Nature(2, 0)+":"+(((b=p1.Content(2, 0))==null)?"Noc":b)+"  ");  }else { System.out.print("??????  ");}
			if(p1.Viewable(1, 2)) {	System.out.print(p1.Nature(2, 1)+":"+(((b=p1.Content(2, 1))==null)?"Noc":b)+"  ");  }else { System.out.print("??????  ");}
			
			System.out.println("");
			
			if(p1.Viewable(-1, 1)){	System.out.print(p1.Nature(1, -1)+":"+(((b=p1.Content(1, -1))==null)?"Noc":b)+"  ");  }else { System.out.print( "??????  ");}
			if(p1.Viewable(0, 1)) {	System.out.print(p1.Nature(1, 0)+":"+(((b=p1.Content(1, 0))==null)?"Noc":b)+"  ");  }else { System.out.print("??????  ");}
			if(p1.Viewable(1, 1)) {	System.out.print(p1.Nature(1, 1)+":"+(((b=p1.Content(1, 1))==null)?"Noc":b)+"  ");  }else { System.out.print("??????  ");}
			
			
			System.out.println(""); } else */
			   ( (PlayerContract) p1).getViewable();
		
					if(p1.Viewable(-1, 3)){	System.out.print(p1.Nature(-1, 3)+":"+(((b=p1.Content(-1, 3))==null)?"Noc":(b instanceof Cow)? b+"|Hp: "+((Cow)b).Hp():b)+"  "); ;  }else { System.out.print("??????  ");}
					if(p1.Viewable(0, 3)) {	System.out.print(p1.Nature(0, 3)+":"+(((b=p1.Content(0, 3))==null)?"Noc":(b instanceof Cow)? b+"|Hp:"+((Cow)b).Hp():b)+"  "); ;  }else { System.out.print("??????  ");}
					if(p1.Viewable(1, 3)) {	System.out.print(p1.Nature(1, 3)+":"+(((b=p1.Content(1, 3))==null)?"Noc":(b instanceof Cow)? b+"|Hp:"+((Cow)b).Hp():b)+"  "); ;  }else { System.out.print("??????  ");}
					
					System.out.println("\n");
					
					if(p1.Viewable(-1, 2)){	System.out.print(p1.Nature(-1, 2)+":"+(((b=p1.Content(-1, 2))==null)?"Noc":(b instanceof Cow)? b+"|Hp:"+((Cow)b).Hp():b)+"  ");  }else { System.out.print("??????  ");}
					if(p1.Viewable(0, 2)) {	System.out.print(p1.Nature(0, 2)+":"+(((b=p1.Content(0, 2))==null)?"Noc":(b instanceof Cow)? b+"|Hp:"+((Cow)b).Hp():b)+"  ");  }else { System.out.print("??????  ");}
					if(p1.Viewable(1, 2)) {	System.out.print(p1.Nature(1, 2)+":"+(((b=p1.Content(1, 2))==null)?"Noc":(b instanceof Cow)? b+"|Hp:"+((Cow)b).Hp():b)+"  ");   }else { System.out.print("??????  ");}
					
					System.out.println("\n");
					
					if(p1.Viewable(-1, 1)){	System.out.print(p1.Nature(-1, 1)+":"+(((b=p1.Content(-1, 1))==null)?"Noc":(b instanceof Cow)? b+"|Hp:"+((Cow)b).Hp():b)+"  ");   }else { System.out.print( "??????  ");}
					if(p1.Viewable(0, 1)) {	System.out.print(p1.Nature(0, 1)+":"+(((b=p1.Content(0, 1))==null)?"Noc":(b instanceof Cow)? b+"|Hp:"+((Cow)b).Hp():b)+"  ");   }else { System.out.print("??????  ");}
					if(p1.Viewable(1, 1)) {	System.out.print(p1.Nature(1, 1)+":"+(((b=p1.Content(1, 1))==null)?"Noc":(b instanceof Cow)? b+"|Hp:"+((Cow)b).Hp():b)+"  ");   }else { System.out.print("??????  ");}
					
					
					System.out.println("\n"); 
			
			while(true) {
			System.out.println("\nVeuillez saisir une Commande parmis {FF,BB,RR,LL,TL,TR,ATT,TAKE,OPEND,CLOSD,EAT,ARROW,BEANS} \n"+"Dir: "+p1.Face() +" | HP: "+p1.Hp()+" | Striking Power: "+p1.Striking_Power()+
				" | Arrows: "+p1.Nb_Arrows()+" | Foods: "+p1.Nb_Foods()+" | Keys: "+p1.Keys().size()+" | Treasures: "+p1.Treasures().size()+" | MagicBeans: "+p1.Beans().size());
			
			System.out.println("");
			
			String s = r.readLine();
			
			System.out.println("");
		
			if(s.equals("FF"))    { c = Command.FF ;   }else
			if(s.equals("BB"))    { c = Command.BB ;   }else
			if(s.equals("TL"))    { c = Command.TL ;   }else
			if(s.equals("TR"))    { c = Command.TR ;   }else
			if(s.equals("LL"))    { c = Command.LL ;   }else
			if(s.equals("RR"))    { c = Command.RR ;   }else 
			if(s.equals("ARROW")) { c = Command.ARROW; }else 
			if(s.equals("EAT"))   { c = Command.EAT;   }else 
			if(s.equals("ATT"))   { c = Command.ATT;   }else 
			if(s.equals("CLOSD")) { c = Command.CLOSD; }else
			if(s.equals("OPEND")) { c = Command.OPEND; }else
			if(s.equals("TAKE"))  { c = Command.TAKE ; }else
			if(s.equals("BEANS")) { c = Command.BEANS ;}else continue;
			break;
			}	
			
			p1.AddCommad(c); 
			
			game_engine.step();
		
	
	}
		
	}








	@Override
	public Player Get_Player() {
		return p1;
	}








	@Override
	public List<Treasure> Get_Necessary_Treasures() {
		List<Treasure> l=  new ArrayList<>();
		l.add(t_impl);
		return l ;
	}
	
}
