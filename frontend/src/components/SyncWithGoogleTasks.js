import { Container, withStyles } from '@material-ui/core'
import React from 'react'
import {connect} from 'react-redux'
import { Redirect } from 'react-router-dom'
import { compose } from 'redux'
import {logIn} from '../store/actions/authActions'
import {getGoogleTasksOAuthUrl, resetSyncUserTodos, syncUserTodos} from '../store/actions/googleTasksActions'

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

    renderSyncingText = () => {
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

    componentDidMount(){
        this.props.getGoogleTasksOAuthUrl()
    }

    render() {
        //TODO add ok message
        const {oAuthUrl} = this.props;

        if(!oAuthUrl){
            return this.renderSyncingText();
        }

        const {userCredentials} = this.props;

        if (!userCredentials) {
            return <Redirect to='/login'/>
        }

        const {errorMessage, isFinished, isLoading} = this.props.googleTasks

        if (errorMessage) {
            return <Redirect to={{
                pathname: '/',
                state: { errorMessage }
            }}/>
        }

        const {hash} = this.props.location;

        const accessToken = new URLSearchParams(hash).get("#access_token")

        if (isLoading) {
            return this.renderSyncingText()
        } else {
            if (isFinished) {
                this.props.resetSyncUserTodos()
                return <Redirect to={{
                    pathname: '/',
                    state: { errorMessage: "Google sync successful" }
                }}/>
            } else if (accessToken && !errorMessage) {
                this.props.syncUserTodosWithGoogleTasks(accessToken)
            }
        }

        if (oAuthUrl && !accessToken && !errorMessage) {
            window.location.href = oAuthUrl
            return null;
        }

        return this.renderSyncingText()
    }
}

const mapStateToProps = (state) => {
    return {
        userCredentials: state.auth.userCredentials,
        authError: state.auth.authError,
        googleTasks: state.googleTasks,
        oAuthUrl: state.googleTasks.oAuthUrl
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logIn: (user) => dispatch(logIn(user)),
        syncUserTodosWithGoogleTasks: (token) => dispatch(syncUserTodos(token)),
        getGoogleTasksOAuthUrl: () => dispatch(getGoogleTasksOAuthUrl()),
        resetSyncUserTodos: () => dispatch(resetSyncUserTodos())
    }
}

export default compose(withStyles(useStyles), connect(mapStateToProps, mapDispatchToProps))(SyncWithGoogleTasks)