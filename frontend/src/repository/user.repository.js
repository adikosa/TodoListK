import {userService} from "../api/user.service";

export const userRepository = {
    login,
    register
};

function login(loginData) {
    return userService.login(loginData)
}

function register(registerData) {
    return userService.register(registerData)
}
