package com.netteans.capture;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class Capturer {
    public static Frame capCam() {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        try {
            grabber.start();
            Frame frame = grabber.grab();
            return frame;
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } finally {
            try {
                grabber.stop();
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
