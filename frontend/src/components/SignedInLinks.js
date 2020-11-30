import { Button } from '@material-ui/core'
import React from 'react'
import {connect} from 'react-redux'
import { withRouter } from 'react-router-dom'
import { compose } from 'redux'
import {logOut} from '../store/actions/authActions'

const SignedInLinks = (props) => {
    return (
        <div>
            <Button onClick={props.logOut} color="inherit">Log Out</Button>
        </div>
    )
}

const mapDispatchToProps = (dispatch) => {
    return {
        logOut: () => dispatch(logOut())
    }
}

export default compose(withRouter, connect(null, mapDispatchToProps))(SignedInLinks)