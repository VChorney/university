package com.university.service;

public interface BaseService<T> {

	T save(T obj);

	Iterable<T> findAll();

	void delete(T var1);

}
