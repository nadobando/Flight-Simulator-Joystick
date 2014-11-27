package view;


import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import model.Config;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import view.graphics.MyLabel2;
import controller.Controller;
import controller.MyController.MsgCommand;

// TODO: Auto-generated Javadoc
/**
 * The Class Preferences.
 */
public class Preferences {
	
	/** The shell. */
	private Shell shell=new Shell(Display.getCurrent());
	
	/** The config. */
	private Config config=null;
	
	/** The buttons array. */
	private ArrayList<Button> buttonsArray;
	
	/** The attributes table. */
	private Table attributesTable;
	
	/** The flight path. */
	private Text flightPath;
	
	/** The flight engine path. */
	private Text flightEnginePath;
	
	/** The in port. */
	private Text inPort;
	
	/** The ip. */
	private Text ip;
	
	/** The port out. */
	private Text portOut;
	
	/** The view key. */
	private HashMap<String, String> viewKey;
	
	/** The view names. */
	private HashMap<String, String> viewNames;
	
	/**
	 * Instantiates a new preferences.
	 *
	 * @param config the config
	 * @param viewKey the view key
	 * @param controller the controller
	 * @param labels the labels
	 * @param viewNames the view names
	 */
	public Preferences(final Config config,final HashMap<String, String> viewKey, final Controller controller, HashMap<String, MyLabel2> labels,final HashMap<String, String> viewNames) {
		this.viewKey=viewKey;
		this.viewNames=viewNames;
		shell.setLayout(new GridLayout(3, false));
		this.config = config;
		String temp[]=config.getAttributes().split(",");
		
		
		shell.setMinimumSize(new Point(625, 400));
		shell.setSize(625, 400);
		
	
		
		Group attributesGroup = new Group(shell, SWT.NONE);
		attributesGroup.setLayout(new GridLayout(1, false));
		GridData gd_attributesGroup = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2);
		gd_attributesGroup.heightHint = 274;
		attributesGroup.setLayoutData(gd_attributesGroup);
		attributesGroup.setText("Attributes");
		
		attributesTable = new Table(attributesGroup, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd_attributesTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_attributesTable.widthHint = 194;
		gd_attributesTable.heightHint = 168;
		attributesTable.setLayoutData(gd_attributesTable);
		
		
		
		
		Group flightGearGroup = new Group(shell, SWT.NONE);
		flightGearGroup.setText("FlightGear");
		GridData gd_flightGearGroup = new GridData(SWT.CENTER, SWT.FILL, true, false, 1, 2);
		gd_flightGearGroup.widthHint = 238;
		flightGearGroup.setLayoutData(gd_flightGearGroup);
		
		Label NumberOfValuesLabel = new Label(flightGearGroup, SWT.NONE);
		NumberOfValuesLabel.setBounds(10, 20, 100, 15);
		NumberOfValuesLabel.setText("Number Of Values:");
		
		final Combo numOfValues = new Combo(flightGearGroup, SWT.NONE);
		numOfValues.setBounds(10, 41, 91, 23);
		numOfValues.setText("(1-100)");
		
		Label FlightControlLabel = new Label(flightGearGroup, SWT.NONE);
		FlightControlLabel.setBounds(10, 69, 108, 15);
		FlightControlLabel.setText("Flight Controls Path:");
		
		flightPath = new Text(flightGearGroup, SWT.BORDER);
		flightPath.setBounds(10, 86, 211, 21);
		
		Label flightEnginePathLabel = new Label(flightGearGroup, SWT.NONE);
		flightEnginePathLabel.setBounds(10, 113, 104, 15);
		flightEnginePathLabel.setText("Flight Engines Path:");
		
		flightEnginePath = new Text(flightGearGroup, SWT.BORDER);
		flightEnginePath.setBounds(10, 134, 211, 21);
		
		final Button setDefault = new Button(flightGearGroup, SWT.CHECK);
		setDefault.setBounds(10, 228, 93, 16);
		setDefault.setText("Set Default");
		
		Group connections = new Group(shell, SWT.NONE);
		connections.setText("Input / Output");
		connections.setLayout(new GridLayout(1, false));
		GridData gd_connections = new GridData(SWT.LEFT, SWT.TOP, true, false, 1, 2);
		gd_connections.widthHint = 153;
		connections.setLayoutData(gd_connections);
		
		Group inGroup = new Group(connections, SWT.NONE);
		inGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		inGroup.setText("In");
		
		Label inPortLabel = new Label(inGroup, SWT.NONE);
		inPortLabel.setBounds(10, 20, 25, 15);
		inPortLabel.setText("Port:");
		
		inPort = new Text(inGroup, SWT.BORDER);
		inPort.setBounds(41, 17, 74, 21);
		new Label(connections, SWT.NONE);
		
		Group outGroup = new Group(connections, SWT.NONE);
		GridData gd_outGroup = new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1);
		gd_outGroup.widthHint = 118;
		outGroup.setLayoutData(gd_outGroup);
		outGroup.setText("Out");
		
