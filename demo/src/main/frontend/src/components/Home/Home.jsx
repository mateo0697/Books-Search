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
             <DivBooks key={book.id}>
              <DivButtons>
                <Link style={{textDecoration:"none", color:"black", backgroundColor:"white", border:"solid", borderRadius:"1em", borderColor:"black", paddingTop:"0.25em", paddingBottom:"0.25em", paddingLeft:"0.5em", paddingRight:"0.5em"}} to={`/editbook/${book.id}`}>Edit</Link>
                <button style={{textDecoration:"none", color:"black", backgroundColor:"white", border:"solid", borderRadius:"1em", borderColor:"black"}}onClick={()=>deleteB(book.id)}>Delete</button>
              </DivButtons>
              <div style={{marginTop:"0.5em"}}>
                <div>Title: {book.title}</div>
                <div>Author: {book.author}</div>
                <div>Price: {book.price}</div>
                <div>Date of Publication: {book.write}</div>
              </div>
             </DivBooks>
           )
          })}
    </DivHome>
    </>
  );
}

export default Home;
