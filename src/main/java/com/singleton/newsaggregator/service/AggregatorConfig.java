package com.singleton.newsaggregator.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AggregatorConfig {
    private List<String> sources;

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }
}
