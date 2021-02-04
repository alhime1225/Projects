package com.example.music.model;

import java.util.List;

public class MusicResponse {
    private int resultCount;
    private List<Music> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Music> getResults() {
        return results;
    }

    public void setResults(List<Music> results) {
        this.results = results;
    }
}
