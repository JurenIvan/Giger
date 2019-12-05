import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Header from "./components/Header";
import Login from "./components/Login";
import './App.css';
import Home from './components/Home';
import Cookies from 'js-cookie'
import ErrorComponent from './components/ErrorComponent';
import RegisterClass from './components/register';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import ProfileClass from './components/Profile/Profile'



function App() {

  const isLoggedIn = Cookies.get('Bearer')? true : false;
  return (
    <BrowserRouter>
      <Header
      isLoggedIn={isLoggedIn}/>
      <div className="App">
        <Switch>
          <Route path='/home'
            render={() => (
              isLoggedIn?
              <Home/> : 
              <Modal show={true} animation={false}>
              <Modal.Footer>
                <p  style={{color:"red"}}> You are not logged in! </p>
                  <Button
                      variant="danger"
                      href="/login"
                  >
                      Go to login...
                  </Button>
              </Modal.Footer>
              </Modal>
            )}/>
          <Route path='/logout' exact component={Login}/>
          <Route path='/login' exact component={Login}/>
          <Route path='/register' exact component={RegisterClass}/>
          <Route path='/error' exact component={ErrorComponent} />
          <Route path='/profile' exact component = {ProfileClass} />
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
