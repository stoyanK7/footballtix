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

const App = () => {
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
        <Route path="/login">
          <Login />
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
