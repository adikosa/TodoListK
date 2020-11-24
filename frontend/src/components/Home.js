import React from 'react'

import TodoModal from './TodoModal';

class Home extends React.Component {
    render() {
        // const {todos} = this.props;
        const todos = 
            [
                {id : 1, title: 'Play Fifa'},
                {id : 2, title: 'Buy milk'},
                {id : 3, title: 'Go sleep'}
            ]

        const todoList = todos.length ? (
            todos.map(todo => {
                return(
                    <div className="row" key={todo.id}>
                        <div className="col s10">
                            <label>
                                <input type="checkbox" />
                                <span className="black-text">{todo.title}</span>
                            </label>
                        </div>
                        <div className="col s1">
                            <button data-target="EditModal" className="btn-small modal-trigger red lighten-1">Edit</button>
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
                <TodoModal id="EditModal" title="Custom Edit Title"/>
                <TodoModal id="AddModal"/>
                <h5 className="grey-text text-darken-3">Tasks</h5>
                {todoList}
                <div className="fixed-action-btn">
                    <button data-target="AddModal" className="btn-floating modal-trigger btn-large red"><i className="material-icons">add</i>Add task</button>
                </div> 
            </div> 
          );
    }
}

export default Home;