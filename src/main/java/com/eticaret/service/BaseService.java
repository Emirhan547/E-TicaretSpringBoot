package com.eticaret.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <T, ID> {
	T create(T entity);
    Optional<T> getById(ID id);
    List<T> getAll();
    void delete(ID id);

}
