package com.medical.entity;

public class Image {
    private Integer id;
    private String url;
    private String marker;
    private String disease;
    private String source;
    private String methodsOfMirnas;
    private String markersMethods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMethodsOfMirnas() {
        return methodsOfMirnas;
    }

    public void setMethodsOfMirnas(String methodsOfMirnas) {
        this.methodsOfMirnas = methodsOfMirnas;
    }

    public String getMarkersMethods() {
        return markersMethods;
    }

    public void setMarkersMethods(String markersMethods) {
        this.markersMethods = markersMethods;
    }

    public Image() {
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", marker='" + marker + '\'' +
                ", disease='" + disease + '\'' +
                ", source='" + source + '\'' +
                ", methodsOfMirnas='" + methodsOfMirnas + '\'' +
                ", markersMethods='" + markersMethods + '\'' +
                '}';
    }
}

