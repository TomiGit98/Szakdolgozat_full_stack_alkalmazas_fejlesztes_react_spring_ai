import React from 'react';
import { Route, Switch } from 'react-router-dom';

import RegistrationForm from './RegistrationForm';
import LoginForm from './LoginForm';

import '../../../Styles/Unauthenticated/UnauthenticatedMainContent.css'

import '../../../Styles/ResponsiveGrid.css';
import FaceVerification from './FaceVerification';

class UnauthenticatedMainContent extends React.Component {

    render() {
        return (
            <div>
                <Switch>
                    <Route path="/auth/login">
                        <div className="row unauthenticatedMainContent">
                            <div className="col-xs-10 col-sm-9 col-md-5 col-lg-4 col-xl-4 col-xxl-3">
                                <LoginForm />
                            </div>
                        </div>
                    </Route>
                    <Route path="/auth/registration">
                        <div className="row unauthenticatedMainContent">
                            <div className="col-xs-10 col-sm-9 col-md-5 col-lg-4 col-xl-4 col-xxl-3">
                                <RegistrationForm />
                            </div>
                        </div>
                    </Route>
                    <Route path="/auth/face">
                        <div className="row unauthenticatedMainContent">
                            <div className="col-xs-10 col-sm-9 col-md-5 col-lg-4 col-xl-4 col-xxl-3">
                                <FaceVerification />
                            </div>
                        </div>
                    </Route>
                </Switch>
            </div>
        );
    }
}

export default UnauthenticatedMainContent;