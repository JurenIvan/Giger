import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import AcceptBandInvite from "./components/Band/AcceptBandInvite";
import AcceptGigInvite from "./components/Gigs/AcceptGigInvite";
import BandCreate from "./components/Band/BandCreate";
import BandView from "./components/Band/BandView";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Button from "react-bootstrap/Button";
import ChangeProfileType from "./components/Profile/ChangeProfileType";
import ChatContainer from "./components/chat/Container";
import Cookies from "js-cookie";
import CreateBandForm from "./components/Band/createBandForm";
import CreateGig from "./components/Gigs/CreateGig";
import { DisplayBands } from "./components/Display/DisplayBands";
import { DisplayGigs } from "./components/Display/DisplayGigs";
import DisplayMusicianCalendar from "./components/Profile/DIsplayMusicianCalendar";
import EditGig from "./components/Gigs/EditGig";
import ErrorComponent from "./components/ErrorComponent";
import Header from "./components/Header";
import Home from "./components/Home/Home";
import InviteToBand from "./components/Band/InviteToBand";
import InviteToGig from "./components/Gigs/InviteToGig";
import Login from "./components/Login/Login";
import Modal from "react-bootstrap/Modal";
import ModalClass from "./components/BasicComponents/Modal";
import MusicianProfile from "./components/Profile/MusicianProfile";
import ProfileClass from "./components/Profile/Profile";
import React from "react";
import RegisterClass from "./components/Register/register";
import WelcomePage from "./components/WelcomePage/WelcomePage";

function App() {
  const isLoggedIn = Cookies.get("Bearer") ? true : false;
  return (
    <BrowserRouter>
      <Header isLoggedIn={isLoggedIn} />
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
          <Route path= '/displayMusicianCalendar' render = { () => (
            isLoggedIn?
            <DisplayMusicianCalendar id = {Cookies.get("userId")} /> : 
            <ModalClass />
          )} />
          <Route path ='/EditGig' render = {() => (
            isLoggedIn?
            <EditGig/> : <ModalClass/>
          )} />
          <Route path='/WelcomePage' exact component = {WelcomePage}/>
          <Route path='/' exact component = {WelcomePage}/>
          <Route
            path="/chat"
            render={() => (isLoggedIn ? <ChatContainer /> : <ModalClass />)}
          />
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
