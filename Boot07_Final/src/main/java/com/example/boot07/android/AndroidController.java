package com.example.boot07.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AndroidController {
	
	@GetMapping("/android/tweet")
	public String tweet(String msg) {
		System.out.println("안드로이드에서 전송된 문자열:"+msg);
		return "success!";
	}
	
	@PostMapping("/android/tweet2")
	public Map<String, Object>tweet2(String msg){
		System.out.println("안드로이드에서 전송된 문자열:"+msg);
		Map<String, Object>map=new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
	@GetMapping("/android/tweet3")
	public List<String>tweet3(String msg){
		System.out.println("안드로이드에서 전송된 문자열:"+msg);
		List<String>names=new ArrayList<String>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		
		return names;
	}
}
