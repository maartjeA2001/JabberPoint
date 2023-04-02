import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * <p>The applicatiewindow for a slideviewcomponent</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerFrame extends JFrame {
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    public SlideViewerFrame(String title) {
        super(title);
    }


    //Setup the GUI
    public void setupWindow(ActionController aController) {
        setTitle(JABTITLE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        getContentPane().add(aController.getSlider());
        addKeyListener(new KeyController(aController)); //Add a controller
        setMenuBar(new MenuController(aController));    //Add another controller
        setSize(new Dimension(WIDTH, HEIGHT)); //Same sizes a slide has
        setVisible(true);
    }
}
