import {AuthService} from "../services/auth/AuthService";
import {Redirect, Route} from "react-router-dom";

const PrivateRoute = ({path, roles, component, ...rest}) => (

    <Route {...rest} render={props => {
        const currentUser = AuthService.getRole();
        if (!currentUser) {
            // not logged in so redirect to login page with the return url
            return <Redirect to={{ pathname: '/prijava', state: { from: props.location } }} />
        }

        // check if route is restricted by role
        if (roles && roles.indexOf(currentUser) === -1) {
            // role not authorised so redirect to home page
            return <Redirect to={{ pathname: '/403'}} />
        }

        // authorised so return component
        return <Route path={path} component={component}/>
    }} />
)

export default PrivateRoute