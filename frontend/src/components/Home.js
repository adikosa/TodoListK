import { Button, Checkbox, Container, Fab, FormControlLabel, Grid, Typography, withStyles } from '@material-ui/core';
import React from 'react'
import {connect} from 'react-redux';
import {Redirect} from 'react-router-dom';
import {logIn} from '../store/actions/authActions'
import { getTodos } from '../store/actions/todoActions';
import AddIcon from '@material-ui/icons/Add';

import TodoModal from './TodoModal';
import { compose } from 'redux';

const useStyles = theme => ({
    fab: {
        position: 'absolute',
        bottom: theme.spacing(2),
        right: theme.spacing(2),
    }
})

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
        } , () => {
            this.setState({
                isModalOpen: true
            })
        })
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

        const {todos, classes} = this.props;

        const todoList = todos.length ? (
            todos.map(todo => {
                return(
                    <div>
                        <Grid container >
                            <Grid item xs={9}>
                                <FormControlLabel
                                    control={<Checkbox color="primary"/>}   
                                    label={todo.title}
                                />
                            </Grid>
                            <Grid item xs={3}>
                                <Button onClick={() => this.handleEditClick(todo)} color="primary">Edit</Button>    
                                <Button color="primary">Delete</Button>  
                            </Grid>
                            

                        </Grid>
 
                    </div>

                );
            })
        ) : (
            <div className="center">No todos yet</div>
        )

        const {id, title, description, priority, dueDateTime} = this.state.todoModalData

        return(
            <Container fixed>
                <TodoModal isOpen={this.state.isModalOpen} onClose={this.handleModalClose} id={id} title={title} description={description} priority={priority} dueDateTime={dueDateTime}/>
                <Typography variant="h4"> Tasks </Typography>
                {todoList}
                <Fab onClick={this.handleAddClick} color="primary" aria-label="add" className={classes.fab}>
                    <AddIcon />
                </Fab>
            </Container>            
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

export default compose(withStyles(useStyles), connect(mapStateToProps, mapDispatchToProps)) (Home);