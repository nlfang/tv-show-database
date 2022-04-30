import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { Navigate, useLocation, useParams } from 'react-router-dom';
import TVShowService from '../services/TVShowService'

class TVShowPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            showID: this.props.match.params.showID,
            show: {}
        }
    }

    componentDidMount() {
        TVShowService.getTVShow(this.state.showID).then( res => {
            this.setState({show: res.data})
        })
    }

    render() {
        return(
            <div>
                <h1>HELLO</h1>
                <div> {this.state.show.showName} </div>
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
