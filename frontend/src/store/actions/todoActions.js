import {todoRepository} from "../../repository/todo.repository"

export const addTodo = (todo) => {
    return (dispatch) => {
        todoRepository.save(todo)
            .then(result => {
                dispatch({type: 'ADD_TODO_SUCCESS', todo: result.data})
            })
            .catch((error) => {
                dispatch({type: 'ADD_TODO_ERROR', errorMessage: error})
            })
    }
}

export const getTodos = () => {
    return (dispatch) => {
        todoRepository.getUserTodos()
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
        todoRepository.deleteById(id)
            .then(result => {
                dispatch({type: 'DELETE_TODO_SUCCESS', id})
            })
            .catch((error) => {
                dispatch({type: 'DELETE_TODO_ERROR', errorMessage: error})
            })
    }
}

export const editTodo = (todo) => {
    return (dispatch) => {
        todoRepository.editTodo(todo)
            .then(result => {
                dispatch({type: 'EDIT_TODO_SUCCESS', todo: result.data})
            })
            .catch((error) => {
                dispatch({type: 'EDIT_TODO_ERROR', errorMessage: error})
            })
    }
}