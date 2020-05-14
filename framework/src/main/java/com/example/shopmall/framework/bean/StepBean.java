package com.example.shopmall.framework.bean;

public class StepBean {
    private int currentStep;//当天步数
    private int systemStep; //系统返回的步数
    private String date; //当天的时间

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public int getSystemStep() {
        return systemStep;
    }

    public void setSystemStep(int systemStep) {
        this.systemStep = systemStep;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
