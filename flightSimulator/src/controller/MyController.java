package controller;


import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import model.Attribute;
import model.Config;
import model.Model;

import org.eclipse.swt.widgets.Display;

import view.UI;

// TODO: Auto-generated Javadoc
/**
 * The Class MyController.
 */
public class MyController implements Controller {

	/** The telnet connection. */
	private boolean telnetConnection=false;
	
	/* (non-Javadoc)
	 * @see controller.Controller#isUIConnected()
	 */
	public boolean isUIConnected() {
		return telnetConnection;
	}
	
	/** The config name. */
	private  String configName;
	
	/** The ui. */
	private UI ui=null;
	
	/** The model. */
	private Model model=null;
	
	/** The commands creators. */
	private HashMap<String,Creator> commandsCreators=new HashMap<String,Creator>();
	
	/** The config. */
	private  Config config=null;
	
	///////////////////////////////////C'tor////////////////////////////////
	
	
	/**
	 * Instantiates a new my controller.
	 */
	public MyController(){
	   

	    Scanner sc;
		try {
			sc = new Scanner (new FileInputStream(new File("default.txt"))); 
		while (sc.hasNextLine())
	    {
	      configName = sc.nextLine();
	      System.out.println (configName);
	    }
	    sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		loadConfig(configName);
		commandsCreators.put("UpdateData", new UpdateDataCreator());
		commandsCreators.put("FlightControl", new FlightControlCreator());
		commandsCreators.put("SafeExit", new SafeExitCreator());
		commandsCreators.put("TurnLights", new LightsSwitchCreator());
		commandsCreators.put("LoadConfig", new LoadConfigCreator());
		
		commandsCreators.put("Connect", new ConnectCreator());
		commandsCreators.put("Msg", new MsgCreator());
		
		
	}
	
  	/**
	   * Gets the config name.
	   *
	   * @return the config name
	   */
	  public  String getConfigName() {
		return configName;
	}

	/**
	 * Sets the config name.
	 *
	 * @param configName the new config name
	 */
	public  void setConfigName(String configName) {
		configName = configName;
	}
	////////////////////////////////////Setters & Getters/////////////////////////////////////
	
	/**
	 * Gets the ui.
	 *
	 * @return the ui
	 */
	public UI getUi() {
		return ui;
	}
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#setModel(model.Model)
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#setUI(view.UI)
	 */
	public void setUI(UI ui) {
		this.ui=ui;
	}
    
    /* (non-Javadoc)
     * @see controller.Controller#getCommands()
     */
    public HashMap<String, Creator> getCommands() {
		return commandsCreators;
	}
	
    /////////////////////////////////////---Methods---////////////////////////////////////////
	/**
     * Load config.
     *
     * @param fileName the file name
     */
    public void loadConfig(String fileName) {
		
		try {
			XMLDecoder x=new XMLDecoder(new FileInputStream(fileName));
			config=(Config)x.readObject();
			x.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pWriter;
		try {
			pWriter = new PrintWriter (new FileWriter(new File("default.txt")));
			pWriter.println (fileName);
	   		pWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#doCommand(controller.MyController.Command)
	 */
	public void doCommand(Command com) {
		
		com.execute();
		
	}
	//////////////////////////////////////////Commands Interfaces////////////////////////////////////////

	/**
	 * The Interface Command.
	 */
	public interface Command {

		/**
		 * Execute.
		 */
		void execute();
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#getConfig()
	 */
	public Config getConfig() {
		// TODO Auto-generated method stub
		return config;
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#setConfig(model.Config)
	 */
	public void setConfig(Config config) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#getAttributes()
	 */
	@Override
	public ConcurrentHashMap<String, Attribute> getAttributes() {
		
		return model.getAttributes();
	}
		//////////////////////////////////////////Commands Classes//////////////////////////////
	/**
		 * The Class SafeExitCommand.
		 */
		public class SafeExitCommand implements Command{

		/* (non-Javadoc)
		 * @see controller.MyController.Command#execute()
		 */
		@Override
		public void execute() {
			
			
			model.close();
			Runnable runna = new Runnable() {
				
				@Override
				public void run() {
					
			ui.close();
					
				}
			};
			Display.getCurrent().asyncExec(runna);
		}
		
	}
	
	/**
	 * The Class UpdateDataCommand.
	 */
	public class UpdateDataCommand implements Command {
		
		/** The data. */
		private ArrayList<Double> data=null;
		
		/**
		 * Sets the data.
		 *
		 * @param data the new data
		 */
		public void setData(ArrayList<Double> data) {
			this.data = data;
		}
		
		/* (non-Javadoc)
		 * @see controller.MyController.Command#execute()
		 */
		public void execute() {
			if(data != null)
				ui.updateData(data);
			else
				System.out.println("No Data");
		}
		
	}
	
	/**
	 * The Class JoystickCommand.
	 */
	public class JoystickCommand implements Command{ 
		//aileron, elevator, rudder, throttle;
		/** The data. */
		double[] data=new double[4];
		
		/**
		 * Instantiates a new joystick command.
		 */
		public JoystickCommand() {
			data[0]=0;
			data[1]=0;
			data[2]=0;
			data[3]=0;
		}
		
		/**
		 * Sets the joystick x.
		 *
		 * @param x the new joystick x
		 */
		public void setJoystickX(double x) {
			data[0] = x;
		}
		
		/**
		 * Sets the joystick y.
		 *
		 * @param y the new joystick y
		 */
		public void setJoystickY(double y) {
			data[1] = y;
		}
		
		/**
		 * Sets the x.
		 *
		 * @param x the new x
		 */
		public void setX(double x) {
			data[3] = x;
		}
		
		/**
		 * Sets the y.
		 *
		 * @param y the new y
		 */
		public void setY(double y) {
			data[2] = y;
		}
		//-----------------------------------------
		/* (non-Javadoc)
		 * @see controller.MyController.Command#execute()
		 */
		public void execute(){ 
			model.updateFlightInstructions(data);
		 
		 } 
		}
	
	/**
	 * The Class LightsSwitchCommand.
	 */
	public class LightsSwitchCommand implements Command{
		
		/* (non-Javadoc)
		 * @see controller.MyController.Command#execute()
		 */
		@Override
		public void execute() {
			
			model.lights();
			
		}
		
		
	}
	
	/**
	 * The Class LoadConfigCommand.
	 */
	public class LoadConfigCommand implements Command{
		
		/** The file name. */
		private String fileName;
		
		/**
		 * Sets the filename.
		 *
		 * @param fileName the new filename
		 */
		public void setFilename(String fileName) {
			this.fileName = fileName;
		}
		
		/* (non-Javadoc)
		 * @see controller.MyController.Command#execute()
		 */
		public void execute() {
			
			
			loadConfig(fileName);
			MsgCommand msg =new MsgCommand();
			msg.setMsg("Loaded");
			msg.execute();
			
		}
				
		}
	

	/**
	 * The Class ConnectCommand.
	 */
	public class ConnectCommand implements Command{

		/* (non-Javadoc)
		 * @see controller.MyController.Command#execute()
		 */
		@Override
		public void execute()
		{
			
		}
		
		/**
		 * Execute1.
		 *
		 * @return true, if successful
		 */
		public boolean execute1()
		{
			telnetConnection=model.connect();
			return telnetConnection;
		}
		
		
			
		
		
	}

	/**
	 * The Class MsgCommand.
	 */
	public class MsgCommand implements Command{
		
		/** The msg. */
		private String msg=null;
		
		/**
		 * Sets the msg.
		 *
		 * @param msg the new msg
		 */
		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		/* (non-Javadoc)
		 * @see controller.MyController.Command#execute()
		 */
		@Override
		public void execute() {
			ui.msg(msg);
			
					
			
		}
		
	}
	
	
	///////////////////////////////////////////Creator Interfaces-Classes/////////////////////////////////////////
	/**
	 * The Interface Creator.
	 */
	public interface Creator {
		
		/**
		 * Creates the.
		 *
		 * @return the command
		 */
		public Command create();

	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#createCommand(java.lang.String)
	 */
	public Command createCommand(String type){
		  Creator c=commandsCreators.get(type);  
		  if(c!=null) 
			  return c.create(); 
		  return null;
	}
	
	/**
	 * The Class FlightControlCreator.
	 */
	public class FlightControlCreator implements Creator {

		
		/* (non-Javadoc)
		 * @see controller.MyController.Creator#create()
		 */
		public Command create() {
			return new JoystickCommand();		
		}
		

	}
	
	/**
	 * The Class UpdateDataCreator.
	 */
	public class UpdateDataCreator implements Creator {

		
		/* (non-Javadoc)
		 * @see controller.MyController.Creator#create()
		 */
		public Command create() {
			return new UpdateDataCommand();		
		}
		

	}
	
	/**
	 * The Class SafeExitCreator.
	 */
	public class SafeExitCreator implements Creator {

		
		/* (non-Javadoc)
		 * @see controller.MyController.Creator#create()
		 */
		public Command create() {
			return new SafeExitCommand();		
		}
	}
	
	/**
	 * The Class LightsSwitchCreator.
	 */
	public class LightsSwitchCreator implements Creator {

			
			/* (non-Javadoc)
			 * @see controller.MyController.Creator#create()
			 */
			public Command create() {
				return new LightsSwitchCommand();		
			}	

	}
	
	/**
	 * The Class LoadConfigCreator.
	 */
	public class LoadConfigCreator implements Creator {

		
		/* (non-Javadoc)
		 * @see controller.MyController.Creator#create()
		 */
		public Command create() {
			return new LoadConfigCommand();		
		}	

}

	/**
	 * The Class ConnectCreator.
	 */
	public class ConnectCreator implements Creator{

		/* (non-Javadoc)
		 * @see controller.MyController.Creator#create()
		 */
		@Override
		public Command create() {
			return new ConnectCommand();
		}
		
	}
	
	/**
	 * The Class MsgCreator.
	 */
	public class MsgCreator implements Creator {

		/* (non-Javadoc)
		 * @see controller.MyController.Creator#create()
		 */
		@Override
		public Command create() {
			return new MsgCommand();
		}
		
		
	}

	

	
}
