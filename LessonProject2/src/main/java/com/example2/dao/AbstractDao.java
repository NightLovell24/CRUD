package com.example2.dao;

import java.util.List;

public interface AbstractDao<T> {

	void create(T t);
	
	T read(int id);
	
	void update(T t);
	
	void delete(int id);
	
	List<T> getAll();
}
