package com.tdil.examen;

import java.util.ArrayList;
import java.util.List;

import com.tdil.examen.model.OptionQuestion;
import com.tdil.examen.model.Questionnaire;
import com.tdil.examen.model.TrueFalseQuestion;

public class NaturalesMateriales {

	public static final Questionnaire createExamen() {
		Questionnaire result = new Questionnaire();
		
		result.addQuestion(new OptionQuestion("Cuales son los estados de la materia:", 
				createList("Solido", "Liquido", "Gaseoso","Semi solido","Semi liquido"), 
				createList("Solido", "Liquido", "Gaseoso")));
		
		result.addQuestion(new OptionQuestion("Cual es el estado de una roca:", 
				createList("Solido", "Liquido", "Gaseoso"), 
				createList("Solido")));
		
		result.addQuestion(new OptionQuestion("Cual es el estado del mar:", 
				createList("Solido", "Liquido", "Gaseoso"), 
				createList("Liquido")));
		
		result.addQuestion(new OptionQuestion("Cual es el estado del aire dentro de un globo:", 
				createList("Solido", "Liquido", "Gaseoso"), 
				createList("Gaseoso")));
		
		result.addQuestion(new OptionQuestion("Como se clasifican los materiales segun su nivel de procesamiento:", 
				createList("Naturales", "Manufacturados", "Sinteticos", "Artificiales"), 
				createList("Naturales", "Manufacturados", "Sinteticos")));
		
		result.addQuestion(new OptionQuestion("Los materiales que se utilizan sin modificar y se obtienen directamente de la naturaleza son:", 
				createList("Naturales", "Manufacturados", "Sinteticos"), 
				createList("Naturales")));
		
		result.addQuestion(new OptionQuestion("Los que se producen a partir de tecnicas sencillas aplicados sobre los materiales naturales son:", 
				createList("Naturales", "Manufacturados", "Sinteticos"), 
				createList("Manufacturados")));
		
		result.addQuestion(new OptionQuestion("Los materiales que provienen de un complejo proceso son:", 
				createList("Naturales", "Manufacturados", "Sinteticos"), 
				createList("Sinteticos")));
		
		result.addQuestion(new OptionQuestion("Los materiales, segun sus propiedades, se pueden clasificar en:", 
				createList("Buenos y malos conductores del calor", "Buenos y malos conductores de la electricidad", "Duros o blandos, flexibles o rigidos", "Claros u oscuros", "Negros o de color"), 
				createList("Buenos y malos conductores del calor", "Buenos y malos conductores de la electricidad", "Duros o blandos, flexibles o rigidos")));
		
		result.addQuestion(new TrueFalseQuestion("Los materiales que NO SE DEGRADAN on el paso del tiempo son NO BIODEGRADABLES", true));
		
		result.addQuestion(new TrueFalseQuestion("Los materiales que SE DEGRADAN on el paso del tiempo son BIODEGRADABLES", true));
		
		result.addQuestion(new TrueFalseQuestion("El vidrio es biodegradable", false));
		result.addQuestion(new TrueFalseQuestion("El plastico es no biodegradable", true));
		
		result.addQuestion(new OptionQuestion("En que estados podemos encontrar los metales:", 
				createList("Solido", "Liquido", "Gaseoso"), 
				createList("Solido", "Liquido")));
		
		result.addQuestion(new TrueFalseQuestion("Los metales son buenos conductores del calor", true));
		result.addQuestion(new TrueFalseQuestion("Los metales no son buenos conductores de la electricidad", false));
		
		result.addQuestion(new TrueFalseQuestion("Los metales son brillosos", true));
		
		result.addQuestion(new OptionQuestion("Cual de los siguientes metales se encuentra en la naturaleza en estado liquido:", 
				createList("Oro", "Mercurio", "Aluminio","Cobre","Plata"), 
				createList("Mercurio")));
		
		result.addQuestion(new OptionQuestion("Cual de los siguientes metales se encuentra en la naturaleza en estado puro:", 
				createList("Oro", "Plata", "Cobre","Hierro","Aluminio"), 
				createList("Oro", "Plata")));
		result.addQuestion(new OptionQuestion("Cual de los siguientes metales requieren del calor para llevarlos a estado puro:", 
				createList("Oro", "Plata", "Cobre","Hierro","Aluminio"), 
				createList("Cobre", "Hierro")));
		result.addQuestion(new OptionQuestion("Cual de los siguientes metales requieren de la electrolisis para llevarlos a estado puro:", 
				createList("Oro", "Plata", "Cobre","Hierro","Aluminio"), 
				createList("Aluminio")));
		
		result.addQuestion(new OptionQuestion("Los metales son maleables porque:", 
				createList("Se pueden doblar sin romperse", "Se pueden transformar en laminas finas", "Son brillantes","Son duros","Son pesados"), 
				createList("Se pueden transformar en laminas finas")));
		
		result.addQuestion(new OptionQuestion("Los metales son ductiles porque:", 
				createList("Se pueden doblar sin romperse", "Se pueden transformar en laminas finas", "Son brillantes","Son duros","Son pesados"), 
				createList("Se pueden doblar sin romperse")));
		
		result.addQuestion(new OptionQuestion("Una aleacion es la mezcla de:", 
				createList("Dos metales", "Dos no metales", "Un metal y un no metal","Dos no metales","Dos liquidos"), 
				createList("Dos metales","Un metal y un no metal")));
		
		result.addQuestion(new OptionQuestion("Los plasticos son materiales:", 
				createList("Naturales", "Manufacturados", "Sinteticos"), 
				createList("Sinteticos")));
		result.addQuestion(new TrueFalseQuestion("Los plasticos derivan del petroleo", true));
		result.addQuestion(new OptionQuestion("El primer plasticos se llamaba:", 
				createList("Celuloide", "Aluminio", "Sinteticos","Aleacion"), 
				createList("Celuloide")));
		
		result.addQuestion(new OptionQuestion("Los plasticos son:", 
				createList("Elasticos y flexibles", "Aislantes termicos y electricos", "Buenos conductores de la electricidad","Buenos conductores de la electricidad"), 
				createList("Elasticos y flexibles", "Aislantes termicos y electricos")));
		
		result.addQuestion(new OptionQuestion("Los plasticos son:", 
				createList("Faciles de moldear", "Dificiles de moldear"), 
				createList("Faciles de moldear")));
		
		result.addQuestion(new OptionQuestion("Los plasticos que con el calor se ponen flexibles son:", 
				createList("Termoplasticos", "Termoestables"), 
				createList("Termoplasticos")));
		result.addQuestion(new OptionQuestion("Los plasticos que con el calor no se modifican son:", 
				createList("Termoplasticos", "Termoestables"), 
				createList("Termoestables")));
		result.addQuestion(new TrueFalseQuestion("Los plasticos termoplasticos son reciclables", true));
		result.addQuestion(new TrueFalseQuestion("Los plasticos termoestables son reciclables", false));
		
		result.addQuestion(new OptionQuestion("Cuales de los siguientes materiales son fibras sinteticas:", 
				createList("Poliester", "Lycra", "Lana", "Algodon"), 
				createList("Poliester", "Lycra")));
		
		result.addQuestion(new OptionQuestion("Las fibras sinteticas:", 
				createList("Se arrugan", "Son impermeables", "No son atacadas por hongos ni bacterias", "Absorben el sudor"), 
				createList("Son impermeables", "No son atacadas por hongos ni bacterias")));
		
		result.addQuestion(new OptionQuestion("Las fibras sinteticas:", 
				createList("No se arrugan", "No son impermeables", "Son atacadas por hongos ni bacterias", "No absorben el sudor"), 
				createList("No se arrugan", "No absorben el sudor")));
		result.addQuestion(new TrueFalseQuestion("Los fibras sinteticas se pueden planchar", false));
		
		result.addQuestion(new OptionQuestion("Los ceramicos son:", 
				createList("No metalicos", "Metalicos", "Se cocinan", "No se cocinan", "Se moldean"), 
				createList("No metalicos", "Se cocinan", "Se moldean")));
		
		result.addQuestion(new TrueFalseQuestion("Los ceramicos se hacen de arcilla", true));
		result.addQuestion(new OptionQuestion("Los ceramicos:", 
				createList("Resisten altas temperaturas", "Son buenos conductores del calor", "No se oxidan", "Son muy duros", "Son elasticos"), 
				createList("Resisten altas temperaturas", "No se oxidan", "Son muy duros")));
		result.addQuestion(new TrueFalseQuestion("Los ceramicos son buenos conductores de la electricidad", false));
		
		result.addQuestion(new OptionQuestion("Los ceramicos que tienen pequeños espacios que permiten el paso de gases y agua son:", 
				createList("Porosos", "No porosos", "Permeables", "Impermeables"), 
				createList("Porosos", "Permeables")));
		
		result.addQuestion(new OptionQuestion("Los ceramicos que no permiten el paso de gases y agua son:", 
				createList("Porosos", "No porosos", "Permeables", "Impermeables"), 
				createList("No porosos", "Impermeables")));
		
		result.addQuestion(new OptionQuestion("Es un polvo que se produce de una piedra llamada alpez, cuando se mezcla con agua es moldeable:", 
				createList("Yeso", "Cemento", "Hormigon", "Hormigon armado"), 
				createList("Yeso")));
		
		result.addQuestion(new OptionQuestion("Es una mezcla de cal viva, arena, arcilla y agua:", 
				createList("Yeso", "Cemento", "Hormigon", "Hormigon armado"), 
				createList("Cemento")));
		
		result.addQuestion(new OptionQuestion("Material muy duro y resistente, se hace con cemento Portland mas arena y piedras:", 
				createList("Yeso", "Cemento", "Hormigon", "Hormigon armado"), 
				createList("Hormigon")));
		
		result.addQuestion(new OptionQuestion("Esqueleto de barras de hierro que se rellena con hormigon:", 
				createList("Yeso", "Cemento", "Hormigon", "Hormigon armado"), 
				createList("Hormigon armado")));
		
		result.random();
		return result;
	}

	private static List<String> createList(String... args) {
		List<String> result = new ArrayList<String>();
		for (String s : args) {
			result.add(s);
		}
		return result;
	}
}
