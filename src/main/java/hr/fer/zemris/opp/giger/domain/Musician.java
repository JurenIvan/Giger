package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.Intrument;

import java.util.List;

public class Musician extends User {

    private String bio;
    private List<Intrument> instruments;

    private List<Band> bands;
    private List<Gig> pastExperience;

    private Integer totalScore;
    private Integer reviewsCount;
}
