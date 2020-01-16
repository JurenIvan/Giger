import React, { useState, useEffect } from "react";
import InboxPeople from "./InboxPeople";
import Mesgs from "./Mesgs";
import "./chat.css";
import fetcingFactory from "../../Utils/external";
import { endpoints } from "../../Utils/Types";

function ChatContainer(props) {
  const [conversationList, setConversationList] = useState([]);
  const [conversationCount, setConversationCount] = useState(0);
  useEffect(() => {
    // dohvat podataka
    const getData = () => {
      fetcingFactory(endpoints.GET_USER_CONVERSATIONS, null)
        .then(response => response.json())
        .then(response => {
          console.log("converssssss", response); //postaviti response u convers
          //this.setState({inValidRegister: true})

          response.forEach(convr => {
            convr.messages.sort(function(a, b) {
              // var keyA = new Date(a.time),
              //   keyB = new Date(b.time);
              var keyA = a.sentTime,
                keyB = b.sentTime;
              // Compare the 2 dates
              if (keyA < keyB) return -1;
              if (keyA > keyB) return 1;
              return 0;
            });
            if (convr.messages.length > 0) {
              convr.lastMsg = convr.messages[convr.messages.length - 1];
            }
          });

          response.sort(function(a, b) {
            var keyA = a.lastMsg,
              keyB = b.lastMsg;
            // Compare the 2 dates

            if (!keyA && !keyB) return 0;
            if (!keyA && keyB) return -1;
            if (keyA && !keyB) return 1;

            if (keyA.sentTime > keyB.sentTime) return -1;
            if (keyA.sentTime < keyB.sentTime) return 1;
            return 0;
          });

          setConversationList(response);
        });
    };
    getData();
    const intervalId = setInterval(getData, 15000);

    return () => clearInterval(intervalId);
  }, [conversationCount]);

  const [selectedConversationIdx, setSelectedConversationIdx] = useState();

  const mesgs =
    selectedConversationIdx !== undefined ? (
      <Mesgs conversation={conversationList[selectedConversationIdx]} />
    ) : null;

  return (
    <div className="container">
      <h3 className=" text-center">Messaging</h3>
      <div className="messaging">
        <div className="inbox_msg">
          <InboxPeople
            conversationList={conversationList}
            conversationCount={conversationCount}
            setConversationCount={setConversationCount}
            setSelectedConversationIdx={setSelectedConversationIdx}
          />
          {mesgs}
        </div>
      </div>
    </div>
  );
}

export default ChatContainer;
