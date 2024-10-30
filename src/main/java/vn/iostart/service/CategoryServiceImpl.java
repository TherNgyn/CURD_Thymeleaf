package vn.iostart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import vn.iostart.entity.CategoryEntity;
import vn.iostart.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
    CategoryRepository category;

	public CategoryServiceImpl(CategoryRepository category) {
		this.category = category;
	}
	
	@Override
	public List<CategoryEntity> findByNameContaining(String name) {
		return category.findByNameContaining(name);
	}

	@Override
	public Page<CategoryEntity> findByNameContaining(String name, Pageable page) {
		return category.findByNameContaining(name, page);
	}

	@Override
	public <S extends CategoryEntity> S save(S entity) {
		if(entity.getCategoryId()==null) {
			return category.save(entity);
		}
		else {
			Optional<CategoryEntity> opt = findById(entity.getCategoryId());
			if(opt.isPresent()) {
				if(StringUtils.isEmpty(entity.getName())) {
					entity.setName(opt.get().getName());
				}else {
					entity.setName(entity.getName());
				}
			}
			return category.save(entity);
		}
		
	}

	@Override
	public <S extends CategoryEntity> Optional<S> findOne(Example<S> example) {
		return category.findOne(example);
	}

	@Override
	public List<CategoryEntity> findAll(Sort sort) {
		return category.findAll(sort);
	}

	@Override
	public Page<CategoryEntity> findAll(Pageable pageable) {
		return category.findAll(pageable);
	}

	@Override
	public List<CategoryEntity> findAll() {
		return category.findAll();
	}

	@Override
	public List<CategoryEntity> findAllById(Iterable<Long> ids) {
		return category.findAllById(ids);
	}

	@Override
	public Optional<CategoryEntity> findById(Long id) {
		return category.findById(id);
	}

	@Override
	public long count() {
		return category.count();
	}

	@Override
	public void deleteById(Long id) {
		category.deleteById(id);
	}

	@Override
	public void delete(CategoryEntity entity) {
		category.delete(entity);
	}

	@Override
	public void deleteAll() {
		category.deleteAll();
	}

	

	
	
}