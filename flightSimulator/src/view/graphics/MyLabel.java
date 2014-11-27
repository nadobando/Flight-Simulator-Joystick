package view.graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

// TODO: Auto-generated Javadoc
/**
 * The Class MyLabel.
 */
public class MyLabel {
	
	/** The label. */
	Label label;
	
	/**
	 * Instantiates a new my label.
	 *
	 * @param shell the shell
	 * @param display the display
	 */
	public MyLabel(Shell shell,Display display) {
		
		label=new Label(shell, SWT.BORDER_SOLID) ;
		label.setText("     ");
		label.setBackground(display.getSystemColor(SWT.COLOR_DARK_BLUE));
		label.setLayoutData(new GridData(SWT.LEFT, SWT.END, false, false));
	}
	
}
