import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Header from "./components/Header";
import Login from "./components/Login";
import register from "./components/register";
import './App.css';
import Home from './components/Home';
import Cookies from 'js-cookie'

function App() {
  const [isLoggedIn, setIsLoggedIn] = React.useState(false);
  const [loadingUser, setLoadingUser] = React.useState(true);

  React.useEffect(() => {
    fetch("/user")
      .then(response => {
         if (response.status !== 401) {
           setLoadingUser(false);
           setIsLoggedIn(true);
         } else {
           setLoadingUser(false);
         }
       })
  }, []);

  if (loadingUser) {
    return <div>Loading...</div>
  }

  function onLogin() {
    setIsLoggedIn(true)
  }

  function onLogut() {
    setIsLoggedIn(false);
  }

  if (!isLoggedIn) {
    return (
      <div className="App">
        <Login onLogin={onLogin}/>
      </div>
    )
  }

  return (
    <BrowserRouter>
      <Header onLogout={onLogut}/>
      <div className="App">
        <Switch>
          <Route path='/home'
            render={() => (
              Cookies.get('Bearer')?
              <Home/> : alert("Please log in!"))}/>
          <Route path='/Logout' exact component={Login}/>
          <Route path='/Login' exact component={Login}/>
          <Route path='/register' exact component={register}/>
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
