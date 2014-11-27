package view.graphics;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import view.GUIActions;
import view.JoystickActions;

// TODO: Auto-generated Javadoc
/**
 * The Class MyLabel2.
 */
public class MyLabel2 {
	
	/** The label. */
	private Label label;
	
	/** The attribute. */
	private String attribute;
	
 	/**
	  * Gets the label.
	  *
	  * @return the label
	  */
	 public Label getLabel() {
		return label;
	}
	
	/**
	 * Instantiates a new my label2.
	 *
	 * @param canvas the canvas
	 * @param viewAttribute the view attribute
	 * @param display the display
	 * @param action the action
	 * @param shell the shell
	 */
	public MyLabel2(Canvas canvas,String viewAttribute,Display display,final GUIActions action,Shell shell) {
		label =  new Label(canvas, SWT.SHADOW_NONE);
		label.setText(viewAttribute+": 0");
		label.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		attribute=viewAttribute;
		label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				action.showChart(attribute);
			}
		});
		
		
		
	}
	
}
