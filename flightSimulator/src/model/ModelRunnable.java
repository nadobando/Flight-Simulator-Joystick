package model;


/**
 * The Class ModelRunnable.
 */
public class ModelRunnable implements Runnable {
	
	/** The model. */
	private Model model=null;
	
	/**
	 * Instantiates a new model runnable.
	 *
	 * @param model the model
	 */
	public ModelRunnable(Model model) {
		
		this.model = model;
	}

	
	/**object adapter for model
	 * 
	 */
	@Override
	public void run() {
		model.start();

	}

}
