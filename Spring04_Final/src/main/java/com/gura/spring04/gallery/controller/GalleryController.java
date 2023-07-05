package com.gura.spring04.gallery.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.gallery.dto.GalleryDto;
import com.gura.spring04.gallery.service.GalleryService;

@Controller
public class GalleryController {
	@Autowired
	private GalleryService service;
	
	//gallery 게시글의 num이 parameter get 방식으로 넘어온다.
	//detail 요청처리
	@RequestMapping(method = RequestMethod.GET, value = "/gallery/detail")
	public ModelAndView detail(ModelAndView mView, int num) {
		//갤러리 detail 페이지에 필요한 data를 num으로 가져와서 ModelAndView에 저장
		service.getDetail(mView, num);
		mView.setViewName("gallery/detail");
		return mView;
	}
	
	//게시글 목록 요청 처리
	@RequestMapping("/gallery/list")
	public String getList(HttpServletRequest request) {
		service.getList(request);
		return "gallery/list";
	}
	//게시글 업로드 폼 요청 처리
	@RequestMapping("/gallery/upload_form")
	public String uploadForm() {
		
		return "gallery/upload_form";
	}
	
	//게시글 업로드 폼2 요청 처리
	@RequestMapping("/gallery/upload_form2")
	public String uploadForm2() {
			
		return "gallery/upload_form2";
	}
	
	//갤러리 사진 업롣 form 페이지로 이동
	@RequestMapping("/gallery/upload_form3")
	public String uploadForm3() {
		
		return "gallery/upload_form3";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/ajax_upload")
	@ResponseBody
	public Map<String, Object> ajaxUpload(GalleryDto dto, HttpServletRequest request){
		//서비스를 이용해서 업로드된 이미지를 저장하고
		service.saveImage(dto, request);
		//{"isSuccess":true}형식의 json 문자열 응답
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	
	//갤러리 사진 업로드&DB 저장
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/upload")
	public String upload(GalleryDto dto, HttpServletRequest request) {
		//form에서 dto로 데이터 받아옴
		//dto : caption, MultipartFile image를 가지고 있다.
		//request: imagePath 만드는데 사용, session 영역의 id 가져오는데 사용
		service.saveImage(dto, request);
		return "gallery/upload";
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

}
