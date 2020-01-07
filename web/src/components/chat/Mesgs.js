import React, { useEffect, useState } from "react";
import Message from "./Message.js";
import Cookies from "js-cookie";

function Mesgs(props) {
  const { messages } = props;
  const userId = Cookies.get("userId");
  messages.forEach(message => {
    if (userId !== message.sender.id) {
      message.is_sent = true;
    }
  });

  const [newMsg, setNewMsg] = useState("");
  const handleClick = e => {
    // alert(newMsg);
    // fetcingFactory(endpoints.GET_USER_CONVERSATIONS, null).then(response => {
    //   if (response.status === 200) {
    //     console.log(response);
    //   } else {
    //     // this.setState({inValidRegister: true})
    //   }
    // });
  };

  return (
    <div className="mesgs">
      <div className="msg_history">
        {messages.map((x, i) => (
          <Message key={i} data={x} />
        ))}
      </div>
      <div className="type_msg">
        <div className="input_msg_write">
          <input
            type="text"
            className="write_msg"
            placeholder="Write a message"
            value={newMsg}
            onChange={e => setNewMsg(e.target.value)}
          />
          <button className="msg_send_btn" type="button" onClick={handleClick}>
            <i className="fa fa-paper-plane-o" aria-hidden="true"></i>
          </button>
        </div>
      </div>
    </div>
  );
}

export default Mesgs;
