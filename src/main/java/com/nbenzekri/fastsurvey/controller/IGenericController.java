package com.nbenzekri.fastsurvey.controller;

import com.nbenzekri.fastsurvey.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IGenericController<T,S> {

     ResponseEntity<ResponseDTO<List<T>>> getPolls();

     ResponseEntity<ResponseDTO<T>> getPollById(String id);

     ResponseEntity<ResponseDTO<T>> createPoll(S entityDto);

     ResponseEntity<ResponseDTO<T>> updatePoll(String id, T entityDto);

     ResponseEntity<ResponseDTO<Map<String, Boolean>>> deletePoll(String id);
}
