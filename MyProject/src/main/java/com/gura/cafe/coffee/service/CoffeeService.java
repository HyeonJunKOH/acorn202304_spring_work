package com.gura.cafe.coffee.service;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.servlet.ModelAndView;

import com.gura.cafe.coffee.dto.CoffeeDto;

public interface CoffeeService {
	//커피의 list 가져오기
	public void getList(HttpServletRequest request);
	//커피의 사진 upload&DB 저장하기
	public void saveImage(CoffeeDto dto,HttpServletRequest request);
	//커피 하나의 정보 얻어오기
	public void getData(HttpServletRequest request);
	//커피 detail 패이지에 필요한 data를 ModelAndView에 저장
	public void getDetail(ModelAndView mView, int num);
	//커피 수정하기
	public void updateCoffee(CoffeeDto dto);
}
