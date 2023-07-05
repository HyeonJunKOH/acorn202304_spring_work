package com.gura.cafe.coffee.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gura.cafe.coffee.dto.CoffeeDto;
import com.gura.cafe.coffee.service.CoffeeService;

@Controller
public class CoffeeController {
	@Autowired
	private CoffeeService service;
	
	//커피 목록 요청 처리
	@RequestMapping("/coffee/list")
	public String getList(HttpServletRequest request) {
		service.getList(request);
		return "coffee/list";
	}
	//커피 메뉴등록 폼 요청 처리
	@RequestMapping("/coffee/upload_form")
	public String uploadForm() {
		
		return "coffee/upload_form";
	}
	//커피메뉴 등록 요청 처리
	@RequestMapping(method = RequestMethod.POST, value = "/coffee/ajax_upload")
	@ResponseBody
	public Map<String, Object>ajaxUpload(CoffeeDto dto,HttpServletRequest request){
		//서비스를 이용해서 업로드된 이미지를 저장하고
		service.saveImage(dto, request);
		//{"isSuccess":true}형식의 json 문자열 응답
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	
}
