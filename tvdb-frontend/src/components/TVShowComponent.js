import React from 'react'
import TVShowService from '../services/TVShowService'
import {Navbar} from 'react-bootstrap'
import {Outlet} from 'react-router-dom'

class TVShowComponent extends React.Component {
    constructor(props) {
        super(props)
        this.state ={
            tvShows:[]
        }
    }
    componentDidMount() {
        TVShowService.getTVShows().then((Response)=>{
            this.setState({tvShows:Response.data})
        });
    }
    render() {
        return(
            <div>
                <Navbar bg="info" variant="dark" >
                    <Navbar.Brand href="#">TV Shows</Navbar.Brand>
                </Navbar>
                <h1 className="text-center mt-5 ">List of Shows</h1>
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
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
                        this.state.tvShows.map(
                            tvShows =>
                                <tr key = {tvShows.showID}>
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
            <Outlet />
            </div>
        )
    }
}
export default TVShowComponent