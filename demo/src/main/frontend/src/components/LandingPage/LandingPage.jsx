import { useSelector, useDispatch} from "react-redux";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function LandingPage() {

  return (
    <>
      <Link to="/home">
        Busca tu libro favorito
      </Link>
    </>
  );
}

export default LandingPage;
