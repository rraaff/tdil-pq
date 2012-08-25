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
				if (StatsManager.LOG.isDebugEnabled()) {
					StatsManager.LOG.debug("stat timer sleeping for " + StatsManager.BUFFER_DUMP_TIME.get());
				}
				sleep(StatsManager.BUFFER_DUMP_TIME.get());
				List<Statistic> towrite;
				// copio el buffer
				synchronized (StatsManager.mutex) {
					towrite = new ArrayList<Statistic>(StatsManager.buffer.size());
					towrite.addAll(StatsManager.buffer);
					StatsManager.buffer.clear();
				}
				// escribo el buffer
				int size = towrite.size();
				if (size > 0) {
					StatsManager.addBatch(towrite);
				}
				// si el tamanio es menos de la mitad, incremento el tiempo
				if (size * 2 < StatsManager.BUFFER_SIZE) {
					int actual = StatsManager.BUFFER_DUMP_TIME.get() + StatsManager.DUMP_TIME_INC;
					if (actual < StatsManager.MAX_DUMP_TIME) {
						if (StatsManager.LOG.isDebugEnabled()) {
							StatsManager.LOG.debug("dump time incremented to " + actual);
						}
						StatsManager.BUFFER_DUMP_TIME.set(actual);
					}
				}
			} catch (InterruptedException e) {
				StatsManager.LOG.error(e.getMessage(), e);
			}
		}
	}
}
