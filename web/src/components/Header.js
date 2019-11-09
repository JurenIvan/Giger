import React from 'react';
  import {Link} from "react-router-dom";
  import "./Header.css";

function Header(props) {

  function logout() {
    fetch("/logout").then(() => {
      props.onLogout();
    });
  }

  return (
    <header className="Header">
      <Link to='/events'>Events</Link>
      <Link to='/bands'>Bands</Link>
      <Link to='/login'>Login</Link>
      <Link to='/register'>Register</Link>
      <button onClick={logout}>Logout</button>
    </header>
  )
}

export default Header;
