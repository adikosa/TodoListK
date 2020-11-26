import React from "react";
import M from "materialize-css";
// import "materialize-css/dist/css/materialize.min.css";
import {Button, DatePicker, Modal, TimePicker} from "react-materialize";
import { connect } from "react-redux";
import {addTodo} from '../store/actions/todoActions'


class TodoModal extends React.Component {
    state = {
        todo : {
            title: this.props.title,
            description: this.props.description,
            dueDate: null,
            dueTime: null,
            priority: this.props.priority
        }
    }

    convertTodoStateToTodoRequest = () => {
        const {title, description, dueDate, dueTime, priority} = this.state.todo;
        var dueDateTime = new Date(dueDate.getTime())
        dueDateTime.setHours(dueTime.hour)
        dueDateTime.setMinutes(dueTime.minute)
        return {
            title,
            description,
            priority,
            dueDateTime
        }
    }

    timePickerCachedData = {
        hour: null,
        minute: null
    }

    componentDidMount() {
        M.AutoInit()
    }

    handleClick = (e) => {
        this.props.addTodo(this.convertTodoStateToTodoRequest());
    }

    handleChange = (e) => {
        const {id, value} = e.target;
        const {todo} = this.state;
        this.setState({
            todo: {
                ...todo,
                [id]: value
            }
        });
    }

    handleTime = (hour, minute) => {
        this.timePickerCachedData = {hour, minute}
    }

    handleSubmit = (e) => {
        e.preventDefault()
    }

    handleTimePickerClose = (e) => {
        this.handleChange({target: {id: "dueTime", value: this.timePickerCachedData}})
    }

    render() {
        return (
            <div>
                <Modal
                    actions={[
                        <Button flat modal="close" node="button">Cancel</Button>,
                        <Button onClick={this.handleClick} flat modal="close" node="button">Save</Button>
                    ]}

                    id={this.props.id}
                >
                    <form onSubmit={this.handleSubmit} autoComplete="off">
                        <div className="row">
                            <div className="col s12 input-field">
                                <input id="title" value={this.state.todo.title} type="text" onChange={this.handleChange}/>
                                <label htmlFor="text">Title</label>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col s12 input-field">
                                <input id ="description" value={this.state.todo.description} type="text" onChange={this.handleChange}/>
                                <label htmlFor="text">Description</label>
                            </div>
                        </div>
                        <div className="row">                           
                            <DatePicker id="dueDate" label="Due date" options={{autoClose: true}} onChange={(date)=>this.handleChange({target: {id: "dueDate", value: date}})} />
                            <TimePicker id="dueTime" label="Due time" options={{autoClose: true, twelveHour: false, onCloseEnd: this.handleTimePickerClose}} onChange={this.handleTime} />
                        </div>
                        <div className="row">
                            <div className="col input-field">
                                <select id="priority" value={this.state.todo.priority} onChange={this.handleChange}>
                                    <option value="LOW">LOW</option>
                                    <option value="MEDIUM">MEDIUM</option>
                                    <option value="HIGH">HIGH</option>
                                </select>
                                <label>Priority</label>
                            </div>
                        </div>
                    </form>
                    
                </Modal>
            </div>
        );
    }
    
}

const mapDispatchToProps = (dispatch) => {
    return {
        addTodo: (todo) => dispatch(addTodo(todo))
    }
}

export default connect(null, mapDispatchToProps)(TodoModal);
