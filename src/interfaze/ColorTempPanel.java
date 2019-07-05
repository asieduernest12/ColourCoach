package interfaze;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.EtchedBorder;

public class ColorTempPanel extends JPanel {
	private JLabel lblColorName;
	private JPanel panelCanvas;

	/**
	 * Create the panel.
	 */
	public ColorTempPanel(String name, Color color) {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new MigLayout("", "[361px,grow,fill]", "[289px,grow][19px]"));
		add(getPanelCanvas(), "cell 0 0,grow");
		add(getLblColorName(), "cell 0 1,growx,aligny top");
		getLblColorName().setText(name);
		getPanelCanvas().setBackground(color);
		validate();

	}

	private JLabel getLblColorName() {
		if (lblColorName == null) {
			lblColorName = new JLabel("ColorName");
			lblColorName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblColorName.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblColorName;
	}

	private JPanel getPanelCanvas() {
		if (panelCanvas == null) {
			panelCanvas = new JPanel();
		}
		return panelCanvas;
	}

	public void updateCanvas(String pName, Color pColor) {
		getLblColorName().setText(pName);
		getPanelCanvas().setBackground(pColor);
	}
}
