package com.tdil.utils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

import java.io.File;

import com.tdil.cache.blob.BlobLocalData;

public class ImageUtils {

	private static int MINIMUN_WIDTH = 60;
	private static int MINIMUN_HEIGHT = 60;
	
	public static void createThumbnail(BlobLocalData blobLocalData, String destFilename, String width, String height) {
		try {
			VideoSize destinationVideoSize = getDestinationVideoSize(blobLocalData.getLocalFileName(), width, width);
			File source = new File(blobLocalData.getLocalFileName());
			File target = new File(destFilename);
			Encoder encoder = new Encoder();
			EncodingAttributes attrs = new EncodingAttributes();
			VideoAttributes video = new VideoAttributes();
			video.setSize(destinationVideoSize);
			attrs.setFormat("image2");
			attrs.setVideoAttributes(video);
			encoder.encode(source, target, attrs, 100);
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static VideoSize getDestinationVideoSize(String fileName, String width, String height) throws InputFormatException, EncoderException {
		Encoder encoder = new Encoder();
		MultimediaInfo info = encoder.getInfo(new File(fileName));
		VideoSize original = info.getVideo().getSize();
		return getDestinationVideoSize(original, width, height);
	}
	
	public static VideoSize getDestinationVideoSize(VideoSize original, String width, String height) {
		if (org.apache.commons.lang.StringUtils.isEmpty(width)) {
			// calculate based in height
			return getVideoSizeUsingHeigth(original, height);
		} else {
			if (org.apache.commons.lang.StringUtils.isEmpty(height)) {
				// calculate based in width
				return getVideoSizeUsingWidth(original, width);
			} else {
				// calculate based in both
				return getVideoSizeUsing(original, width, height);
			}
		}
	}
	
	public static VideoSize createVideoSize(float width, float height) {
		int widthInt = (int)width;
		int heightInt = (int)height;
		int resultWidth = (int)(widthInt % 2 != 0 ? widthInt - 1 : widthInt);
		int resultHeight = (int)(heightInt % 2 != 0 ? heightInt - 1 : heightInt);
		return new VideoSize(resultWidth, resultHeight);
	}

	public static VideoSize getVideoSizeUsingHeigth(VideoSize original, String height) {
		float destHeight = Integer.valueOf(height);
		if (original.getHeight() <= destHeight) {
			// If original is smaller no change
			return createVideoSize(original.getWidth(), original.getHeight());
		} else {
			float ratio = original.getHeight() / destHeight;
			float destWidth = original.getWidth() / ratio;
			if(destWidth < MINIMUN_WIDTH){
				return getVideoSizeUsingWidth(original, Integer.toString(MINIMUN_WIDTH));
			}
			return createVideoSize(destWidth, destHeight);
		}
	}
	
	public static VideoSize getVideoSizeUsingWidth(VideoSize original, String width) {
		float destWidth = Integer.valueOf(width);
		if (original.getWidth() <= destWidth) {
			// If original is smaller no change
			return createVideoSize(original.getWidth(), original.getHeight());
		} else {
			float ratio = (float)original.getWidth() / destWidth;
			float destHeight = original.getHeight() / ratio;
			if(destHeight < MINIMUN_HEIGHT){
				return getVideoSizeUsingHeigth(original, Integer.toString(MINIMUN_HEIGHT));
			}
			return createVideoSize(destWidth, destHeight);
		}
	}

	public static VideoSize getVideoSizeUsing(VideoSize original, String width, String height) {
        float destWidth = Integer.valueOf(width);
        float destHeight = Integer.valueOf(height);
        if (original.getWidth() <= destWidth) {
            return getVideoSizeUsingHeigth(original, height);
        } else {
            if (original.getHeight() <= destHeight) {
                return getVideoSizeUsingWidth(original, width);
            } else {
                float widthRatio = original.getWidth() / destWidth;
                float heightRatio = original.getHeight() / destHeight;

                float minWidthRatio = (float) original.getWidth() / (float) MINIMUN_WIDTH;
                float minHeightRatio = (float) original.getHeight() / (float) MINIMUN_HEIGHT;
                if (widthRatio < heightRatio) { // escalo por el ratio menor, para que sobre la imagen de uno de los lados
                    if (widthRatio > minWidthRatio) {
                        widthRatio = minWidthRatio;
                    }
                    //calculo cuanto quedaria de alto con el escalado del ancho
                    float destHeightFinal = original.getHeight() / widthRatio;
        			if(destHeightFinal < MINIMUN_HEIGHT){
        				//si el alto es menor de 60 tomo el minimo 60.
        				return createVideoSize(original.getWidth() / minHeightRatio, original.getHeight() / minHeightRatio);
        			}else{
        				return createVideoSize(original.getWidth() / widthRatio, original.getHeight() / widthRatio);
        			}
                } else {
                    if (heightRatio > minHeightRatio) {
                        heightRatio = minHeightRatio;
                    }
                  //calculo cuanto quedaria de ancho con el escalado del alto
                    float destWidthFinal = original.getWidth() / heightRatio;
        			if(destWidthFinal < MINIMUN_WIDTH){
        				//si el ancho es menor de 60 tomo el minimo 60.
        				return createVideoSize(original.getWidth() / minWidthRatio, original.getHeight() / minWidthRatio);
        			}else{
        				return createVideoSize(original.getWidth() / heightRatio, original.getHeight() / heightRatio);
        			}
                }
            }
        }
    } 
}
