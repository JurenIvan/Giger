import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Header from "./components/Header";
import Login from "./components/Login";
import './App.css';
import Home from './components/Home';
import Cookies from 'js-cookie'
import ErrorComponent from './components/ErrorComponent';
import RegisterClass from './components/register';
import createBandForm from './components/createBandForm'
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

import ProfileClass from './components/Profile/Profile';
import {DisplayBands} from "./components/Display/DisplayBands";
import CreateGig from './components/CreateGig'
import InviteToGig from './components/InviteToGig'
import ChangeProfileType from './components/Profile/ChangeProfileType';



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
          <Route path='/CreateGig'
          render={() => (
            Cookies.get('Bearer')?
            <CreateGig/> : 
                alert("Please log in!")
          )}/>
          <Route path='/InviteToGig'
          render={() => (
            Cookies.get('Bearer')?
            <InviteToGig/> : 
                alert("Please log in!")
          )}/>
          <Route path='/Logout' exact component={Login}/>
          <Route path='/Login' exact component={Login}/>
          <Route path='/register' exact component={RegisterClass}/>
          <Route path='/error' exact component={ErrorComponent} />
          <Route path='/create_band' exact component = {createBandForm} />
          <Route path='/profile' exact component = {ProfileClass} />
          <Route path='/displaybands' exact component = {DisplayBands} />
          <Route path='/CreateGig' exact component={CreateGig} />
          <Route path='/profile' exact component = {ProfileClass} />
          <Route path='/profile/change_type' exact component = {ChangeProfileType}/>
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
