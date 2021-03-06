import React from 'react';
import logo from './logo.svg';
import { Counter } from './features/counter/Counter';
import './App.css';
import { Redirect, Route, Switch, useLocation } from "react-router-dom";
import LandingPage from "./components/LandingPage/LandingPage.jsx"
import Home from "./components/Home/Home.jsx"
import AddBook from "./components/AddBook/AddBook.jsx"
import EditBook from "./components/EditBook/EditBook.jsx"
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <Switch>
        <Route exact path="/" component={LandingPage}/>
        <Route path = "/home" component={Home}/>
        <Route path = "/addbook" component={AddBook}/>
        <Route path = "/editbook/:id" component={EditBook}/>
      </Switch>
    </div>
  );
}

export default App;
