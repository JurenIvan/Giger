package hr.fer.zemris.opp.giger.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    EXCEPTION(40001, "Exception occurred", BAD_REQUEST),
    NO_SUCH_USER(40002, "", BAD_REQUEST),
    NO_ROLE_DATA_PRESENT(40002, "", BAD_REQUEST),
    ORGANIZER_ALREADY_EXISTS(40002, "", BAD_REQUEST),
    BAND_NAME_NOT_UNIQUE(40002, "", BAD_REQUEST),
    MUSICIAN_ALREADY_EXISTS(40002, "", BAD_REQUEST),
    NO_SUCH_BAND(40002, "", BAD_REQUEST),
    ONLY_LEADER_CAN_INVITE(40002, "", BAD_REQUEST),
    NO_SUCH_MUSICIAN(40002, "", BAD_REQUEST),
    NOT_INVITED(40002, "", BAD_REQUEST),
    NOT_A_MEMBER_OF_BAND(40002, "", BAD_REQUEST),
    ONLY_LEADER_CAN_KICK(40002, "", BAD_REQUEST),
    ONLY_LEADER_CAN_CHANGE_LEADERSHIP(40002, "", BAD_REQUEST),
    ONLY_MEMBER_CAN_BECOME_LEADER(40002, "", BAD_REQUEST),
    ONLY_MEMBERS_CAN_SEE_INVITATIONS(40002, "", BAD_REQUEST),
    ONLY_MEMBERS_CAN_EDIT_BAND(40002, "", BAD_REQUEST),
    NO_SUCH_CONVERSATION(40002, "", BAD_REQUEST),
    NOT_IN_A_CONVERSATION(40002, "", BAD_REQUEST),
    INVALID_PASSWORD(40002, "", BAD_REQUEST),
    NO_SUCH_GIG(40002, "", BAD_REQUEST),
    NO_SUCH_ORGANIZER(40002, "", BAD_REQUEST),
    NO_SUCH_POST(40002, "", BAD_REQUEST);


    private final int code;
    private final String message;
    private final HttpStatus status;
}
