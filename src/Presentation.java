import java.util.ArrayList;


/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation implements Accessable {
    private String showTitle; //The title of the presentation
    private ArrayList<Slide> showList = null; //An ArrayList with slides
    private int currentSlideNumber = 0; //The number of the current slide

    public Presentation() {
        showList = new ArrayList<Slide>();
    }

    public int getSize() {
        return showList.size();
    }

    public String getTitle() {
        return showTitle;
    }

    public void setTitle(String nt) {
        showTitle = nt;
    }

    //Returns the number of the current slide
    public int getSlideNumber() {
        return currentSlideNumber;
    }

    //Change the current slide number and report it the the window
    public void setSlideNumber(int number) {
        if (isValidSlideIndex(number)) {
            currentSlideNumber = number;
        }
    }

    //Navigate to the previous slide unless we are at the first slide
    public void prevSlide() {
        setSlideNumber(currentSlideNumber - 1);
    }

    //Navigate to the next slide unless we are at the last slide
    public void nextSlide() {
        setSlideNumber(currentSlideNumber + 1);
    }

    public Boolean isValidSlideIndex(int newSlideIndex) {
        if (newSlideIndex >= 0 && newSlideIndex < getSize()) {
            return true;
        }
        return false;
    }

    //Remove the presentation
    void clear() {
        showList = new ArrayList<Slide>();
        currentSlideNumber = -1;
    }

    //Add a slide to the presentation
    public void append(Slide slide) {
        showList.add(slide);
    }

    //Return a slide with a specific number
    public Slide getSlide(int number) {
        if (number < 0 || number >= getSize()) {
            return null;
        }
        return (Slide) showList.get(number);
    }

    //Return the current slide
    public Slide getCurrentSlide() {
        return getSlide(currentSlideNumber);
    }

    public String GetXml(){
        String xml = "<?xml version=\"1.0\"?>\n" 
        + "<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">\n" 
        + "<presentation>\n"
        + "<showtitle>\n" 
        + getTitle()
        + "</showtitle>\n";
        for (int slideNumber = 0; slideNumber < getSize(); slideNumber++) {
            Slide slide = getSlide(slideNumber);
            xml+=slide.GetXml();
        }
        xml+="</presentation>\n";
        return xml;
    }
}
