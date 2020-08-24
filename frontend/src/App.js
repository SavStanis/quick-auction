import React from 'react';
import {Switch, Route} from 'react-router-dom';

import './App.css';

import MainPage from './pages/MainPage/MainPage'
import PageHeader from './components/PageHeader/PageHeader';
import RegistrationPage from "./pages/RegistrationPage/RegistrationPage";
import LoginPage from "./pages/LoginPage/LoginPage";

function App() {
  return (
    <div className="App">
        <Switch>
            <Route exact path={"/"} component={MainPage}/>
            <Route exact path={"/registration"} component={RegistrationPage}/>
            <Route exact path={"/login"} component={LoginPage}/>
        </Switch>
    </div>
  );
}

export default App;
