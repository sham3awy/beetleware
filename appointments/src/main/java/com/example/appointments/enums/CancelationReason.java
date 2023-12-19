package com.example.appointments.enums;

public enum CancelationReason {
    No_Show("No show"),
    Patient_Request("based on patient request"),
    Physician_Apology("Physician Apology");

    private String code;

    private CancelationReason(String code) {
	this.code = code;
    }

    public String getCode() {
	return code;
    }
}
