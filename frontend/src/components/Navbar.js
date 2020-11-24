import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import SignedInLinks from './SignedInLinks';
import SignedOutLinks from './SignedOutLinks';

function Navbar(props){
    const { userId } = props;

    const links = userId ? <SignedInLinks/> : <SignedOutLinks/>;

    return(
        <nav className="nav-wrapper red darken-3">
            <div className="container">
                <Link to='/' className="brand-logo">TodoListK</Link>
                {links}
            </div>
        </nav>
    )
}

const mapStateToProps = (state) => {
    return{
      userId: state.auth.userId
    }
}
  
export default connect(mapStateToProps)(Navbar)