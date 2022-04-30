import './App.css';
import TVShowComponent from './components/TVShowComponent';
import UserComponent from './components/UserComponent';
import {BrowserRouter, Routes, Route, Link} from 'react-router-dom';
import Home from './components/Home';
import AddTVShowComponent from './components/AddTVShowComponent';
import LogInForm from './components/LogInForm';
import ProfilePage from './components/ProfilePage';
import React, {useState} from 'react';

import { userContext } from './components/userContext';
import SignUpForm from './components/SignUpForm';

function App() {
  const [user, setUser] = useState(null);

  
  return (
    <div className="App">
      <userContext.Provider value={{ user, setUser }}>
        <BrowserRouter>
        <div>
          <li>
            <Link to="/">Home Page</Link>
          </li>
          <li>
            <Link to="/tvshows">TV Show List</Link>
          </li>
          <li>
            <Link to="/users">User List</Link>
          </li>
          <li>
            <Link to="/login">Login</Link>
          </li>
          <li>
            <Link to="/signup">Sign Up</Link>
          </li>
          <li>
            <Link to="/addtvshow">Add TVShow</Link>
          </li>
          <li>
            <Link to={`/profile/${user || ""}`}>My Profile</Link>
          </li>
        </div>
          <Routes>
            <Route path="/" element={<Home/>}>
            </Route>
            <Route path="/tvshows" element={<TVShowComponent/>}>
              <Route path=":showID" element={<TVShowPage/>}/>
            </Route>
            <Route path="/users" element={<UserComponent/>}>
            </Route>
            <Route path="/addtvshow" element={<AddTVShowComponent/>}>
            </Route>
            <Route path="/login" element={<LogInForm/>}></Route>
            <Route path="/signup" element={<SignUpForm/>}></Route>
            <Route path="/profile">
              <Route path=":username" element={<ProfilePage/>}/>
            </Route>
          </Routes>
        </BrowserRouter>
      </userContext.Provider>
    </div>
  );
  
}

export default App;
