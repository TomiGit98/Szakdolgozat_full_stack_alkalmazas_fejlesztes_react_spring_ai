import React from 'react';
import { Route, Redirect } from 'react-router-dom';

import { isLogin } from '../../Utils/user-authentication.js';

const PrivateRoute = ({ component: Component, ...rest }) => {
    return (
        <Route { ...rest } render={ props => (
            isLogin() ? 
                <Component { ...props }/> : <Redirect to="/auth/login" />
        )} />
    );
};

export default PrivateRoute;