export const tokenRepository = {
    isTokenValid
};

function isTokenPresent() {
    return localStorage.getItem('token')
}

function isTokenValid() {
    return isTokenPresent() && true     // TODO change later
}
