import React, {Component} from 'react';
import TodoList from '../ui/TodoList';
import StateProvider from './StateProvider';
import KeyStrokeHandler from './KeyStrokeHandler';
import * as TodoRepository from '../../data/repos/TodoRepository';


class App extends Component {
    render() {
        TodoRepository.getById(1).then((response) => {
            console.log(response.status)
            console.log(response.data);
        }).catch(e => {
                console.log(e)
            }
        )
        TodoRepository.getAll().then((response) => {
            console.log(response.data);
        })
        return (
            <StateProvider>
                <KeyStrokeHandler>
                    <TodoList/>
                </KeyStrokeHandler>
            </StateProvider>
        );
    }
}

export default App;
