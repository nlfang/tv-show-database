import React from 'react'
import DirectorService from '../services/DirectorService'
import { useParams } from 'react-router-dom'
import { Navbar } from 'react-bootstrap'
import { Outlet } from 'react-router-dom';

class DirectorPageComponent extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            directorID: this.props.match.params.directorID,
            director: []
        }
    }
    componentDidMount() {
        DirectorService.getDirectorByID(this.state.directorID).then((Response) => {
            this.setState({ director: Response.data });
        });
    }
    render() {
        console.log(this.state.director);
        console.log(this.state.shows);
        return (
            <div>
                <h2>{this.state.director.directorName}</h2>
                <ul>
                    <li>Director ID: {this.state.director.directorID}</li>
                    <li>Director Name: {this.state.director.directorName}</li>
                    <li>Director DOB: {this.state.director.directorDOB}</li>
                </ul>
                <Outlet />
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
export default withRouter(DirectorPageComponent);
