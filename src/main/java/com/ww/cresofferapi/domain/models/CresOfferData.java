package com.ww.cresofferapi.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class CresOfferData implements Serializable {
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
