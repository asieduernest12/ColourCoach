package interfaze;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.CardLayout;

public class ColourCoachFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel displayPanel;
    private JButton btnShowDistinct;
    private JButton btnRandomColors;
    private JButton btnBasicColors;
    private JButton btnBasicgreysColors;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	ColourCoachFrame frame = new ColourCoachFrame();
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);

	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
    }

    /**
     * Create the frame.
     */
    public ColourCoachFrame() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 865, 501);
	contentPane = new JPanel();
	// contentPane.addMouseMotionListener(new MouseMotionAdapter() {
	// @Override
	// public void mouseMoved(MouseEvent arg0) {
	// //setTitle(arg0.getLocationOnScreen().toString());
	// }
	// });
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new MigLayout("", "[fill]", "[grow,fill][]"));
	contentPane.add(getDisplayPanel(), "cell 0 0,grow");
	contentPane.add(getBtnShowDistinct(), "flowx,cell 0 1,alignx center");
	contentPane.add(getBtnRandomColors(), "cell 0 1,alignx center");
	contentPane.add(getBtnBasicColors(), "cell 0 1,alignx center");
	contentPane.add(getBtnBasicgreysColors(), "cell 0 1,alignx center");
	getDisplayPanel().setLayout(new CardLayout(0, 0));
	getDisplayPanel().add(new CoachPanelRandom(), "name_4841835977601");
	changeTitle(null);
    }

    private JPanel getDisplayPanel() {
	if (displayPanel == null) {
	    displayPanel = new JPanel();
	    displayPanel.setBackground(Color.WHITE);
	    displayPanel.setMaximumSize(new Dimension(1355, 730));

	}
	return displayPanel;
    }

    private JButton getBtnShowDistinct() {
	if (btnShowDistinct == null) {
	    btnShowDistinct = new JButton("Show Distinct Colors");
	    btnShowDistinct.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

		    getDisplayPanel().removeAll();
		    getDisplayPanel().add(new CoachPanelDistinct(),
			    "cell 0 0, grow");
		    changeTitle(btnShowDistinct.getText());
		    validate();
		}
	    });
	}
	return btnShowDistinct;
    }

    private JButton getBtnRandomColors() {
	if (btnRandomColors == null) {
	    btnRandomColors = new JButton("Random Colors");
	    btnRandomColors.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    getDisplayPanel().removeAll();
		    getDisplayPanel().add(new CoachPanelRandom(),
			    "cell 0 0, grow");
		    changeTitle(btnRandomColors.getText());
		    validate();
		}
	    });
	}
	return btnRandomColors;
    }

    private JButton getBtnBasicColors() {
	if (btnBasicColors == null) {
	    btnBasicColors = new JButton("Basic Colors");
	    btnBasicColors.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    getDisplayPanel().removeAll();
		    getDisplayPanel().add(new ColorCoachPanelBasic(true),
			    "cell 0 0, grow");
		    changeTitle(btnBasicColors.getText());
		    validate();
		}
	    });
	}
	return btnBasicColors;
    }

    private JButton getBtnBasicgreysColors() {
	if (btnBasicgreysColors == null) {
	    btnBasicgreysColors = new JButton("Basic + Grey Colors");
	    btnBasicgreysColors.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    getDisplayPanel().removeAll();
		    getDisplayPanel().add(new ColorCoachPanelBasic(false),
			    "cell 0 0, grow");
		    changeTitle(btnBasicgreysColors.getText());
		    validate();

		}
	    });
	}
	return btnBasicgreysColors;
    }

    void changeTitle(String pTitle) {
	if (pTitle != null) {
	    setTitle(String.format("Welcome to ColourCoach%30s", pTitle));

	} else {
	    setTitle("Welcome to ColourCoach");
	}
    }
}
