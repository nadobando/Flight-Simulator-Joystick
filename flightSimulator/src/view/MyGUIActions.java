package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import view.graphics.MyCharts;
import controller.Controller;
import controller.MyController.ConnectCommand;
import controller.MyController.JoystickCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class MyGUIActions.
 */
public class MyGUIActions implements GUIActions{

	/** The controller. */
	private Controller controller;
	
	/** The view names. */
	private HashMap<String, String> viewNames;
	
	/** The pilot data. */
	private ArrayList<Double> pilotData; 
	
	/**
	 * Instantiates a new my gui actions.
	 *
	 * @param controller the controller
	 * @param viewNames the view names
	 */
	public MyGUIActions(Controller controller,HashMap<String, String> viewNames) {
		this.controller=controller;
		this.viewNames=viewNames;
	}

	/* (non-Javadoc)
	 * @see view.GUIActions#showChart(java.lang.String)
	 */
	@Override
	public void showChart(String attrString) {
		if(controller.getAttributes().get(viewNames.get(attrString))!=null&&controller.getAttributes().get(viewNames.get(attrString)).getValues()!=null)
		{
			MyCharts chart=new MyCharts(attrString, controller.getAttributes().get(viewNames.get(attrString)).getValues());
			chart.getShell().open();
			chart.getRunnable().run();
		}
		
	}

	/* (non-Javadoc)
	 * @see view.GUIActions#connect()
	 */
	@Override
	public boolean connect() throws InterruptedException, ExecutionException {
		Callable<Boolean> connectedCallable = new Callable<Boolean>() {
			
			@Override
			public Boolean call() throws Exception {
				// TODO Auto-generated method stub
				return ((ConnectCommand) controller.getCommands().get("Connect").create()).execute1();
			}
		};
		FutureTask<Boolean> ft = new FutureTask<Boolean>(connectedCallable);
		ExecutorService exec = Executors.newSingleThreadExecutor();
		exec.execute(ft);
		
		
		return ft.get();
	}

	/* (non-Javadoc)
	 * @see view.GUIActions#autoPilot(java.util.ArrayList)
	 */
	@Override
	public void autoPilot(final ArrayList<Double> data) {
		pilotData=new ArrayList<>(data);
		Thread pilot=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
					
			boolean inAir = false;
			JoystickCommand fcc = (JoystickCommand) controller.getCommands().get("FlightControl").create();
			fcc.setY(1);
			if(pilotData.get(1)>-20)
				inAir=true;
			if(inAir&&pilotData.get(4)>0.5)
				{
				
				fcc.setJoystickX(-0.1);
				
				}
			
			else if(inAir&&pilotData.get(4)<-0.5)
				{
				fcc.setJoystickX(0.1);
				
				}
			
			if(pilotData.get(1)<-10&&inAir)
			{
				fcc.setJoystickY(0.03);
				
			}
			else if(pilotData.get(1)>1000&&inAir)
			{
				fcc.setJoystickY(-0.03);
				
				
				
			}
			if(inAir&&pilotData.get(26)>1)
			{
				fcc.setX(-0.005);
				
			}
			else if(inAir&&pilotData.get(26)<-1)
			{
				fcc.setX(0.005);
				
				
				
			}	
			if(inAir&&pilotData.get(5)>1)
			{
				fcc.setJoystickY(-0.00005);
				
			}
			else if(inAir&&pilotData.get(5)<0)
			{
				fcc.setJoystickY(0.00005);
							
			}
			fcc.execute();
			try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			fcc.setJoystickY(0);
			fcc.setX(0);
			fcc.setJoystickY(0);
			fcc.execute();
			}
		});
		pilot.setName("Auto Pilot");
		pilot.start();		
		
				
	}

	
}
	


