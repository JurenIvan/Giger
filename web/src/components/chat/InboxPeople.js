import React, { useEffect, useState } from "react";
import ChatList from "./ChatList";
import Select from "react-select";
import fetcingFactory from "../../Utils/external";
import { endpoints } from "../../Utils/Types";

function InboxPeople(props) {
  const {
    conversationList,
    conversationCount,
    setConversationCount,
    setSelectedConversationIdx
  } = props;

  const [userList, setUserList] = useState([
    { value: 1, label: "u1" },
    { value: 2, label: "u2" },
    { value: 3, label: "u3" },
    { value: 4, label: "user4" },
    { value: 5, label: "user5" },
    { value: 6, label: "user6" }
  ]);
  useEffect(() => {
    let params = {
      //paramsi u backu nemogu biti blanck
      //TODO back izmjena ovoga
      band: "a",
      name: "a"
    };

    fetcingFactory(endpoints.GET_ALL_USERS, JSON.stringify(params)).then(
      response => {
        if (response.status === 200) {
          console.log("users", response.json().PromiseValue);
          // setUserList([
          //   { value: 1, label: "u1" },
          //   { value: 2, label: "u2" }
          // ]);
        } else {
          console.log("response", response);
          console.log(JSON.stringify(params));
          // this.setState({inValidRegister: true})
        }
      }
    );
  }, []);

  const [conversationTitle, setConversationTitle] = useState("");
  const [selectedUserIds, setSelectedUserIds] = useState([]);
  const handleChange = e => {
    if (!e) {
      setUserList([]);
      return;
    }

    setSelectedUserIds(e.map(x => x.value));
  };

  const handleSubmit = e => {
    e.preventDefault();
    if (!(selectedUserIds.length > 0)) {
      alert(
        "morate izabrati barem jednu osobu da biste mogli stvoriti razgovor"
      );
      return;
    }

    console.log("submit");
    setConversationCount(conversationCount + 1);
  };

  return (
    <div className="inbox_people">
      <div className="heading_search">
        <h2>Create conversation</h2>
        <form onSubmit={handleSubmit}>
          Title:
          <input
            value={conversationTitle}
            onChange={e => setConversationTitle(e.target.value)}
          ></input>
          <br />
          Participants:{" "}
          <Select options={userList} isMulti={true} onChange={handleChange} />
          <button type="submit" className="new-conversation">
            Create
          </button>
        </form>
      </div>
      <div className="inbox_chat">
        {conversationList.map((x, i) => (
          <ChatList
            key={i}
            data={x}
            setSelectedConversationIdx={setSelectedConversationIdx}
            idx={i}
          />
        ))}
      </div>
    </div>
  );
}

export default InboxPeople;
