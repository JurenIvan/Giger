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
      /* fetcingFactory(endpoints.GET_USER_CONVERSATIONS, null)
        .then(response => response.json())
        .then(response => {
          console.log("convs", response);
          //setConversationList(response.data);
          //this.setState({inValidRegister: true})
        }); */

      const convers = [
        {
          bandId: {
            gigTypes: ["WEDDING"],
            id: 0,
            name: "string",
            pictureURl: "string"
          },
          messages: [
            {
              content: "bok",
              id: 0,
              sender: {
                id: 0,
                name: "Marko",
                pictureUrl: "string"
              },
              senderBand: {
                gigTypes: ["WEDDING"],
                id: 0,
                name: "string",
                pictureURl: "string"
              },
              sentTime: "2020-01-06T15:14:53.707Z"
            }
          ],
          participantsId: [
            {
              id: 0,
              name: "Marko",
              pictureUrl: "string"
            }
          ],
          pictureUrl: "string"
        },
        {
          bandId: {
            gigTypes: ["WEDDING"],
            id: 0,
            name: "string",
            pictureURl: "string"
          },
          messages: [
            {
              content: "bok",
              id: 0,
              sender: {
                id: 0,
                name: "Marko",
                pictureUrl: "string"
              },
              senderBand: {
                gigTypes: ["WEDDING"],
                id: 0,
                name: "string",
                pictureURl: "string"
              },
              sentTime: "2020-02-06T15:14:53.707Z"
            },
            {
              content: "BOK",
              id: 0,
              sender: {
                id: 0,
                name: "Borna",
                pictureUrl: "string"
              },
              senderBand: {
                gigTypes: ["WEDDING"],
                id: 0,
                name: "string",
                pictureURl: "string"
              },
              sentTime: "2020-03-06T15:14:53.707Z"
            }
          ],
          participantsId: [
            {
              id: 0,
              name: "Marko",
              pictureUrl: "string"
            },
            {
              id: 1,
              name: "Borna",
              pictureUrl: "string2"
            }
          ],
          pictureUrl: "string"
        }
      ];

      convers.forEach(convr => {
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

      convers.sort(function(a, b) {
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
      // console.log(11);

      setConversationList(convers);
    };
    getData();
    const intervalId = setInterval(getData, 5000);

    return () => clearInterval(intervalId);
  }, [conversationCount]);

  const [selectedConversationIdx, setSelectedConversationIdx] = useState();

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
          <Mesgs
            messages={
              selectedConversationIdx !== undefined
                ? conversationList[selectedConversationIdx].messages
                : []
            }
          />
        </div>
      </div>
    </div>
  );
}

export default ChatContainer;
