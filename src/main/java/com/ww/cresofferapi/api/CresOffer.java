package com.ww.cresofferapi.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ww.cresofferapi.domain.data.RespondentService;
import org.springframework.cloud.function.adapter.gcp.FunctionInvoker;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.function.Supplier;

@Component
public class CresOffer implements Supplier<Message<String>> {
    private final RespondentService respondentService;
    private final ObjectMapper objectMapper;

    public CresOffer(RespondentService respondentService, ObjectMapper objectMapper) {
        this.respondentService = respondentService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Message<String> get()
    {
        var respondent = respondentService.getNextRespondentAndUpdateAvailability();
        if (respondent.isEmpty()) {
            return MessageBuilder
                    .withPayload("null")
                    .setHeader(FunctionInvoker.HTTP_STATUS_CODE, HttpURLConnection.HTTP_NOT_FOUND)
                    .build();
        } else {
            try {
                return MessageBuilder
                        .withPayload(objectMapper.writeValueAsString(respondent))
                        .build();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
