import { BrowserRouter as Router, Switch } from 'react-router-dom';

import AuthenticatedPage from './Components/Authenticated/AuthenticatedPage';
import UnauthenticatedPage from './Components/Unauthenticated/UnauthenticatedPage';
import PrivateRoute from './Components/Routes/PrivateRoute';
import PublicRoute from './Components/Routes/PublicRoute';

function App() {
  return (
    <Router>
      <Switch>
        <PublicRoute restricted={true} component={UnauthenticatedPage} path="/auth/login" exact />
        <PublicRoute restricted={true} component={UnauthenticatedPage} path="/auth/registration" exact />
        <PublicRoute restricted={true} component={UnauthenticatedPage} path="/auth/face" exact />
        <PrivateRoute component={AuthenticatedPage} path="/" exact />
        <PrivateRoute component={AuthenticatedPage} path="/userprofile" exact />
        <PrivateRoute component={AuthenticatedPage} path="/friends" exact />
        <PrivateRoute component={AuthenticatedPage} path="/settings/authentication" exact />
      </Switch>
    </Router>
  );
}

export default App;
