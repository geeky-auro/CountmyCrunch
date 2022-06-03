package com.aurosaswatraj.countmycrunch.Fooding.Listeners;

import com.aurosaswatraj.countmycrunch.Fooding.Models.RandomRecipe;

import java.util.List;

public interface RandomAPIResponseListener {
    void didFetch(List<RandomRecipe> responses, String message);
    void didError(String message);
}
