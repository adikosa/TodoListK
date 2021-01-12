const initState = {
    isSnackbarOpen: false,
    severity: "success",
    message: "",
};

const snackbarReducer = (state = initState, action) => {

    switch (action.type) {
        case 'SNACKBAR_OPEN': {
            return {
                ...state,
                isSnackbarOpen: true,
                severity: action.severity,
                message: action.message
            }
        }
        case 'SNACKBAR_CLOSE':
            return {
                ...state,
                isSnackbarOpen: false
            }
        default:
            return state;
    }
};

export default snackbarReducer;