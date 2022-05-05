import React from 'react'
import DirectorService from '../services/DirectorService'
import { useParams } from 'react-router-dom'
import { Navbar } from 'react-bootstrap'

class DirectorPageShowsComponent extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            directorID: this.props.match.params.directorID,
            shows: []
        }
    }
    componentDidMount() {
        DirectorService.getDirectorShows(this.state.directorID).then((Response) => {
            this.setState({ shows: Response.data });
        })
    }
    render() {
        return (
            <div>
                <table className="table table-bordered border-info">
                    <thead>
                        <tr>
                            <th>Show Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.shows.map(
                                show =>
                                    <tr>
                                        <td>{show}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
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
export default withRouter(DirectorPageShowsComponent);
