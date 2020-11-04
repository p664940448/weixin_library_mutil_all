package com.netmax.library.common;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.WriterException;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel; 
import com.google.zxing.qrcode.encoder.ByteMatrix;

public class QRcode {
	// 图片宽度的一般
	private static final int IMAGE_WIDTH = 80;
	private static final int IMAGE_HEIGHT = 80;
	private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
	private static final int FRAME_WIDTH = 0;
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	//
	public BufferedImage getQR(String content,int height){
		BufferedImage img=null;
		
		try{
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map hints = new HashMap();
		    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		    BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, height, height,hints);
		    img=toBufferedImage(deleteWhite(bitMatrix));
		}catch(Exception e){
			e.printStackTrace();
		}
		return img;
	}
	
	public BufferedImage getBarCode(String content,int height){
        BufferedImage img=null;
		
		try{
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map hints = new HashMap();
		    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		    BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.CODE_128, height, height,hints);
		    img=toBufferedImage(deleteWhite(bitMatrix));
		}catch(Exception e){
			e.printStackTrace();
		}
		return img;
	}
	
	//转成图片
	public static BufferedImage toBufferedImage(BitMatrix matrix) {		
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
	
	//去白边
	public static BitMatrix deleteWhite(BitMatrix matrix){
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}
}
