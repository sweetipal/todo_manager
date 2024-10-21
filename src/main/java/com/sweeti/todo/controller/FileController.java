package com.sweeti.todo.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
	
	Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@PostMapping("/single")
	public String uploadSingle(@RequestParam("image")MultipartFile file) {
		logger.info("File name {}",file.getName());
		logger.info("content type {}",file.getContentType());
		logger.info("Origional File Name {}",file.getOriginalFilename());
		logger.info("file size {}",file.getSize());
//		try {
//			InputStream inputStream = file.getInputStream();
//			FileOutputStream fileOutputStream = new FileOutputStream("data.png");
//			byte data[] = new byte[inputStream.available()];
//			fileOutputStream.write(data);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		return "file testing..";
	}
	
	@PostMapping("/multiple")
	public String uploadMultiple(@RequestParam("images") MultipartFile [] files) {
		Arrays.stream(files).forEach(file->{
			logger.info("File name {}", file.getName());
			logger.info("content type {}",file.getContentType());
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			// call service to upload files  and pass file object
			
		});
		return "Handling multiple files";
	}
	
	// serving image file in response
	
	@GetMapping("/serve-image")
	public void serveImageHandler(HttpServletResponse response) {
		// image
		try {
		InputStream fileInputStream =	new FileInputStream("images/o3.jpg");
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(fileInputStream, response.getOutputStream());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
