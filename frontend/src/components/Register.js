import { Button, Container, TextField, Typography, withStyles } from '@material-ui/core'
import React from 'react'
import {connect} from 'react-redux'
import {Redirect} from 'react-router-dom'
import { compose } from 'redux'
import {register} from '../store/actions/authActions'

const useStyles = theme => ({
    root: {
      '& .MuiFormControl-root': {
        width: '100%',
        margin: theme.spacing(2)
        }
    }
})

class Register extends React.Component {
    state = {
        user: {
            username: '',
            email: '',
            password: '',
            firstName: '',
            lastName: '',
        }
    }

    handleChange = (e) => {
      const {id, value} = e.target;
      const {user} = this.state;
      this.setState({
        user: {
              ...user,
              [id]: value
          }
      });
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.register(this.state.user);
    }

    render() {
        const {authError, userCredentials, classes} = this.props;

        if (userCredentials) {
            return <Redirect to='/'/>
        }

        return (
            <form onSubmit={this.handleSubmit} className={classes.root} >
                <Container maxWidth="sm">
                    <TextField id="firstName" label="First name" onChange={this.handleChange} variant="outlined"/>
                    <TextField id="lastName" label="Last name" onChange={this.handleChange} variant="outlined"/>
                    <TextField id="email" label="Email" onChange={this.handleChange} variant="outlined"/>
                    <TextField id="username" label="Username" onChange={this.handleChange} variant="outlined"/>
                    <TextField id="password" label="Password" onChange={this.handleChange} variant="outlined" type="password"/>
                    <Button type="submit" variant="contained" color="primary" > Register</Button>
                </Container>
                <Typography align="center" color="error" variant="h6">
                    {authError ? <p>{authError}</p> : null}
                </Typography>
            </form>           
        )
    }
}

const mapStateToProps = (state) => {
    return {
        userCredentials: state.auth.userCredentials,
        authError: state.auth.authError
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        register: (user) => dispatch(register(user))
    }
}

export default compose(withStyles(useStyles), connect(mapStateToProps, mapDispatchToProps)) (Register)