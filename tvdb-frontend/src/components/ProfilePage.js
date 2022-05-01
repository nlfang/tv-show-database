import React, { useEffect, useState, useContext, useCallback } from 'react';
import axios from 'axios';
import { userContext } from './userContext';
import { Navigate, useLocation } from 'react-router-dom';
import UserService from '../services/UserService';
import GenreService from '../services/GenreService';
import ActorService from '../services/ActorService';
import TVShowService from '../services/TVShowService';
import DirectorService from '../services/DirectorService';


/**
 * UI for any user's profile page (can be your's or someone elses)
 * Includes access to changing credentials (if it's your profile you're viewing)
 */
const ProfilePage = props => {

    // profile page's username
    const location = useLocation();
    const [username, setUsername] = useState("");
    const [toLogin, setToLogin] = useState(false);
    useEffect(() => {
        setUsername( /[^/]*$/.exec(location.pathname)[0] || "" ); // get profile's username
        // reset cred-change hooks to defaults
        setLoadCredChange(false);
        setSort({sort: 'name', asc: 1});
        setArrow({
            title: "",
            year: "",
            length: "",
            rating: ""
        });
        setWhichForm('');
        setU1(''); setU2(''); setU3('');
        setE1(''); setE2(''); setE3('');
        setP1(''); setP2(''); setP3('');
    }, [location]);

    // for logged-in user's username
    const { ...context } = useContext(userContext);
    const [myUsername, setMyUsername] = useState("");
    useEffect(() => {
        //context.user || setToLogin(true); // UNCOMMENT TO MAKE PAGE ONLY ACCESSIBLE TO LOGGED-IN USERS
        setMyUsername(context.user);
    }, [context.user]);

    // data
    const [email, setEmail] = useState('');
    const [genres, setGenres] = useState([]);
    const [actors, setActors] = useState([]);
    const [directors, setDirectors] = useState([]);
    const [shows, setShows] = useState([]);
    useEffect(() => {
        if (username) {
            UserService.getEmailByUsername(username).then(user_res => {
                user_res.data && setEmail(user_res.data);
            });
            GenreService.getTopGenresByUser(username).then(genre_res => {
                genre_res.data && setGenres(genre_res.data);
            });
            ActorService.getTopActorsByUser(username).then(act_res => {
                act_res.data && setActors(act_res.data);
            });
            DirectorService.getTopDirectorsByUser(username).then(dir_res => {
                dir_res.data && setDirectors(dir_res.data);
            });
            TVShowService.getFavorites(username, "name", 1).then(show_res => {
                show_res.data && setShows(show_res.data);
            });
        }
    }, [context.user, username]); // update every time your username changes (for log-in status)
                                // or the profile page's username changes (to load a different user's data)
    
    // sorting by name, year, rating, length
    const [sort, setSort] = useState({sort: "name", asc: true})
    useEffect(() => { // retrieve shows sorted by 'sort' state
        if (username) {
            TVShowService.getFavorites(username, sort.sort, sort.asc ? 1 : 0).then(show_res => {
                show_res.data && setShows(show_res.data);
            });
        }
    }, [sort, username]);
    const handleSort = useCallback(col => { // function to set the 'sort' state accordingly
        if (sort.sort === col)
            setSort({ sort: col, asc: (sort.asc > 0) ? 0 : 1 });
        else
            setSort({ sort: col, asc: 1 });
    }, [sort]);
    const [arrow, setArrow] = useState({ // for the up/down arrow to display sorting
        title: "",
        year: "",
        length: "",
        rating: ""
    });
    useEffect(() => {
        const arrow = (sort.asc > 0) ? 0x25b2 : 0x25bc;
        switch (sort.sort) {
            case 'length':
                setArrow({
                    title: "",
                    year: "",
                    length: arrow,
                    rating: ""
                });
                break;
            case 'year':
                setArrow({
                    title: "",
                    year: arrow,
                    length: "",
                    rating: ""
                });
                break;
            case 'rating':
                setArrow({
                    title: "",
                    year: "",
                    length: "",
                    rating: arrow
                });
                break;
            default:
                setArrow({
                    title: arrow,
                    year: "",
                    length: "",
                    rating: ""
                });
                break;
        }
    }, [sort]);

    // change credentials stuff
    const [loadCredChange, setLoadCredChange] = useState(false);
    const [whichForm, setWhichForm] = useState('');
    const [u1, setU1] = useState(''); const [e1, setE1] = useState(''); // tracks inputs
    const [u2, setU2] = useState(''); const [e2, setE2] = useState('');
    const [u3, setU3] = useState(''); const [e3, setE3] = useState('');
    const [p1, setP1] = useState('');
    const [p2, setP2] = useState('');
    const [p3, setP3] = useState('');
    const submitCredChange = useCallback(type => {
        switch (type) {
            case 'username':
                if ( !(u1 && u2 && u3) ) { window.alert('Must fill out all fields'); return; }
                if ( u1 !== u2 ) { window.alert('Username fields do not match'); return; }
                axios.post('http://localhost:8888/login', {username: context.user, password: u3}).then(
                    (response) => {
                        UserService.changeUsername(context.user, u1).then(res => {
                            if (res.data && res.data === 'success') {
                                context.setUser(u1);
                                window.alert('success');
                                setLoadCredChange(false);
                            }
                            else {
                                window.alert('That username is taken.');
                            }
                        });
                    }, (error) => {
                        window.alert('Incorrect password');
                    }
                )
                break;
            case 'email':
                if ( !(e1 && e2 && e3) ) { window.alert('Must fill out all fields'); return; }
                if ( e1 !== e2 ) { window.alert('Email fields do not match'); return; }
                axios.post('http://localhost:8888/login', {username: context.user, password: e3}).then(
                    (response) => {
                        UserService.changeEmail(context.user, e1).then(res => {
                            if (res.data && res.data === 'success') {
                                window.alert('success');
                                setEmail(e1);
                                setLoadCredChange(false);
                            }
                            else {
                                window.alert('That email is already in use.');
                            }
                        });
                    }, (error) => {
                        window.alert('Incorrect password');
                    }
                )
                break;
            case 'password':
                if ( !(p1 && p2 && p3) ) { window.alert('Must fill out all fields'); return; }
                if ( p2 !== p3 ) { window.alert('New-password fields do not match'); return; }
                axios.post('http://localhost:8888/login', {username: context.user, password: p1}).then(
                    (response) => {
                        UserService.changePassword(context.user, p2).then(res => {
                            if (res.data && res.data === 'success') {
                                window.alert('Success.');
                            }
                            else {
                                window.alert('Failed.');
                            }
                        });
                    }, (error) => {
                        window.alert('Incorrect old password');
                    }
                )
                break;
            default:
                break;
        }
    }, [whichForm, u1, u2, u3, e1, e2, e3, p1, p2, p3, context]);
    
    if ( toLogin ) {
        return <Navigate to="/login" />;
    }
    if ( loadCredChange ) {
        return (
            <>
                <h1>Change Credentials</h1>
                <button onClick={e => setWhichForm('username')}>username</button>
                <button onClick={e => setWhichForm('email')}>email</button>
                <button onClick={e => setWhichForm('password')}>password</button>

                <div style={{margin: '20px', display: (whichForm === 'username') ? 'block' : 'none'}}>
                    <input placeholder='new username' onChange={e => setU1(e.target.value)}/>
                    <input placeholder='confirm new username' onChange={e => setU2(e.target.value)}/>
                    <input type='password' placeholder='enter password' onChange={e => setU3(e.target.value)}/>
                    <button onClick={e => submitCredChange('username')}>Submit</button>
                </div>
                <div style={{margin: '20px', display: (whichForm === 'email') ? 'block' : 'none'}}>
                    <input placeholder='new email' onChange={e => setE1(e.target.value)}/>
                    <input placeholder='confirm new email' onChange={e => setE2(e.target.value)}/>
                    <input type='password' placeholder='enter password' onChange={e => setE3(e.target.value)}/>
                    <button onClick={e => submitCredChange('email')}>Submit</button>
                </div>
                <div style={{margin: '20px', display: (whichForm === 'password') ? 'block' : 'none'}}>
                    <input type='password' placeholder='old password' onChange={e => setP1(e.target.value)}/>
                    <input type='password' placeholder='new password' onChange={e => setP2(e.target.value)}/>
                    <input type='password' placeholder='confirm new password' onChange={e => setP3(e.target.value)}/>
                    <button onClick={e => submitCredChange('password')}>Submit</button>
                </div>
            </>
        );
    }
    return (
        <div>
            {/* css */}
            <style>{`
                table {
                    margin: auto;
                }
                td, th {
                    border: 1px solid #ddd;
                    padding: 8px;
                }
                tbody:nth-child(even) {background-color: #f2f2f2;}
                th {
                    background-color: #ddd;
                }
                .clickable:hover {
                    cursor: pointer;
                    background-color: #0ff;
                }
            `}</style>

            {/* header */}
            <div style={{margin: '20px auto'}}>
                <p style={{display: 'inline'}}>Email: {email}</p>
                <h2 style={{display: 'inline', margin: '0 20px'}}>{username}'s Profile Page</h2>
                <button style={{display: 'inline', visibility: (username === context.user ? 'visible' : 'hidden')}} 
                    onClick={e => setLoadCredChange(true)}>Change Credentials</button>
            </div>

            {/* most reoccuring */}
            <table style={{margin: '20px auto'}}>
                <thead>
                    <tr>
                        <th colSpan='3'>Most Reoccurring...</th>
                    </tr>
                </thead>
                <thead>
                    <tr>
                        <th>Genres</th>
                        <th>Actors</th>
                        <th>Directors</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{genres[0] ? genres[0].genre_name + ' - ' + genres[0].count : '-'}</td>
                        <td>{actors[0] ? actors[0].actorName + ' - ' + actors[0].count : '-'}</td>
                        <td>{directors[0] ? directors[0].directorName + ' - ' + directors[0].count : '-'}</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                        <td>{genres[1] ? genres[1].genre_name + ' - ' + genres[1].count : '-'}</td>
                        <td>{actors[1] ? actors[1].actorName + ' - ' + actors[1].count : '-'}</td>
                        <td>{directors[1] ? directors[1].directorName + ' - ' + directors[1].count : '-'}</td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                        <td>{genres[2] ? genres[2].genre_name + ' - ' + genres[2].count : '-'}</td>
                        <td>{actors[2] ? actors[2].actorName + ' - ' + actors[2].count : '-'}</td>
                        <td>{directors[2] ? directors[2].directorName + ' - ' + directors[2].count : '-'}</td>
                    </tr>
                </tbody>
            </table>

            {/* favorite shows */}
            <table style={{margin: '20px auto'}}>
                <thead>
                    <tr>
                        <th colSpan='5'>Favorite Shows</th>
                    </tr>
                </thead>
                <thead>
                    <tr>
                        <th className="clickable" onClick={e => handleSort('name')}>
                            {arrow.title && String.fromCharCode(arrow.title)}Title</th>
                        <th className="clickable" onClick={e => handleSort('year')}>
                            {arrow.year && String.fromCharCode(arrow.year)}Release Year</th>
                        <th className="clickable" onClick={e => handleSort('length')}>
                            {arrow.length && String.fromCharCode(arrow.length)}Length (# eps)</th>
                        <th className="clickable" onClick={e => handleSort('rating')}>
                            {arrow.rating && String.fromCharCode(arrow.rating)}Rating/5</th>
                        <th>Genre(s)</th>
                    </tr>
                </thead>
                {
                    shows.map(show => (
                        <tbody key={show.showID}>
                            <tr>
                                <td>{show.name}</td>
                                <td>{show.year_of_release}</td>
                                <td>{show.length}</td>
                                <td>{show.rating}</td>
                                <td>{show.genres}</td>
                            </tr>
                        </tbody>
                    ))
                }
            </table>

        </div>
    );

}

export default ProfilePage;