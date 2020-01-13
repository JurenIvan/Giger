import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Header from "./components/Header";
import Login from "./components/Login";
import './App.css';
import Home from './components/Home';
import Cookies from 'js-cookie'
import ErrorComponent from './components/ErrorComponent';
import RegisterClass from './components/register';
import CreateBandForm from './components/createBandForm'

import ProfileClass from './components/Profile/Profile';
import {DisplayBands} from "./components/Display/DisplayBands";
import {DisplayGigs} from "./components/Display/DisplayGigs";
import CreateGig from './components/CreateGig'
import InviteToGig from './components/InviteToGig'
import AcceptGigInvite from './components/AcceptGigInvite'
import ChangeProfileType from './components/Profile/ChangeProfileType';
import BandCreate from './components/Band/BandCreate';
import BandView from './components/Band/BandView';
import InviteToBand from './components/Band/InviteToBand'
import AcceptBandInvite from './components/Band/AcceptBandInvite'
import MusicianProfile from "./components/Profile/MusicianProfile"
import ModalClass from "./components/BasicComponents/Modal"


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
              <ModalClass/>
            )}/>
          <Route path='/AcceptGigInvite'
            render={() => (
              isLoggedIn?
                <AcceptGigInvite/> : <ModalClass/>
          )}/>
          <Route path='/CreateGig'
          render={() => (
            isLoggedIn?
              <CreateGig/> : <ModalClass/>
          )}/>
          <Route path='/InviteToGig'
          render={() => (
            isLoggedIn?
              <InviteToGig/> : <ModalClass/>
          )}/>
          <Route path='/Logout' exact component={Login}/>
          <Route path='/Login' exact component={Login}/>
          <Route path='/register' exact component={RegisterClass}/>
          <Route path='/error' exact component={ErrorComponent} />
          <Route path='/create_band'  render={() => (
            isLoggedIn? <CreateBandForm/> : <ModalClass/>
          )}/>
          <Route path='/profile' render = { ()=> (
            isLoggedIn? 
              <ProfileClass id = {Cookies.get("userId")}/> :
              <ModalClass/>
          )} />
          <Route path='/displaybands' render = {() => (
            isLoggedIn?
            <DisplayBands/> : <ModalClass/>
          )}/>
          <Route path='/CreateGig' exact component={CreateGig} />
          <Route path='/profile_change_type' render = {() => (
            isLoggedIn?
            <ChangeProfileType/> : <ModalClass/>
          )}/>
          <Route path='/createBand' render = {() => (
            isLoggedIn?
            <BandCreate/> : <ModalClass/>
          )}/>
          <Route path ='/viewBand' render = {() => (
            isLoggedIn?
            <BandView/> : <ModalClass/>
          )} />
          <Route path ='/InviteToBand' render = {() => (
            isLoggedIn?
            <InviteToBand/> : <ModalClass/>
          )}  />
          <Route path ='/AcceptBandInvite' render = {() => (
            isLoggedIn?
            <AcceptBandInvite /> : <ModalClass/>
          )} />
          <Route path='/displayGigs' exact component = {DisplayGigs}/>
          <Route path='/profile_musician_view' render = { () => (
            isLoggedIn?
            <MusicianProfile edit = {false}/> :
            <ModalClass />
          )} />
          <Route path='/profile_musician_edit' render = { () => (
            isLoggedIn?
            <MusicianProfile edit = {true}/> :
            <ModalClass/>
          )} />
        </Switch>
      </div>
      


    </BrowserRouter>
   
  );
}

export default App;
