import React, { useState, useContext, Router } from 'react';
import { Alert, Button, Checkbox, Divider, TextInput } from '@mantine/core';
import axios from 'axios';
import { userContext } from './userContext';

export default function LogInForm() {
    const [user, setUser] = useState({
        username: '',
        password: '',
    })

    const {...forLogin} = useContext(userContext); // get context's setUser function to add username to global context

    const FormHandle = (e) => {
        e.preventDefault();
        submit(user);
    }

    const onInputChange = e => {
        setUser({...user, [e.target.name]: e.target.value })
    }

    const submit = async (data) => {
        axios.post('http://localhost:8888/login', data).then(
            (response) => {
                alert("Successfully signed in")
                forLogin.setUser(user.username); // update user's username w/i global context
                window.location.replace("http://localhost:3000/profile/" + user.username)
            }, (error) => {
                alert("Failed to sign in")
            }
        )
    }

    const [passwordShown, setPasswordShown] = useState(false);
    const togglePassword = () => {
        setPasswordShown(!passwordShown);
      };

    return(
        <div>
            <h1 className="text-xl font-bold"> Please Log In </h1>
            <form onSubmit={e => FormHandle(e)}>
                <label>
                    <p>Username</p>
                    <input type="text" id="username" name="username" required onChange={(e) =>  onInputChange(e)}/>
                </label>
                <label>
                    <p>Password</p>
                    <input type={passwordShown ? "text" : "password"} id="password" name="password" required onChange={(e) =>  onInputChange(e)}/>
                    
                </label>
                <div>
                    Show Password<input type="checkbox" onClick={togglePassword}/>
                </div>
                <div>
                    <button type="submit">Log In</button>
                </div>
            </form>
        </div>
    );
}
