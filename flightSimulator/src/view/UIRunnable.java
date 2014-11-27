package view;

import org.eclipse.swt.widgets.Display;



// TODO: Auto-generated Javadoc
/**
 * The Class UIRunnable.
 */
public class UIRunnable implements Runnable {
	
	/** The ui. */
	private UI ui=null;
	
	/**
	 * Instantiates a new uI runnable.
	 *
	 * @param ui the ui
	 */
	public UIRunnable(UI ui) {
		
		this.ui = ui;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		//ui.getDisplay();
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				ui.start();
				
			}
		});
		
	}

}
