package com.example.music.model;

import java.util.ArrayList;
import java.util.List;

public class MusicResponse {
    private int resultCount;
    private ArrayList<Music> results;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public ArrayList<Music> getResults() {
        return results;
    }

    public void setResults(ArrayList<Music> results) {
        this.results = results;
    }
}
