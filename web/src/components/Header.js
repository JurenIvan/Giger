import React from 'react';
  import {Link} from "react-router-dom";
  import "./Header.css";
  import {Dropdown, ButtonGroup, Button} from "react-bootstrap";

function Header(props) {


  return (
    <header className="Header">
      <Button href='/home'>Home</Button>
      <Button href='/events'>Events</Button>
      <Button href='/bands'>Bands</Button>
      <Dropdown as={ButtonGroup}>
        <Button href="/login" className={"dropdown"}>Login</Button>
        <Dropdown.Toggle split className={"dropdown"}>...</Dropdown.Toggle>
        <Dropdown.Menu>
          <Dropdown.Item href="#/action-1">Profile</Dropdown.Item>
          <Dropdown.Item href="#/action-2">Settings</Dropdown.Item>
          <Dropdown.Item href="/register">Register</Dropdown.Item>
        </Dropdown.Menu>
       </Dropdown>
     </header>
  )
}

export default Header;
