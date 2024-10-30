package vn.iostart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import vn.iostart.entity.CategoryEntity;

public interface ICategoryService {

	void deleteAll();

	void delete(CategoryEntity entity);

	void deleteById(Long id);

	long count();

	Optional<CategoryEntity> findById(Long id);

	List<CategoryEntity> findAllById(Iterable<Long> ids);

	List<CategoryEntity> findAll();

	Page<CategoryEntity> findAll(Pageable pageable);

	List<CategoryEntity> findAll(Sort sort);

	<S extends CategoryEntity> Optional<S> findOne(Example<S> example);

	<S extends CategoryEntity> S save(S entity);

	Page<CategoryEntity> findByNameContaining(String name, Pageable page);

	List<CategoryEntity> findByNameContaining(String name);

}
