package com.tdil.djmag.model.deprecated;

import java.util.ArrayList;

import com.tdil.utils.XMLAlias;
import com.tdil.utils.XMLUtils;

public class RankingPositions {

	private ArrayList<RankingPosition> positions = new ArrayList<RankingPosition>(100);
	
	public ArrayList<RankingPosition> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<RankingPosition> positions) {
		this.positions = positions;
	}

	public static void main(String[] args) {
		/*RankingPositions pos = new RankingPositions();
		pos.getPositions().add("Arjona fuiste tu");
		pos.getPositions().add("siete ni idea");
		String xml = XMLUtils.asXML(pos, new ArrayList<XMLAlias>());
		System.out.println(xml);
		RankingPositions pos1 = (RankingPositions)XMLUtils.fromXML(xml, new ArrayList<XMLAlias>());
		System.out.println(pos1);
		System.out.println(pos1.getPositions().get(0));
		System.out.println(pos1.getPositions().get(1));
		pos1.getPositions().set(0, "siete ni idea");
		pos1.getPositions().set(1, "Arjona fuiste tu");
		xml = XMLUtils.asXML(pos1, new ArrayList<XMLAlias>());
		System.out.println(xml);*/
	}
	
}
