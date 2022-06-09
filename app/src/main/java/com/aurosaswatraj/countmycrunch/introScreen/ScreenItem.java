package com.aurosaswatraj.countmycrunch.introScreen;

public class ScreenItem {

    String Title,Description,Title_description;
    int ScreenImg;

    public ScreenItem(String title, String title_description,String description, int screenImg) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
        Title_description=title_description;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle_description() {
        return Title_description;
    }

    public void setTitle_description(String title_description) {
        Title_description = title_description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}
