import React from 'react';
import { Router, Route, Switch, Redirect } from 'react-router-dom';
import {ProtectedRoute} from "../ProtectedRoute";
import {LoginPage} from "../../LoginPage";
import {RegisterPage} from "../../RegisterPage";
import HomePage from "../../HomePage/HomePage";
import {history} from "../history"
import {AuthRoute} from "../AuthRoute";

class App extends React.Component {
    render() {
        return (
            <div className="jumbotron">
                <div className="container">
                    <div className="col-sm-8 col-sm-offset-2">
                        <Router history={history}>
                            <Switch>
                                <ProtectedRoute exact path="/" component={HomePage} />
                                <AuthRoute path="/login" component={LoginPage} />
                                <AuthRoute path="/register" component={RegisterPage} />
                                <Redirect from="*" to="/" />
                            </Switch>
                        </Router>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;
