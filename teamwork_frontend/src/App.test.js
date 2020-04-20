import React from "react";
import {
  render,
  act,
  wait,
  getByTestId,
} from "@testing-library/react";
import App from "./App";
import * as TodoApi from "./api/TodoApi";

describe("<App>", () => {
  const item = { id: 1, content: "Team5 homework", createAt: "2020/04/20" };

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
});