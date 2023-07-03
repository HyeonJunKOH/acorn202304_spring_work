package com.gura.spring04.gallery.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gura.spring04.gallery.dto.GalleryDto;
import com.gura.spring04.gallery.service.GalleryService;

@Controller
public class GalleryController {
	@Autowired
	GalleryService service;
	
	//게시글 목록 요청 처리
	@RequestMapping("/gallery/list")
	public String getList(HttpServletRequest request) {
		
		return "gallery/list";
	}
	//게시글 업로드 폼 요청 처리
	@RequestMapping("/gallery/upload_form")
	public String uploadForm() {
		
		return "gallery/upload_form";
	}
	//게시글 업로드 요청 처리
	@RequestMapping()
	@ResponseBody
	public Map<String, Object>galleryUpload(HttpServletRequest request,
								MultipartFile image){
		return service.saveGallery(request, image);
	}
	//게시글 자세히보기 요청처리
	@RequestMapping("/gallery/detail")
	public String detail(HttpServletRequest request) {
		service.getDetail(request);
		return "gallery/detail";
	}
	//삭제 요청 처리
	@RequestMapping("/gallery/delete")
	public String delete(int num, HttpServletRequest request) {
		service.deleteGallery(num, request);
		return "redirect:/gallery/list";
	}
	//갤러리 수정 폼 요청 처리
	@RequestMapping("/gallery/update_form")
	public String updateForm(HttpServletRequest request) {
		service.getGalleryData(request);
		return "gallery/update_form";
	}
	//갤러리 수정 요청 처리
	public String update(GalleryDto dto) {
		service.updateGallery(dto);
		return "gallery/update";
	}
	//게시글 업로드 요청처리
	@RequestMapping("gallery/upload")
	public String insert(GalleryDto dto, HttpSession session) {
		String writer=(String)session.getAttribute("id");
		dto.setWriter(writer);
		service.addGallery(dto);
		return "gallery/upload";
	}
}
