package com.tdil.milka.web.lovehatefonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import processing.core.PFont;

import com.tdil.log4j.LoggerProvider;

public class LoveHateFonts {
	
	private static final Logger LOG = LoggerProvider.getLogger(LoveHateFonts.class);

	public static PFont createFont(String name, int size) {
	    Font baseFont = null;

	    InputStream stream = null;
	    
	    try {
	    	stream = LoveHateFonts.class.getResourceAsStream(name);
	    	baseFont = Font.createFont(Font.TRUETYPE_FONT, stream);
	    	return new PFont(baseFont.deriveFont(size), true, null, stream != null);
	    } catch (FontFormatException e) {
	    	LOG.error(e.getMessage(), e);
	    	return null;
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			return null;
		} finally {
	    	try {
				stream.close();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
	    }
	}

}
