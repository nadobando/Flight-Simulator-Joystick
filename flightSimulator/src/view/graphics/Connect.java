package view.graphics;

import java.net.InetAddress;
import java.util.concurrent.ExecutionException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.GUIActions;
import view.JoystickActions;

// TODO: Auto-generated Javadoc
/**
 * The Class Connect.
 */
public class Connect {

		/** The connected. */
		private boolean connected=false;
		
		/**
		 * Sets the connected.
		 *
		 * @param connected the new connected
		 */
		public void setConnected(boolean connected) {
			this.connected = connected;
		}
		
		/**
		 * Instantiates a new connect.
		 *
		 * @param shell the shell
		 * @param display the display
		 * @param Actions the actions
		 */
		public Connect(Shell shell,Display display, final GUIActions Actions)
		{
			final Button connect= new Button(shell, SWT.TOGGLE);
			connect.setText("Connect");
			connect.setLayoutData(new GridData(SWT.FILL,SWT.NONE, true, false,2,1));
			 connect.addSelectionListener(new SelectionListener() {
					
					public void widgetSelected(SelectionEvent e) {
						
							
						
						try {
							if(Actions.connect())
							{
								connected=true;
								System.out.println("connecting to simulator. . . .");
								connect.setText("Disconnect");
								
							}
							else if(connect.getText().equals("Disconnect"))
							{
								connected=false;
								connect.setText("Connect");
							}
						} catch (InterruptedException | ExecutionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		}
	


}
