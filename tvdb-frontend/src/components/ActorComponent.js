import React from 'react';
import axios from 'axios';
import ActorService from '../services/ActorService';
import {Navbar} from 'react-bootstrap'

class ActorComponent extends React.Component {
    constructor(props) {
        super(props)
        this.state ={
            actors:[]
        }
    }
    componentDidMount() {
        ActorService.getActors().then((Response)=>{
            this.setState({actors:Response.data})
        });
    }
    render() {
        return(
            <div>
                <Navbar bg="info" variant="dark" >
                    <Navbar.Brand href="#">Actors</Navbar.Brand>
                </Navbar>
                <h1 className="text-center mt-5 ">List of Actors</h1>
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
            <table className="table table-bordered border-info">
                <thead>
                    <tr>
                    <th>Actor ID</th>
                    <th>Name</th>
                    <th>Date of Birth</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        this.state.actors.map(
                            actors =>
                                <tr key = {actors.actorID}>
                                        <td>{actors.actorID}</td>
                                        <td>{actors.actorName}</td>
                                        <td>{actors.actorDOB}</td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
            </div>
            </div>
        )
    }
}
export default ActorComponent