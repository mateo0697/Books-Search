import axios from "axios";

export function getBooks(info = {id:"", string:"books"}){
  return function (dispatch){
    return(
      axios.get(`http://localhost:8080/?id=${info.id}`)
        .then(r => r.data)
        .then(r => dispatch({type:"GET_BOOKS", payload: r, where:info.string }))
    )
  }
}

export function addBook(book){
  return(
    axios.post("http://localhost:8080/", book)
      .then(r => r.data)
      .then(r => r)
  )
}

export function editBook(book){
  return(
    axios.put("http://localhost:8080/", book)
    .then(r => r.data)
    .then(r =>r)
  )
}

export function deleteBook(id){
  return(
    axios.delete(`http://localhost:8080/${id}`)
    .then(r => r.data)
    .then(r =>r)
  )
}