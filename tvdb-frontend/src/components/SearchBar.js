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

    handleSubmit = () => {
        TVShowService.getTVShows().then((res) => {
            this.setState({searchResult: res.data})
        })

        console.log("here")
    }

    // componentDidUpdate(prevProps) {
    //     if (this.props.match.params.id !== prevProps.match.params.id) {
    //         this.setState({ searchQuery: this.props.match.id })
    //     }
    // }

    render() {
        return(
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        <input 
                            type="text" 
                            placeholder="Search shows, actors, and directors"
                            value={this.state.searchQuery} 
                            onChange={this.handleInput}>
                        </input>
                    </label>
                    <Link to={`/search/${this.state.searchQuery}`}>
                        <input type="submit" value="Seach" onClick={this.handleSubmit}></input>
                    </Link>
                    {/* <input
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
                    </Link> */}
                </form>
            </div>
        );
    }
}

export default SearchBar;