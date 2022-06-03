package com.aurosaswatraj.countmycrunch.Fooding.Listeners;


import com.aurosaswatraj.countmycrunch.Fooding.Models.RecipeDetailsResponse;

public interface RecipeDetailsResponseListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
