import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {Provider} from 'react-redux';
import {applyMiddleware, createStore} from 'redux';
import App from './App';
import rootReducer from './store/reducers/rootReducer';
import thunk from 'redux-thunk';
import {persistCredentialsMiddleware} from './store/persistCredentialsMiddleware';


const store = createStore(rootReducer, applyMiddleware(thunk, persistCredentialsMiddleware));

ReactDOM.render(
    <React.StrictMode>
        <Provider store={store}>
            <App/>
        </Provider>
    </React.StrictMode>,
    document.getElementById('root')
);
