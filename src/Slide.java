import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

/**
 * <p>A slide. This class has drawing functionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide implements Accessable {
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    protected String title;
    protected ArrayList<SlideItem> items;

    public Slide() {
        items = new ArrayList<>();
    }

    //Add a SlideItem
    public void append(SlideItem anItem) {
        items.add(anItem);
    }

    //Return the title of a slide
    public String getTitle() {
        return title;
    }

    //Change the title of a slide
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    //Create a TextItem out of a String and add the TextItem
    public void append(int level, String message) {
        append(new TextItem(level, message));
    }

    //Returns the SlideItem
    public SlideItem getSlideItem(int number) {
        return (SlideItem) items.get(number);
    }

    //Return all the SlideItems in a vector
    public ArrayList<SlideItem> getSlideItems() {
        return items;
    }

    //Returns the size of a slide
    public int getSize() {
        return items.size();
    }

    //Draws the slide
    public void draw(Graphics g, Rectangle area, ImageObserver view) {
        float scale = getScale(area);
        int y = area.y;
        //The title is treated separately
        SlideItem slideItem = new TextItem(0, getTitle());
        Style style = Style.getStyle(slideItem.getLevel());
        slideItem.draw(area.x, y, scale, g, style, view);
        y += slideItem.getBoundingBox(g, view, scale, style).height;
        for (int number = 0; number < getSize(); number++) {
            slideItem = (SlideItem) getSlideItems().get(number);
            style = Style.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, g, style, view);
            y += slideItem.getBoundingBox(g, view, scale, style).height;
        }
    }

    //Returns the scale to draw a slide
    private float getScale(Rectangle area) {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }

    public String GetXml(){
        String xml="<slide>\n";
        xml+="<title>" + getTitle() + "</title>\n";
        ArrayList<SlideItem> slideItems = getSlideItems();
        for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
            SlideItem slideItem = (SlideItem) slideItems.get(itemNumber);
            xml+=slideItem.GetXml();
        }
        xml+="</slide>\n";
        return xml;
    }
}
