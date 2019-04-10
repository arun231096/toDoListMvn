package com.to.dolist.utilities;

public enum ErrorCodes {

    TITLE_FILED_EMPTY("Title filed is empty"),
    MESSAGE_ERROR ("message is empty"),
    ESTIMATION_ERROR ("estimation is empty"),
    STATUS_ERROR ("status is empty"),
    START_DATE_ERROR ("start date is empty"),
    DUE_DATE_ERROR("Due Date is empty"),
    ID_ERROR("id Filed is empty"),
    INTERNAL_SERVER_ERROR("Server Error");

    private final String errorMessage;

    private ErrorCodes(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMeggage() {
        return errorMessage;
    }
}
