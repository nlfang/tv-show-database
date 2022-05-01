import React from 'react'
import TVShowService from '../services/TVShowService';
import { Link } from 'react-router-dom'

class Search extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            searchQuery: "",
            searchResult: []
        }
    }

    handleInput = event => {
        this.setState({searchQuery: event.target.value })
    }

    logValue = () => {
        console.log("Searched for: " + this.state.searchQuery);
        TVShowService.getTVShows().then((res) => {
            this.setState({searchResult: res.data})
        })
    }

    render() {
        return(
            <div>
                <form action="/" method="get">
                    <label htmlFor="header-search">
                        <span className="visually-hidden">Search tv shows</span>
                    </label>
                    <input
                        type="text"
                        id="header-search"
                        placeholder="Search tv shows"
                        name="search"
                        onChange={this.handleInput}
                    />
                    <Link to={`/search/${this.state.searchQuery}`}>
                        <button 
                            type="button"
                            onClick={this.logValue}
                        >
                            Search
                        </button>
                    </Link>
                </form>
            </div>
        );
    }
}

export default Search;