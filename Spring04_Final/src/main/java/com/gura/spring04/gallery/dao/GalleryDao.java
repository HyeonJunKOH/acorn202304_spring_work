package com.gura.spring04.gallery.dao;

import java.util.List;

import com.gura.spring04.gallery.dto.GalleryDto;

public interface GalleryDao {
	public void insert(GalleryDto dto);
	public GalleryDto getData(int num);
	public void delete(int num);
	//gallery 리스트 가져오기
	public List<GalleryDto>getList(GalleryDto dto);
	//모든 row의 갯수
	public int getCount();
	public void update(GalleryDto dto);
	//조회수 증가 시키기
	public void addViewCount(int num);
}
