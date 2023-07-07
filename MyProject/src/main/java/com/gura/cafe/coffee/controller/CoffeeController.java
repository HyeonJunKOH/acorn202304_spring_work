package com.gura.cafe.coffee.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	//detail요청처리
	@RequestMapping(method = RequestMethod.GET, value = "/coffee/detail")
	public ModelAndView detail(ModelAndView mView, int num) {
		//coffee detail 페이지에 필요한 data를 num으로 가져와서 ModelAndView에 저장
		service.getDetail(mView, num);
		mView.setViewName("coffee/detail");
		return mView;
	}
	//메뉴 수정 폼 요청 처리 
	@RequestMapping("/coffee/update_form")
	public String updateForm(HttpServletRequest request) {
		service.getData(request);
		return "coffee/update_form";
	}
	//메뉴 수정 요청처리
	@RequestMapping("/coffee/update")
	public String update(CoffeeDto dto) {
		service.updateCoffee(dto);
		return "coffee/update";
	}
	//주문보기 폼 요청 처리
	@RequestMapping("/coffee/order_list")
	public String list() {
		
		return "coffee/order_list";
	}
}
