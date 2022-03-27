import React, { useState } from 'react';
import { Alert, Button, Divider, TextInput } from '@mantine/core';
import axios from 'axios';

export default function LogInForm() {
    const [user, setUser] = useState({
        username: '',
        password: '',
    })

    const FormHandle = e => {
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
            }, (error) => {
                alert("Failed to sign in")
            }
        )
    }



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
                    <input type="password" id="password" name="password" required onChange={(e) =>  onInputChange(e)}/>
                </label>
                <div>
                    <button type="submit">Log In</button>
                </div>
            </form>
        </div>
    );
}
