package com.ww.cresofferapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "RESPONDENTS", schema = "CRES")
public class RespondentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int respondentId;
    private boolean isAvailable;

    @OneToMany(mappedBy = "respondentId")
    private List<CresOfferDataEntity> cresOffers;
}
