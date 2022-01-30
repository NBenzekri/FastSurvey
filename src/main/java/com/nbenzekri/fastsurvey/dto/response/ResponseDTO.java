package com.nbenzekri.fastsurvey.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private int code;
    private T result;

    public ResponseDTO(T result) {
        this.code = 0;
        this.result = result;
    }

    public ResponseEntity<ResponseDTO<T>> buildOk() {
        return new ResponseEntity<ResponseDTO<T>>(this, HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<T>> buildJH_UTF8_Ok() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", "application/json; charset=utf-8");
        return ResponseEntity.ok().headers(headers).body(this);

    }

    public ResponseEntity<ResponseDTO<T>> buildCreated() {
        return new ResponseEntity<ResponseDTO<T>>(this, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseDTO<T>> buildResponse(HttpStatus status) {
        return new ResponseEntity<ResponseDTO<T>>(this, status);
    }
}
