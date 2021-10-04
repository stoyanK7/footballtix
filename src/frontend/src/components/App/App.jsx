import React from 'react';
import Register from '../Register/Register.jsx';
import Login from '../Login/Login.jsx';
import { Route, Switch } from 'react-router';
import './App.css';

const App = () => {
  return (
    <div className="App">
      <Switch>
        {/* <Route exact path="/">
          <Register />
        </Route> */}
        <Route path="/login">
          <Login />
        </Route>
        <Route path="/register">
          <Register />
        </Route>
        {/* <Route path="*">
          <NotFound />
        </Route> */}
      </Switch>
    </div>
  );
};

export default App;
