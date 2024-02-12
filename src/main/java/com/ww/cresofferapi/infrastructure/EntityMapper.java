package com.ww.cresofferapi.infrastructure;

import com.ww.cresofferapi.domain.models.CresOfferData;
import com.ww.cresofferapi.domain.models.Respondent;
import com.ww.cresofferapi.infrastructure.entities.CresOfferDataEntity;
import com.ww.cresofferapi.infrastructure.entities.RespondentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper( EntityMapper.class );

    CresOfferData cresOfferData(CresOfferDataEntity cresOfferDataEntity);
    Respondent respondent(RespondentEntity respondentEntity);
}
