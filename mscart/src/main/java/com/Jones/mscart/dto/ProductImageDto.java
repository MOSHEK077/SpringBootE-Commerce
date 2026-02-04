//package com.Jones.mscart.dto;
//
//public class ProductImageDto {
//    public ProductImageDto(String url) {
//        this.url = url;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public ProductImageDto() {
//    }
//
//    private String url;
//}

package com.Jones.mscart.dto;

public class ProductImageDto {
    private String url;

    public ProductImageDto() {
    }

    public ProductImageDto(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}