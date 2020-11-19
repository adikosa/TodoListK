import React from 'react';
import { Route, Redirect} from 'react-router-dom';
import {tokenRepository} from "../repository/token.repository";

export const ProtectedRoute = ({ component: Component, ...rest }) => (
    <Route {...rest} render={props => (
        tokenRepository.isTokenValid()
            ? <Component {...props} />
            : <Redirect to={{
                pathname: '/login',
                state: { from: props.location }
            }} />
    )} />
)
