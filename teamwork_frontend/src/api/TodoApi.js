import {doRequest} from './BaseApi'

const TODO_PATH = "/api/tasks";

export const getTodos = () => {
    return doRequest(TODO_PATH)
    .then(response => response.json());
}

export const  addTodo = (todo) => {
    return null;
}

export const deleteTodo = (todoId) => {	
    return {}
}