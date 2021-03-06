package com.goldys.gymservice.model;

import org.springframework.data.annotation.Id;

/*
 * Boilerplate Code: Do Not Change
 */
public class Program {

    @Id
    private String programCode;
    private String programName;
    private String description;
    private int durationInMonths;
    private float price;
    private float discountRate;
    private float currentPrice;
    private boolean isActive;

    public Program(String programName, String description, int durationInMonths, float price, float discountRate, boolean isActive) {

        this.programName = programName;
        this.description = description;
        this.durationInMonths = durationInMonths;
        this.price = price;
        this.discountRate = discountRate;
        this.isActive = isActive;
    }

    public Program() {

    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice() {
        this.currentPrice = price - ((price * discountRate) / 100);
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
