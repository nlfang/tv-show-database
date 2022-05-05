import './App.css';
import TVShowComponent from './components/TVShowComponent';
import UserComponent from './components/UserComponent';
import ActorComponent from './components/ActorComponent';
import ActorPageComponent from './components/ActorPageComponent';
import ActorPageShowsComponent from './components/ActorPageShowsComponent';
import DirectorComponent from './components/DirectorComponent';
import DirectorPageComponent from './components/DirectorPageComponent';
import DirectorPageShowsComponent from './components/DirectorPageShowsComponent';
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
          <li>
            <Link to="/">Home Page</Link>
          </li>
          <li>
            <Link to="/tvshows">TV Show List</Link>
          </li>
          <li>
            <Link to="/actors">Actor List</Link>
                      </li>
                      <li>
                          <Link to="/directors">Director List</Link>
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
            <Link to="/adddetail">Add Detail</Link>
          </li>
          <li>
            <Link to={`/profile/${user || ""}`}>My Profile</Link>
          </li>
          <li>
            <SearchBar />
          </li>
        </div>
          <Routes>
            <Route path="/" element={<Home/>}>
            </Route>
            <Route path="/tvshows" element={<TVShowComponent/>}>
              <Route path=":showID" element={<TVShowPage/>}></Route>
            </Route>
                      <Route path="/actors" element={<ActorComponent />}>
                          <Route path=":actorID" element={<ActorPageComponent />}>
                              <Route path=":actorID" element={<ActorPageShowsComponent />}></Route>
                          </Route>
             </Route>
                      <Route path="/directors" element={<DirectorComponent />}>
                          <Route path=":directorID" element={<DirectorPageComponent />}>
                              <Route path=":directorID" element={<DirectorPageShowsComponent />}></Route>
                          </Route>
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
