import React from 'react';
import TodoListHeader from './TodoListHeader'
import TodoList from './TodoList'
import TodoFooter from './TodoFooter'
import "./App.css"

function App() {
  return (
      <div className="App">
        <TodoListHeader />
        <TodoList/>
        <TodoFooter/>
      </div>
  );
}

export default App;
