package interfaze;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import interfaze.ColorUtil.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainerPanel extends JPanel {

	ArrayList<Color> cols = null;
	ArrayList<Color> trainerList = null;
	ColorTempPanel cTP = new ColorTempPanel(null, null);
	ArrayList<Integer> uList;
	private JButton btnStart;
	private JLabel lblStatus;
	boolean running = false;

	/**
	 * Create the panel.
	 */
	public TrainerPanel(ArrayList<Color> pCols) {
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		add(cTP, "grow");
		add(getBtnStart(), "flowx,cell 0 1,alignx center");
		add(getLblStatus(), "cell 0 1,alignx center");
		cols = pCols;
		// setupTrainerPanel();
		setupBasic();
		uList = new ArrayList<Integer>(trainerList.size());

	}

	public void setupBasic() {
		trainerList = cols;
	}

	public void setupTrainerPanel() {
		trainerList = new ArrayList<>();
		int pos = 0;
		int randPos = -1;
		while (pos < cols.size()) {
			if (!trainerListContains(trainerList,
					cols.get(randPos = new Random().nextInt(cols.size())).name)) {
				trainerList.add(cols.get(randPos));
			}
			pos++;
		}

	}

	public boolean ulistContains(ArrayList<Integer> pUlist, int findPos) {
		boolean result = false;
		int pos = 0;
		while (pUlist != null && pos < pUlist.size()
				&& pUlist.get(pos) != findPos)
			pos++;
		if (pos < pUlist.size())
			result = true;
		return result;
	}

	public boolean trainerListContains(ArrayList<Color> pColors, String pName) {
		boolean result = false;
		if (pColors.size() != 0) {
			int pos = 0;
			while (pos < pColors.size()
					&& !pColors.get(pos).name.regionMatches(0, pName, 0,
							pName.length())) {
				pos++;
			}
			if (pos < pColors.size())
				result = true;
		}
		return result;
	}

	private JButton getBtnStart() {
		if (btnStart == null) {
			btnStart = new JButton("START TRAINING");

			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!running) {
						running = true;
						ExecutorService executor = Executors
								.newCachedThreadPool();
						executor.execute(trainerRunnable);
						executor.shutdown();
					} else {
						running = false;
					}

				}
			});
			btnStart.setToolTipText("Click Train to begin");
		}
		return btnStart;
	}

	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("STATUS 0/0");
			lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblStatus;
	}

	Runnable trainerRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// Color tempColor ;
			int tempPos = 0;
			while (uList.size() < trainerList.size() && running) {
				do {
					tempPos = new Random().nextInt(trainerList.size());

				} while (ulistContains(uList, tempPos));

				uList.add(tempPos);

				cTP.updateCanvas(trainerList.get(tempPos).name, java.awt.Color
						.decode(trainerList.get(tempPos).colorValue));
				lblStatus.setText(String.format("Status %d/%d", uList.size(),
						trainerList.size()));
				validate();
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			cTP.updateCanvas(null, null);
			uList.clear();
			running = false;
			JOptionPane.showMessageDialog(getParent(), "Training has ended",
					"End of Training", JOptionPane.OK_OPTION);

		}
	};

	// public void main(String [] arg0){
	// JFrame frame = new JFrame("ColorCoach");
	// frame.setSize(800,500);
	// frame.setVisible(true);
	// frame.setLocationRelativeTo(null);
	// frame.add(new tr)
	//
	// }

}
