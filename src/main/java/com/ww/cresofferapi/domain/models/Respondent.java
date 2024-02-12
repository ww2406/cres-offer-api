package com.ww.cresofferapi.domain.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Respondent {
    private int respondentId;
    private boolean isAvailable;

    private List<CresOfferData> cresOffers = new ArrayList<>();
}
