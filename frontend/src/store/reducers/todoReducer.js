const initState = {
    todos : [],
    errorMessage : null
};

const todoReducer = (state = initState, action) => {
    switch (action.type) {
        case 'ADD_TODO_SUCCESS': {
            const {isDone, userId, createdAt, ...todo} = action.todo;
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
            const todos = action.todos.map(rawTodo => {
                const {isDone, userId, createdAt, ...todo} = rawTodo;
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
        case 'EDIT_TODO_SUCCESS': {
            const {isDone, userId, createdAt, ...todo} = action.todo;

            const editedTodoId = todo.id

            const todos = [...state.todos.filter(todo => todo.id !== editedTodoId), todo]            
            
            return {
                ...state,
                todos,
                errorMessage: null
            }
        }
        case 'EDIT_TODO_ERROR':{
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