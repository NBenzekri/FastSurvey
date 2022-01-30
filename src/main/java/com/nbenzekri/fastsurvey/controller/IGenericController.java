package com.nbenzekri.fastsurvey.controller;

import com.nbenzekri.fastsurvey.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IGenericController<T,S> {

     ResponseEntity<ResponseDTO<List<T>>> getAll();

     ResponseEntity<ResponseDTO<T>> getById(String id);

     ResponseEntity<ResponseDTO<T>> create(S entityDto);

     ResponseEntity<ResponseDTO<T>> update(String id, T entityDto);

     ResponseEntity<ResponseDTO<Map<String, Boolean>>> delete(String id);
}
