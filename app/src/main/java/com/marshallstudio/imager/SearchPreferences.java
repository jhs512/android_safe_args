package com.marshallstudio.imager;

import java.util.HashMap;
import java.util.Map;

public class SearchPreferences {
    private static SearchPreferences searchPreferences;
    private final String key;
    private final int per_page;
    private String searchQuery;
    private String order;
    private String category;
    private String imageType;
    private String orientation;
    private boolean editorsChoice;
    private boolean safeSearch;

    private SearchPreferences() {
        safeSearch = true;
        key = "13095942-0e49e235eab21f1cd5f68a111";
        per_page = 200;
        order = "popular";
        imageType = "all";
        orientation = "all";
        editorsChoice = false;
    }

    public static SearchPreferences getSearchPreferences() {
        if (searchPreferences == null) {
            searchPreferences = new SearchPreferences();
        }
        return searchPreferences;
    }


    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setEditorsChoice(boolean editorsChoice) {
        this.editorsChoice = editorsChoice;
    }

    public void setSafeSearch(boolean safeSearch) {
        this.safeSearch = safeSearch;
    }

    public Map<String, String> getSearchParamsMap() {
        Map<String, String> preferences = new HashMap<>();
        preferences.put("key", key);
        preferences.put("per_page", String.valueOf(per_page));
        preferences.put("order", order);
        preferences.put("image_type", imageType);
        preferences.put("orientation", orientation);
        preferences.put("editors_choice", String.valueOf(editorsChoice));
        preferences.put("safesearch", String.valueOf(safeSearch));
        if (searchQuery != null) {
            preferences.put("q", searchQuery);
        }
        if (category != null) {
            preferences.put("category", category);
        }

        return preferences;
    }
}

