import axios from 'axios';

const BASE_API_URL = 'http://localhost:8888'

class DirectorService {
    
    getTopDirectorsByUser(username) {
        return axios.get(`${BASE_API_URL}/topDirectors/${username}`);
    }

}
export default new DirectorService();