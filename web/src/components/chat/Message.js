import React from "react";

function Message(props) {
  const msgType = props.data.is_sent ? "outgoing_msg" : "incoming_msg";
  const msgType2 = props.data.is_sent ? "sent_msg" : "received_msg";

  return (
    <div className={msgType}>
      <div className={msgType2}>
        <p>{props.data.content}</p>
        <span className="time_date"> {props.data.sentTime}</span>
      </div>
    </div>
  );
}

export default Message;
