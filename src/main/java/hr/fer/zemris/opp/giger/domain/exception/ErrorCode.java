package hr.fer.zemris.opp.giger.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    EXCEPTION(40001, "Exception occurred", BAD_REQUEST),
    NO_SUCH_USER(40002, "No such user exists", BAD_REQUEST),
    NO_ROLE_DATA_PRESENT(40003, "Role data is missing.", BAD_REQUEST),
    ORGANIZER_ALREADY_EXISTS(40004, "Manager name is already taken.", BAD_REQUEST),
    BAND_NAME_NOT_UNIQUE(40005, "Band name is already taken.", BAD_REQUEST),
    MUSICIAN_ALREADY_EXISTS(40006, "Musician already exists.", BAD_REQUEST),
    NO_SUCH_BAND(40007, "There is no such band.", BAD_REQUEST),
    ONLY_LEADER_CAN_INVITE(40008, "Only leader can invite to band.", BAD_REQUEST),
    NO_SUCH_MUSICIAN(40009, "There is no such musician.", BAD_REQUEST),
    NOT_INVITED(40010, "Musician is not invited to this band.", BAD_REQUEST),
    NOT_A_MEMBER_OF_BAND(40011, "Musician is not a member of this band.", BAD_REQUEST),
    ONLY_LEADER_CAN_KICK(40012, "Only leader can kick members out of a band.", BAD_REQUEST),
    ONLY_LEADER_CAN_CHANGE_LEADERSHIP(40013, "Only leader can change the leadership of a band.", BAD_REQUEST),
    ONLY_MEMBER_CAN_BECOME_LEADER(40014, "Only member of a band can become a band leader.", BAD_REQUEST),
    ONLY_MEMBERS_CAN_SEE_INVITATIONS(40015, "Only band members can see invitations.", BAD_REQUEST),
    ONLY_MEMBERS_CAN_EDIT_BAND(40016, "Only band members can edit band.", BAD_REQUEST),
    NO_SUCH_CONVERSATION(40017, "There is no such conversation.", BAD_REQUEST),
    NOT_IN_A_CONVERSATION(40018, "User is not a participant in this conversation.", BAD_REQUEST),
    INVALID_PASSWORD(40019, "Invalid password.", BAD_REQUEST),
    NO_SUCH_GIG(40020, "There is no such gig.", BAD_REQUEST),
    NO_SUCH_ORGANIZER(40021, "There is no such organizer.", BAD_REQUEST),
    NO_SUCH_POST(40022, "There is no such post.", BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus status;
}
