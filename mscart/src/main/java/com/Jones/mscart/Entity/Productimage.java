package com.Jones.mscart.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Productimage {

    public Productimage() { } // JPA needs no-arg constructor

    public Productimage(Long id, String publicid, String url) {
        this.id = id;
        this.publicid = publicid;
        this.url = url;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicid;
    private String url;

    // getters / setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicid() {
        return publicid;
    }

    public void setPublicid(String publicid) {
        this.publicid = publicid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
