import React from 'react';
  import "./Header.css";
  import { Button} from "react-bootstrap";
  import Cookies from "js-cookie"

export default class Header extends React.Component {
  constructor(props) {
    super(props);

    
    this.state = {
      isLoggedIn: this.props.isLoggedIn
    };
    this.handleLogout = this.handleLogout.bind(this);
  }



  handleLogout() {
    this.setState({isLoggedIn: false}, () => Cookies.remove('Bearer'))
  };
    
  
  render() {
      return (
        <header className="Header">
          <Button href='/home'>Home</Button>
          <Button href='/events'>Events</Button>
          <Button href='/bands'>Bands</Button>
          <Button href='/login'>Log in</Button>
          {
          this.state.isLoggedIn?
                <Button className="header-btn" href='/login' onClick={this.handleLogout}>Log out</Button>
                :
                <Button href='/register'>Register</Button>
          }
        
        </header>
      )
    }
}

