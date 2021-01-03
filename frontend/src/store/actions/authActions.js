import {userRepository} from "../../repository/user.repository"

export const logIn = (credentials) => {
    return (dispatch) => {
        userRepository.login(credentials)
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
        userRepository.register(user)
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