import React from 'react'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'
import { signIn } from '../store/actions/authActions'

class LogIn extends React.Component {
    state = {
      email: '',
      password: ''
    }
    handleChange = (e) => {
      this.setState({
        [e.target.id]: e.target.value
      })
    }
    handleSubmit = (e) => {
      e.preventDefault();
      this.props.signIn(this.state)
    }
    render() {
      const { userId, authError } = this.props;

      // if (userId === null) return <Redirect to='/' /> 
      return (
        <div className="container">
          <form className="white" onSubmit={this.handleSubmit}>
            <h5 className="grey-text text-darken-3">Log In</h5>
            <div className="row">
              <div className="input-field col s12">
                <label htmlFor="email">Email</label>
                <input type="email" id='email' onChange={this.handleChange} />
              </div>
            </div>
            <div className="row">
              <div className="input-field col s12">
                <label htmlFor="password">Password</label>
                <input type="password" id='password' onChange={this.handleChange} />
              </div>
            </div>

            <div className="input-field">
              <button className="btn red lighten-1 z-depth-0">Log In</button>
              <div className="center red-text">
                { authError ? <p>{authError}</p> : null }
              </div>
            </div>
          </form>
        </div>
      )
    }
  }
  
  const mapStateToProps = (state) => {
    return{
      userId: state.auth.userId,
      authError: state.auth.authError
    }
  }
  
  const mapDispatchToProps = (dispatch) => {
    return {
      signIn: (creds) => dispatch(signIn(creds))
    }
  }
  
  export default connect(mapStateToProps, mapDispatchToProps)(LogIn)