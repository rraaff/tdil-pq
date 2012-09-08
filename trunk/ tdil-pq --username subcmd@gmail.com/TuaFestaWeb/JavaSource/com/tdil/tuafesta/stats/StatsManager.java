package com.tdil.tuafesta.stats;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.tuafesta.model.Statistic;

public class StatsManager {

	protected static Object mutex = new Object();
	
	protected static Object signal = new Object();
	
	protected static final int BUFFER_SIZE = 200;
	protected static final AtomicInteger BUFFER_DUMP_TIME = new AtomicInteger(1000 * 10); //10 segundos
	protected static final int MIN_DUMP_TIME = 1000 * 5; // 5 segundos
	protected static final int MAX_DUMP_TIME = 1000 * 60 * 60; // 1 hora
	protected static final int DUMP_TIME_INC = 1000 * 5; // 5 segundos
	
	protected static List<Statistic> buffer = new ArrayList<Statistic>();
	private static Thread timer;
	private static ThreadPoolExecutor writepool = new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	
	protected static final Logger LOG = LoggerProvider.getLogger(StatsManager.class);
	
	public static synchronized final void start() {
		if (timer == null) {
			timer = new StatTimer();
			timer.start();
			
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					writepool.shutdown();
				}
			});
		}
	}
	
	public static void addStat(StatisticType type, int id, String text) {
		Statistic statistic = new Statistic();
		statistic.setStattype(type.getID());
		statistic.setObjectid(id);
		statistic.setTextdata(text);
		statistic.setObjecttime(new Date());
		statistic.setDeleted(0);
		addStat(statistic);
	}
	
	public static void addStat(StatisticType type, int id, int extraId, String text) {
		Statistic statistic = new Statistic();
		statistic.setStattype(type.getID());
		statistic.setObjectid(id);
		statistic.setExtraid(extraId);
		statistic.setTextdata(text);
		statistic.setObjecttime(new Date());
		statistic.setDeleted(0);
		addStat(statistic);
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
				// si puedo decrementar el tiempo de corrida, lo decremento
				int actual = BUFFER_DUMP_TIME.get() - DUMP_TIME_INC;
				if (actual > MIN_DUMP_TIME) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("dump time decremented to " + actual);
					}
					BUFFER_DUMP_TIME.set(actual);
				}
			}
		}
	}
	
	
	
	protected static void addBatch(List<Statistic> batch) {
		writepool.submit(new StatBatch(batch));
	}
	
}
