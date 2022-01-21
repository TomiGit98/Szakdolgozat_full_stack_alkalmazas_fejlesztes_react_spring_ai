import React from 'react';
import { Redirect, Link } from "react-router-dom";

import '../../../Styles/Unauthenticated/LoginRegistrationForm.css'
import '../../../Styles/ResponsiveGrid.css';

import { login } from "../../../Utils/user-authentication.js";

import Cookies from 'universal-cookie';

class LoginForm extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            redirect: false,
            loginSuccessful: false,
            loginFailed: false,
            loginFailedMessage: "",
            user: '',
            cookies: new Cookies(),
            responseStatus: null,
        };

        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);

        this.faceVerification = this.faceVerification.bind(this);

        console.log(`${this.props.routeUrl}/registration`)
    }

    onChangeEmail(e) {
        this.setState({ email: e.target.value })
        console.log("OnEmailChange");
    }

    onChangePassword(e) {
        this.setState({ password: e.target.value })
        console.log("OnPasswordChange");
    }

    faceVerification(){
        console.log("FaceVerification")
    }

    onFormSubmit(e) {
        e.preventDefault();

        //fetch('http://localhost:8080/login', {
        fetch('http://localhost:8099/api/login', {
            /*headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },*/
            method: 'POST',
            credentials: "include",
            /*body: JSON.stringify({
                email: this.state.email,
                password: this.state.password
            })*/
            body: new URLSearchParams({
                'email': this.state.email,
                'password': this.state.password,
            })
        })
            //.then(response => response.json())
            .then(response => {
                console.log("Status " + response.status)
                this.state.responseStatus = response.status
                if (response.status === 200) {
                    return response.json()
                }
            })
            .then(result => {
                if (this.state.responseStatus === 200) {
                    console.log(result)
                    console.log(result['refresh_token'])

                    this.state.cookies.set('access_token', result["access_token"], { path: '/' })
                    this.state.cookies.set('refresh_token', result["refresh_token"], { path: '/' })
                    login(result["userId"])

                    this.setState({
                        redirect: true,
                    })
                } else {
                    console.log("Nem sikerült a login")
                }

            })
            .catch(error => {
                console.error('Error: ', error);
            });
    }

    loginFailed = () => {
        if (this.state.loginFailed) {
            return <label>{this.state.loginFailedMessage}</label>
        }
    }

    renderRedirect = () => {
        if (this.state.redirect) {
            return <Redirect to='/' push />
        }
    }

    render() {
        return (
            <div>
                <div>
                    {this.renderRedirect()}
                </div>
                <form className="row loginForm" onSubmit={this.onFormSubmit} autoComplete="off">
                    <label className="col-xs-12 formTitle">Post It!</label>
                    <div className="error-div">
                        {this.loginFailed()}
                    </div>
                    <div className="formLabelContainer col-xs-10">
                        <label>Email</label>
                    </div>
                    <input className="col-xs-10 formInput" type="text" name="email" value={this.state.email} onChange={this.onChangeEmail} autoComplete="new-password" />
                    <div className="formLabelContainer col-xs-10">
                        <label>Password</label>
                    </div>
                    <input className="col-xs-10 formInput" type="password" name="password" value={this.state.password} onChange={this.onChangePassword} autoComplete="new-password" />
                    <div className="formButtonContainer col-xs-10">
                        <button className="formButton">Sign In</button>
                        <p>Do you want to <Link to="/auth/face"> Verify with face?</Link></p>
                    </div>
                </form>
                <div className="loginForm-bottom">
                    <p>Do you want to <Link to="/auth/registration">Register?</Link></p>
                </div>
            </div>
        );
    }
}

export default LoginForm;