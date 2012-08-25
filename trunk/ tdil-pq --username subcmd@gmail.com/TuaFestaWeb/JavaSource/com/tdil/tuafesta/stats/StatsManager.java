package com.tdil.tuafesta.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.tdil.tuafesta.model.Statistic;

public class StatsManager {

	protected static Object mutex = new Object();
	
	protected static Object signal = new Object();
	
	protected static final int BUFFER_SIZE = 1000;
	protected static final int BUFFER_DUMP_TIME = 1000 * 60; //1 minuto
	
	protected static List<Statistic> buffer = new ArrayList<Statistic>();
	private static Thread timer;
	private static ThreadPoolExecutor writepool = new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	
	public static synchronized final void start() {
		if (timer == null) {
			timer = new StatTimer();
			timer.start();
		}
	}
	
	public static void addStat(Statistic stat) {
		synchronized (mutex) {
			buffer.add(stat);
			if (buffer.size() >= BUFFER_SIZE) {
				List<Statistic> towrite;
				// copio el buffer
				towrite = new ArrayList<Statistic>(StatsManager.buffer.size());
				towrite.addAll(StatsManager.buffer);
				StatsManager.buffer.clear();
				// escribo el buffer
				StatsManager.addBatch(towrite);
			}
		}
	}
	
	protected static void addBatch(List<Statistic> batch) {
		writepool.submit(new StatBatch(batch));
	}
	
}
