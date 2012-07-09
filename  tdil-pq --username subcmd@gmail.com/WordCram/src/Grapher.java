public class Grapher
{
    
    public static void main(String args[])
    {
	// create a GraphRenderer object
        GraphRenderer g = new GraphRenderer();

	// initialize it, passing the length and width of the desired target image
        g.initialize(800, 200);

	// have the GraphRenderer render out to a file; the file extension determines the output format
        g.render("test.png");	
    }    
} // class Grapher