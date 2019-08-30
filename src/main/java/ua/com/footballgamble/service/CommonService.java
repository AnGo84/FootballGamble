package ua.com.footballgamble.service;

import java.util.List;

public interface CommonService<T> {

	T findById(long id);

	T findByName(String name);

	void save(T object);

	void update(T object);

	void deleteById(long id);

	List<T> findAll();

	boolean isExist(T object);

}
