import { Button } from '@material-ui/core'
import React from 'react'
import {Link, withRouter} from 'react-router-dom'

const SignedOutLinks = (props) => {
    return (
        <div>
            <Button component={Link} to="/register" color="inherit">Register</Button>
            <Button component={Link} to="/login" color="inherit">Log In</Button>
        </div>
    )
}

export default withRouter(SignedOutLinks)