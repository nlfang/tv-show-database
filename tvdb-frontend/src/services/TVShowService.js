import axios from 'axios';
const TVSHOW_REST_API_URL = 'http://localhost:8888/tvshows'
class TVShowService {
    getTVShows() {
        return axios.get(TVSHOW_REST_API_URL);
    }

    getFavorites(username, sort, asc) {
        return axios.get(`http://localhost:8888/favorites/${username}/${sort}/${asc}`)
    }
}
export default new TVShowService();