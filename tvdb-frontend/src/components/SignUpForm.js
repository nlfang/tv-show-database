import axios from 'axios';
import React, {useState} from 'react';

export default function SignUpForm() {
    const [user, setUser] = useState({
        email: '',
        username: '',
        password: '',
        confirm_password: ''
    })

    const FormHandle = e => {
        e.preventDefault();
        submit(user);
    }

    const onInputChange = e => {
        setUser({ ...user, [e.target.name]: e.target.value })
    }
    const { email, username, password, confirm_password} = user;

    const submit = async (data) => {
        if (confirm_password != password) {
            alert("Passwords must match!");
        }
        else {
            axios.post('http://localhost:8888/signup', data).then(
                (response) => {
                    alert("User successfully added")
                }, (error) => {
                    alert("failed to add");
                    alert(error);
                }
            )
        }
    }

    const [passwordShown, setPasswordShown] = useState(false);
    const togglePassword = () => {
        setPasswordShown(!passwordShown);
      };
    

    return(
        <div>
            <h1 className="text-xl font-bold"> Please Sign Up </h1>
            <form onSubmit={e => FormHandle(e)}>
                <label>
                    <p>Email Address</p>
                    <input type="email" id="email" name="email" required value={email} onChange={(e) => onInputChange(e)}></input>
                </label>
                <label>
                    <p>Username</p>
                    <input type="text" id="username" name="username" required value={username} onChange={(e) => onInputChange(e)}/>
                </label>
                <label>
                    <p>Password</p>
                    <input type={passwordShown ? "text" : "password"} id="password" name="password" required value={password} onChange={(e) => onInputChange(e)}/>
                </label>
                <label>
                    <p>Confirm Password</p>
                    <input type={passwordShown ? "text" : "password"} id="confirm_password" name="confirm_password" required value={confirm_password} onChange={(e) => onInputChange(e)}/>
                </label>
                <div>
                Show Password<input type="checkbox" onClick={togglePassword}/>
                </div>
                <div>
                    <button type="submit">Sign Up</button>
                </div>
            </form>
        </div>
    );
}