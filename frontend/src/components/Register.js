import React from 'react'
import {connect} from 'react-redux'
import {Redirect} from 'react-router-dom'
import {register} from '../store/actions/authActions'

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
        const {authError, userCredentials} = this.props;

        if (userCredentials) {
            return <Redirect to='/'/>
        }

        return (
            <div className="container">
                <form className="white" onSubmit={this.handleSubmit}>
                    <h5 className="grey-text text-darken-3">Register</h5>
                    <div className="row">
                        <div className="input-field col s6">
                            <input id='firstName' type="text" onChange={this.handleChange}/>
                            <label htmlFor="firstName">First Name</label>
                        </div>
                        <div className="input-field col s6">
                            <input id='lastName' type="text" onChange={this.handleChange}/>
                            <label htmlFor="lastName">Last Name</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="input-field col s12">
                            <input id="email" type="email" onChange={this.handleChange}/>
                            <label htmlFor="email">Email</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="input-field col s12">
                            <input id="username" type="text" onChange={this.handleChange}/>
                            <label htmlFor="username">Username</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="input-field col s12">
                            <input id="password" type="password" onChange={this.handleChange}/>
                            <label htmlFor="password">Password</label>
                        </div>
                    </div>


                    <div className="input-field">
                        <button className="btn red lighten-1 z-depth-0">Register</button>
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
        register: (user) => dispatch(register(user))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Register)