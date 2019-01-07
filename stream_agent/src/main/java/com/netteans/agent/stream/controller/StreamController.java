package com.netteans.agent.stream.controller;

import com.netteans.capture.Capturer;
import com.netteans.logging.request.annotation.RequestLog;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;

@Controller
//@RequestLog
public class StreamController {
    private final static Logger logger = LoggerFactory.getLogger(StreamController.class);

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    @RequestLog
    public Object root() {
        return ResponseEntity.ok("exists");
    }

    @RequestMapping(value = "/getVideo/{name:.+}")
    public ResponseEntity getVideo(@PathVariable(value = "name", required = true) String name) {
        File file = new File(System.getProperty("user.home") + "/video/" + name);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("video/mp4"));
        headers.add("Accept-Ranges", "bytes");
        headers.add("Connection", "keep-alive");
//        headers.setContentDispositionFormData("attachment", file.getName());

        try {
            InputStream is = new FileInputStream(file);
            byte[] body = FileCopyUtils.copyToByteArray(is);
            return ResponseEntity.ok().headers(headers).body(body);
        } catch (IOException e) {
            logger.error("{}", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("{\"msg\":\"file not found\"}");
        }
    }

    @RequestMapping(value = "/cam/caputre")
    public ResponseEntity captureCam() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("image/png"));
        headers.add("Accept-Ranges", "bytes");
        headers.add("Connection", "keep-alive");
//        headers.setContentDispositionFormData("attachment", file.getName());


        Frame frame = Capturer.capCam();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        BufferedImage bufferedImage = paintConverter.convert(frame);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", bos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] body = bos.toByteArray();
        return ResponseEntity.ok().headers(headers).body(body);
    }

    @RequestMapping(value = "/cam/stream")
    public ResponseEntity streamCam() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("image/png"));
        headers.add("Accept-Ranges", "bytes");
        headers.add("Connection", "keep-alive");
//        headers.setContentDispositionFormData("attachment", file.getName());


        Frame frame = Capturer.capCam();

        byte[] body = new byte[0];
        return ResponseEntity.ok().headers(headers).body(body);
    }
}
