import { applyMiddleware, createStore } from "redux";
import thunk from "redux-thunk";
import { persistCredentialsMiddleware } from "./middlewares/persistCredentialsMiddleware";
import rootReducer from "./reducers/rootReducer";

export const store = createStore(rootReducer, applyMiddleware(thunk, persistCredentialsMiddleware));
