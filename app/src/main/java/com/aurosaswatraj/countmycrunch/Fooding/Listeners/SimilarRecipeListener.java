package com.aurosaswatraj.countmycrunch.Fooding.Listeners;



import com.aurosaswatraj.countmycrunch.Fooding.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipeListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);
    void didError(String message);
}
