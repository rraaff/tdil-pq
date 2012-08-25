package com.tdil.tuafesta.stats;

import java.util.ArrayList;
import java.util.List;

import com.tdil.tuafesta.model.Statistic;

public class StatTimer extends Thread {

	public StatTimer() {
		super("stat-timer");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				sleep(StatsManager.BUFFER_DUMP_TIME);
				List<Statistic> towrite;
				// copio el buffer
				synchronized (StatsManager.mutex) {
					towrite = new ArrayList<Statistic>(StatsManager.buffer.size());
					towrite.addAll(StatsManager.buffer);
					StatsManager.buffer.clear();
				}
				// escribo el buffer
				if (towrite.size() > 0) {
					StatsManager.addBatch(towrite);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