		Label ipLabel = new Label(outGroup, SWT.NONE);
		ipLabel.setBounds(10, 20, 13, 15);
		ipLabel.setText("IP:");
		
		ip = new Text(outGroup, SWT.BORDER);
		ip.setBounds(39, 17, 76, 21);
		
		Label portOutLabel = new Label(outGroup, SWT.NONE);
		portOutLabel.setBounds(10, 47, 25, 15);
		portOutLabel.setText("Port:");
		
		portOut = new Text(outGroup, SWT.BORDER);
		portOut.setBounds(39, 47, 76, 21);
		
		Button cancel = new Button(shell, SWT.NONE);
		GridData gd_cancel = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 3, 1);
		gd_cancel.widthHint = 553;
		cancel.setLayoutData(gd_cancel);
		cancel.setText("Cancel");
		
		Button save = new Button(shell, SWT.NONE);
		GridData gd_save = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 3, 1);
		gd_save.widthHint = 553;
		save.setLayoutData(gd_save);
		save.setText("Save Configuration");

		
		
		
		
		ip.setText(config.getIp());
		inPort.setText(String.valueOf(config.getModelPort()));
		flightEnginePath.setText(config.getFlightEnginesPath());
		flightPath.setText(config.getFlightControlPath());
		numOfValues.setText(String.valueOf(config.getNValues()));
		portOut.setText(String.valueOf(config.getJoystickPort()));
		
		
		save.addSelectionListener(new SelectionListener() {
			
			

			@Override
			public void widgetSelected(SelectionEvent e) {
				

				Config newConfig= new Config();
				newConfig.setAttributes(config.getAttributes());
				newConfig.setDelimiter(",");
				newConfig.setFileName("names.csv");
				newConfig.setFlightControlPath(flightPath.getText());
				newConfig.setFlightEnginesPath(flightEnginePath.getText());
				newConfig.setIp(ip.getText());
				newConfig.setJoystickPort(Integer.parseInt(portOut.getText()));
				newConfig.setModelPort(Integer.parseInt(inPort.getText()));
				newConfig.setNValues(numOfValues.getSelectionIndex()+1);
				String temp=new String();
				String temp2;
				TableItem[] tempItems = attributesTable.getItems();
				
				
				for(TableItem t:tempItems)
				{
					if(t.getChecked())
						temp=temp+t.getText()+",";
				}
				newConfig.setViewAttributes(temp);
				FileDialog dialog= new FileDialog(shell, SWT.SAVE);
				//Extensible Markup Language (XML)
				
				
				
				dialog.setText("Save");
		        dialog.setFilterPath("C:\\Users\\Nadir\\Desktop\\College\\2nd Year\\Java\\flightSimulator");
		        String[] filterExt = { "*.xml" };
		        dialog.setFilterNames(new String[] { "Extensible Markup Language (XML)"});
		        dialog.setFilterExtensions(filterExt);
		        String selected = dialog.open();
		        System.out.println(selected);
		        try {
					XMLEncoder x=new XMLEncoder(new FileOutputStream(selected));
					x.writeObject(newConfig);
					x.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
		        //config.setConfigName(selected);
				if(setDefault.getSelection())
				{
				     
				    PrintWriter pWriter;
					try {
						pWriter = new PrintWriter (new FileWriter(new File("default.txt")));
						pWriter.println (selected);
				   		pWriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    
				}
				MsgCommand msg = (MsgCommand) controller.getCommands().get("Msg").create();
				
				shell.close();
				msg.setMsg("Saved");
				msg.execute();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		cancel.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		for(int i=0;i<100;i++)
			numOfValues.add(Integer.toString(i+1), i);
		for(int i=0;i<temp.length;i++)
		{
			TableItem item = new TableItem(attributesTable, SWT.NONE);
		    item.setText(viewKey.get(temp[i]));
		    
			if(labels.get(viewKey.get(temp[i])) != null)
				item.setChecked(true);
			
		}
		

		shell.open();
	}

	
	
	

}
