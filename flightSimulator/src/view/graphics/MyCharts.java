package view.graphics;

import java.awt.Event;
import java.util.concurrent.ArrayBlockingQueue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.ILineSeries;
import org.swtchart.ISeries.SeriesType;

// TODO: Auto-generated Javadoc
/**
 * The Class MyCharts.
 */
public class MyCharts {
	
	/** The attr chart. */
	private Chart attrChart;
	
	/** The x series. */
	private double[] ySeries,xSeries;
	
	/** The shell. */
	private Shell shell;
	
	/** The line series. */
	private ILineSeries lineSeries;
	
	/** The data. */
	private ArrayBlockingQueue<Double>data;
	
	/** The graph. */
	private Runnable graph = new Runnable() {
		
		@Override
		public void run() {
			setYseries(data);
			attrChart.redraw();
			
			
				Display.getCurrent().timerExec(1, this);
		}
	};
	
	/**
	 * Gets the shell.
	 *
	 * @return the shell
	 */
	public Shell getShell() {
		return shell;
	}



	/**
	 * Gets the chart.
	 *
	 * @return the chart
	 */
	public Chart getChart() {
		return attrChart;
	}
	
	
	
	/**
	 * Instantiates a new my charts.
	 *
	 * @param attrName the attr name
	 * @param data the data
	 */
	public MyCharts( String attrName,ArrayBlockingQueue<Double> data) {
		this.data=data;
		ySeries=new double[data.size()];
		//xSeries=new double[time.size()];
		int i=0;
		for(Double d:data)
		{	
			ySeries[i++]=d;
		}
		
		shell = new Shell(Display.getCurrent());
		shell.setLayout(new FillLayout());
		shell.setSize(500, 400);
		shell.setText(attrName +" Graph");
		attrChart= new Chart(shell, SWT.NONE);
		attrChart.getTitle().setText(attrName);
        attrChart.getAxisSet().getXAxis(0).getTitle().setText("Time");
        attrChart.getAxisSet().getYAxis(0).getTitle().setText(attrName);
		
        lineSeries = (ILineSeries) attrChart.
        		getSeriesSet().createSeries(SeriesType.LINE, "line series");
        //lineSeries.setXSeries(xSeries);
		lineSeries.setYSeries(ySeries);
		final IAxis xAxis = attrChart.getAxisSet().getXAxis(0);
        final IAxis yAxis = attrChart.getAxisSet().getYAxis(0);
		
		attrChart.getPlotArea().addMouseMoveListener(new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
                double x = xAxis.getDataCoordinate(e.x);
                double y = yAxis.getDataCoordinate(e.y);
                attrChart.getPlotArea().setToolTipText("x:" + x + ", y:" + y);
            }
        });
		//ILineSeries line=(ILineSeries)attrChart.getSeriesSet().createSeries(SeriesType.LINE, "line series");
		attrChart.getAxisSet().adjustRange();
		
		shell.addShellListener(new ShellListener() {
			
			@Override
			public void shellIconified(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellDeiconified(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellDeactivated(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellClosed(ShellEvent e) {
				
				Display.getCurrent().timerExec(-1, graph);
				
			}
			
			@Override
			public void shellActivated(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	/**
	 * Sets the yseries.
	 *
	 * @param y the new yseries
	 */
	public void setYseries(ArrayBlockingQueue<Double>y)
	{
		ySeries=new double[y.size()];
		//xSeries=new double[x.size()];
		int i=0;
		if(y!=null)
		{	
			for(Double d:y)
			{	
				ySeries[i++]=d;
			}
			lineSeries.setYSeries(ySeries);
			
		}
	
		
		attrChart.getAxisSet().adjustRange();
		
	}



	/**
	 * Gets the runnable.
	 *
	 * @return the runnable
	 */
	public Runnable getRunnable() {
		return graph;
	}

}
