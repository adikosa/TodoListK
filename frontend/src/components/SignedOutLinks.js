import React from 'react'
import {NavLink} from 'react-router-dom'

const SignedOutLinks = () => {
    return (
        <div>
            <ul className="right">
                <li><NavLink to='/register'>Register</NavLink></li>
                <li><NavLink to='/login'>Log In</NavLink></li>
            </ul>
        </div>
    )
}

export default SignedOutLinks