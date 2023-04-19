package com.example.jedisdemo2;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BechmarkController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BechmarkController.class);

    @Autowired
    MyService myService;

    @GetMapping("/bm")
    public Long benchmark(@RequestParam(value = "n", defaultValue = "10") int n) {
        try {
            log.info("Running benchmark with n = {}", n);
            return GetSetBenchmark.run(n);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error running benchmark", e);
        }
        return -1L;
    }
}
