import axios from 'axios';

const BASE_API_URL = 'http://localhost:8888'

class ActorService {
    
    getTopActorsByUser(username) {
        return axios.get(`${BASE_API_URL}/topActors/${username}`);
    }

    getActors() {
        return axios.get(`${BASE_API_URL}/actors`);
    }

    getActorSearch(searchQuery) {
        return axios.get(`${BASE_API_URL}/actors/search/${searchQuery}`);
    }

}
export default new ActorService();