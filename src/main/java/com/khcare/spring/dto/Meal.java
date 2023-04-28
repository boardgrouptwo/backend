package com.khcare.spring.dto;

public class Meal {
    private int mealNo;
    private String mealType;
    private String mealOrigin;
    private int mealCal;
    private String mealNut;
    private String mealDate;

    public int getMealNo() {
        return mealNo;
    }

    public void setMealNo(int mealNo) {
        this.mealNo = mealNo;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealOrigin() {
        return mealOrigin;
    }

    public void setMealOrigin(String mealOrigin) {
        this.mealOrigin = mealOrigin;
    }

    public int getMealCal() {
        return mealCal;
    }

    public void setMealCal(int mealCal) {
        this.mealCal = mealCal;
    }

    public String getMealNut() {
        return mealNut;
    }

    public void setMealNut(String mealNut) {
        this.mealNut = mealNut;
    }

    public String getMealDate() {
        return mealDate;
    }

    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }
}

