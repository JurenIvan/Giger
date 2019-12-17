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
    NO_ROLE_DATA_PRESENT(40003, "NO_ROLE_DATA_PRESENT", BAD_REQUEST),
    ORGANIZER_ALREADY_EXISTS(40004, "ORGANIZER_ALREADY_EXISTS", BAD_REQUEST),
    BAND_NAME_NOT_UNIQUE(40005, "BAND_NAME_NOT_UNIQUE", BAD_REQUEST),
    MUSICIAN_ALREADY_EXISTS(40006, "MUSICIAN_ALREADY_EXISTS", BAD_REQUEST),
    NO_SUCH_BAND(40007, "NO_SUCH_BAND", BAD_REQUEST),
    ONLY_LEADER_CAN_INVITE(40008, "ONLY_LEADER_CAN_INVITE", BAD_REQUEST),
    NO_SUCH_MUSICIAN(40009, "NO_SUCH_MUSICIAN", BAD_REQUEST),
    NOT_INVITED(40010, "NOT_INVITED", BAD_REQUEST),
    NOT_A_MEMBER_OF_BAND(40011, "NOT_A_MEMBER_OF_BAND", BAD_REQUEST),
    ONLY_LEADER_CAN_KICK(40012, "ONLY_LEADER_CAN_KICK", BAD_REQUEST),
    ONLY_LEADER_CAN_CHANGE_LEADERSHIP(40013, "ONLY_LEADER_CAN_CHANGE_LEADERSHIP", BAD_REQUEST),
    ONLY_MEMBER_CAN_BECOME_LEADER(40014, "ONLY_MEMBER_CAN_BECOME_LEADER", BAD_REQUEST),
    ONLY_MEMBERS_CAN_SEE_INVITATIONS(40015, "ONLY_MEMBERS_CAN_SEE_INVITATIONS", BAD_REQUEST),
    ONLY_MEMBERS_CAN_EDIT_BAND(40016, "ONLY_MEMBERS_CAN_EDIT_BAND", BAD_REQUEST),
    NO_SUCH_CONVERSATION(40017, "NO_SUCH_CONVERSATION", BAD_REQUEST),
    NOT_IN_A_CONVERSATION(40018, "NOT_IN_A_CONVERSATION", BAD_REQUEST),
    INVALID_PASSWORD(40019, "INVALID_PASSWORD", BAD_REQUEST),
    NO_SUCH_GIG(40020, "NO_SUCH_GIG", BAD_REQUEST),
    NO_SUCH_ORGANIZER(40021, "NO_SUCH_ORGANIZER", BAD_REQUEST),
    NO_SUCH_POST(40022, "NO_SUCH_POST", BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus status;
}
