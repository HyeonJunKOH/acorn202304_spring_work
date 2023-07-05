package com.gura.cafe.coffee.dto;

import org.springframework.web.multipart.MultipartFile;

public class CoffeeDto {
	private int num;
	private String id;
	private String title;
	private String caption;
	private String imagePath;
	private int price;
	//페이징 처리를 위한 필드
	private int startRowNum;
	private int endRowNum;
	private int prevNum;
	private int nextNum;
	//파일 업로드 처리를 하기 위한 필드
	private MultipartFile image;
	
	public CoffeeDto() {}

	public CoffeeDto(int num, String id, String title, String caption, String imagePath, int price, int startRowNum,
			int endRowNum, int prevNum, int nextNum, MultipartFile image) {
		super();
		this.num = num;
		this.id = id;
		this.title = title;
		this.caption = caption;
		this.imagePath = imagePath;
		this.price = price;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
		this.prevNum = prevNum;
		this.nextNum = nextNum;
		this.image = image;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public int getPrevNum() {
		return prevNum;
	}

	public void setPrevNum(int prevNum) {
		this.prevNum = prevNum;
	}

	public int getNextNum() {
		return nextNum;
	}

	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	
	
}
