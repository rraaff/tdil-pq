package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.valueobjects.WallWrittingValueObject;

public class WallUtils {

	public static List<WallWrittingValueObject> getWallWritings(int start, int wallId, int idprof)  {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idWall", wallId);
			params.put("start", start);
			params.put("limit", start + 11);
			List<WallWrittingValueObject> list = DAOManager.getWallWrittingDAO().selectWallWindow(params);
			Profesional prof = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(idprof);
			for (WallWrittingValueObject wwvo : list) {
				if (wwvo.getIdAuthor() == null || wwvo.getIdAuthor().equals(0)) {
					wwvo.setAuthorName(prof.getBusinessname());
				}
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<WallWrittingValueObject>();
		}
	}
	
	public static List<WallWrittingValueObject> getWallWritingsModerate(int start, int wallId, int idprof)  {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idWall", wallId);
			params.put("start", start);
			params.put("limit", start + 11);
			List<WallWrittingValueObject> list = DAOManager.getWallWrittingDAO().selectWallWindowAll(params);
			Profesional prof = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(idprof);
			for (WallWrittingValueObject wwvo : list) {
				if (wwvo.getIdAuthor() == null || wwvo.getIdAuthor().equals(0)) {
					wwvo.setAuthorName(prof.getBusinessname());
				}
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<WallWrittingValueObject>();
		}
	}
	
}
