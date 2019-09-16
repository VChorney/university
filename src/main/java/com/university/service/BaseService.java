package com.university.service;


import java.util.Optional;

public interface BaseService<T> {

	T save(T obj);

	Iterable<T> saveAll(Iterable<T> objs);

	Optional<T> findById(Long id);

	boolean existsById(Long id);

	Iterable<T> findAll();

	Iterable<T> findAllById(Iterable<Long> objs);

	long count();

	void deleteById(Long id);

	void delete(T var1);

	void deleteAll(Iterable<T> obj);

	void deleteAll();
}
