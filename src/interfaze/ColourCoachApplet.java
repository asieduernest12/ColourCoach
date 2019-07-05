package interfaze;

import javax.swing.JApplet;

public class ColourCoachApplet extends JApplet {

    /**
     * Create the applet.
     * 
     * @author HP17
     */
    public ColourCoachApplet() {

	add(new ColourCoachFrame().getContentPane());
	// add(new ColorCoachPanelBasic(true));
    }

}
