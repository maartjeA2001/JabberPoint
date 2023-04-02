import java.io.IOException;

/**
 * <p>An Accessor makes it possible to read and write data
 * for a presentation.</p>
 * <p>Non-abstract subclasses should implement the load and save methods.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

interface Accessor {
    public static final String DEMO_NAME = "Demo presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

    abstract public Presentation loadFile(String fn) throws IOException;
}
