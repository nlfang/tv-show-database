import React from 'react'
import UserService from '../services/UserService'
import {Navbar} from 'react-bootstrap'

class UserComponent extends React.Component {
    constructor(props) {
        super(props)
        this.state ={
            users:[]
        }
    }
    componentDidMount() {
        UserService.getUsers().then((Response)=>{
            this.setState({users:Response.data})
        });
    }
    render() {
        return(
            <div>
                <Navbar bg="info" variant="dark" >
                    <Navbar.Brand href="#">User App</Navbar.Brand>
                </Navbar>
                <h1 className="text-center mt-5 ">List of Users</h1>
            <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
            <table className="table table-bordered border-info">
                <thead>
                    <tr>
                    <th>User ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        this.state.users.map(
                            users =>
                                <tr key = {users.userID}>
                                        <td>{users.userID}</td>
                                        <td><a href={`/profile/${users.username}`}>{users.username}</a></td>
                                        <td>{users.email}</td>
                                </tr>
                        )
                    }
                </tbody>
            </table>
            </div>
            </div>
        )
    }
}
export default UserComponent