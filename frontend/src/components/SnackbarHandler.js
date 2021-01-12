import {Snackbar} from "@material-ui/core";
import MuiAlert from '@material-ui/lab/Alert';
import {useDispatch, useSelector} from "react-redux";
import {closeSnackbar} from "../store/actions/snackbarActions";

const SnackbarHandler = () => {
    const dispatch = useDispatch()
    const { isSnackbarOpen, message, severity } = useSelector((state) => state.snackbar);

    function Alert(props) {
        return <MuiAlert elevation={6} variant="filled" {...props} />;
    }

    const handleClose = () => {
        dispatch(closeSnackbar())
    };

    return (
        <div>
            <Snackbar open={isSnackbarOpen} autoHideDuration={6000} onClose={handleClose}>
                <Alert onClose={handleClose} severity={severity}>
                    {message}
                </Alert>
            </Snackbar>
        </div>
    );
}

export default SnackbarHandler;