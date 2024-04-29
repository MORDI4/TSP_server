package com.example.tsp_server.dto;

public class LanguageUpdateDTO {

    private Integer languageId;

    public LanguageUpdateDTO() {
    }

    public LanguageUpdateDTO(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "LanguageUpdateDTO{" +
                "languageId=" + languageId +
                '}';
    }
}
