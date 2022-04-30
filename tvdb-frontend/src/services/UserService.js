import axios from 'axios';
const USER_REST_API_URL = 'http://localhost:8888/users'
class UserService {
    getUsers() {
        return axios.get(USER_REST_API_URL);
    }

    getEmailByUsername(username) {
        return axios.get(`http://localhost:8888/emailByUser/${username}`);
    }

    changeEmail(username, newEmail) {
        return axios.put(`http://localhost:8888/changeEmail/${username}/${newEmail}`);
    }

    changeUsername(oldUsername, newUsername) {
        return axios.put(`http://localhost:8888/changeUsername/${oldUsername}/${newUsername}`);
    }

    changePassword(username, password) {
        return axios.put(`http://localhost:8888/changePassword/${username}/${password}`);
    }

}
export default new UserService();