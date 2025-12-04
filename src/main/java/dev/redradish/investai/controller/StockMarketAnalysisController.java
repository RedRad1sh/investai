package dev.redradish.investai.controller;

import dev.redradish.investai.integration.perplexity.QueryMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nkucherenko
 */
@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class StockMarketAnalysisController {
    private final QueryMaker queryMaker;

    @GetMapping("/general")
    public ResponseEntity<String> getGeneralAnalytics() {
        return ResponseEntity.ok(queryMaker.makeQuery(""));
    }
}
