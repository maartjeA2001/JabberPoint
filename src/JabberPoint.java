/**
 * JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman 
 * (Updated by Maartje Arnoldus 4664140)
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class JabberPoint {
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";
    protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    /**
     * The main program
     */
    public static void main(String[] argv) {
        SlideViewerFrame frame = new SlideViewerFrame(JABVERSION);
        ActionController actionController = new ActionController(frame);
        if (argv.length == 0) { //a demo presentation
            actionController.open(DemoPresentation.demoPresentation());
        } else {
            actionController.open(new XMLAccessor(), argv[0]);
        }
        frame.setupWindow(actionController);
    }
}
