import { Route, Switch } from "react-router-dom";

import Home from "./MainContent/Home/Home";
import UserProfile from "./MainContent/UserProfile/UserProfile";
import Friends from "./MainContent/Friends/Friends";
import CreatePhoto from "./MainContent/UserProfile/PhotoCreateComponent/CreatePhoto";


function AuthenticatedMainContent() {
    
    return (
        <div>
            <Switch>
            <Route path="/settings/authentication">
                    <div>
                        <CreatePhoto />
                    </div>
                </Route>
                <Route path="/friends">
                    <div>
                        <Friends />
                    </div>
                </Route>
                <Route path="/userprofile">
                    <div>
                        <UserProfile />
                    </div>
                </Route>
                <Route path="/">
                    <div>
                        <Home />
                    </div>
                </Route>
            </Switch>
        </div>
    );
}

export default AuthenticatedMainContent;