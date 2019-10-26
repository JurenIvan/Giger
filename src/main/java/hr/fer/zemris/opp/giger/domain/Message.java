package hr.fer.zemris.opp.giger.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Message {

    private User sender;
    private String content;
    private LocalDateTime sentTime;
    private List<User> seenList;

}
