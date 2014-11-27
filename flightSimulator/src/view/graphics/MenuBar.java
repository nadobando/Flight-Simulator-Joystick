package view.graphics;

import java.util.HashMap;

import javax.xml.ws.Holder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import view.JoystickActions;
import view.Preferences;
import controller.Controller;
import controller.MyController.LoadConfigCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuBar.
 */
public class MenuBar {
	
	
	
	
	/**
	 * Instantiates a new menu bar.
	 *
	 * @param pilot the pilot
	 * @param shell the shell
	 * @param controller the controller
	 * @param viewKey the view key
	 * @param labels the labels
	 * @param viewNames the view names
	 * @param action the action
	 */
	public MenuBar(final Holder<Boolean> pilot,final Shell shell,  final Controller controller,
			final HashMap<String, String> viewKey,final HashMap<String
			, MyLabel2> labels, final HashMap<String, String> viewNames,final JoystickActions action)
	{
		
		
		
		
		
		
		
	    
	
	     Menu menuBar = new Menu(shell, SWT.BAR);
	     MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	     fileMenuHeader.setText("File");

	     Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
	     fileMenuHeader.setMenu(fileMenu);
	     
	   //open menu:
	     MenuItem preferences= new MenuItem(fileMenu, SWT.PUSH);
	     preferences.setText("Preferences");
	     
	     MenuItem fileOpenItem = new MenuItem(fileMenu, SWT.PUSH);
	     fileOpenItem.setText("Open File");
      //exit menu:
	     MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	     fileExitItem.setText("Exit");
	     
	   //action menu:
	     
	     MenuItem ActionMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	     ActionMenuHeader.setText("Action");

	     Menu actionMenu = new Menu(shell, SWT.DROP_DOWN);
	     ActionMenuHeader.setMenu(actionMenu);

	     MenuItem fileActionItem = new MenuItem(actionMenu, SWT.PUSH);
	     fileActionItem.setText("TakeOff");
		
	     shell.setMenuBar(menuBar);
	    preferences.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				new Preferences(controller.getConfig(),viewKey,controller,labels,viewNames);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
 		fileOpenItem.addSelectionListener(new SelectionListener (){

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("C:\\Users\\Nadir\\Desktop\\College\\2nd Year\\Java\\flightSimulator");
				String[] filterExt = { "*.xml","*.*"};
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				
				if (selected != null)
				{
					 
					 LoadConfigCommand c= (LoadConfigCommand) controller.getCommands().get("LoadConfig").create();
					 
					 c.setFilename(selected);
					 c.execute();
					 System.out.println("Config Loaded");
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
				
			}
 		});
 		
 		fileExitItem.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				MessageBox messageBox = new MessageBox(shell,SWT.ICON_QUESTION| SWT.YES | SWT.NO);
		 		messageBox.setMessage("Do you really want to exit?");
		 		messageBox.setText("Exiting Application");
		 		int response = messageBox.open();
		 		if (response == SWT.YES)
		 		{
		 			controller.getCommands().get("SafeExit").create().execute();
		 			shell.dispose();
		 		}
		 		  
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
 			
 		});

 		fileActionItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				pilot.value=true;
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
   }

}
