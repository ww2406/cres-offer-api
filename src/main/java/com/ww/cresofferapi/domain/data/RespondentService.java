package com.ww.cresofferapi.domain.data;

import com.ww.cresofferapi.domain.models.Respondent;

import java.util.Optional;

public interface RespondentService {
    Optional<Respondent> getNextRespondentAndUpdateAvailability();
}
