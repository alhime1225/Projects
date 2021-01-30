package com.example.music.model;

import java.util.List;

public class MusicResponse {
    private int resultCount;
    private List<MusicModel> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<MusicModel> getResults() {
        return results;
    }

    public void setResults(List<MusicModel> results) {
        this.results = results;
    }
}
