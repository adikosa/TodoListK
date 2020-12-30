import React from "react";
import Select from '@material-ui/core/Select';
import DateFnsUtils from '@date-io/date-fns';
import { MuiPickersUtilsProvider } from '@material-ui/pickers';
import { Button, Dialog, DialogActions, DialogContent, FormControl, InputLabel, MenuItem, TextField, withStyles } from "@material-ui/core";
import { KeyboardDatePicker, KeyboardTimePicker } from "@material-ui/pickers";

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
        id: this.props.id,
        completed: this.props.completed,
        title: this.props.description,
        description: this.props.description,
        dueDateTime: this.props.dueDateTime,
        priority: this.props.priority,
    }

    onDialogOpen = () => {
        const { id, completed, title, description, dueDateTime, priority} = this.props;
        this.setState({ id, completed, title, description, dueDateTime, priority });
    }

    handleTextChange = (e) => {
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

    render() {
        const {classes} = this.props;

        return(
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <Dialog open={this.props.isOpen} onClose={() => this.props.onClose()} onEnter={this.onDialogOpen}> 
                    <DialogContent className={classes.root}>
                        <TextField id="title" label="Title" autoComplete="off" value={this.state.title} onChange={this.handleTextChange}/>
                        <TextField id="description" label="Description" autoComplete="off" multiline={true} value={this.state.description} onChange={this.handleTextChange} />
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
                        <Button onClick={() => this.props.onClose()} color="primary">Cancel</Button>
                        <Button onClick={() => this.props.onClose(this.state)} color="primary">Save</Button>
                    </DialogActions>
                </Dialog>
            </MuiPickersUtilsProvider>
        )
    }
}

export default withStyles(useStyles)(TodoModal);