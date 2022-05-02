import React from 'react'
import TVShowService from '../services/TVShowService'
import {Navbar} from 'react-bootstrap'

class Home extends React.Component {
    constructor(props) {
        super(props)
        this.state ={
            tvShows:[]
        }
    }

    componentDidMount() {
        TVShowService.getPopularTVShows().then((Response)=>{
            this.setState({tvShows:Response.data})
        });
    }

    render() {
        return(
            <div>
                <Navbar bg="info" variant="dark" >
                    <Navbar.Brand href="#">TV Shows</Navbar.Brand>
                </Navbar>
                <h1 className="text-center mt-5 ">Welcome to the TV Show Database!</h1>
                <h2>Popular Shows</h2>
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
            <table>
                <thead>
                    <tr>
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
            </div>
        )
    }
}

export default Home;
