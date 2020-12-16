import { Container, withStyles } from '@material-ui/core'
import React from 'react'
import {connect} from 'react-redux'
import { Redirect } from 'react-router-dom'
import { compose } from 'redux'
import {logIn} from '../store/actions/authActions'
import { syncUserTodos } from '../store/actions/googleTasksActions'

const useStyles = theme => ({
    root: {
      '& .MuiFormControl-root': {
        width: '100%',
        margin: theme.spacing(2)
        }
    }
})

class SyncWithGoogleTasks extends React.Component {

    handleChange = (e) => {
        const {id, value} = e.target;
        const {userCredentials} = this.state;
        this.setState({
            userCredentials: {
                ...userCredentials,
                [id]: value
            }
        });
    }


    render() {
        const {errorMessage} = this.props.googleTasks

        if (errorMessage) {
            console.log("SYNC ERROR", errorMessage)
            return <Redirect to ="/"/>
        }
        
        const {userCredentials} = this.props;

        if (!userCredentials) {
            return <Redirect to='/login'/>
        }

        const params = new URLSearchParams(this.props.location.hash); 

        const accessToken = params.get("#access_token")

        if (accessToken) {
            this.props.syncUserTodosWithGoogleTasks(accessToken)
        }

        return (
               <div>
                    <Container fixed>
                        <h1>
                            Syncing...
                        </h1>
                    </Container>
                   
               </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        userCredentials: state.auth.userCredentials,
        authError: state.auth.authError,
        googleTasks: state.googleTasks
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logIn: (user) => dispatch(logIn(user)),
        syncUserTodosWithGoogleTasks: (token) => dispatch(syncUserTodos(token))
    }
}

export default compose(withStyles(useStyles), connect(mapStateToProps, mapDispatchToProps))(SyncWithGoogleTasks)