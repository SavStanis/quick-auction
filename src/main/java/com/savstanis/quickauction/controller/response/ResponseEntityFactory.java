package com.savstanis.quickauction.controller.response;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseEntityFactory {

    public static ResponseEntity getSuccessResponse(String name, Object data) {
        Map<Object, Object> body = new LinkedHashMap<>();
        Map<Object, Object> dataMap = new HashMap<>();

        body.put("status", "success");
        dataMap.put(name, data);
        body.put("payload", dataMap);

        return ResponseEntity.ok(body);
    }

    public static ResponseEntity getSuccessResponse() {
        Map<Object, Object> body = new LinkedHashMap<>();
        body.put("status", "success");

        return ResponseEntity.ok(body);
    }

    public static ResponseEntity getErrorResponse(String name, Object data) {
        Map<Object, Object> body = new LinkedHashMap<>();
        Map<Object, Object> errors = new HashMap<>();

        body.put("status", "error");
        errors.put(name, data);
        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }

    public static ResponseEntity getErrorResponse(Object message) {
        Map<Object, Object> body = new LinkedHashMap<>();
        Map<Object, Object> errors = new HashMap<>();

        body.put("status", "error");
        errors.put("message", message);
        body.put("errors", errors);

        return ResponseEntity.badRequest().body(body);
    }

}
