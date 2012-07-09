import processing.core.PApplet;
import wordcram.Word;
import wordcram.WordCram;

public class renderToPdf extends PApplet {




WordCram wc;
Word[] names;
/*
 * After you run this,
 * open the sketch's folder.
 * See the PDF.
 */

public renderToPdf() {
	// TODO Auto-generated constructor stub
}

public void setup() {
  //size(800, 200);
  background(255);
  
  loadNames();
  
  new WordCram(this)
    .fromWords(names)
    .withColors(0xff000000, 0xff0000dd, 0xffff0000)
    .withFonts("LiberationSans")
    .withFonts("Georgia Italic", "Minya Nouvelle")
    .angledAt(radians(0), radians(90))
    //.maxNumberOfWordsToDraw(10)
    .drawAll();

  save("./mf1.png");
  
  exit();
}

public void loadNames() { 
  String[] nameData = loadStrings("./names.txt");
  names = new Word[nameData.length];
  for (int i = 0; i < names.length; i++) {
    names[i] = parseName(nameData[i]);
  }
}

public Word parseName(String data) {
  String[] parts = split(data, '\t');
  String name = parts[0];
  float frequency = PApplet.parseFloat(parts[1]);
  Word word = new Word(name, frequency);
  return word;
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#DFDFDF", "renderToPdf" });
	//  new renderToPdf().setup();
  }
}
