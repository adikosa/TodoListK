import {delete_with_auth, get_with_auth, post_with_auth} from "./base.request";

export const todoService = {
    save,
    getUserTodos,
    deleteById
};

function save(todo) {
    return post_with_auth('/todos', todo)
}

function getUserTodos() {
    return get_with_auth('/todos', null)
}

function deleteById(todoId) {
    return delete_with_auth('/todos/' + todoId, null)
}
