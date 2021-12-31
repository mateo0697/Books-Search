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
    let devolution = await dispatch(getBooks({id:props.match.params.id, string:"book"}))
    if (devolution.payload.state) {
      setEditedBook(devolution.payload.books[0])
      let date = devolution.payload.books[0].write.split("-")
      setWrite({day:date[2], month:date[1], year:date[0]})
    }else {
      setErr(devolution.payload.message)
    }

  },[])

  function setDate({day, month, year}){
    return `${day}-${month}-${year}`
  }

  async function handleSubmit(e){
    e.preventDefault();
    let devolution = await editBook({...editedBook, write:setDate(write)})
    if (!devolution.state) {
      setErr(devolution.message)
      return
    }
    dispatch(getBooks())
    setSubmit(true)
}

function handleChange(e){
    const prop = e.target.name
    const texto =  e.target.value
    if (prop[0] === "w") {
      setWrite(old => ({...old, [prop.slice(1)]:texto}))
      return
    }
    if (texto === "") {
      setEditedBook(old => ({...old, [prop]: null}))
      return
    }
    setEditedBook(old => ({...old, [prop]: texto}))
    setErr("")
    }

  return submit?<Redirect to="/home"/> : (
    <>
    <Link to="/home" style={{textDecoration:"none", color:"black"}}>
      Back
    </Link>
    <h3 style={{marginLeft:"0.5em", marginTop:"0.5em"}}>Edit Book</h3>
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
          <label style={{marginLeft:"0.6em"}}>Day:</label><input style={{width:"2em", textAlign: "center", marginLeft:"0.2em"}} value={write.day} name="wday" onChange={(e)=>handleChange(e)} />
          <label style={{marginLeft:"0.3em"}}>Month:</label><input style={{width:"2em", textAlign: "center", marginLeft:"0.2em"}} value={write.month} name="wmonth" onChange={(e)=>handleChange(e)} />
          <label style={{marginLeft:"0.3em"}}>Year:</label><input style={{width:"4em", textAlign: "center", marginLeft:"0.2em"}} value={write.year} name="wyear" onChange={(e)=>handleChange(e)} />
        </div>
      <button style={{textDecoration:"none", color:"black", backgroundColor:"white", border:"solid", borderRadius:"1em", borderColor:"black"}}>Edit book</button>
      <div>{err}</div>
    </FormGeneral>
    </>
  );
}

export default EditBook;
