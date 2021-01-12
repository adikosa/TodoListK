import { AppBar, Button, makeStyles, Toolbar, Typography } from '@material-ui/core';
import React from 'react';
import { connect } from 'react-redux';
import { Link, withRouter } from 'react-router-dom';
import { compose } from 'redux';
import SignedInLinks from './SignedInLinks';

function Navbar(props){
    const { userCredentials } = props;

    const useStyles = makeStyles((theme) => ({
        root: {
          flexGrow: 1,
        },
        title: {
          flexGrow: 1,
        },
      }));

    const classes = useStyles();

    return (
        userCredentials ?
            <div className={classes.root}>
                <AppBar position="static">
                    <Toolbar>
                        <Button component={Link} to="/" color="inherit">TodoListK</Button>
                        <Typography variant="h6" className={classes.title}/>
                        <SignedInLinks username={userCredentials.username} />
                    </Toolbar>
                </AppBar>
            </div>
            : <div/>
      );
}

const mapStateToProps = (state) => {
    return{
        userCredentials: state.auth.userCredentials,
    }
}
  
export default compose(withRouter, connect(mapStateToProps)) (Navbar)