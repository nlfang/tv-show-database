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
            actor:[]
        }
    }
    componentDidMount() {
        ActorService.getActorByID(this.state.actorID).then((Response) => {
            this.setState({ actor: Response.data })
        });
    }
    render() {
        console.log(this.state.actor);
        return (
            <div>
                <h2>{this.state.actor.actorName}</h2>
                <ul>
                    <li>Actor ID: {this.state.actor.actorID}</li>
                    <li>Actor Name: {this.state.actor.actorName}</li>
                    <li>Actor DOB: {this.state.actor.actorDOB}</li>
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
export default withRouter(ActorPageComponent);
