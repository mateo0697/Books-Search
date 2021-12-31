
const initialState = {
  books:[],
  book:{}
}

const todos = (state = initialState, action) => {
  switch (action.type) {
    case "GET_BOOKS":
      console.log(action)
      return ({...state, [action.where]:action.payload.books})

    default:
      return state

  }
}

export default todos;
