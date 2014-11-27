package view;

import controller.Controller;
import controller.MyController.JoystickCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class FlightJoystickActions.
 */
public class FlightJoystickActions implements JoystickActions {
	
	/** The fcc. */
	private JoystickCommand fcc;
	
	/** The controller. */
	private Controller controller;
	
	
	/**
	 * Instantiates a new flight joystick actions.
	 *
	 * @param controller the controller
	 */
	public FlightJoystickActions(Controller controller) {
		this.controller=controller;
		fcc=(JoystickCommand) controller.getCommands().get("FlightControl").create();
		
	}
	
	/** (non-Javadoc)
	 * Joystick X ,Joystick Y
	 */
	@Override
	public void doAction(double x, double y) {
		fcc.setJoystickX(x);
		fcc.setJoystickY(y);
		
		if(controller.isUIConnected())
			fcc.execute();
		
		System.out.println("aileron"+x+","+y+ "elevator");

	}

	/**Horizontal Action
	 * 	 * @see view.JoystickActions#doActionX(double)
	 */
	@Override
	public void doActionX(double x) {
		fcc.setX(x);
		if(controller.isUIConnected())
			fcc.execute();
		System.out.println(x);

	}

	/** Vertical Action
	 * @see view.JoystickActions#doActionY(double)
	 */
	@Override
	public void doActionY(double y) {
		fcc.setY(y);
		if(controller.isUIConnected())
			fcc.execute();
		System.out.println(y);

	}
}
	


