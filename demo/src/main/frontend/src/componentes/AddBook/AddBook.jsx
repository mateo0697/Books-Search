import { useSelector, useDispatch} from "react-redux";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import {addBook} from "../../Redux/Actions.js"
function AddBook() {
  const [err, setErr] = useState("")
  const [book, setBook] = useState({
    title:"",
    author:"",
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
      title:"",
      author:"",
      price:0,
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
      <form onSubmit={(e)=>handleSubmit(e)}>
        <label>Title:</label>
        <input value={book.title} name="title" onChange={(e)=>handleChange(e)} placeholder="(only leters)"/>
        <br/>
        <label>Author:</label>
        <input value={book.author} name="author" onChange={(e)=>handleChange(e)} placeholder="(only leters)"/>
        <br/>
        <label>Price:</label>
        <input value={book.price} name="price" onChange={(e)=>handleChange(e)} placeholder="(only numbers)"/>
        <br/>
        <div>
        <label>Write:</label>
          {/*<label>Day:</label>*/}
            <input value={write.day} name="wday" onChange={(e)=>handleChange(e)} placeholder="Day(numbers)"/>
          {/*<label>Month:</label>*/}
            <input value={write.month} name="wmonth" onChange={(e)=>handleChange(e)} placeholder="Month"/>
          {/*<label>Year:</label>*/}
            <input value={write.year} name="wyear" onChange={(e)=>handleChange(e)} placeholder="Year"/>
        </div>
        <button>Add book</button>
      </form>
      <label>{err}</label>
    </>
  );
}

export default AddBook;
