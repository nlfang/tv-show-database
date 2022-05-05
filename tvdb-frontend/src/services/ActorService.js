import axios from 'axios';

const BASE_API_URL = 'http://localhost:8888'

class ActorService {
    
    getTopActorsByUser(username) {
        return axios.get(`${BASE_API_URL}/topActors/${username}`);
    }

    getActors() {
        return axios.get(`${BASE_API_URL}/actors`);
    }

    getActorByID(actorID) {
        return axios.get(`${BASE_API_URL}/actors/${actorID}`);
    }

    getActorShows(actorID) {
        return axios.get(`${BASE_API_URL}/actors/${actorID}/${actorID}`);
    }

    getActorSearch(searchQuery) {
        return axios.get(`${BASE_API_URL}/actors/search/${searchQuery}`);
    }

}
export default new ActorService();