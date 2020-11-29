import React from "react";
import M from "materialize-css";
import "materialize-css/dist/css/materialize.min.css";
import {Button, DatePicker, Modal, Row, Select, TextInput, TimePicker} from "react-materialize";
import { connect } from "react-redux";
import {addTodo} from '../store/actions/todoActions'


class TodoModal extends React.Component {
    
    state = {
        title: this.props.description,
        description: this.props.description,
        dueDateTime: this.props.dueDateTime,
        priority: this.props.priority
    }

    convertTodoStateToTodoRequest = () => {
        const {title, description, dueDateTime, priority} = this.state;
        return {
            title,
            description,
            dueDateTime,
            priority
        }
    }

    timePickerCachedData = {
        hour: null,
        minute: null
    }

    componentDidMount() {
        M.AutoInit()
    }

    onOpenStart = () => {
        const { title, description, dueDateTime, priority} = this.props;
        this.setState({ title, description, dueDateTime, priority }, () => console.log("props received", this.state));
    }

    handleClick = (e) => {
        this.props.addTodo(this.convertTodoStateToTodoRequest());
    }

    handleChange = (e) => {
        const {id, value} = e.target;
        this.setState({
            [id]: value
        });
    }

    handleTime = (hour, minute) => {
        this.timePickerCachedData = {hour, minute}
    }

    handleSubmit = (e) => {
        e.preventDefault()
    }

    handleDateChange = (date) => {
        const oldDueDateTime = this.state.dueDateTime
        date.setHours(oldDueDateTime.getHours())
        date.setMinutes(oldDueDateTime.getMinutes())
        this.setState({
            dueDateTime: date
        })
    }

    handleTimePickerClose = (e) => {
        const {dueDateTime} = this.state
        dueDateTime.setHours(this.timePickerCachedData.hour)
        dueDateTime.setMinutes(this.timePickerCachedData.minute)
        this.setState({
            dueDateTime
        })    
    }

    render() {
        return (
            <div>
                <Modal
                    open={this.props.isOpen}
                    actions={[
                        <Button flat modal="close" node="button">Cancel</Button>,
                        <Button onClick={this.handleClick} flat modal="close" node="button">Save</Button>
                    ]}

                    options={{onCloseEnd : this.props.onClose, onOpenStart: this.onOpenStart}}
                >
                    <form onSubmit={this.handleSubmit} autoComplete="off">
                        <Row>
                            <TextInput id="title" label="Title" value={this.state.title} onChange={this.handleChange}/>  
                        </Row>
                        <Row>
                            <TextInput id="description" label="Description" value={this.state.description} onChange={this.handleChange}/>  
                        </Row>
                        <Row>                           
                            <DatePicker id="dueDate" label="Due date" options={{autoClose: true, defaultDate: this.state.dueDateTime, setDefaultDate: true}} onChange={this.handleDateChange} />
                            <TimePicker id="dueTime" label="Due time" options={{autoClose: true, twelveHour: false, onCloseEnd: this.handleTimePickerClose, defaultTime: this.state.dueDateTime.getHours() + ":" + this.state.dueDateTime.getMinutes}} onChange={this.handleTime} />
                        </Row>
                        <Row>
                            <Select id="priority" label="Priority" value={this.state.priority} onChange={this.handleChange}>
                                <option value="LOW">LOW</option>
                                <option value="MEDIUM">MEDIUM</option>
                                <option value="HIGH">HIGH</option>
                            </Select>
                        </Row>
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
