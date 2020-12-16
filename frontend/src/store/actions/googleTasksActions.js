import {googleTasksRepository} from "../../repository/googleTasks.repository"

export const getGoogleTasksOAuthUrl = () => {
    return (dispatch) => {
        googleTasksRepository.getOAuthUrl()
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
        googleTasksRepository.syncUserTodos(googleTasksToken)
            .then(result => {
                dispatch({type: 'SYNC_TASKS_SUCCESS'})
            })
            .catch((error) => {
                dispatch({type: 'SYNC_TASKS_ERROR', errorMessage: error})
            })
    }
}