package com.gura.spring04.gallery.dao;

import java.util.List;

import com.gura.spring04.gallery.dto.GalleryDto;

public interface GalleryDao {
	public void insert(GalleryDto dto);
	public GalleryDto getData(int num);
	public void delete(int num);
	public List<GalleryDto>getList(GalleryDto dto);
	public int getCount(GalleryDto dto);
	public void update(GalleryDto dto);
	//키워드를 활용한 글정보 얻어오기 (키워드에 부합하는 글중에서 이전글, 다음글의 글번호도 얻어올수 있도록)
	public GalleryDto getData(GalleryDto dto);
	//조회수 증가 시키기
	public void addViewCount(int num);
}
