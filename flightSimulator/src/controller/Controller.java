
package controller;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import model.Attribute;
import model.Config;
import model.Model;
import view.UI;
import controller.MyController.Command;
import controller.MyController.Creator;


// TODO: Auto-generated Javadoc
/**
 * The Interface Controller.
 */
public interface Controller {
	
	/**
	 * Checks if is uI connected.
	 *
	 * @return true, if is uI connected
	 */
	public boolean isUIConnected();
	
	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	void setModel(Model model);
	
	/**
	 * Sets the ui.
	 *
	 * @param ui the new ui
	 */
	public void setUI(UI ui);
	
	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public Config getConfig();
	
	/**
	 * Sets the config.
	 *
	 * @param config the new config
	 */
	public void setConfig(Config config);
	//public void updateData(ArrayList<Double> dataD);
	/**
	 * Do command.
	 *
	 * @param com the com
	 */
	public void doCommand(Command com);
	
	/**
	 * Gets the commands.
	 *
	 * @return the commands
	 */
	public HashMap<String, Creator> getCommands();
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public ConcurrentHashMap<String, Attribute> getAttributes();
	
	/**
	 * Creates the command.
	 *
	 * @param type the type
	 * @return the command
	 */
	public Command createCommand(String type);
	
}
