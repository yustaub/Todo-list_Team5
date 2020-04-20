import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";

const TodoItem = ({ item, onItemUpdate, onItemDelete }) => {
  const [isEditable, setIsEditable] = useState(false);
  const [todoItem, setTodoItem] = useState(item);

  return (
    <li className="task-item" id={"task-item-" + todoItem.id} data-testid="task-item">
      <TextField
        value={todoItem.content}
        disabled={!isEditable}
        multiline
        onChange={(e) => setTodoItem({ ...item, content: e.target.value })}
        onBlur={() => {
          onItemUpdate(todoItem);
          setIsEditable(false);
        }}
        margin="dense"
        data-testid="task-item-content"
      />
      <button
        className="text-button edit-button"
        onClick={() => setIsEditable(!isEditable)}
        data-testid="edit-button"
      >
        Edit
      </button>
      <button
        className="text-button delete-button"
        onClick={() => onItemDelete(item.id)}
        data-testid="delete-button"
      >
        Delete
      </button>
    </li>
  );
};

export default TodoItem;
