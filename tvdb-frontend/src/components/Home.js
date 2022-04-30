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
    render() {
        return(
            <div>
                Home Page
            </div>
        )
    }
}
export default Home;