import { BrowserRouter, Route, Switch } from "react-router-dom";
import Home from "./components/Home";
import LogIn from "./components/LogIn";
import Navbar from "./components/Navbar";
import Register from "./components/Register";
import SyncWithGoogleTasks from "./components/SyncWithGoogleTasks";
import SnackbarHandler from "./components/SnackbarHandler";
import React from "react";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
          <Navbar/>
          <div style={{marginTop:'3%'}}>
              <Switch>
                  <Route exact path ='/syncWithGoogleTasks' component={SyncWithGoogleTasks} />
                  <Route exact path ='/' component={Home} />
                  <Route exact path ='/login' component={LogIn} />
                  <Route exact path ='/register' component={Register} />
              </Switch>
          </div>
          <SnackbarHandler />
      </div>
    </BrowserRouter>
  );
}

export default App;
