import React from "react";
import TodoItem from "./TodoItem";

const TodoList = () => {
    const list = [
        {"id":1,"content":"Restful","updatedAt":"2020-04-17 20:55:08"},
        {"id":2,"content":"React","updatedAt":"2020-04-17 22:40:00"}
    ]

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