import axios from 'axios';
const USER_REST_API_URL = 'http://localhost:8888/users'
class UserService {
    getUsers() {
        return axios.get(USER_REST_API_URL);
    }
}
export default new UserService();