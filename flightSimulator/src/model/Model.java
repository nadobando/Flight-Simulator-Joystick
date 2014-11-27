package model;


import java.util.concurrent.ConcurrentHashMap;

import controller.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Interface Model.
 */
public interface Model  {
//	public void loadConfig(String fileName);
	/**
 * Sets the controller.
 *
 * @param controller the new controller
 */
public void setController(Controller controller);
	
	/**
	 * Close.
	 */
	public void close();
	
	/**
	 * Start.
	 */
	public void start();
	
	/**
	 * Update flight instructions.
	 *
	 * @param data the data
	 */
	public void updateFlightInstructions(double[] data) ;
	
	/**
	 * Lights.
	 */
	public void lights();
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public ConcurrentHashMap<String, Attribute> getAttributes();
	
	/**
	 * Connect.
	 *
	 * @return true, if successful
	 */
	public boolean connect();
}
