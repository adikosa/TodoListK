import React from "react";
import M from "materialize-css";
import "materialize-css/dist/css/materialize.min.css";
import {Button, DatePicker, Modal, Row, Select, TextInput} from "react-materialize";
import { connect } from "react-redux";
import {addTodo} from '../store/actions/todoActions'


class TodoModal extends React.Component {
    
    state = {
        title: this.props.description,
        description: this.props.description,
        dueDateTime: this.props.dueDateTime,
        priority: this.props.priority,
        dueTimeText: this.props.dueDateTime.toLocaleTimeString("pl-pl", {hour: "2-digit", minute: "2-digit"})
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

        const dueTimeText = this.props.dueDateTime.toLocaleTimeString("pl-pl", {hour: "2-digit", minute: "2-digit"})
        this.setState({ title, description, dueDateTime, priority, dueTimeText });
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

    handleTime = (e) => {
        this.handleChange(e)
        const {id, value} = e.target;
        const isTimeValid = /^([01]\d|2[0-3]):([0-5]\d)$/.test(value)

        if(isTimeValid) {
            const hours = value.substring(0,2)
            const minutes = value.substring(3,5)

            const dueDateTime = new Date(this.state.dueDateTime.getTime());
            dueDateTime.setHours(hours);
            dueDateTime.setMinutes(minutes);
            this.setState({
                dueDateTime
            })
        }
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
                            <TextInput id="dueTimeText" label="Due time" value={this.state.dueTimeText} onChange={this.handleTime}/>  
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
