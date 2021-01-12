import {Button, Grid, Typography} from '@material-ui/core'
import React from 'react'
import {connect} from 'react-redux'
import { withRouter } from 'react-router-dom'
import { compose } from 'redux'
import {logOut} from '../store/actions/authActions'

const SignedInLinks = (props) => {
    const { userCredentials } = props;

    return (
        <div>
            <Grid container spacing={2}>
                <Grid item>
                    <Button disableRipple={true} disableFocusRipple={true} color="inherit">Hi, {userCredentials.username}</Button>
                </Grid>
                <Grid item>
                    <Button onClick={props.logOut} color="inherit">Log Out</Button>
                </Grid>
            </Grid>
        </div>
    )
}

const mapStateToProps = (state) => {
    return{
        userCredentials: state.auth.userCredentials,
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logOut: () => dispatch(logOut())
    }
}

export default compose(withRouter, connect(mapStateToProps, mapDispatchToProps))(SignedInLinks)