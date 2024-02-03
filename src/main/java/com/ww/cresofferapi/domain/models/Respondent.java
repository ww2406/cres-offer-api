package com.ww.cresofferapi.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Respondent {
    private int respondentId;
    private boolean isAvailable;

    private List<CresOfferData> cresOffers = new ArrayList<>();
}
