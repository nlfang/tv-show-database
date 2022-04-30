import React, { useState } from 'react'
import axios from 'axios';

// Component to add TV Shows, Actors, and Directors to the database.
function AddDetail() {
    const [addTVShow, setAddTVShow] = useState(false)
    const [addActor, setAddActor] = useState(false)
    const [addDirector, setAddDirector] = useState(false)

    const [tvShow, setTVShow] = useState({
        name: '',
        length: '',
        year_of_release: '',
        rating: ''
    });

    const [actor, setActor] = useState({
        actorName: '',
        actorDOB: '',
    });

    const [director, setDirector] = useState({
        directorName: '',
        directorDOB: '',
        tvshows: ''
    });

    const onInputChange = e => {
        if (addTVShow) {
            console.log(e.target.name)
            console.log(e.target.value)
            setTVShow({ ...tvShow, [e.target.name]: e.target.value })
        } else if (addActor) {
            setActor({ ...actor, [e.target.name]: e.target.value})
        } else if (addDirector) {
            setDirector({ ...director, [e.target.name]: e.target.value})
        }
    }
    const {name, length, year_of_release, rating} = tvShow;
    const {actorName, actorDOB} = actor;
    const {directorName, directorDOB, tvshows} = director;

    const FormHandle = e => {
        e.preventDefault();
        console.log(e)
        if (addTVShow) {
            addShowToServer(tvShow);
        } else if (addActor) {
            addActorToServer(actor);
        } else if (addDirector) {
            addDirectorToServer(director);
        }
        
    }

    const selectChange = (e) => {
        let value = e.target.value

        if (value === "--Choose one--") {
            setAddTVShow(false)
            setAddDirector(false)
            setAddActor(false)
        } else if (value === "tvshow") {
            setAddTVShow(true)
            setAddActor(false)
            setAddDirector(false)
        } else if (value === "actor") {
            setAddActor(true)
            setAddTVShow(false)
            setAddDirector(false)
        } else if (value === "director") {
            setAddDirector(true)
            setAddTVShow(false)
            setAddActor(false)
        }
    }

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
                alert("Show successfully added")
            }, (error) => {
                alert("Failed to add")
            }
        );
    }

    const addDirectorToServer = (data) => {
        axios.post("http://localhost:8888/directors", data).then(
            (response) => {
                alert("Show successfully added")
            }, (error) => {
                alert("Failed to add")
            }
        );
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
                                <button type="reset" className="btn btn-outline-primary text-center mr-2">Clear Form</button>
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
                            {/* <div className="form-group">
                                <label>Acts In: (separate by commas) </label>
                                <input type="text" className="form-control" name="actsIns" placeholder="Enter Here" value={actsIns} onChange={(e) => onInputChange(e)}/>
                            </div> */}
                            <div className="container text-center">
                                <button type="submit" className="btn btn-outline-secondary my-2 text-center mr-2">Add Actor</button>
                                <button type="reset" className="btn btn-outline-primary text-center mr-2">Clear Form</button>
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
                            <div className="form-group">
                                <label>TV Shows: (separate by commas) </label>
                                <input type="text" className="form-control" name="tvshows" placeholder="Enter Here" value={tvshows} onChange={(e) => onInputChange(e)}/>
                            </div>
                            <div className="container text-center">
                                <button type="submit" className="btn btn-outline-secondary my-2 text-center mr-2">Add Actor</button>
                                <button type="reset" className="btn btn-outline-primary text-center mr-2">Clear Form</button>
                            </div>
                        </form>}
                    </div>
                </div>
            </div>
        </div>
    </div>
    )
}


export default AddDetail