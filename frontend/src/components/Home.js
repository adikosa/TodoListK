import React from 'react'
import {connect} from 'react-redux';
import {Redirect} from 'react-router-dom';
import {logIn} from '../store/actions/authActions'
import { getTodos } from '../store/actions/todoActions';


import TodoModal from './TodoModal';

class Home extends React.Component {
    componentDidMount(){
        this.props.getTodos()
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
                            <button data-target="TodoModal" className="btn-small modal-trigger red lighten-1">Edit
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

        return (
            <div className="container home">
                <TodoModal id="TodoModal" title="Custom Edit Title" description="AAA" priority="MEDIUM" />
                <h5 className="grey-text text-darken-3">Tasks</h5>
                {todoList}
                <div className="fixed-action-btn">
                    <button data-target="TodoModal" className="btn-floating modal-trigger btn-large red"><i
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