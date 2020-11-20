import React from "react";
import StateProvider from "../components/wrappers/StateProvider";
import KeyStrokeHandler from "../components/wrappers/KeyStrokeHandler";
import TodoList from "../components/ui/TodoList";
import '../assets/style/index.css';

class HomePage extends React.Component {
    render() {
        return (
            <StateProvider>
                <KeyStrokeHandler>
                    <TodoList/>
                </KeyStrokeHandler>
            </StateProvider>
        );
    }
}

export default HomePage;