import React from "react";
import Select from '@material-ui/core/Select';
import DateFnsUtils from '@date-io/date-fns';
import { MuiPickersUtilsProvider } from '@material-ui/pickers';
import { connect } from "react-redux";
import {addTodo} from '../store/actions/todoActions'
import { Button, Dialog, DialogActions, DialogContent, FormControl, InputLabel, MenuItem, TextField, withStyles } from "@material-ui/core";
import { KeyboardDatePicker, KeyboardTimePicker } from "@material-ui/pickers";
import { compose } from "redux";

const useStyles = theme => ({
    root: {
      '& .MuiFormControl-root': {
        width: '90%',
        margin: theme.spacing(2)
        }
    }
})

class TodoModal extends React.Component {
    
    state = {
        title: this.props.description,
        description: this.props.description,
        dueDateTime: this.props.dueDateTime,
        priority: this.props.priority,
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

    onDialogOpen = () => {
        const { title, description, dueDateTime, priority} = this.props;
        this.setState({ title, description, dueDateTime, priority });
    }

    handleChange = (e) => {
        const {id, value} = e.target;
        this.setState({
            [id]: value
        });
    }

    handleDueTimeChange = (dueDateTime) => {
        this.setState({
            dueDateTime
        });
    }

    handlePriorityChange = (e) => {
        const {value} = e.target;

        this.setState({
            priority: value
        });
    }

    handleSaveClick = () => {
        this.props.addTodo(this.convertTodoStateToTodoRequest());
        this.props.onClose()
    }

    render() {
        const {classes} = this.props;

        return(
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <Dialog open={this.props.isOpen} onClose={this.props.onClose} onEnter={this.onDialogOpen}> 
                    <DialogContent className={classes.root}>
                        <TextField id="title" label="Title" autoComplete="off" value={this.state.title} onChange={this.handleChange}/>
                        <TextField id="description" label="Description" autoComplete="off" multiline={true} value={this.state.description} onChange={this.handleChange} />
                        <KeyboardDatePicker margin="normal" label="Due date" format="MM/dd/yyyy" value={this.state.dueDateTime} onChange={this.handleDueTimeChange}/>
                        <KeyboardTimePicker margin="normal" label="Due time" value={this.state.dueDateTime} onChange={this.handleDueTimeChange}/>
                        <FormControl >
                            <InputLabel id="label">Priority</InputLabel>
                            <Select labelId="label" id="priority" value={this.state.priority} onChange={this.handlePriorityChange}>
                                <MenuItem value={"LOW"}>LOW</MenuItem>
                                <MenuItem value={"MEDIUM"}>MEDIUM</MenuItem>
                                <MenuItem value={"HIGH"}>HIGH</MenuItem>
                            </Select>
                        </FormControl>

                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.props.onClose} color="primary">Cancel</Button>
                        <Button onClick={this.handleSaveClick} color="primary">Save</Button>
                    </DialogActions>
                </Dialog>
            </MuiPickersUtilsProvider>
        )
    }
    
}

const mapDispatchToProps = (dispatch) => {
    return {
        addTodo: (todo) => dispatch(addTodo(todo))
    }
}

export default compose(withStyles(useStyles),connect(null, mapDispatchToProps))(TodoModal);
