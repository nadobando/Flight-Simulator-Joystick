package view.graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;

import view.JoystickActions;

// TODO: Auto-generated Javadoc
/**
 * The Class MySlider.
 */
public class MySlider {
	
	/** The vertical. */
	private boolean vertical;
	
	/** The slider. */
	private Slider slider;
	
	/** The shell. */
	private Shell shell;
	
	/**
	 * Instantiates a new my slider.
	 *
	 * @param shell the shell
	 * @param vertical the vertical
	 * @param action the action
	 */
	public MySlider(Shell shell,final boolean vertical,final JoystickActions action) {
		this.shell=shell;
		
		if(vertical)
		{
			this.vertical=vertical;
			slider=new Slider(shell,SWT.VERTICAL);
			slider.setLayoutData(new GridData(SWT.LEAD,SWT.FILL, false, true,0,1));
			slider.setMaximum(110);
			slider.setSelection(110);
			slider.setIncrement(1);
			
		}
		else
		{
			this.vertical=vertical;
			slider=new Slider(shell,SWT.HORIZONTAL);
			slider.setLayoutData(new GridData(SWT.FILL, SWT.END, true, false,1,0));
			slider.setIncrement(1);
			slider.setSelection(50);
			
		}
		slider.setMaximum(110);
		
		slider.addListener(SWT.Selection,new Listener() 
		{
			
			@Override
			public void handleEvent(Event event) 
			{
				if(!(slider.getSelection()==slider.getMinimum())&&!(slider.getSelection()==slider.getMaximum()))
					if(vertical)
					{

					        	action.doActionY(-(((double)slider.getSelection()/(double)100)-1));
				         
					}
					else
					{
						action.doActionX(((double)(slider.getSelection()/(double)100)-0.5)*2);
					}
			
			}
	});
		
	}
	
	/**
	 * Gets the slider.
	 *
	 * @return the slider
	 */
	public Slider getSlider() {
		return slider;
	}

}
