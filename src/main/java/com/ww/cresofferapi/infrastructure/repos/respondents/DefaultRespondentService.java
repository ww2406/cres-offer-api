package com.ww.cresofferapi.infrastructure.repos.respondents;

import com.ww.cresofferapi.domain.data.RespondentService;
import com.ww.cresofferapi.domain.models.Respondent;
import com.ww.cresofferapi.infrastructure.EntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DefaultRespondentService implements RespondentService {
    private final SqlRespondentRepository sqlRespondentRepository;
    private final EntityMapper entityMapper;

    DefaultRespondentService(SqlRespondentRepository sqlRespondentRepository, EntityMapper entityMapper) {
        this.sqlRespondentRepository = sqlRespondentRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<Respondent> getNextRespondentAndUpdateAvailability() {
        var optionalRespondent = sqlRespondentRepository.getNextRespondent();

        if (optionalRespondent.isEmpty()) {
            return Optional.empty();
        }

        var respondentEntity = optionalRespondent.get();
        var respondent = entityMapper.respondent(respondentEntity);

        respondentEntity.setAvailable(false);

        sqlRespondentRepository.save(respondentEntity);

        return Optional.of(respondent);
    }
}
