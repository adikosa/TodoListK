import { Button } from '@material-ui/core'
import React from 'react'
import {withRouter} from 'react-router-dom'

const SignedOutLinks = (props) => {
    const handleRegisterClick = () => {
        props.history.push('/register')
    }
    const handleLoginClick = () => {
        props.history.push('/login')
    }

    return (
        <div>
            <Button onClick={handleRegisterClick} color="inherit">Register</Button>
            <Button onClick={handleLoginClick} color="inherit">Log In</Button>
        </div>
    )
}

export default withRouter(SignedOutLinks)