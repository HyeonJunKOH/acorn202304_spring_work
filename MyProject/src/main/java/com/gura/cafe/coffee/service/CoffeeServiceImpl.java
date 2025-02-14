package com.gura.cafe.coffee.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.cafe.coffee.dao.CoffeeDao;
import com.gura.cafe.coffee.dto.CoffeeDto;

@Service
public class CoffeeServiceImpl implements CoffeeService{
	@Autowired
	private CoffeeDao dao;
	
	@Override
	public void getList(HttpServletRequest request) {
		 //한 페이지에 몇개씩 표시할 것인지
	     final int PAGE_ROW_COUNT=8;
	     //하단 페이지를 몇개씩 표시할 것인지
	     final int PAGE_DISPLAY_COUNT=5;
	      
	     //보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	     int pageNum=1;
	     //페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	     String strPageNum = request.getParameter("pageNum");
	     //만일 페이지 번호가 파라미터로 넘어 온다면
	     if(strPageNum != null){
	        //숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
	        pageNum=Integer.parseInt(strPageNum);
	     }
	      
	     //보여줄 페이지의 시작 ROWNUM
	     int startRowNum = 1 + (pageNum-1) * PAGE_ROW_COUNT;
	     //보여줄 페이지의 끝 ROWNUM
	     int endRowNum = pageNum * PAGE_ROW_COUNT;
	      
	     //startRowNum 과 endRowNum  을 GalleryDto 객체에 담고
	     CoffeeDto dto = new CoffeeDto();
	     dto.setStartRowNum(startRowNum);
	     dto.setEndRowNum(endRowNum);
	      
	     //GalleryDao 객체를 이용해서 회원 목록을 얻어온다.
	     List<CoffeeDto> list = dao.getList(dto);
	      
	     //하단 시작 페이지 번호 
	     int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
	     //하단 끝 페이지 번호
	     int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
	      
	     //전체 row 의 갯수
	     int totalRow = dao.getCount();
	     //전체 페이지의 갯수 구하기
	     int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
	     //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	     if(endPageNum > totalPageCount){
	         endPageNum = totalPageCount; //보정해 준다. 
	     }
	      
	     //request 영역에 담아주기
	     request.setAttribute("list", list);   //gallery list
	     request.setAttribute("startPageNum", startPageNum);   //시작 페이지 번호
	     request.setAttribute("endPageNum", endPageNum);   //끝 페이지 번호
	     request.setAttribute("pageNum", pageNum);   //현재 페이지 번호
	     request.setAttribute("totalPageCount", totalPageCount);   //모든 페이지 count
	
	}

	@Override
	public void saveImage(CoffeeDto dto, HttpServletRequest request) {
		//업로드된 파일의 정보를 가지고 있는 MultipartFile 객체의 참조값을 얻어오기
		MultipartFile image=dto.getImage();
		//원본 파일명 -> 저장할 파일 이름 만들기 위해 사용됨
		String orgFileName = image.getOriginalFilename();
		//파일 크기 -> 다운로드가 없으므로, 여기서는 필요 없다.
		long fileSize = image.getSize();
		
		//webapp/upload 폴더까지의 실제 경로(서버의 파일 시스템 상에서의 경로)
		String realPath = request.getServletContext().getRealPath("/resources/upload");
		//db 에 저장할 파일의 상세경로
		String filePath = realPath + File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(filePath);
		if(!upload.exists()) {
			//만약 디렉토리가 존재하지X
			upload.mkdir();//폴더 생성
		}
		//저장할 파일의 이름을 구성한다. -> 우리가 직접 구성해줘야 한다.
		String saveFileName = System.currentTimeMillis()+ orgFileName;
		
		try {
			//upload 폴더에 파일을 저장한다.
			image.transferTo(new File(filePath + saveFileName));
			System.out.println();
		}catch(Exception e){
			e.printStackTrace();
		}
		//dto 에 업로드된 파일의 정보를 담는다.
	    //-> parameer 로 넘어온 dto 에는 caption, image 가 들어 있었다.
	    //-> 추가할 것 : writer(id), imagePath 만 추가로 담아주면 된다.
	    //-> num, regdate : db 에 추가하면서 자동으로 들어감
		String id=(String)request.getSession().getAttribute("id");
		dto.setId(id);
		//gallery 는 사진 다운 기능이 없다. -> orgFileName, saveFileName, fileSize 저장할 필요X
	    //imagePath 만 저장해주면 됨
		dto.setImagePath("/resources/upload/"+saveFileName);
		//CoffeeDao를 이용해서 db에 저장하기
		dao.insert(dto);
	}

	@Override
	public void getData(HttpServletRequest request) {
		//수정할 메뉴 번호
		int num=Integer.parseInt(request.getParameter("num"));
		//수정할 메뉴의 정보를 얻어와서
		CoffeeDto dto=dao.getData(num);
		//request에 담아준다.
		request.setAttribute("dto", dto);
		
	}

	@Override
	public void getDetail(ModelAndView mView, int num) {
		//dao로 해당 게시글 num에 해당하는 데이터(dto)를 가져온다.
		CoffeeDto dto=dao.getData(num);//prev, next 번호까지 담긴다.
		//ModelAndView에 가져온 dto를 담는다.
		mView.addObject("dto", dto);
	}

	@Override
	public void updateCoffee(CoffeeDto dto) {
		
		dao.update(dto);
	}

}
