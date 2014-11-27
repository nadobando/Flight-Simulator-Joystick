package view;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.widgets.Display;

import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Interface UI.
 */
public interface UI  {
	
	/**
	 * Sets the connected.
	 *
	 * @param connected the new connected
	 */
	public void setConnected(boolean connected);
	
	/**
	 * Start.
	 */
	public void start();
	
	/**
	 * Gets the display.
	 *
	 * @return the display
	 */
	public Display getDisplay();
	
	/**
	 * Close.
	 */
	public void close();
	
	/**
	 * Update data.
	 *
	 * @param data the data
	 */
	public void updateData(ArrayList<Double> data);
	
	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(Controller controller);
	
	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public Controller getController();
	
	/**
	 * Gets the view key.
	 *
	 * @return the view key
	 */
	public HashMap<String, String> getViewKey(); 
	
	/**
	 * Msg.
	 *
	 * @param msg the msg
	 */
	public void msg(String msg);
}
