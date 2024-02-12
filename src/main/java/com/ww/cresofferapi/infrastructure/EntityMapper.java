package com.ww.cresofferapi.infrastructure;

import com.ww.cresofferapi.domain.models.CresOfferData;
import com.ww.cresofferapi.domain.models.Respondent;
import com.ww.cresofferapi.infrastructure.entities.CresOfferDataEntity;
import com.ww.cresofferapi.infrastructure.entities.RespondentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper( EntityMapper.class );

    CresOfferData cresOfferData(CresOfferDataEntity cresOfferDataEntity);
    Respondent respondent(RespondentEntity respondentEntity);
}
