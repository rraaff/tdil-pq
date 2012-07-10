import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphicsJava2D;
import wordcram.Anglers;
import wordcram.BBTreeBuilder;
import wordcram.Colorers;
import wordcram.Fonters;
import wordcram.Placers;
import wordcram.RenderOptions;
import wordcram.Sizers;
import wordcram.SpiralWordNudger;
import wordcram.Word;
import wordcram.WordAngler;
import wordcram.WordColorer;
import wordcram.WordCramEngine;
import wordcram.WordFonter;
import wordcram.WordNudger;
import wordcram.WordPlacer;
import wordcram.WordShaper;
import wordcram.WordSizer;

public class MilkaTest {

	public static void main(String args[]) {

		WordFonter fonter = Fonters.pickFrom(PApplet.createFont("LiberationSerif-Regular.ttf", 10),
				PApplet.createFont("MINYN___.TTF", 10));
			//Fonters.alwaysUse(PApplet.createDefaultFont(12));
		WordSizer sizer = Sizers.byWeight(5, 70);
		WordColorer colorer = Colorers.pickFrom(PApplet.color(0,255,0), PApplet.color(255,0,0), PApplet.color(0,0,255));
//		alwaysUse();
		WordAngler angler = Anglers.pickFrom(0, PApplet.radians(270));
		WordPlacer placer = Placers.horizLine();
		WordNudger nudger = new SpiralWordNudger();
		PGraphicsJava2D graphics = new PGraphicsJava2D();
		graphics.setSize(400, 100);
		graphics.background(0xffffffff);
		Word words[] = new Word[] { new Word("test", 0.1f) ,
				new Word("ahora", 0.15f),
				new Word("boenaccion", 0.2f),
				new Word("bob", 0.6f),
				new Word("oso", 0.4f),
				new Word("hi5", 0.3f),
				new Word("kiki", 0.25f),
				new Word("lili", 0.15f),
				new Word("chango", 0.057f),
				new Word("publiquest", 0.134f),
				new Word("tdil", 0.05f),
				new Word("AVIONETA", 0.23f),
				new Word("chocolate", 0.1013f),
				new Word("Milka", 0.1234f),
				new Word("alfajor", 0.19f),
				new Word("lolo", 0.3f)};
		WordCramEngine wordCramEngine = new WordCramEngine(graphics, words, fonter, sizer, colorer, angler, placer,
				nudger, new WordShaper(), new BBTreeBuilder(), new RenderOptions());
		wordCramEngine.drawAll();
		graphics.get().save(System.getProperty("user.home") + "/aaaaa.png");
	}
} // class Grapher