package model;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class SimReaderRunnable.
 */
public class SimReaderRunnable implements Runnable 
{
	
	/** The sim read. */
	private SimulatorReader simRead=null;
	
	/**
	 * Instantiates a new sim reader runnable.
	 *
	 * @param simRead the sim read
	 */
	public SimReaderRunnable(SimulatorReader simRead) {
		
		this.simRead = simRead;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	
		try {
			simRead.startToRead();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
