import React from "react";
import TextField from "@material-ui/core/TextField";

const TodoItem = ({ item }) => {

  return (
    <li className="task-item" id={"task-item-" + item.id} data-testid="task-item">
      <TextField
        value={item.content}
        disabled={true}
        multiline
        data-testid="task-item-content"
      />
      <button
        className="text-button edit-button"
        data-testid="edit-button"
      >
        Edit
      </button>
      <button
        className="text-button delete-button"
        data-testid="delete-button"
      >
        Delete
      </button>
    </li>
  );
};

export default TodoItem;
