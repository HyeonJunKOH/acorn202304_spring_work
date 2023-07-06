package com.gura.cafe.coffee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.cafe.coffee.dto.CoffeeDto;

@Repository
public class CoffeeDaoImpl implements CoffeeDao {
	@Autowired
	SqlSession session;
	
	@Override
	public void insert(CoffeeDto dto) {
		session.insert("coffee.insert", dto);
	}

	@Override
	public CoffeeDto getData(int num) {
		
		return session.selectOne("coffee.getData",num);
	}

	@Override
	public List<CoffeeDto> getList(CoffeeDto dto) {
		
		return session.selectList("coffee.getList", dto);
	}

	@Override
	public int getCount() {
		
		return session.selectOne("coffee.getCount");
	}

	@Override
	public void update(CoffeeDto dto) {
		session.update("coffee.update", dto);
	}

}
