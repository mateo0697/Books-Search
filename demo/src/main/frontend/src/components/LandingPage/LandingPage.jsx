import { useSelector, useDispatch} from "react-redux";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function LandingPage() {

  return (
    <div style={{position:"relative", display:"flex", alingItems:"center"}}>
      <Link to="/home" style={{position:"absolute", textDecoration:"none", color:"white", marginLeft:"9.5em", fontSize:"6em", marginTop:"1.75em"}}>
         Book Search
      </Link>
        <img alt="Fotos de perritos" style={{width: "100%" ,height:"100%"}} src="https://fondosmil.com/fondo/1674.jpg"/>
    </div>
  );
}

export default LandingPage;
