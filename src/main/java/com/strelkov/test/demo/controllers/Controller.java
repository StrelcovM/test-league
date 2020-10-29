package com.strelkov.test.demo.controllers;

import com.strelkov.test.demo.models.Query;
import com.strelkov.test.demo.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test-app")
public class Controller {

    @GetMapping("/dates/{MONTH_NUMBER}")
    public ResponseEntity<Map<String, List<String>>> dates(@PathVariable final int MONTH_NUMBER) {
        DateUtil dateUtil = new DateUtil(MONTH_NUMBER);
        return new ResponseEntity<>(dateUtil.getWeeks(), HttpStatus.OK);
    }

    @PostMapping("/query")
    public ResponseEntity<Map<String, Object>> query(@RequestBody Query query) {
        Map<String, Object> response = new LinkedHashMap<>();
        String validate = query.validate();

        if (validate != null) {
            response.put("status", "validation");
            response.put("message", validate);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            response.put("count", query.getCount());
        } catch (IOException e) {
            String error = e.getMessage();
            response.put("status", "error");
            response.put("message", error);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}