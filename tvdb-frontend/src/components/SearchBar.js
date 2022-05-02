import React from 'react'
import TVShowService from '../services/TVShowService';
import { Link } from 'react-router-dom'

class SearchBar extends React.Component {
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
                    <input
                        type="text"
                        id="header-search"
                        placeholder="Search shows, actors, and directors"
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

export default SearchBar;