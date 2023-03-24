package ru.warmouse.limiter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmouse.limiter.service.RateLimiterService;

@RequiredArgsConstructor
@RestController
@RequestMapping("rate-limit")
public class RateLimiterController {

    private final RateLimiterService rateLimiterService;

    @GetMapping("/{resourceName}")
    public ResponseEntity<String> canProcess(@PathVariable("resourceName") String resourceName) {
        if (rateLimiterService.canProcess(resourceName)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
    }
}
