package com.groupeisi.dao;

import java.util.List;

public interface Repository<T> {

	boolean save(T t);

	boolean delete(int id);

	boolean update(T t);

	List<T> list();

	T get(int id);
}