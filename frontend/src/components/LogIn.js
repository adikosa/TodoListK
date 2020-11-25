import React from 'react'
import {connect} from 'react-redux'
import {Redirect} from 'react-router-dom'
import {logIn} from '../store/actions/authActions'

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
        const {authError, userCredentials} = this.props;

        if (userCredentials) {
            return <Redirect to='/'/>
        }

        return (
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Log In</h5>
                    <div className="row">
                        <div className="input-field col s12">
                            <label htmlFor="username">Username</label>
                            <input type="text" id='username' onChange={this.handleChange}/>
                        </div>
                    </div>
                    <div className="row">
                        <div className="input-field col s12">
                            <label htmlFor="password">Password</label>
                            <input type="password" id='password' onChange={this.handleChange}/>
                        </div>
                    </div>

                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Log In</button>
                        <div className="center red-text">
                            {authError ? <p>{authError}</p> : null}
                        </div>
                    </div>
                </form>
            </div>
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
        logIn: (user) => dispatch(logIn(user))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(LogIn)