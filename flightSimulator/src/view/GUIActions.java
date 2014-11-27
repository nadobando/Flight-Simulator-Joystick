package view;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

// TODO: Auto-generated Javadoc
/**
 * The Interface GUIActions.
 */
public interface GUIActions {

	/**
	 * Show chart.
	 *
	 * @param attrString the attr string
	 */
	public void showChart(String attrString);
	
	/**
	 * Connect.
	 *
	 * @return true, if successful
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public boolean connect() throws InterruptedException, ExecutionException;
	
	/**
	 * Auto pilot.
	 *
	 * @param data the data
	 */
	public void autoPilot(ArrayList<Double> data);
}
