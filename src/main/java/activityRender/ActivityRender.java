/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activityRender;

/**
 *
 * @author Katherine
 */

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.media.MediaLocator;
import principal.Pestanas;
public class ActivityRender {
    /**
	 * Screen Width.
	 */
	public static int screenWidth = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getWidth();

	/**
	 * Screen Height.
	 */
	public static int screenHeight = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getHeight();

	/**
	 * Interval between which the image needs to be captured.
	 */
	public static int captureInterval = 10;

	/**
	 * Temporary folder to store the screenshot.
	 */
	//public static String store = "imgActivityRender";

	/**
	 * Status of the recorder.
	 */
	public static boolean record = false;
        boolean isRunning = true;

	/**
	 * 
	 */
        public void startRecord(String store) {
            Thread recordThread = new Thread() {
                    @Override
                    public void run() {
                        System.out.println("Se comenzo a capturar la pantalla...");
                            Robot rt;
                            int cnt = 0;
                            try {
                                    rt = new Robot();
                                    while (isRunning) {
                                            BufferedImage img = rt
                                                            .createScreenCapture(new Rectangle(screenWidth,
                                                                            screenHeight));
                                            ImageIO.write(img, "jpeg", new File("./"+store+"/"
                                                            + System.currentTimeMillis() + ".jpeg"));
                                          
                                            // System.out.println(record);
                                            Thread.sleep(captureInterval);
                                    }
                            } catch (Exception e) {
                                    e.printStackTrace();
                            }
                    }
            };
            recordThread.start();
    }
        
        
        
    	public static void makeVideo(String movFile, String store) throws MalformedURLException {
		System.out
				.println("#### Easy Capture making video, please wait!!! ####");
		JpegImagesToMovie imageToMovie = new JpegImagesToMovie();
		Vector<String> imgLst = new Vector<String>();
		File f = new File(store);
		File[] fileLst = f.listFiles();
		for (int i = 0; i < fileLst.length; i++) {
			imgLst.add(fileLst[i].getAbsolutePath());
		}
		// Generate the output media locators.
		MediaLocator oml;
		if ((oml = imageToMovie.createMediaLocator(movFile)) == null) {
			System.err.println("Cannot build media locator from: " + movFile);
			System.exit(0);
		}
		imageToMovie.doIt(screenWidth, screenHeight, (1000 / captureInterval),
				imgLst, oml);

	}
      
        
        public void inicializar (String path, int numeroMuestra, String store ) throws Exception{
        System.out.println("######### Starting Easy Capture Recorder #######");
        	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("Your Screen [Width,Height]:" + "["
				+ screen.getWidth() + "," + screen.getHeight() + "]");
		
              /**  for(int i=0;i<5;i++){
			System.out.print(".");
			Thread.sleep(1000);
		}**/
		File f = new File(store);
		if(!f.exists()){
			f.mkdir();
		}
                //startRecord();
                Pestanas.iPerspectivaActivityRender= true;
                System.out.println("Grabando");
        }
        public void detener() throws MalformedURLException{
        
        record = false;
		System.out.println("Easy Capture has stopped.");
		//makeVideo(System.currentTimeMillis()+".mov");
        Pestanas.dPerspectivaActivityRender = true;
        }
}
