package view;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.ws.Holder;

import model.Config;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.graphics.Connect;
import view.graphics.GraphicalJoystick;
import view.graphics.MenuBar;
import view.graphics.MyLabel;
import view.graphics.MyLabel2;
import view.graphics.MySlider;
import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class RemoteControlGUI.
 */
public class RemoteControlGUI implements UI
{
	
	/** The pilot. */
	private Holder<Boolean> pilot=new Holder<Boolean>(false);




	/** The connected. */
	private  boolean connected=false;
	
	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public  boolean isConnected() {
		return connected;
	}




	
	/** The view attribute. */
	private String[] viewAttribute;
	
	/** The labels. */
	private HashMap<String, MyLabel2> labels=new HashMap<String,MyLabel2>();
	
	/** The view names. */
	private HashMap<String, String> viewNames=new HashMap<String, String>();
	
	/** The view key. */
	private HashMap<String, String> viewKey=new HashMap<String, String>();
	
	/** The display. */
	private Display display=new Display();
	
	/** The shell. */
	private Shell shell=new Shell();
	
	/** The controller. */
	private Controller controller;
	
	/** The config. */
	private Config config=null;
	
	/** The gj. */
	private GraphicalJoystick gj;
	
	/** The joystick. */
	private Joystick joystick ;
	
	/** The gui action. */
	private GUIActions guiAction;
	
	/** The flight actions. */
	private FlightJoystickActions flightActions;
	
	/** The connect. */
	private Connect connect;
	
	/**
	 * Instantiates a new remote control gui.
	 *
	 * @param config the config
	 * @throws FileNotFoundException the file not found exception
	 */
	public RemoteControlGUI(Config config) throws FileNotFoundException
	{
		this.config=config;
			try {
			BufferedReader br = new BufferedReader(new FileReader(config.getFileName()));
			String line=""; 
			
			while ((line = br.readLine()) != null) {
				 
		        
				String[]  tempAttr= line.split(config.getDelimiter());
				viewNames.put(tempAttr[1], tempAttr[0]);
				viewKey.put(tempAttr[0], tempAttr[1]);
				
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		shell.setLayout(new GridLayout(2, false));
		shell.setSize(500, 300);
		shell.setText("FlightGear Simulator");
		shell.open();
    
}
	
  
	

	/* (non-Javadoc)
	 * @see view.UI#updateData(java.util.ArrayList)
	 */
	@Override
	public void updateData(final ArrayList<Double> data1) {
		final ArrayList<Double> data=data1;
		if(!(data==null))
		{	if(pilot.value)
			{
				guiAction.autoPilot(data);
			}
			display.syncExec(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MyLabel2 temp=null;
				
				
				
				int i=0;
				String[] tempStr=config.getAttributes().split(",");
				for(String s:tempStr)
				{
					
					temp=labels.get(viewKey.get(s));
					if(!(temp==null))
					{
//						System.out.println(data.get(i));
//						System.out.println(s);
						temp.getLabel().setText(viewKey.get(s)+": "+ data.get(i));
						temp.getLabel().pack();
					}
					i++;
				}
				
			}
		
		});
		}
			
	}
	
	/* (non-Javadoc)
	 * @see view.UI#setController(controller.Controller)
	 */
	@Override
	public void setController(Controller controller) {
		this.controller=controller;
		
	}
	
	/**
	 * Sets the flight instructions.
	 */
	public void setFlightInstructions() {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see view.UI#start()
	 */
	@Override
	public void start() 
	{
		flightActions=new FlightJoystickActions(controller);
		guiAction = new MyGUIActions(controller, viewNames);
		joystick=new MyJoystick(flightActions);
		connect=new Connect(shell,display,guiAction);
		
		
		MySlider VertSlider=new MySlider(shell,true,flightActions);
		gj=new GraphicalJoystick(controller, config,shell,display, flightActions, labels, viewAttribute);
		MyLabel label=new MyLabel(shell, display);
		MySlider HorSlider=new MySlider(shell,false,flightActions);


		
		
		viewAttribute=this.config.getViewAttributes().split(this.config.getDelimiter());
		
		for(int i=0;i<viewAttribute.length;i++)
		{
			if(!(viewNames.get(viewAttribute[i])==null))
				labels.put(viewAttribute[i], new MyLabel2(gj.getCanvas(), viewAttribute[i], display, guiAction,shell));
			
		}
		
		MenuBar menu = new MenuBar(pilot,shell, controller,viewKey,labels,viewNames,flightActions);
		// myjoyActions=new MyJoystickActions();
		//gj.start(viewAttribute,labels);
		
		while (!shell.isDisposed())
     	{
			
     		if (!display.readAndDispatch())  // If no more entries in the event queue
     			{
     				display.sleep();
     			}
        }
		

				
			controller.getCommands().get("SafeExit").create().execute();
			
		
    	
    		
		
	}


	/* (non-Javadoc)
	 * @see view.UI#getDisplay()
	 */
	@Override
	public Display getDisplay() {
		return display;
	}


	/* (non-Javadoc)
	 * @see view.UI#getController()
	 */
	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see view.UI#msg(java.lang.String)
	 */
	@Override
	public void msg(final String msg) {
		display.asyncExec(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Msg message=new Msg(msg,shell);
				message.getMsgBox().open();
		
			}
		});
		
		
	}


	/* (non-Javadoc)
	 * @see view.UI#setConnected(boolean)
	 */
	@Override
	public void setConnected(boolean connected) {
		this.connected=connected;
		connect.setConnected(true);
		
	}


	/* (non-Javadoc)
	 * @see view.UI#getViewKey()
	 */
	@Override
	public HashMap<String, String> getViewKey() {
		// TODO Auto-generated method stub
		return null;
	}




	/* (non-Javadoc)
	 * @see view.UI#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}



	
	
	
};
