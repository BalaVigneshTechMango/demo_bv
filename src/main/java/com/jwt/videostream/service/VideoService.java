package com.jwt.videostream.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.jwt.videostream.entity.Video;

public interface VideoService {

	 public ResponseEntity<String> uploadVideo(MultipartFile video, String title, String description) throws IOException;
	
	 public ResponseEntity<List<Video>> getAllVideos();
	 
	 public ResponseEntity<String> deleteVideo(int id);
	 
	 public ResponseEntity<StreamingResponseBody> streamVideo(String filename);
	     
}
