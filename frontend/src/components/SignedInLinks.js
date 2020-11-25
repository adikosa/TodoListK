import React from 'react'
import {connect} from 'react-redux'
import {logOut} from '../store/actions/authActions'

const SignedInLinks = (props) => {
    return (
        <div>
            <ul className="right">
                <li><a onClick={props.logOut}>Log Out</a></li>
            </ul>
        </div>
    )
}

const mapDispatchToProps = (dispatch) => {
    return {
        logOut: () => dispatch(logOut())
    }
}

export default connect(null, mapDispatchToProps)(SignedInLinks)