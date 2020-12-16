import {combineReducers} from 'redux';
import authReducer from './authReducer'
import todoReducer from './todoReducer';
import googleTasksReducer from './googleTasksReducer'


const rootReducer = combineReducers({
    auth: authReducer,
    todo: todoReducer,
    googleTasks: googleTasksReducer
});

export default rootReducer