package main;
 
import java.awt.Point;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import contracts.ArrowContract;
import contracts.EngineContract;
import contracts.FoodContract;
import contracts.GridContract;
import contracts.MagicBeansContract;
import contracts.PlayerContract;
import enumeration.Command;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import services.Arrow;
import services.Containable;
import services.Cow;
import services.EditMap;
import services.Engine;
import services.Environement;
import services.Food;
import services.Grid;
import services.Key;
import services.MagicBeans;
import services.Monster;
import services.Player;
import services.Treasure;
import services.Weapon;
import servicesImplementations.ArrowImpl;
import servicesImplementations.EditMapImpl;
import servicesImplementations.EngineImpl;
import servicesImplementations.EnvironementImpl;
import servicesImplementations.FoodImpl;
import servicesImplementations.GridImpl;
import servicesImplementations.KeyImpl;
import servicesImplementations.MagicBeansImpl;
import servicesImplementations.MonsterImpl;
import servicesImplementations.PlayerImpl;
import servicesImplementations.TreasureImpl;
import sun.text.normalizer.CharTrie.FriendAgent;
import utilis.ImageLoader;
 
public class DunMasterGUI extends Application {
 
	 File file=null;
	 GridPane root;
	 Label [][] labels;
	 Label [] labelsInfos;
	 Label dir;
	 Stage primaryStage;
	 
	 Button playResetButton;
     Button BBButton, FFButton, LLButton, RRButton, TLButton, TRButton,
            ARROWButton, EATButton,ATTButton, CLOSDButton,OPENDButton,TAKEButton,BEANSButton;
		
	Button loadhButton;
	Button genreateButton;
	Button quitButton;
	Point out;
	Treasure t_impl ; 
	boolean hasQuit=false;
	boolean generated =false;
	PlayerContract p1;
	Engine game_engine;
	 
