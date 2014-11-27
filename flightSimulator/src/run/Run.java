               package run;


import java.io.IOException;

import model.FlightGearModel;
import model.Model;
import model.ModelRunnable;
import view.RemoteControlGUI;
import view.UI;
import view.UIRunnable;
import controller.Controller;
import controller.MyController;

// TODO: Auto-generated Javadoc
/**
 * The Class Run.
 */
public class Run {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException{
	//UI gui=new GUI();
	
	
	Controller myControl=new MyController();
	Model model=new FlightGearModel(myControl.getConfig());
	UI cli=new RemoteControlGUI(myControl.getConfig());
	
	cli.setController(myControl);
	model.setController(myControl);
	myControl.setModel(model);
	myControl.setUI(cli);
	//Thread UIThread = new Thread(new UIRunnable(cli));
//	Thread modelThread = new Thread(new ModelRunnable(model));
//	modelThread.start();
	//UIThread.start();
	
	Thread modelThread = new Thread(new ModelRunnable(model));
	modelThread.setName("Model");
	modelThread.start();
	new UIRunnable(cli).run();
	//cli.start();
	

	}
	

}
