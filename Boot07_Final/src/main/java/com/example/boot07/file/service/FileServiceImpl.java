package com.example.boot07.file.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.exception.NotDeleteException;
import com.example.boot07.file.dao.FileDao;
import com.example.boot07.file.dto.FileDto;



@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileDao dao;
	
	//다운로드 or 업로드할 파일이 저장된 위치 얻어내기 
	@Value("${file.location}")
	private String fileLocation;

	@Override
	public void getList(HttpServletRequest request) {
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=5;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;

		//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int pageNum=1;

		//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
		String strPageNum=request.getParameter("pageNum");
		//만일 페이지 번호가 파라미터로 넘어 온다면
		if(strPageNum != null){
			//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
			pageNum=Integer.parseInt(strPageNum);
		}	
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		/*
			[ 검색 키워드에 관련된 처리 ]
			-검색 키워드가 파라미터로 넘어올수도 있고 안넘어 올수도 있다.		
		*/
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		//만일 키워드가 넘어오지 않는다면 
		if(keyword==null){
			//키워드와 검색 조건에 빈 문자열을 넣어준다. 
			//클라이언트 웹브라우저에 출력할때 "null" 을 출력되지 않게 하기 위해서  
			keyword="";
			condition=""; 
		}

		//특수기호를 인코딩한 키워드를 미리 준비한다. 
		String encodedK=URLEncoder.encode(keyword);
			
		//FileDto 객체에 startRowNum 과 endRowNum 을 담는다.
		FileDto dto=new FileDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
	
		//만일 검색 키워드가 넘어온다면 
		if(!keyword.equals("")){
			//검색 조건이 무엇이냐에 따라 분기 하기
			if(condition.equals("title_filename")){//제목 + 파일명 검색인 경우
				dto.setTitle(keyword);
				dto.setOrgFileName(keyword);
			}else if(condition.equals("title")){ //제목 검색인 경우
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){ //작성자 검색인 경우
				dto.setWriter(keyword);
			} // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
		}
		
		
		//파일 목록을 select 해 온다.(검색 키워드가 있는경우 키워드에 부합하는 전체 글) 
		List<FileDto> list=dao.getList(dto);
		
		//전체 글의 갯수(검색 키워드가 있는경우 키워드에 부합하는 전체 글의 갯수)
		int totalRow=dao.getCount(dto);
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		//응답에 필요한 데이터를 view page 에 전달하기 위해  request scope 에 담는다
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
		request.setAttribute("totalRow", totalRow); 
		request.setAttribute("condition", condition);
		
	}

	@Override
	public void saveFile(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		//업로드된 파일의 정보를 가지고 있는 MultipartFile 객체의 참조값 얻어오기 
		MultipartFile myFile=dto.getMyFile();
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		
		//저장할 파일명을 하나 얻어낸다. 
		String saveFileName=UUID.randomUUID().toString();
		
		//저장할 파일의 상세 경로
		String filePath = fileLocation+File.separator+saveFileName;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //만들어 준다.
		}
		try {
			//upload 폴더에 파일을 저장한다.
			myFile.transferTo(new File(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
		//dto 에 업로드된 파일의 정보를 담는다.
		String id=(String)request.getSession().getAttribute("id");
		dto.setWriter(id); //세션에서 읽어낸 파일 업로더의 아이디 
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		//fileDao 를 이용해서 DB 에 저장하기
		dao.insert(dto);
		//view 페이지에서 사용할 모델 담기 
		mView.addObject("dto", dto);
	}


	@Override
	public void deleteFile(int num, HttpServletRequest request) {
		//삭제할 파일의 정보 얻어오기
		FileDto dto=dao.getData(num);
		//글 작성자와 로그인된 아이디가 일치하는지 확인해서 일치하면 삭제하고, 일치하지 않으면 예외를 발생시키기 
		String id=(String)request.getSession().getAttribute("id");
		if(!dto.getWriter().equals(id)) {
			//예외를 발생시키면 해당예외를 처리하는 곳으로 실행의 흐름이 넘어간다. 
			throw new NotDeleteException("남의 파일 지우기 없기!");
		}
		//파일 시스템에서 삭제
		String saveFileName=dto.getSaveFileName();
		//필드에 있는 파일이 있는 위치를 이용해서 경로를 구성하고 
		String path=fileLocation+File.separator+saveFileName;
		//삭제한다.
		new File(path).delete();
		//DB 에서 파일 정보 삭제
		dao.delete(num);
	}

	@Override
	public ResponseEntity<InputStreamResource> getFileData(int num) throws UnsupportedEncodingException, FileNotFoundException {
		//다운로드 해줄 파일의 정보를 DB 에서 읽어온다.
		FileDto dto=dao.getData(num);
		
		//다운로드 시켜줄 원본 파일명
		String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
		//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
		encodedName=encodedName.replaceAll("\\+"," ");
		//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
		HttpHeaders headers=new HttpHeaders();
		//파일을 다운로드 시켜 주겠다는 정보
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
		//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
		//파일의 크기 정보도 담아준다.
		headers.setContentLength(dto.getFileSize());
		
		//읽어들일 파일의 경로 구성
		String filePath=fileLocation + File.separator + dto.getSaveFileName();
		//파일에서 읽어들일 스트림 객체
		InputStream is=new FileInputStream(filePath);
		//InputStreamResource 객체의 참조값 얻어내기
		InputStreamResource isr=new InputStreamResource(is);
		
		//ResponseEntity 객체의 참조값 얻어내기 
		ResponseEntity<InputStreamResource> resEn=ResponseEntity.ok()
			.headers(headers)
			.body(isr);
		
		return resEn;
	}

}