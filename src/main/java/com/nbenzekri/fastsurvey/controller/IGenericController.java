package com.nbenzekri.fastsurvey.controller;

import com.nbenzekri.fastsurvey.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IGenericController<T> {

    public ResponseEntity<ResponseDTO<List<T>>> getPolls();

    public ResponseEntity<ResponseDTO<T>> getPollById(String id);

    public ResponseEntity<ResponseDTO<T>> createPoll(T entityDto);

    public ResponseEntity<ResponseDTO<T>> updatePoll(String id, T entityDto);

    public ResponseEntity<ResponseDTO<Map<String, Boolean>>> deletePoll(String id);
}
