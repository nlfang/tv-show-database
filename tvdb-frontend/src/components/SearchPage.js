import React from 'react'
import { useParams } from 'react-router-dom';
import TVShowService from '../services/TVShowService'
import ActorService from '../services/ActorService'
import DirectorService from '../services/DirectorService'

class SearchPage extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            searchQuery:this.props.match.params.searchQuery,
            searchResultsShows: [],
            searchResultsActors: [],
            searchResultsDirectors: []
        }
    }

    componentDidMount() {
        TVShowService.getTVShowSearch(this.state.searchQuery).then((res) => {
            this.setState({searchResultsShows: res.data});
            console.log(res.data);
        })

        ActorService.getActorSearch(this.state.searchQuery).then((res) => {
            this.setState({searchResultsActors: res.data});
            console.log(res.data);
        })

        DirectorService.getDirectorSearch(this.state.searchQuery).then((res) => {
            this.setState({searchResultsDirectors: res.data});
        })
    }

    render() {
        console.log(this.state.searchQuery)
        console.log(this.state.searchResultsShows)
        return(
            <div>
                <h2>TV Show Search Results: </h2>
                <div>
                    <table className="table table-bordered border-info">
                        <thead>
                            <tr>
                                <th>Show ID</th>
                                <th>Name</th>
                                <th>Length</th>
                                <th>Year of Release</th>
                                <th>Rating</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.searchResultsShows.map(
                                    tvShows =>
                                        <tr key={tvShows.showID}>
                                            <td><a href={'/tvshows/' + tvShows.showID}>{tvShows.showID}</a></td>
                                            <td>{tvShows.name}</td>
                                            <td>{tvShows.length}</td>
                                            <td>{tvShows.yearOfRelease}</td>
                                            <td>{tvShows.rating}</td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <h2>Actor Search Results: </h2>
                <div>
                    <table className="table table-bordered border-info">
                        <thead>
                            <tr>
                                <th>Actor ID</th>
                                <th>Name</th>
                                <th>DOB</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.searchResultsActors.map(
                                    actors =>
                                        <tr key={actors.actorid}>
                                            <td><a href={'/actors/' + actors.actorid}>{actors.actorid}</a></td>
                                            <td>{actors.actor_name}</td>
                                            <td>{actors.actorDOB}</td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <h2>Director Search Results: </h2>
                <div>
                    <table className="table table-bordered border-info">
                        <thead>
                            <tr>
                                <th>Director ID</th>
                                <th>Name</th>
                                <th>DOB</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.searchResultsDirectors.map(
                                    directors =>
                                        <tr key={directors.directorID}>
                                            <td><a href={'/directors/' + directors.directorID}>{directors.directorID}</a></td>
                                            <td>{directors.director_name}</td>
                                            <td>{directors.directorDOB}</td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

// for using url params
function withRouter(Component) {
    function ComponentWithRouterProp(props) {
        const params = useParams();
        return <Component {...props} match={{ params }} />;
    }
    return ComponentWithRouterProp;
}
export default withRouter(SearchPage);