package com.example.demo.model.enums;

public enum Status {
    AVAILABLE("available"),
    ON_GOING("onGoing"),
    COMPLETED("completed");

    String status;
    Status(String status){
        this.status = status;
    }

    // Getter & Setter
    public String getStatus(){
        return this.status;
    }
}