	public static void main(String args[])
	{
		launch(args);
	}
   @Override
   public void start(Stage primaryStage) throws Exception {
      this.primaryStage=primaryStage;
      initRootLayout();
      
   }
   /**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {

		 root = new GridPane();
		// primaryStage.setF(true);
	       root.setPadding(new Insets(20));
	       root.setHgap(25);
	       root.setVgap(15);
	       labelsInfos =new Label[7];
	       dir =new Label("Dir");
	       for(int i=0; i<7; i++)
	       {
	    	   labelsInfos[i] = new Label(); 
	    	   root.add(labelsInfos[i], i, 0);
	       }
	       labelsInfos[0].setText("Joueur Hp :");
	       labelsInfos[2].setText("Joueur Sac :");
	       //updateLabels();
	       GridPane gPane =new GridPane();
	       root.add(gPane, 3, 2,3,3);
	       labels =new Label[3][3];
	       for(int i=0; i<3; i++)
	    	   for(int j=0; j<3; j++)
	       {
	    	  labels[j][i] = new Label("...");
	    	  root.add(labels[j][i],2+j , 2+i,2,1);
	       }
	      
	    	root.add(dir, 3, 5);	   
	        loadhButton = new Button("Chager une Grille");
	        genreateButton = new Button("Generer Grille");
	        quitButton =new Button("Quit");
	        
	        loadhButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					 FileChooser fileChooser = new FileChooser();
					 fileChooser.setTitle("Open Resource File");
					 fileChooser.getExtensionFilters().addAll(
					         new ExtensionFilter("Text Dungeon", "*.dgs"),
					         new ExtensionFilter("All Files", "*.*"));
					 file = fileChooser.showOpenDialog(primaryStage);
					 if (file != null) {
						 toggleButtonRG(true);
							generated=false;
							initGame();
					 }
					
					
				}
			});
	        root.add(loadhButton, 7, 2,2,1);
	        
	        genreateButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					toggleButtonRG(true);
					generated=true;
					initGame();
					
				}
			});
	        root.add(genreateButton, 7, 3,2,1);
	        
	        
	        quitButton.setOnAction(new EventHandler<ActionEvent>() {

	 				@Override
	 				public void handle(ActionEvent event) {
	 				hasQuit=true;
	 					
	 				}
	 			});
	 	        root.add(quitButton, 7, 4,2,1);
	 	        
	        BBButton = new Button("BB");
	        BBButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					p1.AddCommad(Command.BB);
					toggleButtons(true);
					
				}
			});
	        root.add(BBButton, 0, 6); 
	        FFButton = new Button("FF");
	        FFButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					p1.AddCommad(Command.FF);
					toggleButtons(true);
					
				}
			});
	        
	        
	        root.add(FFButton, 1, 6);
	        
	        LLButton = new Button("LL");
	        LLButton.setOnAction(new EventHandler<ActionEvent>() {

	   				@Override
	   				public void handle(ActionEvent event) {
	   				p1.AddCommad(Command.LL);
	   				toggleButtons(true);
	   				
	   					
	   				}
	   			});  
	        root.add(LLButton, 2, 6); 
	        
	        RRButton = new Button("RR");
	        RRButton.setOnAction(new EventHandler<ActionEvent>() {

   				@Override
   				public void handle(ActionEvent event) {
   					p1.AddCommad(Command.RR);
   					toggleButtons(true);
   					
   				}
   			});
	        root.add(RRButton, 3, 6); 
	        TLButton = new Button("TL");
	        TLButton.setOnAction(new EventHandler<ActionEvent>() {

   				@Override
   				public void handle(ActionEvent event) {
   					p1.AddCommad(Command.TL);
   					toggleButtons(true);
   					
   				}
   			});
	        root.add(TLButton, 4, 6); 
	        TRButton = new Button("TR");
	        TRButton.setOnAction(new EventHandler<ActionEvent>() {

   				@Override
   				public void handle(ActionEvent event) {
   					p1.AddCommad(Command.TR);
   					toggleButtons(true);
   					
   					
   				}
   			});
	        root.add(TRButton, 5, 6); 
	        
	        ARROWButton = new Button("ARROW");
	        ARROWButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					p1.AddCommad(Command.ARROW);
					toggleButtons(true);
					
				}
			});
	        root.add(ARROWButton, 0, 7); 
	        
	        EATButton = new Button("EAT");
	        EATButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					p1.AddCommad(Command.EAT);
					toggleButtons(true);
					
				}
			});
	        root.add(EATButton, 1, 7);
	        
	        BEANSButton = new Button("BEANS");
	        BEANSButton.setOnAction(new EventHandler<ActionEvent>() {

	   				@Override
	   				public void handle(ActionEvent event) {
	   				p1.AddCommad(Command.BEANS);
	   				toggleButtons(true);
	   				
	   					
	   				}
	   			});  
	        root.add(BEANSButton, 2,7); 
	        
	        CLOSDButton = new Button("CLOSD");
	        CLOSDButton.setOnAction(new EventHandler<ActionEvent>() {

   				@Override
   				public void handle(ActionEvent event) {
   					p1.AddCommad(Command.CLOSD);
   					toggleButtons(true);
   					
   				}
   			});
	        root.add(CLOSDButton, 3, 7); 
	        
	        OPENDButton = new Button("OPEND");
	        OPENDButton.setOnAction(new EventHandler<ActionEvent>() {

   				@Override
   				public void handle(ActionEvent event) {
   					p1.AddCommad(Command.OPEND);
   					toggleButtons(true);
   					
   				}
   			});
	        root.add(OPENDButton, 4, 7); 
	        
	        TAKEButton = new Button("TAKE");
	        TAKEButton.setOnAction(new EventHandler<ActionEvent>() {

   				@Override
   				public void handle(ActionEvent event) {
   					p1.AddCommad(Command.TAKE);
   					toggleButtons(true);
   					
   					
   				}
   			});
	        root.add(TAKEButton, 5, 7); 
	        
	        ATTButton = new Button("ATT");
	        TAKEButton.setOnAction(new EventHandler<ActionEvent>() {

   				@Override
   				public void handle(ActionEvent event) {
   					p1.AddCommad(Command.ATT);
   					toggleButtons(true);
   					
   					
   				}
   			});
	        root.add(ATTButton, 6, 7);
	       
	       Scene scene = new Scene(root, 300, 300);
	       primaryStage.setTitle("DUNGEON MASTER");
	       primaryStage.setScene(scene);
	       primaryStage.setResizable(false);
	       primaryStage.centerOnScreen();
	       primaryStage.setHeight(500);
	       primaryStage.setWidth(1000);
	       toggleButtons(true);
	       primaryStage.show();
	   }
	
	public void initGame() {
		Command c = null;

		
				Arrow a = new ArrowContract(new ArrowImpl());
				a.init();
				
				MagicBeans bean = new MagicBeansContract(new MagicBeansImpl());
				bean.init();
				
				Food f = new FoodContract(new FoodImpl());
				f.init();
		p1 = new PlayerContract(new PlayerImpl());
		
		Grid grid = new GridContract(new GridImpl());
		
		if(!generated) {
			System.out.println( " AAAAAAAAAAA");
			grid.init(file.getName());
			grid.loadGrid(file.getName(),p1);
		} 
		else {
			grid.init(8, 8);
			 grid.generatedGrid(p1);
			 }
		
		p1.Add_Weapon(a);
		p1.Add_Food(f);
		p1.Add_Beans(bean);
		
		
		
		
		// Create Engine
		out=grid.getOut();
		game_engine = new EngineContract(new EngineImpl());
		Environement env = grid.environement();
		game_engine.init(env);
		
		// add entities to game_engine

		game_engine.addEntity(p1);
	//	game_engine.addEntity(cow);
		System.out.println(grid.containers().size());
		changePlateau(p1.getViewable());
		toggleButtons(false);
		displayPlateau();
		
	}
		
		
		
	private void changePlateau(String texte){
		
		String []line = texte.split("/");
		 for(int i=0; i<3;i++)
	    	   for(int j=0; j<3;j++)
	    	   {
	    		   String [] col =line[i].split("eS");
	    		   String [] caSe= col[j].split(":");
	    		   Label lab=labels[j][i];
	    		   ImageView image;
	    		   System.out.println(caSe[0]);
	    		   
	    		    image = new ImageView(ImageLoader.getByRepresentation(caSe[0]));
	    		    if(image==null) continue;
					image.setFitHeight(28);
					image.setFitWidth(30);
					if(caSe.length==2) {
					lab.setText(caSe[1]);
					lab.setGraphic(image);
					}
					
					else {
						lab.setText("?");
						lab.setGraphic(image);
					}
					image.setPreserveRatio(true);
					lab.setContentDisplay(ContentDisplay.LEFT);
					
	    		    //root.add(lab,2+j , 2+i,2,1);
	    		   // labels[j][i] = lab;
	    	   }
	}

	protected void displayPlateau() {

		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run(){
				javafx.application.Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if(hasQuit) {primaryStage.close();timer.cancel();}
						if(p1.Hp()<=0) { System.out.println("You Died. GAME OVER !");  toggleButtonRG(false);timer.cancel(); }
						
						// on peut itérer sur l'env pour trouver dynamiquement la position de la sortie. ici on suppose qu'on la connais
						
						if(p1.Col()==out.x && p1.Row()==out.y) {
							
							if(p1.Treasures().contains(t_impl)) {
							System.out.println("Good Job. You've found the Exit !"); return;}
							System.out.println("You don't have the Treasure yet. You can not finish this level!");
						}
						game_engine.step();
					    updateLabels();
						changePlateau(p1.getViewable());
						toggleButtons(false);
					}
				});
			}
		}, 0, 1000); // SpeedGame peut changer

	}
	
	private void toggleButtons(boolean value){
		BBButton.setDisable(value);
		LLButton.setDisable(value);
		TRButton.setDisable(value);
		FFButton.setDisable(value);
		TLButton.setDisable(value);
		RRButton.setDisable(value);
		
		ARROWButton.setDisable(value);
		BEANSButton.setDisable(value);
        ATTButton.setDisable(value);
        CLOSDButton.setDisable(value);
        OPENDButton.setDisable(value);
        EATButton.setDisable(value);
        TAKEButton.setDisable(value);
        
		

	}
	private void toggleButtonRG(boolean value){
		loadhButton.setDisable(value);
		genreateButton.setDisable(value);

		

	}
	public void updateLabels() {
		labelsInfos[1].setText(Integer.toString(p1.Hp()));
		dir.setText(p1.Face().toString());
		labelsInfos[3].setText("Keys :"+Integer.toString(p1.Keys().size()));
		labelsInfos[4].setText("Armes :"+Integer.toString(p1.Weapons().size()));
		labelsInfos[5].setText("Foods :"+Integer.toString(p1.Nb_Foods()));
		labelsInfos[6].setText("Fleches :"+Integer.toString(p1.Nb_Arrows()));
		labelsInfos[6].setText("Tresors :"+Integer.toString(p1.Treasures().size()));
	
	}
	
	
}