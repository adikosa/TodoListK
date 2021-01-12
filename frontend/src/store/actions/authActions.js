import {userService} from "../../api/user.service"
import {openSnackbar} from "./snackbarActions";

export const logIn = (credentials) => {
    return (dispatch) => {
        userService.login(credentials)
            .then(tokenResult => {
                const userCredentials = {
                    userId: tokenResult.data.id,
                    token: tokenResult.data.token,
                    username: tokenResult.data.username
                }
                localStorage.setItem("userCredentials", JSON.stringify(userCredentials))
                dispatch(openSnackbar("success", "Logged in successfully!"))
                dispatch({type: 'LOGIN_SUCCESS', userCredentials})
            })
            .catch((error) => {
                let errorMessage;
                if (error.response?.status >= 400 && error.response?.status < 500) {
                    errorMessage = "Bad username and/or password, try again"
                } else {
                    errorMessage = error
                }
                dispatch(openSnackbar("error", errorMessage))
            })
    }
}

export const logOut = () => {
    return (dispatch) => {
        localStorage.removeItem("userCredentials")
        dispatch(openSnackbar("success", "Logged out successfully!"))
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
                dispatch(openSnackbar("success", "Registered successfully! Please confirm your e-mail address and log in to continue"))
            })
            .catch((error) => {
                let errorMessage;
                if (error.response?.status >= 400 && error.response?.status < 500) {
                    errorMessage = "Bad username and/or password, try again"
                } else if (error.response?.status >= 500) {
                    errorMessage = "Invalid form data or email address is already taken"
                } else {
                    errorMessage = error
                }
                dispatch(openSnackbar("error", errorMessage))
            })
    }
}