package com.ww.cresofferapi.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "CRES_OFFER_DATA", schema = "CRES")
public class CresOfferDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int offerRecordId;
    private int respondentId;
    private BigDecimal ssoPrice;
    private BigDecimal cresPrice;
    private String cresRateType;
    private int cresContractLength;
    private BigDecimal cresEtf;
    private BigDecimal cresMonthlyFee;
    private BigDecimal cresEnrollmentFee;
    private String cresOneTimeIncentives;
    private String cresOngoingIncentives;
    private double cresRenewablePct;
}
