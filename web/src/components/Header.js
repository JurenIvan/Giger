import React from 'react';
import "../CSS/Header.css";
import {Button} from "react-bootstrap";
import Cookies from "js-cookie"


export default function Header(props) {
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
            
            {
                Cookies.get('Bearer') ?
                    [
                    <Button onClick={handleLogout}>Log out</Button>,
                    <Button href="/profile">Profile</Button>
                    ]
                    :
                    [<Button href='/login'>Log in</Button>,
                    <Button href='/register'>Register</Button>]
            }

        </header>
    )
}

