import {post} from "./base.request";

export const todoService = {
    save,
    getUserTodos,
    deleteById
};

function save(todo) {
    return post('/todos', todo)
}

function getUserTodos(userId) {
    return post('/todos/' + userId, null)
}

function deleteById(todoId) {
    return post('/todos/' + todoId, null)
}
