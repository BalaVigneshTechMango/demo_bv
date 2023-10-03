package com.jwt.videostream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.jwt.videostream.entity.Video;
import com.jwt.videostream.service.VideoService;
import com.jwt.videostream.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class VideoController {

	@Autowired
	private VideoService videoService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/savevideo")
	public ResponseEntity<String> uploadFile(@RequestParam("video") MultipartFile video,
                                             @RequestParam("title") String title,
                                             @RequestParam("description") String description,
                                             @RequestHeader(value = "Authorization", defaultValue = "") 
	                                              String auth) throws Exception{
		jwtUtil.verify(auth);
		return videoService.uploadVideo(video, title, description);
	}		
	
	@GetMapping("/videos")
	public ResponseEntity<List<Video>> getAllVideos(@RequestHeader(value = "Authorization", defaultValue = "") 
	                 String auth)throws Exception {
        jwtUtil.verify(auth);
		return videoService.getAllVideos();
	}
	
	@DeleteMapping("/videos/{id}")
	public ResponseEntity<String> deleteVideo(@PathVariable int id,@RequestHeader(value = "Authorization", 
	                      defaultValue = "") String auth)throws Exception {
		jwtUtil.verify(auth);
		return videoService.deleteVideo(id);
	}
	
	@GetMapping("/video/{filename}")
	public ResponseEntity<StreamingResponseBody> streamVideo(@PathVariable String filename){
		return videoService.streamVideo(filename);
	}
	
}
