import {googleTasksService} from "../../api/googleTasks.service"
import {openSnackbar} from "./snackbarActions";

export const resetSyncUserTodos = () => {
    return (dispatch) => {
        dispatch({type: 'SYNC_TASKS_RESET'})
    }
}

export const getGoogleTasksOAuthUrl = () => {
    return (dispatch) => {
        googleTasksService.getOAuthUrl()
            .then(result => {
                dispatch({type: 'GET_OAUTH_URL_SUCCESS', oAuthUrl: result.data.url})
            })
            .catch((error) => {
                dispatch({type: 'GET_OAUTH_URL_ERROR', errorMessage: error})
            })
    }
}

export const syncUserTodos = (googleTasksToken) => {
    return (dispatch) => {
        dispatch({type: 'SYNC_TASKS'})

        googleTasksService.syncUserTodos(googleTasksToken)
            .then(result => {
                dispatch(openSnackbar("success", "Todos synced with Google Tasks, check out your Google Calendar or Google Tasks App!"))
                dispatch({type: 'SYNC_TASKS_SUCCESS'})
            })
            .catch((error) => {
                dispatch({type: 'SYNC_TASKS_ERROR', errorMessage: error})
            })
    }
}