export const openSnackbar = (severity, message) => {
    return (dispatch) => {
        dispatch({type: 'SNACKBAR_OPEN', severity: severity, message: message})
    }
}

export const closeSnackbar = () => {
    return (dispatch) => {
        dispatch({type: 'SNACKBAR_CLOSE'})
    }
}