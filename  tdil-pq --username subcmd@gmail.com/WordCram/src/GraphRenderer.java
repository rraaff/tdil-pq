import processing.core.*;

public class GraphRenderer
{

    // our headless Processing applet, to which we're drawing
    private PApplet applet = null;

    // the graphics context of the applet - all drawing operations are performed on this object
    private PGraphics context = null;
    
    
    GraphRenderer()
    {
        // this space intentionally left blank
    } // constructor
    
    
    void initialize(int _width, int _height)
    {
        // create a PApplet object and retrieve its graphics context
        applet = new PApplet();        
        context = applet.createGraphics(_width, _height, PApplet.JAVA2D);
        applet.g = context;
    } // initialize

    
    // call this function to perform drawing operations and save the results to an image.
    void render(String _filename)
    {
        if (applet == null || context == null)
        {
            System.out.println("error: GraphRenderer::render(): call initialize() before rendering.");
            return;
        }
        
	// tell the context we're starting to draw
       applet.setup();
        
    } // render
    
    
} // class GraphRenderer