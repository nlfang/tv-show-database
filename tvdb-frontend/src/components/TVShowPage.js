import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { Navigate, useLocation, useParams } from 'react-router-dom';
import TVShowService from '../services/TVShowService'

class TVShowPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            showID: this.props.match.params.showID,
            show: []
        }
    }

    componentDidMount() {
        TVShowService.getTVShow(this.state.showID).then( res => {
            this.setState({show: res.data})
        })
    }

    render() {
        console.log(this.state.show)
        return(
            <div>
                <h1>showID: {this.state.show.showID}</h1>
                <h1>name: {this.state.show.name}</h1>
                <h1>length: {this.state.show.length}</h1>
                <h1>year of release: {this.state.show.year_of_release}</h1>
                <h1>rating: {this.state.show.rating}</h1>
                <h1>director: {this.state.show.directors}</h1>
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
export default withRouter(TVShowPage);
