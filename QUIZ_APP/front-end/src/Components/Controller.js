import React from 'react';
import Navbar from './Navbar';
import Main from './Main';
import {UpdateUserContext,UserContext} from '../Contexts/user';
export default function  Controller() { 
   
     const [loggedInUser, setLoggedInUser] = React.useState(null);
  
    return(
        <UpdateUserContext.Provider value={setLoggedInUser}>
        <UserContext.Provider value={loggedInUser}>
          {loggedInUser ? <Navbar /> : <Main />}
        </UserContext.Provider>
      </UpdateUserContext.Provider>
    )
  }