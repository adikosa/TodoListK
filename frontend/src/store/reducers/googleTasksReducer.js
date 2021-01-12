const initState = {
    isLoading: false,
    isFinished: false,
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
        case 'SYNC_TASKS': {
            return {
                ...state,
                isLoading: true,
                isFinished: false,
                errorMessage: null
            }
        }
        case 'SYNC_TASKS_SUCCESS': {
            return {
                ...state,
                isLoading: false,
                isFinished: true,
                errorMessage: null
            }
        }
        case 'SYNC_TASKS_ERROR':{
            return {
                ...state,
                isLoading: false,
                isFinished: true,
                errorMessage: action.errorMessage.toString()
            }    
        }
        case 'SYNC_TASKS_RESET':{
            return {
                ...state,
                isLoading: false,
                isFinished: false,
                errorMessage: null
            }
        }
        default:
            return state;
    }
};

export default googleTasksReducer;