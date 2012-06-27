package com.tdil.milka.web;

import java.util.ArrayList;
import java.util.List;

public class ExperienceUtils {

	public static List<String> getAllExperiences() {
		List<String> result = new ArrayList<String>();
		result.add(Experience.FINALES_DE_EMAIL.name());
		result.add(Experience.POST_ITS.name());
		result.add(Experience.PAPAPEDIA.name());
		result.add(Experience.CARTAS_DE_HIJOS_A_PADRES.name());
		result.add(Experience.APODOS_DE_AMOR.name());
		result.add(Experience.CARTAS_DE_PADRES_A_HIJOS.name());
		return result;
	}
}
