package com.tdil.ljpeugeot.utils;

import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.services.PeugeotService;

// TODO pablo para las imagenes
public class ModelUtils {

	public static String getImageUrlPath(Vehicle vehicle) {
		return getImageUrlPath(vehicle.getIdModel());
	}
	
	public static String getImageUrlPath(int modelId) {
		Model model = PeugeotService.getModel(modelId);
		if (model == null) {
			return "";
		}
		String imageName = model.getName().replace(" ", "_");
		imageName = imageName.toLowerCase();
		return imageName;
	}
}
