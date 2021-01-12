export const persistCredentialsMiddleware = () => {
    return next => action => {
        const result = next(action);

        if (['REGISTER_SUCCESS', 'LOGOUT_SUCCESS', 'LOGIN_SUCCESS'].includes(action.type)) {
            result.userCredentials ? (
                localStorage.setItem("userCredentials", JSON.stringify(result.userCredentials))
            ) : (
                localStorage.removeItem("userCredentials")
            );

        }
        return result;
    };
};