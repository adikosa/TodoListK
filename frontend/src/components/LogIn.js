import {
    Avatar,
    Box,
    Button,
    Container,
    CssBaseline,
    Grid,
    Link,
    TextField,
    Typography,
    withStyles
} from '@material-ui/core'
import React from 'react'
import {connect} from 'react-redux'
import {Redirect} from 'react-router-dom'
import { compose } from 'redux'
import {logIn} from '../store/actions/authActions'
import {Copyright} from "./Copyright";

const useStyles = theme => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        margin: theme.spacing(1),
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(1),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
})

class LogIn extends React.Component {
    state = {
        userCredentials: {
            username: '',
            password: ''
        }
    }

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

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.logIn(this.state.userCredentials);
    }

    render() {
        const {authError, userCredentials, classes} = this.props;

        if (userCredentials) {
            return <Redirect to='/'/>
        }

        return (
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <div className={classes.paper}>
                    <Avatar className={classes.avatar} />
                    <Typography component="h1" variant="h5">
                        Sign in
                    </Typography>
                    <Typography component="h1" variant="h6">
                        with your TodoListK Account
                    </Typography>
                    <form className={classes.form} onSubmit={this.handleSubmit}>
                        <TextField
                            id="username"
                            label="Username"
                            onChange={this.handleChange}
                            variant="outlined"
                            margin="normal"
                            required
                            fullWidth
                            autoComplete="username"
                            autoFocus
                        />
                        <TextField
                            id="password"
                            label="Password"
                            onChange={this.handleChange}
                            variant="outlined"
                            type="password"
                            margin="normal"
                            required
                            fullWidth
                            autoComplete="current-password"
                        />
                        <Button
                            id="login-button"
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                            className={classes.submit}
                        >
                            Sign In
                        </Button>
                        <Grid container>
                            <Grid item xs>
                                <Link href="/password-reset" variant="body2">
                                    Forgot password?
                                </Link>
                            </Grid>
                            <Grid item>
                                <Link href="/register" variant="body2">
                                    {"Don't have an account? Register now"}
                                </Link>
                            </Grid>
                        </Grid>
                    </form>
                </div>
                <Box mt={8}>
                    <Copyright />
                </Box>
            </Container>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        userCredentials: state.auth.userCredentials,
        authError: state.auth.authError,
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logIn: (user) => dispatch(logIn(user))
    }
}

export default compose(withStyles(useStyles), connect(mapStateToProps, mapDispatchToProps))(LogIn)