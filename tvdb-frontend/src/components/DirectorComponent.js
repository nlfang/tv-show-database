import React from 'react';
import axios from 'axios';
import DirectorService from '../services/DirectorService';
import { Navbar } from 'react-bootstrap'

class DirectorComponent extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            directors: []
        }
    }
    componentDidMount() {
        DirectorService.getAllDirectors().then((Response) => {
            this.setState({ directors: Response.data })
        });
    }
    render() {
        return (
            <div>
                <Navbar bg="info" variant="dark" >
                    <Navbar.Brand href="#">Directors</Navbar.Brand>
                </Navbar>
                <h1 className="text-center mt-5 ">List of Directors</h1>
                <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                    <table className="table table-bordered border-info">
                        <thead>
                            <tr>
                                <th>Director ID</th>
                                <th>Name</th>
                                <th>Date of Birth</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.directors.map(
                                    directors =>
                                        <tr key={directors.directorID}>
                                            <td>{directors.directorID}</td>
                                            <td>{directors.directorName}</td>
                                            <td>{directors.directorDOB}</td>
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
export default DirectorComponent