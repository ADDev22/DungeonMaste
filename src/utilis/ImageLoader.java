package utilis;


import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;

public abstract class ImageLoader {

	public static final Image METAL_IMG = getRessource("metal.png");
	public static final Image DNC_IMG = getRessource("dnc.png");
	public static final Image DWC_IMG = getRessource("dwc.png");
	public static final Image DNO_IMG = getRessource("dno.png");
	public static final Image DWO_IMG = getRessource("dwo.png");
	public static final Image EMPTY_IMG = getRessource("empty.png");
	public static final Image ENTER_IMG = getRessource("enter.png");
	public static final Image EXIT_IMG = getRessource("exit.png");
	public static final Image STOP_IMG = getRessource("stop.png");



	private static Image getRessource(String res){
		File file = new File("res/images/"+res);
	//	System.out.println(ImageLoader.class.getResource("/"+res).toString());
		return new Image(ImageLoader.class.getResource("/"+res).toString());
		/*
		try {
			String url =file.toURI().toURL().toString();
			return new Image(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
	}
	
	public static Image getByRepresentation(String toString){
		if(toString==null) return null;
		
		toString = toString.replaceAll("[0-9]", "");
		switch (toString) {
		case "WLL":
			return METAL_IMG;
		case "???":
			return STOP_IMG;

		case "DNC":
			return DNC_IMG;
			
		case "DNO":
			return DNO_IMG;
			
		case "EMP":
			return EMPTY_IMG;
			
		case "IN":
			return ENTER_IMG;
			
		case "OUT":
			return EXIT_IMG;
		
		case "DWC":
			return DWC_IMG;
		case "DWO":
			return DWO_IMG;
			
		default:
			return EMPTY_IMG;
		}
	}

}