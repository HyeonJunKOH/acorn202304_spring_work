package com.gura.spring04.gallery.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.gallery.dto.GalleryDto;

public interface GalleryService {
	//그림 목록 얻어오기
	public void getList(HttpServletRequest request);
	//그림 업로드하기
	public Map<String, Object> saveGallery(HttpServletRequest request, MultipartFile mFile);
	//갤러리 하나의 정보 얻어오기
	public void getGalleryData(HttpServletRequest request);
	//갤러리 삭제하기
	public void deleteGallery(int num, HttpServletRequest request);
	//갤러리 자세히보기
	public void getDetail(HttpServletRequest request);
	//갤러리 수정하기
	public void updateGallery(GalleryDto dto);
	//업로드된 그림 db에 저장하기
	public void addGallery(GalleryDto dto);
}
