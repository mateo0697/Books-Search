import { useSelector, useDispatch} from "react-redux";
import { useState, useEffect } from "react";
import { Link, Redirect } from "react-router-dom";
import {getBooks, editBook, addBook} from "../../Redux/Actions.js"
import {FormGeneral} from "./EditBook.js"

function EditBook(props) {
  const dispatch = useDispatch()
  const book = useSelector(store => store.book)
  const [err, setErr] = useState("")
  const [submit, setSubmit] = useState(false)
  const [editedBook, setEditedBook] = useState({});
  const [write, setWrite] = useState({
    day:"",
    month:"",
    year:""
  })
  useEffect(async ()=>{
    let b = await dispatch(getBooks({id:props.match.params.id, string:"book"}))
    setEditedBook(b.payload[0])
    let date = b.payload[0].write.split("-")
    setWrite({day:date[2], month:date[1], year:date[0]})
  },[])

  function setDate({day, month, year}){
    return `${year}-${month}-${day}`
  }

  async function handleSubmit(e){
    console.log(editedBook)
    e.preventDefault();
    let hola = await editBook({...editedBook, write:setDate(write)})
    if (!hola.state) {
      setErr(hola.message)
      return
    }
    setSubmit(true)
}

function handleChange(e){
    const prop = e.target.name
    const texto =  e.target.value
    if (prop[0] === "w") {
      setWrite(old => ({...old, [prop.slice(1)]:texto}))
      return
    }
    console.log("" === texto)
    if (texto === "") {
      setEditedBook(old => ({...old, [prop]: null}))
      return
    }
    setEditedBook(old => ({...old, [prop]: texto}))
    setErr("")
    }

  return submit?<Redirect to="/home"/> : (
    <>
    <Link to="/home">
      Back
    </Link>
    <FormGeneral onSubmit={(e)=>handleSubmit(e)}>
      <div>
        <label>Title:</label>
          <br/>
        <input value={editedBook.title} name="title" onChange={(e)=>handleChange(e)} placeholder="(only leters)"/>
      </div>
      <div>
        <label>Author:</label>
          <br/>
        <input value={editedBook.author} name="author" onChange={(e)=>handleChange(e)} placeholder="(only leters)"/>
      </div>
      <div>
        <label>Price:</label>
          <br/>
        <input value={editedBook.price} name="price" onChange={(e)=>handleChange(e)} placeholder="(only numbers)"/>
      </div>
      <div>
        <label>Publication date:</label>
          <br/>
        <input style={{width:"2em", textAlign: "center"}} value={write.day} name="wday" onChange={(e)=>handleChange(e)} placeholder="Day(numbers)"/>
        <input style={{width:"2em", textAlign: "center"}} value={write.month} name="wmonth" onChange={(e)=>handleChange(e)} placeholder="Month"/>
        <input style={{width:"4em", textAlign: "center"}} value={write.year} name="wyear" onChange={(e)=>handleChange(e)} placeholder="Year"/>
        </div>
      <button>Edit book</button>
      <div>{err}</div>
    </FormGeneral>
    </>
  );
}

export default EditBook;
