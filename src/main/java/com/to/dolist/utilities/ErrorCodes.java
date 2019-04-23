package com.to.dolist.utilities;

public enum ErrorCodes {

    TITLE_FILED_EMPTY("Title filed is empty"),
    MESSAGE_ERROR ("message is empty"),
    ESTIMATION_ERROR ("estimation is empty"),
    STATUS_ERROR ("status is empty"),
    START_DATE_ERROR ("start should be present date"),
    DUE_DATE_ERROR("Due should be present date or above"),
    ID_ERROR("id Filed is empty"),
    INTERNAL_SERVER_ERROR("Server Error"),
    DUPLICATE_ENTRY("Entry is duplicate"),
    SEARCH_NOT_FOUND("no content found"),
    NOT_FOUND("no data found");

    private final String errorMessage;

    private ErrorCodes(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMeggage() {
        return errorMessage;
    }
}
