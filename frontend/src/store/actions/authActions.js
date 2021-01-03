import {userService} from "../../api/user.service"

export const logIn = (credentials) => {
    return (dispatch) => {
        userService.login(credentials)
            .then(tokenResult => {
                const userCredentials = {
                    userId: tokenResult.data.id,
                    token: tokenResult.data.token
                }
                dispatch({type: 'LOGIN_SUCCESS', userCredentials})
            })
            .catch((error) => {
                const errorMessage = error
                dispatch({type: 'LOGIN_ERROR', errorMessage})
            })
    }
}

export const logOut = () => {
    return (dispatch) => {
        dispatch({type: 'LOGOUT_SUCCESS'})
    }
}

export const register = (user) => {
    return (dispatch) => {
        userService.register(user)
            .then(tokenResult => {
                const userCredentials = {
                    userId: tokenResult.data.id,
                    token: tokenResult.data.token
                }
                dispatch({type: 'REGISTER_SUCCESS', userCredentials})
            })
            .catch((error) => {
                const errorMessage = error
                dispatch({type: 'REGISTER_ERROR', errorMessage})
            })
    }
}