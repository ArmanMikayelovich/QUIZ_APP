import React from 'react';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import Drawer from '@material-ui/core/Drawer';
import InputBase from '@material-ui/core/InputBase';
import SearchIcon from '@material-ui/icons/Search';
import Button from '@material-ui/core/Button';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import User from './User';
import '../Css/styles.css';
import { makeStyles, fade } from '@material-ui/core/styles';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import Search_List from './Search_List';


const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
  },
  search: {
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: fade(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: fade(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
   
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  },
  searchIcon: {
    width: theme.spacing(7),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 7),
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: 200,
      '&:focus': {
        width: 250,
      },
    },
  },
}));

function Navbar() {
  //states
  
  
  const classes = useStyles();
  const [state, Setstate] = React.useState(false);
  const [search, Setsearchstate] = React.useState("");

  const toggleDrawer = (e) => () => Setstate(e)
 
  return (
    <Router>
      
      <div >
        <AppBar position="static" >
          <Toolbar>
            <div className="menu">
              <IconButton style={{ padding: "0 10px 0 0" }} onClick={toggleDrawer(true)} ge="start" color="inherit" aria-label="menu">

                <MenuIcon />

              </IconButton>
            <div className="spacerphone"></div>
              <div className={classes.search}>
                <div className={classes.searchIcon}>
                  <SearchIcon />
                </div>
                <InputBase
               
                onChange={(e) => Setsearchstate(e.target.value)}
                  placeholder="Search…"
                  classes={{
                    root: classes.inputRoot,
                    input: classes.inputInput,
                  }}
                  inputProps={{ 'aria-label': 'search' }}
                />
              </div>
              <Link  style={{ color: 'white', textDecoration: 'none' }}  to={`/search/${search}`}>    <Button  color="inherit" >
                Search
                </Button> </Link>

              <Drawer open={state} onClose={toggleDrawer(false)}>
                <div className="drawer">
                  <Link style={{ color: 'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
                    Main
                </Button></Link>


                  <Link style={{ color: 'white', textDecoration: 'none' }} to="/users">    <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
                    User
                </Button> </Link>


                  <Link style={{ color: 'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
                    Exams
                </Button></Link>


                  <Link style={{ color: 'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
                    Contact
                </Button></Link>


                  <Link style={{ color: 'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
                    Logout
                </Button></Link>
                </div>
              </Drawer>
            </div>
            <div className="navbar-menu">
              <Link style={{ color: 'white', textDecoration: 'none' }} to="/main">  <Button color="inherit" >
                Main
                </Button></Link>
              <Link style={{ color: 'white', textDecoration: 'none' }} to="/users">    <Button color="inherit" >
                User
                </Button> </Link>

              <Button color="inherit" >
                Exams
                </Button>

              <Button color="inherit"  >
                Contact
                    </Button>
                    <Button color="inherit"  >
                Logout
                    </Button>
                    <div className="spacer"></div>
              <div className={classes.search}>
                <div className={classes.searchIcon}>
                  <SearchIcon />
                </div>
                <InputBase
                  onKeyDown={()=>{console.log(this)}}
                  onChange={(e) => Setsearchstate(e.target.value)}
                  placeholder="Search…"
                  classes={{
                    root: classes.inputRoot,
                    input: classes.inputInput,
                  }}
                  inputProps={{ 'aria-label': 'search' }}
                />
              </div>
              
              <Link style={{ color: 'white', textDecoration: 'none' }} to={`/search/${search}`}>    <Button  color="inherit" >
                Search
                </Button> </Link>
            </div>
          </Toolbar>
        </AppBar>
       
        <Switch>

          <Route path="/users">
            <User />
          </Route>
          <Route   path="/main">
            Hello
            </Route>
            <Route  exact path= {`/search/${search}`}>
            <Search_List input={search} />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}
export default Navbar;