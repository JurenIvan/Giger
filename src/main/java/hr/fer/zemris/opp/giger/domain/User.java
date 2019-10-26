package hr.fer.zemris.opp.giger.domain;

import java.util.List;

public class User {

    private String email;
    private String phoneNumber;
    private String passwordHash;

    private Musician musician;
    private Organizer organizer;

    private List<Conversation> conversations;
    private List<Review> reviews;
}
