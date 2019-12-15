import React from 'react';
import "../CSS/Header.css";
import {Button} from "react-bootstrap";
import Cookies from "js-cookie"

<<<<<<< HEAD
function Header(props) {
    const handleLogout = function () {
        if (Cookies.get('Bearer')) {
            Cookies.remove('Bearer');
            alert("You have logged out!");
            window.location.href = '/login'
        } else {
            alert('You are not logged in!')
        }

    }
    return (
        <header className="Header">
            <Button href='/home'>Home</Button>
            {/*<Button href='/events'>Events</Button>*/}
            {/*<Button href='/bands'>Bands</Button>*/}
            <Button href='/login'>Log in</Button>
            {
                Cookies.get('Bearer') ? 
                   <div>
                    <Button onClick={handleLogout}>Log out</Button>
                    <Button href = '/create_band'> Create Band </Button>
                   </div>
                    :
                    <Button href='/register'>Register</Button>
            }

        </header>
    )
=======
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
>>>>>>> 9013508f9f34ed2d093da7ef96b14edfd2f5fa69
}

