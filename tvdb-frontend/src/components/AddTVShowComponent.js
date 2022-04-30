import React, { useState } from 'react'
import axcios from 'axios'
import axios from 'axios';

function AddTVShowComponent() {
    const [tvShow, setTVShow] = useState({
        name: '',
        length: '',
        year_of_release: '',
        rating: '',
        /*director: '',*/
    });

    const onInputChange = e => {
        setTVShow({ ...tvShow, [e.target.name]: e.target.value })
    }
    const { name, length, year_of_release, rating} = tvShow;

    const FormHandle = e => {
        e.preventDefault();
        addShowToServer(tvShow);
    }

    const addShowToServer = (data) => {
        
        axios.post("http://localhost:8888/tvshows", data).then(
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
                <div class="jumbotron">
                    <h1 class="display-4 text-center">Add TV Show Form</h1>
                    <div>
                        <form onSubmit={e => FormHandle(e)}>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Name</label>
                                <input type="text" class="form-control" name="name"  placeholder="Enter Here" value={name} onChange={(e) => onInputChange(e)} />
                            </div>
                            <div class="form-group">
                                <label for="exampleInputLength1">Length</label>
                                <input type="text" class="form-control" name="length"  placeholder="Enter Here" value={length} onChange={(e) => onInputChange(e)} />
                            </div>
                            <div class="form-group">
                                <label for="exampleInputYearOfRelease1">Year of Release</label>
                                <input type="text" class="form-control" name="year_of_release"  placeholder="Enter Here" value={year_of_release} onChange={(e) => onInputChange(e)} />
                            </div>
                            <div class="form-group">
                                <label for="exampleInputRating">Rating</label>
                                <input type="text" class="form-control" name="rating"  placeholder="Enter Here" value={rating} onChange={(e) => onInputChange(e)} />
                            </div>
                            <div className="container text-center">
                                <button type="submit" class="btn btn-outline-secondary my-2 text-center mr-2">Add Show</button>
                                <button type="reset" class="btn btn-outline-primary text-center mr-2">Clear Form</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    )
}


export default AddTVShowComponent