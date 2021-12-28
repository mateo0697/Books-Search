import { useSelector, useDispatch} from "react-redux";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import {getBooks, deleteBook} from "../../Redux/Actions.js"
function Home() {
  const prueba = useSelector(store=>store.books)
  const dispatch = useDispatch()
  useEffect(()=>{
      dispatch(getBooks());
  },[])

  async function deleteB(id){
    await deleteBook(id)
    dispatch(getBooks())
  }
  return (
    <>
    <Link to="/addbook">Add New Book</Link>
     {prueba.map(book => {
      return (
        <div>
        <label key={book.id}>{book.title}</label>
        <Link to={`/editbook/${book.id}`}>Edit</Link>
        <button onClick={()=>deleteB(book.id)}>Delete</button>
        </div>
      )
     })}
    </>
  );
}

export default Home;
