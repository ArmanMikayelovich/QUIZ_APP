import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";
import Register from './Register';
import Login from './Login';

export default function Main()
{ 
    return(
        <Router>
        <Switch>
          <Route exact path='/'>
            <Login></Login>
            
          </Route>
          <Route exact path='/register'>
            <Register></Register>
          </Route>
        </Switch>
     </Router>
  )

}