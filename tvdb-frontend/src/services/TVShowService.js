import axios from 'axios';
const TVSHOW_REST_API_URL = 'http://localhost:8888/tvshows'
class TVShowService {
    getTVShows() {
        return axios.get(TVSHOW_REST_API_URL);
    }

    getTVShow(showID) {
        return axios.get(`${TVSHOW_REST_API_URL}/tvshows/${showID}`)
    }

    getTVShowSearch(searchQuery) {
        return axios.get(`${TVSHOW_REST_API_URL}/search/${searchQuery}`)
    }

    getFavorites(username, sort, asc) {
        return axios.get(`http://localhost:8888/favorites/${username}/${sort}/${asc}`)
    }

    getPopularTVShows() {
        return axios.get('http://localhost:8888/tvshowspopular');
    }
}
export default new TVShowService();