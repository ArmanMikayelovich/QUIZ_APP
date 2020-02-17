import React from 'react';
import '../Css/styles.css';
import icon from '../Image/quiz.png';
import TextField from '@material-ui/core/TextField';
import Button from "@material-ui/core/Button";
import Login from './Login';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";
function Register_comp()
{ 
    const HandleRegister = async ()=>
    {
       
        window.location.href = "/";
       
    }
    const HandleBack =()=>
    {
      window.location.href='/'
    }
    return (
        <div className="register-main">
             <img width='50px' src={icon}></img>
            <h2 style={{color:"#1976d2",textDecoration:"underline"}}>Register</h2>
            <TextField  style={{backgroundColor:'whitesmoke'}} label="Name" variant="outlined"></TextField>
            <br/>
            <br/>
            <TextField  style={{backgroundColor:'whitesmoke'}}  label="Surname" variant="outlined"></TextField>
            <br/>
            <br/>
            <TextField  style={{backgroundColor:'whitesmoke'}}  required label="email" variant="outlined"></TextField>
            <br/>
            <br/>
            <TextField  style={{backgroundColor:'whitesmoke'}}  required label="password" type='password' variant="outlined"></TextField>
         <br></br>
         <br></br>
         <Button style={{marginRight:'10px'}} onClick={HandleBack} color='primary' variant='contained'>Go Back</Button>
           <Button onClick={HandleRegister} color='primary' variant='contained'>Register</Button>
        </div>
    )

}
function Register()
{ return(
    <Router>
        
        <div>
        <Switch>
          <Route exact path="/">
            <Login />
          </Route>
          <Route exact  path="/register">
            <Register_comp />
          </Route>
        </Switch>
        </div>
        
          </Router>

);

}
export default Register;