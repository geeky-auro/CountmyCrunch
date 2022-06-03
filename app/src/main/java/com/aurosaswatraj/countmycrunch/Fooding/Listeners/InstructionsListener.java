package com.aurosaswatraj.countmycrunch.Fooding.Listeners;

import com.aurosaswatraj.countmycrunch.Fooding.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {


    void didFetch(List<InstructionsResponse> responses, String message);
    void didError(String message);
}
