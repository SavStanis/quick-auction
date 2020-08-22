import React from 'react';
import {Switch, Route} from 'react-router-dom';

import './App.css';

import MainPage from './pages/Main/MainPage'
import PageHeader from './components/PageHeader/PageHeader';
import RegistrationPage from "./pages/RegistrationPage/RegistrationPage";

function App() {
  return (
    <div className="App">
        <PageHeader/>
        <Switch>
            <Route exact path={"/"} component={MainPage}/>
            <Route exact path={"/registration"} component={RegistrationPage}/>
        </Switch>
    </div>
  );
}

export default App;
