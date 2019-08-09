package com.bungii.ios.enums;

public enum Status {
	EN_ROUTE("EN ROUTE"),
	ARRIVED("ARRIVED"),
	LOADING_ITEM("LOADING ITEM"),
	DRIVING_TO_DROP_OFF("DRIVING TO DROP OFF"),
	UNLOADING_ITEM("UNLOADING ITEM");
    
    public final String name;
    Status(String status) {
        this.name = status;
    }
    
    public String toString() {
        return this.name;
     }
}