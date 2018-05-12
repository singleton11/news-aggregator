package com.singleton.newsaggregator.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AggregatorConfig {
    private List<HashMap<String, String>> sources;

    List<HashMap<String, String>> getSources() {
        return sources;
    }

    public void setSources(List<HashMap<String, String>> sources) {
        this.sources = sources;
    }
}
