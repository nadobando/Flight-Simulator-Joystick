package view;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

// TODO: Auto-generated Javadoc
/**
 * The Class Msg.
 */
public class Msg {
	
	/** The msg box. */
	private MessageBox msgBox;
	
	/** The messages. */
	private HashMap<String, String> messages= new HashMap<>();
	
	/**
	 * Gets the msg box.
	 *
	 * @return the msg box
	 */
	public MessageBox getMsgBox() {
		
		return msgBox;
	}
	
	/**
	 * Instantiates a new msg.
	 *
	 * @param msg the msg
	 * @param shell the shell
	 */
	public Msg(String msg,Shell shell) {
		messages.put("Connected","Connection Status,Model Connection Established");
		messages.put("JConnected","Connection Status,Joystick Connection Established");
		messages.put("Saved","Configuration Status,Configuration Saved and will take Effect Next Restart");
		messages.put("Loaded","Configuration Status,Configuration Loaded and will take Effect Next Restart");
		
		
		
		String[] temp = messages.get(msg).split(",");
		msgBox= new MessageBox(shell, SWT.ICON_INFORMATION);
		msgBox.setText(temp[0]);
		msgBox.setMessage(temp[1]);
	}
	
}
