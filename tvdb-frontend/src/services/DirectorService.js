import axios from 'axios';

const BASE_API_URL = 'http://localhost:8888'

class DirectorService {
    
    getTopDirectorsByUser(username) {
        return axios.get(`${BASE_API_URL}/topDirectors/${username}`);
    }

    getAllDirectors() {
        return axios.get(`${BASE_API_URL}/directors`);
    }

    getDirectorByID(directorID) {
        return axios.get(`${BASE_API_URL}/directors/${directorID}`);
    }

    getDirectorShows(directorID) {
        return axios.get(`${BASE_API_URL}/directors/${directorID}/shows`);
    }

    getDirectorSearch(searchQuery) {
        return axios.get(`${BASE_API_URL}/directors/search/${searchQuery}`);
    }

}
export default new DirectorService();