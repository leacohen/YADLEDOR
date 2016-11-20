package com.lea.samsung.orledorapp.Models;

import java.util.List;

/**
 * Created by USER on 11/20/2016.
 */
public class Category {
    private String name;
    private List<String> subcategories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<String> subcategories) {
        this.subcategories = subcategories;
    }
}
