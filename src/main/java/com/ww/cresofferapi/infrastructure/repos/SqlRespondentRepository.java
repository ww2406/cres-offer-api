package com.ww.cresofferapi.infrastructure.repos;

import com.ww.cresofferapi.domain.data.RespondentRepository;
import com.ww.cresofferapi.domain.models.Respondent;
import com.ww.cresofferapi.infrastructure.EntityMapper;
import com.ww.cresofferapi.infrastructure.entities.RespondentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class SqlRespondentRepository implements RespondentRepository {
    private final EntityManager entityManager;

    public SqlRespondentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<Respondent> getNextRespondent() {
        TypedQuery<RespondentEntity> query = entityManager.createQuery(
                "FROM RespondentEntity r INNER JOIN CresOfferDataEntity c ON r.respondentId = c.respondentId WHERE r.isAvailable=true ORDER BY r.respondentId LIMIT 1",
                RespondentEntity.class
        );
        var entity = query.getResultStream().findFirst().orElse(null);
        var respondent = EntityMapper.INSTANCE.respondent(entity);
        return Optional.ofNullable(respondent);
    }
}
