import React, { useState, useEffect } from "react";
import TodoItem from "./TodoItem";
import {getTodos} from "./api/TodoApi";

const TodoList = () => {
    const [list, setList] = useState(null);
    const [error, setError] = useState("");

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
        <ul data-testid="task-items" className="task-items">
          {list.map((item) => (
            <TodoItem
              key={item.id}
              item={item}
              index={item.id}
            />
          ))}
        </ul>    
    );
  };
  
export default TodoList;