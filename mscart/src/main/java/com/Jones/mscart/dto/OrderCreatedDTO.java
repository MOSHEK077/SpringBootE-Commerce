package com.Jones.mscart.dto;

public class OrderCreatedDTO {
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    private String referenceId;

    public OrderCreatedDTO(String referenceId) {
        this.referenceId = referenceId;
    }
}
