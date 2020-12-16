import {get_with_auth, post_with_auth} from "./base.request";

export const googleTasksService = {
    getOAuthUrl,
    syncUserTodos
};

function getOAuthUrl() {
    return get_with_auth('/googleTasks/oAuthUrl')
}

function syncUserTodos(googleTasksToken){
    return post_with_auth('/googleTasks/sync', undefined, {googleTasksToken})
}