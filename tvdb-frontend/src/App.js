import './App.css';
import TVShowComponent from './components/TVShowComponent';
import UserComponent from './components/UserComponent';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './components/Home';
import AddTVShowComponent from './components/AddTVShowComponent';


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home/>}>
          </Route>
          <Route path="/tvshows" element={<TVShowComponent/>}>
          </Route>
          <Route path="/users" element={<UserComponent/>}>
          </Route>
          <Route path="/addtvshow" element={<AddTVShowComponent/>}>

          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
