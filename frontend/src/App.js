import { BrowserRouter, Route, Switch } from "react-router-dom";
import Home from "./components/Home";
import LogIn from "./components/LogIn";
import Navbar from "./components/Navbar";
import Register from "./components/Register";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar/>
        <Switch>
            <Route exact path ='/' component={Home} />
            <Route exact path ='/login' component={LogIn} />
            <Route exact path ='/register' component={Register} />
          </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
