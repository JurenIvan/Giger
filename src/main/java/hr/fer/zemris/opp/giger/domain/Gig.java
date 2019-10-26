package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.GigType;

import java.time.LocalDateTime;

public class Gig {

    private User organizer;

    private LocalDateTime dateTime;
    private Location location;
    private String description;
    private String expectedDuration;
    private Integer proposedPrice;
    private GigType gigType;

    private boolean finalDealAchieved;
    private Band finalBand;

    private boolean privateGig;
}
