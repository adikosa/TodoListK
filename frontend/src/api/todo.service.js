import {delete_with_auth, get_with_auth, patch_with_auth, post_with_auth} from "./base.request";

export const todoService = {
    save,
    getUserTodos,
    deleteById,
    editTodo
};

function save(todo) {
    return post_with_auth('/todos', todo)
}

function getUserTodos() {
    return get_with_auth('/todos')
}

function deleteById(todoId) {
    return delete_with_auth('/todos', undefined, {todoId})
}

function editTodo(todo) {
    return patch_with_auth('/todos/' + todo.id, todo)
}