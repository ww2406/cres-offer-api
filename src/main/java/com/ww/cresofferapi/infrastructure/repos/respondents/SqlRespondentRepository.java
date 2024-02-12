package com.ww.cresofferapi.infrastructure.repos.respondents;

import com.ww.cresofferapi.infrastructure.entities.RespondentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlRespondentRepository extends CrudRepository<RespondentEntity, Integer> {
     @Query("FROM RespondentEntity r INNER JOIN CresOfferDataEntity c ON r.respondentId = c.respondentId WHERE r.isAvailable=true ORDER BY r.respondentId LIMIT 1")
     Optional<RespondentEntity> getNextRespondent();
}
