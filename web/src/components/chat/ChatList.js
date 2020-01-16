import React from "react";

function ChatList(props) {
  return (
    <div
      className="chat_list active_chat"
      onClick={e => props.setSelectedConversationIdx(props.idx)}
    >
      <div className="chat_people">
        <div className="chat_img">
          {" "}
          <img
            src="https://ptetutorials.com/images/user-profile.png"
            alt="sunil"
          />{" "}
        </div>
        <div className="chat_ib">
          <h5>
            {props.data.title
              ? props.data.title
              : props.data.participantsId.map(x => x.name).join(", ")}{" "}
            <span className="chat_date">
              {props.data.lastMsg
                ? Date(Date.parse(props.data.lastMsg.sentTime))
                : ""}
            </span>
          </h5>
          <p>{props.data.lastMsg ? props.data.lastMsg.content : ""}</p>
        </div>
      </div>
    </div>
  );
}

export default ChatList;
