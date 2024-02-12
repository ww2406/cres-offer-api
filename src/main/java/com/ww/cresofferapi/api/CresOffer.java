package com.ww.cresofferapi.api;

import com.ww.cresofferapi.domain.data.RespondentService;
import com.ww.cresofferapi.domain.models.Respondent;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Supplier;

@Component
public class CresOffer implements Supplier<Optional<Respondent>> {
    private final RespondentService respondentService;

    public CresOffer(RespondentService respondentService) {
        this.respondentService = respondentService;
    }

    @Override
    public Optional<Respondent> get()
    {
        return respondentService.getNextRespondentAndUpdateAvailability();
    }
}
