package view.graphics;






import java.awt.GridLayout;
import java.awt.geom.Point2D;
import java.util.HashMap;

import javax.xml.ws.Holder;

import model.Config;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.JoystickActions;
import controller.Controller;
// TODO: Auto-generated Javadoc

/**
 * The Class GraphicalJoystick.
 */
public class GraphicalJoystick {
	
	/** The shell. */
	private Shell shell;
	
	/** The display. */
	private Display display;
	
	/** The right click. */
	private boolean rightClick=false;
	
	/** The canvas. */
	private Canvas canvas;
	
	/** The action. */
	private JoystickActions action;
	
	/**
	 * Gets the canvas.
	 *
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/** The r. */
	private int a,b,distance,x,y,r;
	
	/** The First draw. */
	private boolean FirstDraw=true;
	
	/** The drag. */
	private boolean drag=false;
	
	/** The center. */
	private Point center = new Point(0, 0);
	
	/** The config. */
	private Config config;
	
	
	
	/** The distance2. */
	private double distance2;
	
	/**
	 * Gets the display.
	 *
	 * @return the display
	 */
	public Display getDisplay() {
		return display;
		
	}
	
	/**
	 * Instantiates a new graphical joystick.
	 *
	 * @param controller the controller
	 * @param config the config
	 * @param shell the shell
	 * @param display the display
	 * @param action the action
	 * @param labels1 the labels1
	 * @param viewNames the view names
	 */
	public GraphicalJoystick(Controller controller,Config config,final Shell shell, final Display display
			,final JoystickActions action,HashMap<String, MyLabel2> labels1,final String[] viewNames)
	{
		this.action=action;
		this.config=config;
		
		this.shell=shell;
		this.display=display;
		canvas=new Canvas(shell, SWT.BORDER);
		canvas.setSize(200, 200);
		canvas.setLayout(new org.eclipse.swt.layout.GridLayout(1, true));
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		canvas.setBackground(new Color(null, 255, 255, 255));
		x=canvas.getSize().x;
		y=canvas.getSize().y;
		
		
//		 shell.addListener(SWT.Resize,  new Listener () {
//			    public void handleEvent (Event e) {
//			    	double dist=Point2D.distance(center.x, center.y, x, y);
//			      if(dist!=0)
//			    	{
//			    	  	Point2D.distance(center.x, center.y, x, y);
//				    	int maxX=canvas.getSize().x;
//				    	int maxY=canvas.getSize().y;
//				    	center.x=maxX/2;
//				    	center.y=maxY/2;
//				    	if(center.x>x) 
//						{
//							if(center.y>y)
//							{
//								x=(int) 2*center.x-(int) (Point2D.distance(center.x, center.y, x, y)*x/dist);
//								y=(int) (2*center.y-(Point2D.distance(center.x, center.y, x, y)*y/dist));
//							}
//							if(center.y<y)
//							{
//								x=(int) (2*midx-(midx+r*3*Math.sin(a)));
//								y=(int) (midy+r*3*Math.cos(a));
//							}
							
//						}
//						else if(center.x<e.x)
//							{
//								if(center.y>e.y)
//								{		
//									x=(int) (midx+r*3*Math.sin(a));
//									y=(int) (2*midy-(midy+r*3*Math.cos(a)));
//								}
//								if(center.y<e.y)
//								{
//									x=(int) (midx+r*3*Math.sin(a));
//									y=(int) (midy+r*3*Math.cos(a));
//									
//								}
//								
//							}
//				    	x=(int) (Point2D.distance(center.x, center.y, x, y)*x/dist);
//				    	y=(int) (Point2D.distance(center.x, center.y, x, y)*y/dist);
//						
//			    	}
//			    	
//			     
//			    }
//
//				
//			  });
		canvas.addPaintListener(new PaintListener() 
		{

			public void paintControl(PaintEvent e) 
			{
				
				distance2=Point2D.distance(center.x, center.y, x, y);
				int maxX=canvas.getSize().x, mx=maxX/2;
				int maxY=canvas.getSize().y, my=maxY/2;
				r=Math.min(maxX, maxY)/8;
				center.x=mx;
				center.y=my;
				e.gc.drawOval(mx-(r*3), my-(r*3), r*6, r*6);
				
				e.gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
				if(drag==false&&distance2==0)
				{
					x=mx;
					y=my;
				}
				else if(FirstDraw)
				{
					x=mx;
					y=my;
				}
				e.gc.fillOval(x-r, y-r, r*2, r*2);
				FirstDraw=false;
			}
	
	});
		canvas.addMouseMoveListener(new MouseMoveListener() {
		@Override
		public void mouseMove(MouseEvent e) {
			int maxX=canvas.getSize().x, midx=maxX/2;
			int maxY=canvas.getSize().y, midy=maxY/2;
			
			if(drag==true)
			{
				if(Math.sqrt((Math.pow(midx-e.x,2))+(Math.pow(midy-e.y,2)))<=r*3)
				{
					x=e.x;
					y=e.y;
					canvas.redraw();
					
				}
				else 
				{
					double triangleHypotenuse =Point2D.distance(midx, midy, e.x, e.y);
					double triangleBase=Point2D.distance(midx, midy, midx, e.y);
					double a = (Math.acos(triangleBase/triangleHypotenuse ));
					if(center.x>e.x) 
					{
						if(center.y>e.y)
						{
							x=(int) (2*midx-(midx+r*3*Math.sin(a)));
							y=(int) (2*midy-(midy+r*3*Math.cos(a)));
						}
						if(center.y<e.y)
						{
							x=(int) (2*midx-(midx+r*3*Math.sin(a)));
							y=(int) (midy+r*3*Math.cos(a));
						}
						
					}
					else if(center.x<e.x)
						{
							if(center.y>e.y)
							{		
								x=(int) (midx+r*3*Math.sin(a));
								y=(int) (2*midy-(midy+r*3*Math.cos(a)));
							}
							if(center.y<e.y)
							{
								x=(int) (midx+r*3*Math.sin(a));
								y=(int) (midy+r*3*Math.cos(a));
								
							}
							
						}
					canvas.redraw();  
				}
				action.doAction(((double)(x-center.x)/(double)(r*3)), ((double)-(y-center.y)/(double)(r*3)));
			}
		}
	});
		canvas.addMouseListener(new MouseListener() {

		
			@Override
		public void mouseUp(final MouseEvent e) 
				{
					drag=false;
					double triangleHypotenuse =Point2D.distance(center.x, center.y, x, y);
					double triangleBase=Point2D.distance(center.x, center.y, center.x, y);
					final Holder<Double> alpha=new Holder<Double>();
					
					distance = (int) Point2D.distance(center.x, center.y, x, y);
					a= (int) Point2D.distance(center.x, y, x, y);
					b = (int) Point2D.distance(x, y, x, center.y);
					final float realX1=x;
					float realY1=y;
					
					
					
					if(!rightClick)
					{
						alpha.value= (Math.acos(triangleBase/triangleHypotenuse ));
						final double cosAlpha=Math.cos(alpha.value);
						final double sinAlpha=Math.sin(alpha.value);
						if(x!=center.x&&y!=center.y)
						{
							
							
							Runnable animation = new Runnable() {
							      public void run()
							      {
							    	
							        if(y<center.y)
									{
										if(realX1>center.x)
										{
											y=center.y- (int) (cosAlpha * (distance)-1);
											x=center.x+(int) (sinAlpha * (distance)-1);
											
											
											
										}
										else if(realX1<center.x)
										{
											y=center.y- (int) (cosAlpha * (distance)-1);
											x=center.x-(int) (sinAlpha * (distance)-1);
											
										}
									}
									else
									{
										if(realX1<center.x)
										{
											y=center.y+ (int) (cosAlpha * (distance)-1);
											x=center.x-(int) (sinAlpha * (distance)-1);
											
										}
										else
										{
											y=center.y+ (int) (cosAlpha * (distance)-1);
											x=center.x+(int) (sinAlpha * (distance)-1);
											
										}
										
									}
							        action.doAction(((double)(x-center.x)/(double)(r*3)), ((double)-(y-center.y)/(double)(r*3)));
									distance--;
									if(distance<=0)
								    {
								    	display.timerExec(-1, this);
								    	x=canvas.getSize().x/2;
										y=canvas.getSize().y/2;
										canvas.redraw();
										
								    }
									else if(rightClick)
										display.timerExec(-1, this);
									else
									{
										canvas.redraw();
										display.timerExec(5, this);
									}
							      }
							    };

							    animation.run();
							}
					}
					
				}
					
		@Override
		public void mouseDown(MouseEvent e) {
			if(e.button==1)	
				drag=true;
			if(e.button==3&&rightClick==false)
				rightClick=true;
			else
				rightClick=false;
			
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
		
	}

	
		
		

}
