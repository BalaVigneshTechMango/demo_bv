package com.jwt.videostream.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.videostream.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Integer> {
	
	

}
