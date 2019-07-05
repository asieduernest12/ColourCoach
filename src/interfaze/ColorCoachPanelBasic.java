package interfaze;

import interfaze.ColorUtil.Color;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class ColorCoachPanelBasic extends JPanel {

    ArrayList<Color> colors = null;
    boolean threadRunning = false;
    boolean onlyBasics;

    /**
     * Create the panel.
     */
    public ColorCoachPanelBasic() {
	super();
	setAutoscrolls(true);
	setLayout(new GridLayout(4, 0, 0, 0));
	// setLayout(null);
	addMouseListener(madp);

    }

    public ColorCoachPanelBasic(Boolean onlyBasics) {
	this();
	setup(onlyBasics);

    }

    public void setup(boolean pOnlyBasics) {
	// colors = new ArrayList<ColorUtil.Color>(new
	// ColorUtil().getDistinct().subList(16, 36));

	colors = new ColorUtil().getBasic(pOnlyBasics);
	int column = 0, row = 0;
	for (Color col : colors) {
	    String str = String.format("cell %d %d", column, row);
	    System.out.println(str);
	    add(new ColorTempPanel(col.name,
		    java.awt.Color.decode(col.colorValue)), str);

	    if (row++ == 7) {
		row = 0;
		column++;
	    }

	}

	validate();

    }

    // public static void main(String[] arg0) {
    // EventQueue.invokeLater(new Runnable() {
    // public void run() {
    // try {
    //
    // JFrame frame = new JFrame();
    // frame.getContentPane().add(new ColorCoachPanelBasic(true));
    // frame.setSize(800, 600);
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // });
    // }

    @Override
    protected void paintComponent(Graphics arg0) {
	// TODO Auto-generated method stub
	super.paintComponent(arg0);

    }

    private MouseAdapter madp = new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent arg0) {
	    // TODO Auto-generated method stub
	    super.mouseClicked(arg0);
	    // if (arg0.getButton() == MouseEvent.BUTTON1 && !threadRunning) {
	    // // Perform a runnable task by implementing an Executoor instance
	    // threadRunning = true;
	    // ExecutorService executor = Executors.newCachedThreadPool();
	    // executor.execute(screenRunner);
	    // executor.shutdown();
	    // } else
	    if (arg0.getButton() == MouseEvent.BUTTON3 && !threadRunning) {
		JFrame frame = new JFrame("ColorCoach");
		frame.setSize(800, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new TrainerPanel(colors));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    }

	    else if (arg0.getButton() == MouseEvent.BUTTON1 && threadRunning) {
		threadRunning = false;
	    }
	}

    };

    // private Executor screenExecutor = new Executor() {
    //
    // @Override
    // public void execute(Runnable arg0) {
    // // TODO Auto-generated method stub
    //
    // }
    // };

    private Runnable screenRunner = new Runnable() {

	@Override
	public void run() {
	    // TODO Auto-generated method stub
	    final int cant = 4;
	    int pos = 0;
	    while (pos++ < cant && threadRunning) {
		removeAll();
		setup(onlyBasics);
		try {
		    Thread.sleep(12000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

	    }
	    ;
	    JOptionPane.showMessageDialog(null, "Loop thread has completed",
		    "Loop Terminated", JOptionPane.OK_OPTION);
	    threadRunning = false;
	}

    };
}
