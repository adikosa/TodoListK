import React from 'react';
import { Route, Redirect} from 'react-router-dom';
import {tokenRepository} from "../repository/token.repository";

export const AuthRoute = ({ component: Component, ...rest }) => (
    <Route {...rest} render={props => (
        tokenRepository.isTokenValid()
            ? <Redirect to={{
                pathname: '/',
                state: { from: props.location }
            }} />
            : <Component {...props} />
    )} />
)
