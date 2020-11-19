import {post} from "./base.request";

export const userService = {
    login,
    register
};

function login(loginData) {
    return post('/login', loginData)
}

function register(registerData) {
    return post('/register', registerData)
}
