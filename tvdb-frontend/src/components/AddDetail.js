import React, { useEffect, useState } from 'react'
import axios from 'axios';

// Component to add TV Shows, Actors, and Directors to the database.
function AddDetail() {
    // Boolean consts to check for what detail is being added
    const [addTVShow, setAddTVShow] = useState(false)
    const [addActor, setAddActor] = useState(false)
    const [addDirector, setAddDirector] = useState(false)
    const [addGenre, setAddGenre] = useState(false)
    const [addActorRole, setAddActorRole] = useState(false)
    const [addDirectorRole, setAddDirectorRole] = useState(false)

    // Actual detail consts to be sent to the backend
    const [tvShow, setTVShow] = useState({
        name: '',
        length: '',
        year_of_release: '',
        rating: ''
    });

    const [actor, setActor] = useState({
        actorName: '',
        actorDOB: ''
    });

    const [director, setDirector] = useState({
        directorName: '',
        directorDOB: '',
    });

    const [genre, setGenre] = useState({
        genre_name: ''
    })

    const [actorRole, setActorRole] = useState({
        characterName: '',
        roleActorName: '',
        showName: ''
    });

    const [directorRole, setDirectorRole] = useState({
        roleDirectorName: '',
        directsShowName: ''
    });

    // Handles text input changes in the forms and sets detail consts as needed
    const onInputChange = e => {
        if (addTVShow) {
            console.log(e.target.name)
            console.log(e.target.value)
            setTVShow({ ...tvShow, [e.target.name]: e.target.value })
        } else if (addActor) {
            setActor({ ...actor, [e.target.name]: e.target.value})
        } else if (addDirector) {
            setDirector({ ...director, [e.target.name]: e.target.value})
        } else if (addGenre) {
            setGenre({ ...genre, [e.target.name]: e.target.value})
        } else if (addActorRole) {
            setActorRole({ ...actorRole, [e.target.name]: e.target.value})
        } else if (addDirectorRole) {
            setDirectorRole({ ...directorRole, [e.target.name]: e.target.value})
        }
    }

    // Consts to build detail JSON objects
    const {name, length, year_of_release, rating} = tvShow;
    const {actorName, actorDOB} = actor;
    const {directorName, directorDOB} = director;
    const {genre_name} = genre;
    const {characterName, roleActorName, showName} = actorRole;
    const {roleDirectorName, directsShowName} = directorRole;

    // Form handler to send data to backend when the submit button is pressed
    const FormHandle = e => {
        e.preventDefault();
        console.log(e)
        if (addTVShow) {
            addShowToServer(tvShow);
        } else if (addActor) {
            addActorToServer(actor);
        } else if (addDirector) {
            addDirectorToServer(director);
        } else if (addGenre) {
            addGenreToServer(genre);
        } else if (addActorRole) {
            addActorRoleToServer(actorRole);
        } else if (addDirectorRole) {
            addDirectorRoleToServer(directorRole);
        }
    }

    // Select handler for which option is chosen in the dropdown menu
    const selectChange = (e) => {
        let value = e.target.value
        if (value === "tvshow") {
            setAddTVShow(true)
            setAddActor(false)
            setAddDirector(false)
            setAddGenre(false)
            setAddActorRole(false)
            setAddDirectorRole(false)
        } else if (value === "actor") {
            setAddTVShow(false)
            setAddActor(true)
            setAddDirector(false)
            setAddGenre(false)
            setAddActorRole(false)
            setAddDirectorRole(false)
        } else if (value === "director") {
            setAddTVShow(false)
            setAddActor(false)
            setAddDirector(true)
            setAddGenre(false)
            setAddActorRole(false)
            setAddDirectorRole(false)
        } else if (value === "genre") {
            setAddTVShow(false)
            setAddDirector(false)
            setAddActor(false)
            setAddGenre(true)
            setAddActorRole(false)
            setAddDirectorRole(false)
        } else if (value === "actorRole") {
            setAddTVShow(false)
            setAddActor(false)
            setAddDirector(false)
            setAddGenre(false)
            setAddActorRole(true)
            setAddDirectorRole(false)
        } else if (value === "directorRole") {
            setAddTVShow(false)
            setAddDirector(false)
            setAddActor(false)
            setAddGenre(false)
            setAddActorRole(false)
            setAddDirectorRole(true)
        } 
    }

    // Functions to send data to the backend
    const addShowToServer = (data) => {
        axios.post("http://localhost:8888/addtvshow", data).then(
            (response) => {
                alert("Show successfully added")
            }, (error) => {
                alert("Failed to add")
            }
        );
    }

    const addActorToServer = (data) => {
        axios.post("http://localhost:8888/addactor", data).then(
            (response) => {
                alert("Actor successfully added")
            }, (error) => {
                alert("Failed to add")
            }
        );
    }

    const addDirectorToServer = (data) => {
        axios.post("http://localhost:8888/adddirector", data).then(
            (response) => {
                alert("Director successfully added")
            }, (error) => {
                alert("Failed to add")
            }
        );
    }

    const addGenreToServer = (data) => {
        axios.post("http://localhost:8888/addgenre/" + genre.genre_name).then(
            (response) => {
                alert("Genre successfully added")
            }, (error) => {
                alert("Failed to add")
            }
        );
    }

    const addActorRoleToServer = () => {
        axios.post("http://localhost:8888/addactorrole/" + actorRole.characterName + "/" + actorRole.roleActorName + "/" + actorRole.showName).then(
            (response) => {
                alert("Actor role successfully added")
            }, (error) => {
                alert("Failed to add")
            }
        );
    }

    const addDirectorRoleToServer = () => {
        axios.post("http://localhost:8888/adddirectorrole/" + directorRole.roleDirectorName + "/" + directorRole.directsShowName).then(
            (response) => {
                alert("Director role successfully added")
            }, (error) => {
                alert("Failed to add")
            }
        )
    }

    return (
        <div>
        <div className="container">
            <div className="w-75 mx-auto shadow p-5 mt-2 bg-light">
                <div className="jumbotron">
                    <h1 className="display-4 text-center">Add a Detail</h1>
                    <h2 className="display-4 text-center">Choose a detail to add...</h2>
                    <select onChange={(e) => selectChange(e)}>
                        <option value="tvshow">TV Show</option>
                        <option value="actor">Actor</option>
                        <option value="director">Director</option>
                        <option value="genre">Genre</option>
                        <option value="actorRole">Actor Role</option>
                        <option value="directorRole">Director Role</option>
                    </select>
                    <div>
                        {addTVShow &&
                        <form onSubmit={e => FormHandle(e)}>
                            <div className="form-group">
                                <label htmlFor="exampleInputEmail1">Name </label>
                                <input type="text" className="form-control" name="name"  placeholder="Enter Here" value={name} onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="exampleInputLength1">Length </label>
                                <input type="text" className="form-control" name="length"  placeholder="Enter Here" value={length} onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="exampleInputYearOfRelease1">Year of Release </label>
                                <input type="text" className="form-control" name="year_of_release"  placeholder="Enter Here" value={year_of_release} onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="exampleInputRating">Rating </label>
                                <input type="text" className="form-control" name="rating"  placeholder="Enter Here" value={rating} onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="container text-center">
                                <button type="submit" className="btn btn-outline-secondary my-2 text-center mr-2">Add Show</button>
                            </div>
                        </form>}

                        {addActor &&
                        <form onSubmit={e => FormHandle(e)}>
                            <div className="form-group">
                                <label>Name </label>
                                <input type="text" className="form-control" name="actorName" placeholder="Enter Here" value={actorName} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="form-group">
                                <label>Date of Birth </label>
                                <input type="text" className="form-control" name="actorDOB" placeholder="Enter Here" value={actorDOB} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="container text-center">
                                <button type="submit" className="btn btn-outline-secondary my-2 text-center mr-2">Add Actor</button>
                            </div>
                        </form>}

                        {addDirector &&
                        <form onSubmit={e => FormHandle(e)}>
                            <div className="form-group">
                                <label>Name </label>
                                <input type="text" className="form-control" name="directorName" placeholder="Enter Here" value={directorName} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="form-group">
                                <label>Date of Birth </label>
                                <input type="text" className="form-control" name="directorDOB" placeholder="Enter Here" value={directorDOB} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="container text-center">
                                <button type="submit" className="btn btn-outline-secondary my-2 text-center mr-2">Add Actor</button>
                            </div>
                        </form>}

                        {addGenre &&
                        <form onSubmit={e => FormHandle(e)}>
                            <div className="form-group">
                                <label>Name </label>
                                <input type="text" className="form-control" name="genre_name" placeholder="Enter Here" value={genre_name} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="container text-center">
                                <button type="submit" className="btn btn-outline-secondary my-2 text-center mr-2">Add Genre</button>
                            </div>
                        </form>}

                        {addActorRole &&
                        <form onSubmit={e => FormHandle(e)}>
                            <div className="form-group">
                                <label>Character Name </label>
                                <input type="text" className="form-control" name="characterName" placeholder="Enter Here" value={characterName} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="form-group">
                                <label>Actor Name </label>
                                <input type="text" className="form-control" name="roleActorName" placeholder="Enter Here" value={roleActorName} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="form-group">
                                <label>Show Name </label>
                                <input type="text" className="form-control" name="showName" placeholder="Enter Here" value={showName} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="container text-center">
                                <button type="submit" className="btn btn-outline-secondary my-2 text-center mr-2">Add Actor Role</button>
                            </div>
                        </form>
                        }
                        
                        {addDirectorRole &&
                        <form onSubmit={e => FormHandle(e)}>
                            <div className="form-group">
                                <label>Director Name </label>
                                <input type="text" className="form-control" name="roleDirectorName" placeholder="Enter Here" value={roleDirectorName} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="form-group">
                                <label>Show Name </label>
                                <input type="text" className="form-control" name="directsShowName" placeholder="Enter Here" value={directsShowName} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="container text-center">
                                <button type="submit" className="btn btn-outline-secondary my-2 text-center mr-2">Add Director Role</button>
                            </div>
                        </form>
                        }
                    </div>
                </div>
            </div>
        </div>
    </div>
    )
}


export default AddDetail