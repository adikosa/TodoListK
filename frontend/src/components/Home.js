import React from 'react'
import {connect} from 'react-redux';
import {Redirect} from 'react-router-dom';
import {logIn} from '../store/actions/authActions'
import { getTodos } from '../store/actions/todoActions';


import TodoModal from './TodoModal';

class Home extends React.Component {
    state = {
        todoModalData : {
            id: null,
            title: "",
            description: "",
            priority: "",
            dueDateTime: new Date()
        },
        isModalOpen : false
    }

    componentDidMount(){
        this.props.getTodos()
    }

    handleEditClick = (todo) => {
        this.setState({
            todoModalData: todo
        } , () => this.setState({
            isModalOpen: true
        }))
    }

    handleAddClick = () => {
        const emptyTodoModalData = {
            id: null,
            title: "",
            description: "",
            priority: "MEDIUM",
            dueDateTime: new Date()
        }

        this.setState({
            todoModalData: emptyTodoModalData
        } , () => this.setState({
            isModalOpen: true
        }))    
    }

    handleModalClose = () => {
        this.setState({
            isModalOpen: false
        })
    }
    
    render() {
        const {userCredentials} = this.props;

        if (!userCredentials) {
            return <Redirect to='/login'/>
        }

        const {todos} = this.props;

        const todoList = todos.length ? (
            todos.map(todo => {
                return (
                    <div className="row" key={todo.id}>
                        <div className="col s10">
                            <label>
                                <input type="checkbox"/>
                                <span className="black-text">{todo.title}</span>
                            </label>
                        </div>
                        <div className="col s1">
                            <button onClick={() => this.handleEditClick(todo)} className="btn-small red lighten-1">Edit
                            </button>
                        </div>
                        <div className="col s1">
                            <a className="btn-small red lighten-1">Delete</a>
                        </div>
                    </div>
                )
            })
        ) : (
            <div className="center">No todos yet</div>
        )

    
        const {id, title, description, priority, dueDateTime} = this.state.todoModalData

        return (
            <div className="container home">
                <TodoModal isOpen={this.state.isModalOpen} onClose={this.handleModalClose} id={id} title={title} description={description} priority={priority} dueDateTime={dueDateTime} />
                <h5 className="grey-text text-darken-3">Tasks</h5>
                {todoList}
                <div className="fixed-action-btn">
                    <button onClick={this.handleAddClick}  className="btn-floating btn-large red"><i
                        className="material-icons">add</i>Add task
                    </button>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        userCredentials: state.auth.userCredentials,
        todos: state.todo.todos
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logIn: (credentials) => dispatch(logIn(credentials)),
        getTodos: () => dispatch(getTodos())
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Home);