package com.unicommerce.utilities.model.tracelog;

import lombok.Data;

@Data
public class TraceLogQueueMessage {
    public String level;
    public String threadName;
    public String messageClassName;
    public String message;
    public String logRoutingKey;
    public String requestTimestamp;

    @Override public String toString() {
        return requestTimestamp + " " + level + " [" + threadName + "] [" + messageClassName + "] [" + "] - " + message;
    }
}
