# Specification

## Board

- GET `/board` : serve page to show the list of every boards.
- GET `/board/read/:name` : show the content of the board as Markdown specified by `:name`
- GET `/board/write` : show the editor to create a new board
- POST `/board/write` : add/replace board, and redirects to the board
  - parameter `title` : the name of the board
  - parameter `content` : the Markdown content of the board
- GET `/board/edit/:name` : opens the editor to modify the content of the board specified by `:name`
- POST `/board/edit/:name` : replaces the board. (same as POST `/board/write`)
- POST `/board/delete/:name` : delete the board immediately, and shows the result.

## Food (Requires logged in)

- GET `/food` : serve page to show the list of foods.
- GET `/food/get/:id`: get single food data
- POST `/food/insert` : add food (non-idempotent)
  - parameter `name`: name of food
  - parameter `description`: simple description of the food
  - parameter `price`: proposal price of the food (KRW)
- POST `/food/delete`: delete food
  - parameter `id`: the id of the food to delete.

## User

- GET `/login`
