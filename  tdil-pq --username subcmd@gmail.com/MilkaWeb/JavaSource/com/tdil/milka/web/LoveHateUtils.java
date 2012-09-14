package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphicsJava2D;
import wordcram.Anglers;
import wordcram.BBTreeBuilder;
import wordcram.Colorers;
import wordcram.Fonters;
import wordcram.Placers;
import wordcram.RenderOptions;
import wordcram.Sizers;
import wordcram.SpiralWordNudger;
import wordcram.Word;
import wordcram.WordAngler;
import wordcram.WordColorer;
import wordcram.WordCramEngine;
import wordcram.WordFonter;
import wordcram.WordNudger;
import wordcram.WordPlacer;
import wordcram.WordShaper;
import wordcram.WordSizer;

import com.tdil.cache.blob.BlobLocalDiskCache;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.LoveHate;
import com.tdil.milka.model.LoveHateExample;
import com.tdil.milka.web.lovehatefonts.LoveHateFonts;
import com.tdil.struts.TransactionalActionWithResult;

public class LoveHateUtils {

	private static Logger getLog() {
		return LoggerProvider.getLogger(LoveHateUtils.class);
	}
	
	private static final class GetLoveWordCloudTag implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			LoveHateExample loveHateExample = new LoveHateExample();
			loveHateExample.createCriteria().andApprovedEqualTo(1).andLoveEqualTo(1);
			loveHateExample.setOrderByClause("votes DESC");
			return DAOManager.getLoveHateDAO().selectLoveHateByExample(loveHateExample);
		}
	}
	
	private static final class GetLoveWordCloudTagCount implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			LoveHateExample loveHateExample = new LoveHateExample();
			loveHateExample.createCriteria().andApprovedEqualTo(1).andLoveEqualTo(1);
			return DAOManager.getLoveHateDAO().countLoveHateByExample(loveHateExample);
		}
	}
	
	private static final class GetHateWordCloudTag implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			LoveHateExample loveHateExample = new LoveHateExample();
			loveHateExample.createCriteria().andApprovedEqualTo(1).andLoveEqualTo(0);
			loveHateExample.setOrderByClause("votes DESC");
			return DAOManager.getLoveHateDAO().selectLoveHateByExample(loveHateExample);
		}
	}
	
	private static final class GetHateWordCloudTagCount implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			LoveHateExample loveHateExample = new LoveHateExample();
			loveHateExample.createCriteria().andApprovedEqualTo(1).andLoveEqualTo(0);
			return DAOManager.getLoveHateDAO().countLoveHateByExample(loveHateExample);
		}
	}
	
	public static Integer getLoveCount() {
		try {
			Integer count = (Integer)TransactionProvider.executeInTransactionWithResult(new GetLoveWordCloudTagCount());
			return count;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return 0;
		}
	}
	
	public static Integer getHateCount() {
		try {
			Integer count = (Integer)TransactionProvider.executeInTransactionWithResult(new GetHateWordCloudTagCount());
			return count;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	private static List<Word> getLoveWordCloudTag() {
		try {
			List<LoveHate> intermediate = (List<LoveHate>)TransactionProvider.executeInTransactionWithResult(new GetLoveWordCloudTag());
			float total = 0;
			for (LoveHate loveHate : intermediate) {
				total = total + loveHate.getVotes();
			}
			List<Word> result = new ArrayList<Word>();
			for (LoveHate loveHate : intermediate) {
				result.add(new Word(loveHate.getContent(), Math.max(loveHate.getVotes().floatValue() / total, 0.05f)));
			}
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Word>();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static List<Word> getHateWordCloudTag() {
		try {
			List<LoveHate> intermediate = (List<LoveHate>)TransactionProvider.executeInTransactionWithResult(new GetHateWordCloudTag());
			float total = 0;
			for (LoveHate loveHate : intermediate) {
				total = total + loveHate.getVotes();
			}
			List<Word> result = new ArrayList<Word>();
			for (LoveHate loveHate : intermediate) {
				result.add(new Word(loveHate.getContent(), Math.max(loveHate.getVotes().floatValue() / total, 0.05f)));
			}
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Word>();
		}
	}

	public static void createLoveWordCloudTag() {
		List<Word> wordList = getLoveWordCloudTag();
		Word words[] = new Word[wordList.size()];
		int index = 0;
		for (Word word : wordList) {
			words[index++] = word;
		}
		PFont font1 = LoveHateFonts.createFont("georgiaz_0.ttf", 20);
		PFont font2 = LoveHateFonts.createFont("BOD_PSTC.ttf", 20);
		PFont font3 = LoveHateFonts.createFont("georgia_0.ttf", 20);
		WordFonter fonter = Fonters.pickFrom(font1, font2, font3);
		WordSizer sizer = Sizers.byWeight(10, 50);
		WordColorer colorer = Colorers.pickFrom(PApplet.color(113,68,149), PApplet.color(23,20,19), PApplet.color(111,74,116), PApplet.color(147,145,147), PApplet.color(168,91,167));
		WordAngler angler = Anglers.pickFrom(0, PApplet.radians(270));
		WordPlacer placer = Placers.centerClump();
		WordNudger nudger = new SpiralWordNudger();
		PGraphicsJava2D graphics = new PGraphicsJava2D();
		graphics.setSize(1200, 357);
		graphics.background(0xffffffff);
		RenderOptions renderOptions = new RenderOptions();
		WordCramEngine wordCramEngine = new WordCramEngine(graphics, words, fonter, sizer, colorer, angler, placer,
				nudger, new WordShaper(), new BBTreeBuilder(), renderOptions);
		wordCramEngine.drawAll();
		graphics.get().save(BlobLocalDiskCache.getDiskBlobLocation() + "/loveCloud.png");
		for (Word word : words) {
			if (word.wasSkipped()) {
				System.out.print("skipped " + word.word + " because ");
				System.out.println(word.wasSkippedBecause());
			}
		}
	}
	
	public static void createHateWordCloudTag() {
		List<Word> wordList = getHateWordCloudTag();
		Word words[] = new Word[wordList.size()];
		int index = 0;
		for (Word word : wordList) {
			words[index++] = word;
		}
		PFont font1 = LoveHateFonts.createFont("roughage.ttf", 20);
		PFont font2 = LoveHateFonts.createFont("SkunkMonkeyRough", 20);
		PFont font3 = LoveHateFonts.createFont("wornmss.ttf", 20);
		WordFonter fonter = Fonters.pickFrom(font1, font2, font3);
		WordSizer sizer = Sizers.byWeight(10, 50);
		WordColorer colorer = Colorers.pickFrom(PApplet.color(0,0,0), PApplet.color(10,10,10), PApplet.color(20,20,20));
		WordAngler angler = Anglers.pickFrom(0, PApplet.radians(270));
		WordPlacer placer = Placers.centerClump();
		WordNudger nudger = new SpiralWordNudger();
		PGraphicsJava2D graphics = new PGraphicsJava2D();
		graphics.setSize(1200, 367);
		graphics.background(0xffffffff);
		RenderOptions renderOptions = new RenderOptions();
		WordCramEngine wordCramEngine = new WordCramEngine(graphics, words, fonter, sizer, colorer, angler, placer,
				nudger, new WordShaper(), new BBTreeBuilder(), renderOptions);
		wordCramEngine.drawAll();
		graphics.get().save(BlobLocalDiskCache.getDiskBlobLocation() + "/hateCloud.png");
		for (Word word : words) {
			if (word.wasSkipped()) {
				System.out.print("skipped " + word.word + " because ");
				System.out.println(word.wasSkippedBecause());
			}
		}
	}
	
	
}
