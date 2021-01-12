import {
    Button,
    Checkbox,
    Container,
    Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle,
    Fab,
    FormControlLabel,
    Grid,
    Typography,
    withStyles
} from '@material-ui/core';
import React from 'react'
import {connect} from 'react-redux';
import {Link, Redirect} from 'react-router-dom';
import {logIn} from '../store/actions/authActions'
import { addTodo, deleteTodo, editTodo, getTodos } from '../store/actions/todoActions';
import AddIcon from '@material-ui/icons/Add';
import TodoModal from './TodoModal';
import { compose } from 'redux';

const initModalState = {
    id: null,
    completed: null,
    title: "",
    description: "",
    priority: "MEDIUM",
    dueDateTime: new Date()
};

const useStyles = theme => ({
    fabRight: {
        position: 'absolute',
        bottom: theme.spacing(2),
        right: theme.spacing(2),
    },
    fabLeft: {
        position: 'absolute',
        bottom: theme.spacing(2),
        left: theme.spacing(2),
    }
})

class Home extends React.Component {
    state = {
        todoModalData: initModalState,
        isModalOpen: false,
        todoToBeDeleted: '',
        isDialogOpen: false
    };

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

    handleDeleteClick = (todo) => {
        this.setState({
            todoToBeDeleted: todo,
            isDialogOpen: true
        })
    }

    handleDialogClose = () => {
        this.setState({
            isDialogOpen: false
        })
    };

    handleDeleteTodoDefinitely = () => {
        this.props.deleteById(this.state.todoToBeDeleted.id)
        this.handleDialogClose()
    }

    handleAddClick = () => {
        this.setState({
            todoModalData: initModalState
        } , () => this.setState({
            isModalOpen: true
        }))
    }

    handleModalClose = (todo) => {
        if (todo !== undefined){
            (todo.id) ? this.props.editTodo(todo) : this.props.addTodo(todo)
        }
        this.setState({
            isModalOpen: false
        })
    }

    handleCheckboxClick = (todo, checked) => {
        todo.completed = checked ? new Date() : null
        this.props.editTodo(todo)
    }

    taskToView = (todo) => {
        return(
            <div key={todo.id}>
                <Grid container>
                    <Grid item xs={9}>
                        <FormControlLabel
                            control={<Checkbox color="primary" checked={todo.completed ? true : false} onChange={(e) => this.handleCheckboxClick(todo, e.target.checked)}/> }   
                            label={todo.title}
                        />
                    </Grid>
                    <Grid item xs={3}>
                        <Button onClick={() => this.handleEditClick(todo)} color="primary">Edit</Button>    
                        <Button onClick={() => this.handleDeleteClick(todo)} color="primary">Delete</Button>  
                    </Grid>
                </Grid>
            </div>
        );
    }

    tasksToTaskViews = (tasks) => {
        return (tasks.length ? tasks.map(todo => this.taskToView(todo)) : null)
    }
    
    render() {
        const {userCredentials} = this.props;

        if (!userCredentials) {
            return <Redirect to='/login'/>
        }

        const {todos, classes} = this.props;

        todos.sort((a, b) => a.dueDateTime > b.dueDateTime ? 1 : -1)

        const completedTasks = this.tasksToTaskViews(todos.filter(task => task.completed !== null))

        const todoTasks = this.tasksToTaskViews(todos.filter(task => task.completed == null))

        const {id, completed, title, description, priority, dueDateTime} = this.state.todoModalData

        const exportErrorMessage = this.props.location.state?.errorMessage

        const todoErrorMessage = this.props.todoErrorMessage

        return(
            <div>
                <Container fixed >
                    <TodoModal isOpen={this.state.isModalOpen} onClose={this.handleModalClose} id={id} title={title} description={description} priority={priority} dueDateTime={dueDateTime} completed={completed}/>
                    <Typography variant="h4"> {todoTasks ? "Tasks" : "Nothing \"TODO\" here" }</Typography>
                    {todoTasks}
                    {completedTasks ? <Typography variant="h5"> Completed Tasks </Typography> : null}
                    {completedTasks}
                    <Fab onClick={this.handleAddClick} color="primary" aria-label="add" className={classes.fabRight}>
                        <AddIcon />
                    </Fab>
                    <Fab component={Link} to="/syncWithGoogleTasks" variant="extended" color="primary" className={classes.fabLeft}>
                        Export to Google Tasks
                    </Fab>
                    <Typography align="center" color="error" variant="h6">
                        {/*{exportErrorMessage ? <p>{exportErrorMessage}</p> : null}*/}
                        {todoErrorMessage ? <p>{todoErrorMessage}</p> : null}
                    </Typography>
                </Container>
                <Dialog
                    open={this.state.isDialogOpen}
                    onClose={this.handleDialogClose}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">{"Are you sure you want to delete this Todo?"}</DialogTitle>
                    <DialogContent>
                        <DialogContentText id="alert-dialog-description">
                            It will be deleted permanently.
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleDeleteTodoDefinitely} color="primary" autoFocus>
                            Yes
                        </Button>
                        <Button onClick={this.handleDialogClose} color="primary">
                            Cancel
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>

        );
    }
}

const mapStateToProps = (state) => {
    return {
        userCredentials: state.auth.userCredentials,
        todos: state.todo.todos,
        todoErrorMessage: state.todo.errorMessage
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logIn: (credentials) => dispatch(logIn(credentials)),
        getTodos: () => dispatch(getTodos()),
        addTodo: (todo) => dispatch(addTodo(todo)),
        deleteById: (id) => dispatch(deleteTodo(id)),
        editTodo: (todo) => dispatch(editTodo(todo))
    }
}

export default compose(withStyles(useStyles), connect(mapStateToProps, mapDispatchToProps)) (Home);