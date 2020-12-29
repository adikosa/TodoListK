const initState = {
    todos : [],
    errorMessage : null
};

const todoReducer = (state = initState, action) => {
    switch (action.type) {
        case 'ADD_TODO_SUCCESS': {
            // TODO do it better
            const {id, title, description, priority, dueDateTime} = action.todo;
            const todo = {id, title, description, priority, dueDateTime};
            const todos = [...state.todos, todo];
            return {
                ...state,
                todos,
                errorMessage: null
            };
        }
        case 'ADD_TODO_ERROR': {
            return {
                ...state,
                errorMessage: action.errorMessage.toString()
            };
        }
        case 'GET_TODOS_SUCCESS': {
            // TODO do it better
            const todos = action.todos.map(rawTodo => {
                const {id, title, description, priority, dueDateTime} = rawTodo;
                const todo = {id, title, description, priority, dueDateTime};
                return todo;
            })

            return {
                ...state,
                todos,
                errorMessage: null
            }
        }
        case 'GET_TODOS_ERROR':{
            return {
                ...state,
                errorMessage: action.errorMessage.toString()
            }    
        }
        case 'DELETE_TODO_SUCCESS': {
            const deletedTodoId = action.id
            
            const todos = state.todos.filter(todo => todo.id !== deletedTodoId)
            return {
                ...state,
                todos,
                errorMessage: null
            }
        }
        case 'DELETE_TODO_ERROR':{
            return {
                ...state,
                errorMessage: action.errorMessage.toString()
            }    
        }
        default:
            return state;
    }
};

export default todoReducer;