package com.example.boot06;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {
	//application.properties 파일에 있는 정보 얻어내기
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping("/image/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable("imageName") String imageName) {
		// imageName 에는 응답해줄 이미지의 이름이 들어 있다.
		
		//	fileLocation 필드에는 파일이 저장되어 있는 서버의 파일 시스템상에서의 위치가 들어 있다.
		
		return null;
	}
}
