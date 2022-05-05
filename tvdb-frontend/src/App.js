import './App.css';
import TVShowComponent from './components/TVShowComponent';
import UserComponent from './components/UserComponent';
import ActorComponent from './components/ActorComponent';
import {BrowserRouter, Routes, Route, Link} from 'react-router-dom';
import Home from './components/Home';
import AddDetail from './components/AddDetail';
import LogInForm from './components/LogInForm';
import ProfilePage from './components/ProfilePage';
import TVShowPage from "./components/TVShowPage";
import SearchBar from './components/SearchBar';
import SearchPage from './components/SearchPage';
import React, {useState} from 'react';

import { userContext } from './components/userContext';
import SignUpForm from './components/SignUpForm';
import Button from 'react-bootstrap/Button';

function App() {
  const [user, setUser] = useState(null);

  return (
    <div className="App">
      <userContext.Provider value={{ user, setUser }}>
        <BrowserRouter>
        <div style={{position: 'absolute', left: 5}}>
          <h1>tv-show-database</h1>
        </div>
        <div>
          <ul>
            <li style={{display: 'inline'}}>
              <Link to="/">
                <button type="button">
                  Home Page
                </button>
              </Link>
            </li>
            <li style={{display: 'inline'}}>
              <Link to="/tvshows">
                <button type="button">
                  TV Show list
                </button>
              </Link>
            </li>
            <li style={{display: 'inline'}}>
              <Link to="/users">
                <button type="button">
                  User List
                </button>
              </Link>
            </li>
            <li style={{display: 'inline'}}>
              <Link to="/login">
                <button type="button">
                  Login
                </button>
              </Link>
            </li>
            <li style={{display: 'inline'}}>
              <Link to="/signup">
                <button type="button">
                  Sign up
                </button>
              </Link>
            </li>
            <li style={{display: 'inline'}}>
              <Link to="/adddetail">
                <button type="button">
                  Add Detail
                </button>
              </Link>
            </li>
            <li style={{display: 'inline'}}>
              <Link to={`/profile/${user || ""}`}>
                <button type="button">
                  My Profile
                </button>
              </Link>
            </li>
          </ul>
        </div>
          <Routes>
            <Route path="/" element={<Home/>}>
            </Route>
            <Route path="/tvshows" element={<TVShowComponent/>}>
              <Route path=":showID" element={<TVShowPage/>}></Route>
            </Route>
            <Route path="/actors" element={<ActorComponent/>}>
            </Route>
            <Route path="/users" element={<UserComponent/>}>
            </Route>
            <Route path="/adddetail" element={<AddDetail/>}>
            </Route>
            <Route path="/login" element={<LogInForm/>}></Route>
            <Route path="/signup" element={<SignUpForm/>}></Route>
            <Route path="/profile">
              <Route path=":username" element={<ProfilePage/>}/>
            </Route>
            <Route path="/search/:searchQuery" element={<SearchPage/>}></Route>
          </Routes>
        </BrowserRouter>
      </userContext.Provider>
    </div>
  );
  
}

export default App;
