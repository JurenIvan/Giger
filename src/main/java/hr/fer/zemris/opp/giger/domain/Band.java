package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.GigType;

import java.time.LocalDate;
import java.util.List;

public class Band {

    private String name;
    private String bio;
    private LocalDate formedDate;

    private Musician leader;
    private List<Musician> members;

    private List<Gig> previousExperience;
    private List<Post> posts;

    private List<GigType> acceptableGigTypes;
}
