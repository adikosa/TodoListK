const initState = {
    authError: null,
    userCredentials: loadUserCredentials()
};

function loadUserCredentials() {
    const jsonCredentials = localStorage.getItem('userCredentials');
    return jsonCredentials ? JSON.parse(jsonCredentials) : null;
}

const authReducer = (state = initState, action) => {
    switch (action.type) {
        case 'LOGIN_SUCCESS':
            return {
                ...state,
                authError: null,
                userCredentials: action.userCredentials
            }

        case 'LOGIN_ERROR':
            return {
                ...state,
                authError: action.errorMessage.toString()
            }

        case 'LOGOUT_SUCCESS':
            return {
                ...state,
                authError: null,
                userCredentials: null
            }

        case 'REGISTER_SUCCESS':
            return {
                ...state,
                authError: null,
                userCredentials: action.userCredentials
            }

        case 'REGISTER_ERROR':
            return {
                ...state,
                authError: action.errorMessage.toString()
            }

        default:
            return state
    }
};

export default authReducer;