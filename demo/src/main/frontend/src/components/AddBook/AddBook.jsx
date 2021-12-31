import { useSelector, useDispatch} from "react-redux";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import {addBook} from "../../Redux/Actions.js"
import {FormGeneral} from "./AddBook.js"

function AddBook() {
  const [err, setErr] = useState("")
  const [book, setBook] = useState({
    title:null,
    author:null,
    price:undefined,
  })
  const [write, setWrite] = useState({
    day:"",
    month:"",
    year:""
  })

  function setDate({day, month, year}){
    return `${year}-${month}-${day}`
  }
  async function handleSubmit(e){
    e.preventDefault();
    let hola = await addBook({...book, write:setDate(write)})
    if (!hola.state) {
      setErr(hola.message)
      return
    }
    setErr("")
    setBook({
      title:null,
      author:null,
      price:undefined,
    })
    setWrite({
      day:"",
      month:"",
      year:""
    })
}

function handleChange(e){
    const prop = e.target.name
    const texto =  e.target.value
    if (prop[0] === "w") {
      setWrite(old => ({...old, [prop.slice(1)]:texto}))
      return
    }
    setBook(old => ({...old, [prop]: texto}))
    setErr("")
    }

  return (
    <>
      <Link to="/home">
        Back
      </Link>
      <FormGeneral onSubmit={(e)=>handleSubmit(e)}>
      <div>
        <label>Title:</label>
        <br/>
        <input style={{marginLeft:"0.6em", marginTop:"0.2em"}} value={book.title} name="title" onChange={(e)=>handleChange(e)} placeholder="(only leters)"/>
      </div>
      <div>
        <label>Author:</label>
        <br/>
        <input style={{marginLeft:"0.6em", marginTop:"0.2em"}} value={book.author} name="author" onChange={(e)=>handleChange(e)} placeholder="(only leters)"/>
      </div>
      <div>
        <label>Price:</label>
        <br/>
        <input style={{marginLeft:"0.6em", marginTop:"0.2em"}} value={book.price} name="price" onChange={(e)=>handleChange(e)} placeholder="(only numbers)"/>
      </div>
      <div>
        <label>Write:</label>
        <br/>
        <label style={{marginLeft:"0.6em"}}>Day:</label><input style={{width:"2em", textAlign: "center", marginLeft:"0.2em"}} value={write.day} name="wday" onChange={(e)=>handleChange(e)} />
        <label style={{marginLeft:"0.3em"}}>Month:</label><input style={{width:"2em", textAlign: "center", marginLeft:"0.2em"}} value={write.month} name="wmonth" onChange={(e)=>handleChange(e)} />
        <label style={{marginLeft:"0.3em"}}>Year:</label><input style={{width:"4em", textAlign: "center", marginLeft:"0.2em"}} value={write.year} name="wyear" onChange={(e)=>handleChange(e)} />
      </div>
        <button>Add book</button>
      </FormGeneral>
      <label>{err}</label>
    </>
  );
}

export default AddBook;
