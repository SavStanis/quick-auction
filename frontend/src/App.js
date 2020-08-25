import React from 'react';
import {Switch, Route} from 'react-router-dom';

import './App.css';

import MainPage from './pages/MainPage/MainPage'
import RegistrationPage from "./pages/RegistrationPage/RegistrationPage";
import LoginPage from "./pages/LoginPage/LoginPage";
import UserPage from "./pages/UserPage/UserPage";

function App() {
  return (
    <div className="App">
        <Switch>
            <Route exact path={"/"} component={MainPage}/>
            <Route exact path={"/registration"} component={RegistrationPage}/>
            <Route exact path={"/login"} component={LoginPage}/>
            <Route exact path={"/user"} component={UserPage}/>
        </Switch>
    </div>
  );
}

export default App;
