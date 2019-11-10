import React from 'react';
  import {Link} from "react-router-dom";
  import "./Header.css";

function Header(props) {


  return (
    <header className="Header">
      <Link to ='/home'>Home</Link>
      <Link to='/events'>Events</Link>
      <Link to='/bands'>Bands</Link>
      <Link to='/login'>Login</Link>
      <Link to='/register'>Register</Link>
      <Link to='/logut'>Logout</Link>
     </header>
  )
}

export default Header;
