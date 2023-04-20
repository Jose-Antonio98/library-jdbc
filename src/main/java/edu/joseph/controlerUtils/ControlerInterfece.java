package edu.joseph.controlerUtils;

import java.util.List;

public interface ControlerInterfece<T> {

	void insert(T obj);

	int update(T obj);

	int update(int id, int column, String field);

	void deleteById(Integer id);

	T findById(Integer id);

	List<T> findByString(int column, String field);

	List<T> findAll();
}
