import javax.swing.*;
import java.io.IOException;
import java.awt.Frame;

/**
 * <p>Actioncontroller wordt gebruikt als centraal punt van alle presentatie acties</p>
 * <p>zoals door de Menu of KeyController</p>
 *
 * @author Maartje Arnoldus
 * @version 2.0 Maartje Arnoldus
 */

public class ActionController {
    private SlideViewerComponent slider;
    private Frame dialogFrame;

    protected static final String TESTFILE = "testPresentation.xml";
    protected static final String SAVEFILE = "savedPresentation.xml";

    private static final String PAGENR = "Page number?";

    private static final String IOEX = "IO Exception: ";
    private static final String LOADERR = "Load Error";
    private static final String SAVEERR = "Save Error";

    public ActionController(SlideViewerFrame SlideViewerFrame) {
        slider = new SlideViewerComponent(SlideViewerFrame);
        dialogFrame = SlideViewerFrame;
    }

    public void nextSlide() {
        slider.getPresentation().nextSlide();
        if (slider != null) {
            slider.update();
        }
    }

    public void prevSlide() {
        slider.getPresentation().prevSlide();
        if (slider != null) {
            slider.update();
        }
    }

    public void navTo(int slideIndex) {
        slider.getPresentation().setSlideNumber(slideIndex);
        if (slider != null) {
            slider.update();
        }
    }

    public void exit(int n) {
        System.exit(n);
    }

    public void clear() {
        slider.getPresentation().clear();
    }

    public void open(Accessor accessor, String fileNm) {
        try {
            open(accessor.loadFile(fileNm));
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(dialogFrame, IOEX + exc,
                    LOADERR, JOptionPane.ERROR_MESSAGE);
            exit(0);
        }
    }

    public void open(Presentation presentation){
        slider.setPresentation(presentation);
        if (dialogFrame != null) {
            dialogFrame.repaint();
        }
        navTo(0);
    }

    public void save() {
        try {
            XMLAccessor xmlAccessor = new XMLAccessor();
            xmlAccessor.saveFile(slider.getPresentation(), SAVEFILE);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(dialogFrame, IOEX + exc,
                    SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
        if (dialogFrame != null) {
            dialogFrame.repaint();
        }
    }

    public void showAboutBox() {
        AboutBox.show(dialogFrame);
    }

    public Presentation getPresentation() {
        return slider.getPresentation();
    }

    public SlideViewerComponent getSlider() {
        return slider;
    }

    public void gotoSlide() {
        String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        navTo(pageNumber - 1);
    }
}
