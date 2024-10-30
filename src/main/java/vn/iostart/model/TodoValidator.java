package vn.iostart.model;

import java.util.Optional;

import org.thymeleaf.util.StringUtils;

public class TodoValidator {
	public boolean isValid(Todo todo) {
		return Optional.ofNullable(todo).filter(t -> !StringUtils.isEmpty(t.getTitle()))
				.filter(t -> !StringUtils.isEmpty(t.getDetail())).isPresent();
	}
}
