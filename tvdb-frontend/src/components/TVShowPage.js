import React from 'react'
import { useParams } from 'react-router-dom';
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
                <h2>{this.state.show.name}</h2>
                <ul>
                    <li>Show ID: {this.state.show.showID}</li>
                    <li>Length: {this.state.show.length}</li>
                    <li>Year of Release: {this.state.show.year_of_release}</li>
                    <li>Rating: {this.state.show.rating}</li>
                </ul>
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
