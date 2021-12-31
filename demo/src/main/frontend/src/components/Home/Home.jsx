import { useSelector, useDispatch} from "react-redux";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import {getBooks, deleteBook} from "../../Redux/Actions.js"
import {DivHome, DivBooks, DivButtons} from "./Home.js"

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
    <DivHome>
    {prueba.map(book => {
     return (
       <DivBooks>
        <DivButtons>
          <Link style={{textDecoration:"none", color:"black"}} to={`/editbook/${book.id}`}>Edit</Link>
          <button onClick={()=>deleteB(book.id)}>Delete</button>
        </DivButtons>
        <div style={{marginTop:"0.5em"}}>
          <div key={book.id}>Title: {book.title}</div>
          <div key={book.id}>Author: {book.author}</div>
          <div key={book.id}>Price: {book.price}</div>
          <div key={book.id}>Date of Publication: {book.write}</div>
        </div>
       </DivBooks>
     )
    })}
    </DivHome>
    </>
  );
}

export default Home;
