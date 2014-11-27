package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import controller.Controller;
import controller.MyController.MsgCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class FlightGearModel.
 */
public class FlightGearModel  implements Model {
	
	/** The controller. */
	private Controller controller=null;
	
	/** The config. */
	private Config config=null;
	
	/**
	 * Instantiates a new flight gear model.
	 *
	 * @param config the config
	 */
	public FlightGearModel(Config config) {
		super();
		this.config = config;
	}

	/** The list. */
	private ConcurrentHashMap<String,Attribute> list=new ConcurrentHashMap<String,Attribute>();
	
	/** The names. */
	private String[] names;
	
	/** The lights. */
	private boolean lights=false;
	
	/** The telnet connection. */
	private TelnetClient telnetConnection;
	
	/** The server. */
	private ServerSocket server;
	
	/** The flight gear. */
	private Socket flightGear;

	/** The engines path. */
	private String enginesPath=null;
	
	/** The flight path. */
	private String flightPath=null;
	
	/** The temp path. */
	private String tempPath=null;
	

	//////////////////////////////// interfaces methods //////////////////////////

	/** 
	 * close the connection
	 */
	@Override
	public void close() {
		
			
			
	
		if(flightGear!=null)
			try {
				flightGear.getInputStream().close();
				flightGear.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(server!=null)
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(telnetConnection!=null)
			try {
				telnetConnection.close();
			} catch (IOException e) {
				return;
				
			}
	}
	
	/** start the stream
	 */
	@Override
	public void start() {
		SimulatorReader reader=null;
		
		//loadConfig(MyController.getConfigName());
		flightPath=config.getFlightControlPath();
		enginesPath=config.getFlightEnginesPath();
		names=config.getAttributes().split(config.getDelimiter());
		try {
			server = new ServerSocket(config.getModelPort());
			flightGear=server.accept();
			MsgCommand msg = (MsgCommand) controller.getCommands().get("Msg").create();
			msg.setMsg("Connected");
			msg.execute();
			System.out.println("connected");
		} catch (IOException e1) {
			return;
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		for(int i=0;i<names.length;i++) {
			list.put(names[i], new Attribute(names[i],0,config.getNValues()));
		}
		
		
		 
		try {
			 reader = new SimulatorReader(config.getDelimiter() , 
					new BufferedReader(new InputStreamReader(flightGear.getInputStream())),list,names,controller);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread th= new Thread(new SimReaderRunnable(reader));
		th.setName("simReader");
		th.start();
		
		
		
		

	}
	
	/* (non-Javadoc)
	 * @see model.Model#updateFlightInstructions(double[])
	 */
	public void updateFlightInstructions(double[] data)  {
		 
		try {
			tempPath=flightPath+"aileron";
			telnetConnection.set(tempPath, data[0]);
			tempPath=flightPath+"elevator";
			telnetConnection.set(tempPath, data[1]);
			tempPath=enginesPath+"throttle";
			telnetConnection.set(tempPath, data[2]);
			tempPath=flightPath+"rudder";
			telnetConnection.set(tempPath, data[3]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}
	
	/* (non-Javadoc)
	 * @see model.Model#setController(controller.Controller)
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}
	/////////////////////////////// FlightGear Methods  /////////////////////////////////
	/* (non-Javadoc)
	 * @see model.Model#getAttributes()
	 */
	public ConcurrentHashMap<String, Attribute> getAttributes() {
		return list;
	}
	
	/* (non-Javadoc)
	 * @see model.Model#lights()
	 */
	public void lights(){
		if(lights){
			System.out.println("Lights Off\n");
			lights=false;
		}
		else{ 
			System.out.println("Lights On\n");
			lights=true;
		}
	}
	
	/* (non-Javadoc)
	 * @see model.Model#connect()
	 */
	@Override
	public boolean connect() {
	
		try {
			telnetConnection=new TelnetClient(config.getIp(), config.getJoystickPort());
		} catch (IOException e) {
			return false;// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		MsgCommand msg = (MsgCommand) controller.getCommands().get("Msg").create();
		msg.setMsg("JConnected");
		msg.execute();
		//controller.getCommands().get("Connected").create().execute();
		return true;
			
			
			

		
	}

	
	
	
}



