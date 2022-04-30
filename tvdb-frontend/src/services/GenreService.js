import axios from 'axios';

const BASE_API_URL = 'http://localhost:8888'

class GenreService {
    
    getTopGenresByUser(username) {
        return axios.get(`${BASE_API_URL}/topGenres/${username}`);
    }

}
export default new GenreService();