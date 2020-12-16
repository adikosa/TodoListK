import {googleTasksService} from "../api/googleTasks.service";

export const googleTasksRepository = {
    getOAuthUrl,
    syncUserTodos
};

function getOAuthUrl() {
    return googleTasksService.getOAuthUrl()
}

function syncUserTodos(googleTasksToken) {
    return googleTasksService.syncUserTodos(googleTasksToken)
}