import React from 'react';
import { Router, Route, Switch, Redirect } from 'react-router-dom';
import * as TodoRepository from '../../data/repos/TodoRepository';
import {PrivateRoute} from "../PrivateRoute";
import {LoginPage} from "../../LoginPage";
import { createBrowserHistory } from 'history';

export const history = createBrowserHistory();


class App extends React.Component {
    render() {
        TodoRepository.getById(1).then((response) => {
            console.log(response.status)
            console.log(response.data);
        }).catch(e => {
                console.log(e)
            }
        )
        TodoRepository.getAll().then((response) => {
            console.log(response.data);
        })
        return (
            <div className="jumbotron">
                <div className="container">
                    <div className="col-sm-8 col-sm-offset-2">
                        <Router history={history}>
                            <Switch>
                                {/*<PrivateRoute exact path="/" component={HomePage} />*/}
                                <Route path="/login" component={LoginPage} />
                                {/*<Route path="/register" component={RegisterPage} />*/}
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
