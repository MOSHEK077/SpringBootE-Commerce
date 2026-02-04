package com.Jones.mscart.Entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.time.DurationMax;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>(); //Relationship field

    private Double  totalItemAmount;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Order(Double totalItemAmount, Double tax_amount, Double total_amount, String status, String orderno, List<OrderItem> orderItems) {
        this.totalItemAmount = totalItemAmount;
        this.tax_amount = tax_amount;
        this.total_amount = total_amount;
        this.status = status;
        this.referenceId = orderno;
        this.orderItems = orderItems;
    }

    public Order() {

    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalItemAmount() {
        return totalItemAmount;
    }

    public void setTotalItemAmount(Double totalItemAmount) {
        this.totalItemAmount = totalItemAmount;
    }

    public Double getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(Double tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

//    public String getOrderno() {
//        return referenceId;
//    }

//    public void setOrderno(String referenceId) {
//        this.referenceId = referenceId;
//    }

    private Double tax_amount;
    private Double total_amount;
    private String status;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    private  String referenceId;

    

}
