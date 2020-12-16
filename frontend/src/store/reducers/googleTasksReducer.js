const initState = {
    oAuthUrl: null,
    errorMessage: null
};

const googleTasksReducer = (state = initState, action) => {

    switch (action.type) {
        case 'GET_OAUTH_URL_SUCCESS': {
            const {oAuthUrl} = action

            return {
                ...state,
                oAuthUrl,
                errorMessage: null
            }
        }
        case 'GET_OAUTH_URL_ERROR':{
            return {
                ...state,
                oAuthUrl: null,
                errorMessage: action.errorMessage.toString()
            }    
        }
        case 'SYNC_TASKS_SUCCESS': {
            return {
                ...state,
                oAuthUrl: null,
                errorMessage: null
            }
        }
        case 'SYNC_TASKS_ERROR':{
            return {
                ...state,
                oAuthUrl: null,
                errorMessage: action.errorMessage.toString()
            }    
        }
        default:
            return state;
    }
};

export default googleTasksReducer;