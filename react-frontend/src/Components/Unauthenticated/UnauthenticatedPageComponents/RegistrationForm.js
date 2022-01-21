import "../../../Styles/ResponsiveGrid.css";

import React from 'react'

import { Link, Redirect } from "react-router-dom"

class RegistrationForm extends React.Component {

    constructor(props) {
        super(props);

        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangeUsername = this.onChangeUsername.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);

        this.state = {
            registrationSuccessful: false,
            registrationFailed: false,
            registrationFailedMessage: "",
            email: '',
            username: '',
            password: ''
        }
    }

    onChangeEmail(e) {
        this.setState({ email: e.target.value })
        console.log("OnChangePassword EMAIL")
    }

    onChangeUsername(e) {
        this.setState({ username: e.target.value })
        console.log("OnChangePassword USERNAME")
    }

    onChangePassword(e) {
        this.setState({ password: e.target.value })
        console.log("OnChangePassword REGISTRATION")
    }

    onFormSubmit(e) {
        e.preventDefault();

        //fetch('http://localhost:8000/register', {
        fetch('http://localhost:8000/register', {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                email: this.state.email,
                username: this.state.username,
                password: this.state.password
            })
        })
            .then(response => {
                    console.log(response.status)
                    if(response.status === 200){
                        this.setState({
                            registrationSuccessful: true,
                            registrationFailedMessage: '',
                        });
                    }
                    if(response.status === 409){
                        this.setState({
                            registrationFailedMessage: "User Already Exists",
                            registrationFailed: true,
                        })
                    }
                    if(response.status === 208){
                        this.setState({
                            registrationFailedMessage: "Wrong Credentials",
                            registrationFailed: true,
                        })
                    }
                }
            )
            /*.then(result => {
                console.log(result)

                if(result["responseId"] === 1){
                    this.setState({
                        registrationSuccessful: true,
                        registrationFailedMessage: '',
                    });
                }
                if(result["responseId"] === 2){
                    this.setState({
                        registrationFailedMessage: "Wrong Credentials",
                        registrationFailed: true,
                    })
                }
                if(result["responseId"] === 3){
                    this.setState({
                        registrationFailedMessage: "User Already Exists",
                        registrationFailed: true,
                    })
                }
            })*/
            .catch(error => {
                console.error('Error: ', error);
            });
    }

    redirectToLogin = () => {
        if (this.state.registrationSuccessful) {
            return <Redirect to='/auth/login' push />
        }
    }

    registrationFailed = () => {
        if (this.state.registrationFailed) {
            return <label>{this.state.registrationFailedMessage}</label>
        }
    }

    render() {
        return (
            <div>
                <div>
                    {this.redirectToLogin()}
                </div>
                <form className="loginForm" onSubmit={this.onFormSubmit}>
                    <label className="formTitle">Post It!</label>
                    <div className="error-div">
                        {this.registrationFailed()}
                    </div>
                    <div className="formLabelContainer col-xs-10">
                        <label>Email</label>
                    </div>
                    <input className="col-xs-10 formInput" type="text" name="email" value={this.state.email} onChange={this.onChangeEmail} />
                    <div className="formLabelContainer col-xs-10">
                        <label>Username</label>
                    </div>
                    <input className="col-xs-10 formInput" type="text" name="username" value={this.state.username} onChange={this.onChangeUsername} />
                    <div className="formLabelContainer col-xs-10">
                        <label>Password</label>
                    </div>
                    <input className="col-xs-10 formInput" type="password" name="password" value={this.state.password} onChange={this.onChangePassword} />
                    <div className="formButtonContainer col-xs-10">
                        <button className="formButton">Sign Up</button>
                    </div>
                </form>
                <div className="loginForm-bottom">
                    <p>Do you want to <Link to="/auth/login">Login?</Link></p>
                </div>
            </div>
        );
    }
}

export default RegistrationForm;