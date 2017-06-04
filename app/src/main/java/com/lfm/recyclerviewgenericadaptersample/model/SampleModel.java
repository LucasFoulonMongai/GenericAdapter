package com.lfm.recyclerviewgenericadaptersample.model;

/**
 * Created by Lucas FOULON-MONGAI, github.com/LucasFoulonMongai
 */
public class SampleModel {
    private String titre;
    private String description;

    public SampleModel(String titre, String description) {
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
