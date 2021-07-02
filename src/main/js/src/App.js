import './App.css';
import Header from "./components/headers/Header";
import Footer from "./components/Footer";
import Page403 from "./pages/Page-403";
import {Route, BrowserRouter as Router, Switch} from "react-router-dom";
import Registration from "./pages/Registration";
import Login from "./pages/Login";
import FrontPage from "./pages/FrontPage";
import Artikli from "./pages/artikli/Artikli";
import AddArtikal from "./pages/artikli/AddArtikal";
import Artikal from "./pages/artikli/Artikal";
import Porudzbine from "./pages/kupci/Porudzbine";
import EditArtikal from "./pages/artikli/EditArtikal";
import PrivateRoute from "./components/PrivateRoute";
import AllUsers from "./pages/administrator/AllUsers";

function App() {
  return (
    <div>
        <Router>
            <Header/>
                <div className="container-fluid">
                    <Switch>
                        <Route exact path="/registracija" component={Registration}/>
                        <Route exact path="/prijava" component={Login}/>
                    </Switch>
                    <Switch>
                        <Route exact path="/prodavci/:username/artikli/:id" component={Artikal}/>
                        <Route exact path="/prodavci/:username" component={Artikli} />
                        <PrivateRoute roles={["ROLE_PRODAVAC"]} path="/artikli/dodavanje" component={AddArtikal}/>
                        <PrivateRoute roles={["ROLE_PRODAVAC"]} path="/artikli/:id" component={EditArtikal}/>
                    </Switch>
                    <Switch>
                        <PrivateRoute roles={["ROLE_KUPAC"]} path="/porudzbine" component={Porudzbine}/>
                    </Switch>
                    <Switch>
                        <PrivateRoute roles={["ROLE_ADMINISTRATOR"]} path="/korisnici" component={AllUsers}/>
                    </Switch>
                    <Switch>
                        <Route exact path="/403" component={Page403}/>
                        <Route exact path="/" component={FrontPage}/>
                    </Switch>
                </div>
            <Footer/>
        </Router>
    </div>
  );
}

export default App;
