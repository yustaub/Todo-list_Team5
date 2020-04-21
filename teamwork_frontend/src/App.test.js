import React from "react";
import {
  render,
  act,
  wait,
  getByTestId,
  fireEvent,
  getAllByTestId,
} from "@testing-library/react";
import App from "./App";
import * as TodoApi from "./api/TodoApi";

describe("<App>", () => {
  const item = { id: 1, content: "Team5 homework", createAt: "2020/04/20" };
  const addedItem = { id: 2, content: "Team5 sing", createAt: "2020/04/20" };
  const updateItem = { id: 1, content: "Update Item", createAt: "2020/04/21" };


  beforeEach(() => {
    jest
      .spyOn(TodoApi, "getTodos")
      .mockImplementation(() => Promise.resolve([item]));
  });

  test("should display todo items correctly", async () => {
    await act(async () => {
      render(<App />);
    });

    expect(getByTestId(document.body, "task-item")).toHaveTextContent(
      "Team5 homework"
    );
  });

  test("should add todo item correctly", async () => {
    jest
      .spyOn(TodoApi, "addTodo")
      .mockImplementation(() => Promise.resolve(addedItem));
    
    await act(async () => {
      render(<App />);
    });

    act(() => {
      fireEvent.change(getByTestId(document.body, "task-input"), {
        target: { value: addedItem.content },
      });
    });

    act(() => {
      fireEvent.click(getByTestId(document.body, "add-button"));
    });
    await wait(() => expect(TodoApi.addTodo).toHaveBeenCalled());

    const taskItems = getAllByTestId(document.body, "task-item");
    expect(taskItems.length).toEqual(2);
    expect(taskItems[1]).toHaveTextContent(addedItem.content);
  });

  test("should delete todo item correctly", async () => {
    jest
      .spyOn(TodoApi, "deleteTodo")
      .mockImplementation(() => Promise.resolve({}));


    await act(async () => {
      render(<App />);
    });

    act(() => {
      fireEvent.click(getByTestId(document.body, "delete-button"));
    });
    await wait(() => expect(TodoApi.deleteTodo).toHaveBeenCalled());
    expect(getByTestId(document.body, "task-items")).toBeEmpty();
  });

  test("should edit todo item correctly", async () => {
    jest
      .spyOn(TodoApi, "updateTodo")
      .mockImplementation(() => Promise.resolve(updateItem));

    await act(async () => {
      render(<App />);
    });

    const textarea = document.querySelector("li textarea");
    act(() => {
      fireEvent.click(getByTestId(document.body, "edit-button"));
      fireEvent.change(textarea, {
        target: { value: updateItem.content },
      });
      fireEvent.blur(textarea);
    });

    await wait(() => expect(TodoApi.updateTodo).toHaveBeenCalled());
    expect(textarea.value).toEqual(updateItem.content);
  });

});