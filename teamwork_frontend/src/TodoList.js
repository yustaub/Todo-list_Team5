import React, { useState, useEffect, Fragment } from "react";
import TodoItem from "./TodoItem";
import {getTodos} from "./api/TodoApi";

const TodoList = () => {
    const [list, setList] = useState(null);
    const [error, setError] = useState("");
    const inputValue = "";

    const handleLoadTasks = () => {
      getTodos()
        .then((response) => {
          setList(response);
        })
        .catch((error) => {
          setError("Unable to retrieve todo's");
        });
    };

    useEffect(() => {
      handleLoadTasks();
    }, []);

    if (list === null) {
      return <div>Tasks is loading ...</div>;
    }
  
    if (error) {
      return <div>{error}</div>;
    }

    return (
      <Fragment>
        <div>
          <input
            className="task-input"
            type="text"
            value={inputValue}
            data-testid="task-input"
          />
         <button className="submit-button" data-testid="add-button" >
            提交
         </button>
        </div>
        <ul data-testid="task-items" className="task-items">
          {list.map((item) => (
            <TodoItem
              key={item.id}
              item={item}
              index={item.id}
            />
          ))}
        </ul>
    </Fragment>
         
    );
  };
  
export default TodoList;