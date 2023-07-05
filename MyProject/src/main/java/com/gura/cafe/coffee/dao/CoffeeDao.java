package com.gura.cafe.coffee.dao;

import java.util.List;

import com.gura.cafe.coffee.dto.CoffeeDto;

public interface CoffeeDao {
	public void insert(CoffeeDto dto);
	public CoffeeDto getData(int num);
	public List<CoffeeDto>getList(CoffeeDto dto);
	public int getCount();
	public void update(CoffeeDto dto);
}
