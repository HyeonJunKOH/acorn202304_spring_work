package com.example.boot07.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot07.cafe.dao.CafeDao;
import com.example.boot07.cafe.dto.CafeDto;

@RestController
public class AndroidController {
	/*
	 * String type을 리턴하면 리턴된 String의 내용이 그대로 응답된다.
	 */
	@GetMapping("/android/tweet")
	public String tweet(String msg) {
		System.out.println("안드로이드에서 전송된 문자열:"+msg);
		return "success!";
	}
	// Map type을 리턴하면 Map에 담긴 내용이 {} 형식의 json 문자열로 응답된다. -> Android에서는 JSONObject 객체를 생성해서 추출
	@PostMapping("/android/tweet2")
	public Map<String, Object>tweet2(String msg){
		System.out.println("안드로이드에서 전송된 문자열:"+msg);
		Map<String, Object>map=new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
	// List type을 리턴하면 List 에 담긴 내용이 [] 형식의 json 문자열로 응답된다. -> Android에서는 JSONArray 객체를 생성해서 추출
	@GetMapping("/android/tweet3")
	public List<String>tweet3(String msg){
		System.out.println("안드로이드에서 전송된 문자열:"+msg);
		List<String>names=new ArrayList<String>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		
		return names;
	}
	//테스트를 위해서 CafeDao 주입 받기
	@Autowired
	private CafeDao dao;
	@GetMapping("/android/list")
	public List<CafeDto>list(){
		//1page 의 내용을 select 해오기 위한 CafeDto 객체를 준비
		CafeDto dto=new CafeDto();
		dto.setStartRowNum(1);
		dto.setEndRowNum(10);
		
		dao.getList(dto);
		
		List<CafeDto>list=dao.getList(dto);
		return list;
	}
}
