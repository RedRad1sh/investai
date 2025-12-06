package dev.redradish.investai.controller;

import dev.redradish.investai.dto.portfolio.PortfolioActionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Так как полностью доверить управление капиталом АИ сейчас небезопасно,
 * все предложенные пользователю действия будут совершаться только после предварительного ревью
 *
 * @author nkucherenko
 */
@RestController
@RequestMapping("/requests")
public class RequestController {
    @PostMapping("/{id}/accept")
    ResponseEntity<PortfolioActionResponse> acceptRequest(@PathVariable Integer id) {
        throw new IllegalStateException();
    }

    @PostMapping("/{id}/cancel")
    ResponseEntity<PortfolioActionResponse> cancelRequest(@PathVariable Integer id) {
        throw new IllegalStateException();
    }

    @GetMapping("/")
    ResponseEntity<List<PortfolioActionResponse>> getAllRequests() {
        throw new IllegalStateException();
    }

    @PostMapping("/{id}/rebuild")
    ResponseEntity<PortfolioActionResponse> rebuildRequestWithComment(@PathVariable Integer id, @RequestBody String userRequest) {
        throw new IllegalStateException();
    }
}
