import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Header from "./components/Header";
import Login from "./components/Login";
import './App.css';
import Home from './components/Home';
import Cookies from 'js-cookie'
import ErrorComponent from './components/ErrorComponent';
import RegisterClass from './components/register';
import CreateGig from './components/CreateGig'
import InviteToGig from './components/InviteToGig'
import AcceptGigInvite from './components/AcceptGigInvite'



function App() {

  return (
    <BrowserRouter>
      <Header/>
      <div className="App">
        <Switch>
          <Route path='/home'
            render={() => (
              Cookies.get('Bearer')?
              <Home/> : 
                  alert("Please log in!")
            )}/>
          <Route path='/AcceptGigInvite'
          render={() => (
            Cookies.get('Bearer')?
            <AcceptGigInvite/> : 
                alert("Please log in!")
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
          <Route path='/CreateGig' exact component={CreateGig} />
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
