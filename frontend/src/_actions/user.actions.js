import { userConstants } from '../_constants';
// import { userService } from '../_services';
import { todoService } from '../api/todo.service';
import { alertActions } from './';
import { history } from '../_helpers';

export const userActions = {
    register,
};


function register(user) {
    return dispatch => {
        dispatch(request(user));
        //todo create proper service and call it
        // userService.register(user)
        todoService.getUserTodos(1)
            .then(
                user => { 
                    dispatch(success());
                    history.push('/login');
                    dispatch(alertActions.success('Registration successful'));
                },
                error => {
                    dispatch(failure(error.toString()));
                    dispatch(alertActions.error(error.toString()));
                }
            );
    };

    function request(user) { return { type: userConstants.REGISTER_REQUEST, user } }
    function success(user) { return { type: userConstants.REGISTER_SUCCESS, user } }
    function failure(error) { return { type: userConstants.REGISTER_FAILURE, error } }
}