import {combineReducers} from 'redux';
import authReducer from './authReducer'
import todoReducer from './todoReducer';
import googleTasksReducer from './googleTasksReducer'
import snackbarReducer from "./snackbarReducer";

const rootReducer = combineReducers({
    auth: authReducer,
    todo: todoReducer,
    googleTasks: googleTasksReducer,
    snackbar: snackbarReducer
});

export default rootReducer