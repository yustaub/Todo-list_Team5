import React from 'react';
import TodoListHeader from './TodoListHeader'
import TodoList from './TodoList'
import TodoFooter from './TodoFooter'

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
