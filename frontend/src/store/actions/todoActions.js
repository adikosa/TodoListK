import {todoService} from "../../api/todo.service"
import {openSnackbar} from "./snackbarActions";

export const addTodo = (todo) => {
    return (dispatch) => {
        todoService.save(todo)
            .then(result => {
                dispatch(openSnackbar("success", "Added new Todo!"))
                dispatch({type: 'ADD_TODO_SUCCESS', todo: result.data})
            })
            .catch((error) => {
                dispatch({type: 'ADD_TODO_ERROR', errorMessage: error})
            })
    }
}

export const getTodos = () => {
    return (dispatch) => {
        todoService.getUserTodos()
            .then(result => {
                dispatch({type: 'GET_TODOS_SUCCESS', todos: result.data})
            })
            .catch((error) => {
                dispatch({type: 'GET_TODOS_ERROR', errorMessage: error})
            })
    }
}

export const deleteTodo = (id) => {
    return (dispatch) => {
        todoService.deleteById(id)
            .then(result => {
                dispatch(openSnackbar("success", "Todo removed successfully!"))
                dispatch({type: 'DELETE_TODO_SUCCESS', id})
            })
            .catch((error) => {
                dispatch({type: 'DELETE_TODO_ERROR', errorMessage: error})
            })
    }
}

export const editTodo = (todo) => {
    return (dispatch) => {
        todoService.editTodo(todo)
            .then(result => {
                dispatch(openSnackbar("success", "Todo updated successfully!"))
                dispatch({type: 'EDIT_TODO_SUCCESS', todo: result.data})
            })
            .catch((error) => {
                dispatch({type: 'EDIT_TODO_ERROR', errorMessage: error})
            })
    }
}