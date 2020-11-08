import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';

import { store } from './_helpers';
import App from './components/wrappers/App';

// // Add bootstrap
// import 'bootstrap/dist/css/bootstrap.css';
//
// // Add our style
// import './assets/style/index.css';

// render(
//     <React.StrictMode>
//         <App />
//     </React.StrictMode>,
//     document.getElementById('root')
// );

render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('app')
);

