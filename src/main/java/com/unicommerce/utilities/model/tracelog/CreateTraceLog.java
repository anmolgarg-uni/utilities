package com.unicommerce.utilities.model.tracelog;

import lombok.Data;

@Data
public class CreateTraceLog{
    private boolean successful;
    private Object message;
    private Object errors;
    private Object warnings;
    private String logRedirectUrl;
}