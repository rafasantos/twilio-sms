package com.github.rafasantos.twiliosmssample.ui;

import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Data
public class MessagePojo {
    private String sid;
    private String status;
    private ZonedDateTime dateSent;
    private String to;
    private String from;
    private String body;
    private String direction;
    private String errorMessage;
    public boolean isInbound() {
        return "inbound".equals(this.getDirection());
    }
    public boolean isOutbound() {
        return "outbound".equals(this.getDirection());
    }
}
