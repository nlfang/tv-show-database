import React from 'react'
import ActorService from '../services/ActorService'
import { useParams } from 'react-router-dom'
import { Navbar } from 'react-bootstrap'
import { Outlet } from 'react-router-dom';

class ActorPageComponent extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            actorID: this.props.match.params.actorID,
            actor: []
        }
    }
    componentDidMount() {
        ActorService.getActorByID(this.state.actorID).then((Response) => {
            console.log(Response.data)
            this.setState({ actor: Response.data })
        });
    }
    render() {
        console.log(this.state.actor);
        return (
            <div>
                <h2>{this.state.actor.actorName}</h2>
                <ul>
                    <li>Actor ID: {this.state.actor.map(actor => actor.actorID)} </li>
                    <li>Actor Name: {this.state.actor.map(actor => actor.actorName)}</li>
                    <li>Actor DOB: {this.state.actor.map(actor => actor.actorDOB)}</li>
                </ul>
                <table className="table table-bordered border-info">
                    <thead>
                        <tr>
                            <th>Show</th>
                            <th>Character Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.actor.map(
                                actor =>
                                    <tr>
                                        <td>{actor.actsName}</td>
                                        <td>{actor.charName}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
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
export default withRouter(ActorPageComponent);