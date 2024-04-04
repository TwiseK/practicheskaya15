package com.example.orderingfood.model;

import javax.persistence.*;
import jdk.jfr.Enabled;

import java.util.Collection;

@Entity
@Table (name = "feedback")
public class FeedbackModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "mark")
    private int mark;

    @Column (name = "info")
    private String info;

    @ManyToOne
    @JoinColumn(name = "deliver_id") // Указывает на столбец, который связывает сущности
    private DeliveryModel delivery;

    @ManyToOne
    @JoinColumn(name = "user_id") // Указывает на столбец, который связывает сущности
    private FeedbackModel feedback;

    public FeedbackModel() {
    }

    public FeedbackModel(long id, int mark, String info, DeliveryModel delivery, FeedbackModel feedback) {
        this.id = id;
        this.mark = mark;
        this.info = info;
        this.delivery = delivery;
        this.feedback = feedback;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DeliveryModel getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryModel delivery) {
        this.delivery = delivery;
    }

    public FeedbackModel getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackModel feedback) {
        this.feedback = feedback;
    }
}
