package com.lfm.recyclerviewgenericadaptersample.models;

/**
 * Created by mogwai on 13/10/15.
 */
public class SampleItem {
    private String titre;
    private String description;

    public SampleItem(String titre, String description){
        this.titre = titre;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }
}
