
const initialState = {
  books:[],
  book:{}
}

const todos = (state = initialState, action) => {
  switch (action.type) {
    case "GET_BOOKS":
      return ({...state, [action.where]:action.payload})

    default:
      return state

  }
}

export default todos;
