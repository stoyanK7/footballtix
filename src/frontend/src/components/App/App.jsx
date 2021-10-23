import Register from '../Register/Register.jsx';
import Login from '../Login/Login.jsx';
import CreateMatch from '../CreateMatch/CreateMatch.jsx';
import { Route, Switch } from 'react-router';
import './App.css';
import React from 'react'
import Header from '../Header/Header.jsx';
import Main from '../Main/Main.jsx';
import Footer from '../Footer/Footer.jsx';
import UpcomingMatches from '../UpcomingMatches/UpcomingMatches.jsx';
import MatchOverview from '../MatchOverview/MatchOverview.jsx';
import OrderOverview from '../OrderOverview/OrderOverview.jsx';
import '../css/PageWrapper.css';
import useToken from '../../hooks/useToken.jsx';
import PrivacyPolicy from '../PrivacyPolicy/PrivacyPolicy.jsx';
import TermsAndConditions from '../TermsAndConditions/TermsAndConditions.jsx';
import Contact from '../Contact/Contact.jsx';


const App = () => {
  const { token, setToken } = useToken();

  if (!token) {
    return (
      <div className="App">
        <Switch>
          <Route exact path="/">
            <div className="PageWrapper">
              <Header />
              <Main content="Upcoming matches" component={UpcomingMatches} />
              <Footer />
            </div>
          </Route>
          <Route path="/matches/:matchId/order">
            <Login setToken={setToken} />
          </Route>
          <Route path="/matches/:matchId">
            <div className="PageWrapper">
              <Header />
              <Main content="Match overview" component={MatchOverview} />
              <Footer />
            </div>
          </Route>
          <Route path="/contact">
            <div className="PageWrapper">
              <Header />
              <Main content="Contact us" component={Contact} />
              <Footer />
            </div>
          </Route>
          <Route path="/privacy">
            <div className="PageWrapper">
              <Header />
              <Main content="Privacy policy" component={PrivacyPolicy} />
              <Footer />
            </div>
          </Route>
          <Route path="/terms">
            <div className="PageWrapper">
              <Header />
              <Main content="Terms and conditions" component={TermsAndConditions} />
              <Footer />
            </div>
          </Route>
          <Route path="/login">
            <Login setToken={setToken} />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <Route path="/create-match">
            <CreateMatch />
          </Route>
          {/* <Route path="*">
          <NotFound />
        </Route> */}
        </Switch>
      </div>
    );
  }

  return (
    <div className="App">
      <Switch>
        <Route exact path="/">
          <div className="PageWrapper">
            <Header />
            <Main content="Upcoming matches" component={UpcomingMatches} />
            <Footer />
          </div>
        </Route>
        <Route path="/matches/:matchId/order">
          <div className="PageWrapper">
            <Header />
            <Main content="Review your order" component={OrderOverview} />
            <Footer />
          </div>
        </Route>
        <Route path="/matches/:matchId">
          <div className="PageWrapper">
            <Header />
            <Main content="Match overview" component={MatchOverview} />
            <Footer />
          </div>
        </Route>
        <Route path="/contact">
            <div className="PageWrapper">
              <Header />
              <Main content="Contact us" component={Contact} />
              <Footer />
            </div>
          </Route>
        <Route path="/privacy">
          <div className="PageWrapper">
            <Header />
            <Main content="Privacy policy" component={PrivacyPolicy} />
            <Footer />
          </div>
        </Route>
        <Route path="/terms">
          <div className="PageWrapper">
            <Header />
            <Main content="Terms and conditions" component={TermsAndConditions} />
            <Footer />
          </div>
        </Route>
        <Route path="/login">
          <Login setToken={setToken}/>
        </Route>
        <Route path="/register">
          <Register />
        </Route>
        <Route path="/create-match">
          <CreateMatch />
        </Route>
        {/* <Route path="*">
          <NotFound />
        </Route> */}
      </Switch>
    </div>
  );
};

export default App;
