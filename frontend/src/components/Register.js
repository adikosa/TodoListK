import React from 'react'
import { connect } from 'react-redux'
import { Redirect } from 'react-router-dom'
import { signIn, signUp } from '../store/actions/authActions'

class Register extends React.Component {
    state = {
        email: '',
        password: '',
        firstName: '',
        lastName: '',
      }
      handleChange = (e) => {
        this.setState({
          [e.target.id]: e.target.value
        })
      }
      handleSubmit = (e) => {
        e.preventDefault();
        this.props.signUp(this.state);
      }
      render() {
        const { userId, authError } = this.props;

        // if (userId === null) return <Redirect to='/' /> 
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
                        <input type="text"/>
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
    
    const mapDispatchToProps = (dispatch)=> {
      return {
        signUp: (creds) => dispatch(signUp(creds))
      }
    }
  
  export default connect(mapStateToProps, mapDispatchToProps)(Register)