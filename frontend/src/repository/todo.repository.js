import {todoService} from "../api/todo.service";

export const todoRepository = {
    save,
    getUserTodos,
    deleteById,
    editTodo
};

function save(todo) {
    return todoService.save(todo)
}

function getUserTodos() {
    return todoService.getUserTodos()
}

function deleteById(todoId) {
    return todoService.deleteById(todoId)
}

function editTodo(todo) {
    return todoService.editTodo(todo)
}