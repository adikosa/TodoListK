import React from "react";
import M from "materialize-css";
import "materialize-css/dist/css/materialize.min.css";
import { Button, DatePicker, Modal, TimePicker } from "react-materialize";

class TodoModal extends React.Component {
  componentDidMount(){
    M.AutoInit()
  }
  render() {
    return (
      <div>
        <Modal
            actions={[
                <Button flat modal="close" node="button">Cancel</Button>,
                <Button flat modal="close" node="button">Save</Button>
            ]}

            id={this.props.id}
            >
                <div className="row">
                  <div className="col s12 input-field">
                    <input value={this.props.title} type="text"/>
                    <label htmlFor="text">Title</label>
                  </div>
                </div>
                <div className="row">
                  <div className="col s12 input-field">
                    <input value={this.props.description} type="text"/>
                    <label htmlFor="text">Description</label>
                  </div>
                </div>
                <div className="row">
                  <DatePicker label="Due date"/>
                  <TimePicker label="Due time"/>
                </div>
                <div className="row">
                  <div className="col input-field">
                    <select ref="dropdown">
                      <option value="LOW">LOW</option>
                      <option value="MEDIUM">MEDIUM</option>
                      <option value="HIGH">HIGH</option>
                    </select>
                    <label>Priority</label>
                  </div>
                </div>
        </Modal>
      </div>
    );
  }
}

export default TodoModal;
