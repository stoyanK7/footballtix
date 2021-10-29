import '../css/components/App.css';

import { Route, Switch } from 'react-router';

import Contact from './Contact';
import CreateMatch from './CreateMatch';
import EditMatch from './EditMatch';
import Footer from './Footer';
import Header from './Header';
import Login from './Login';
import Main from './Main';
import MatchList from './MatchList';
import MatchOverview from './MatchOverview';
import NotFound from './NotFound';
import OrderOverview from './OrderOverview';
import PrivacyPolicy from './PrivacyPolicy';
import Profile from './Profile';
import React from 'react';
import Register from './Register';
import TermsAndConditions from './TermsAndConditions';
import useToken from '../hooks/useToken';

const App = () => {
  const { token } = useToken();

  // if (!token) {
  //   return (
  //     <div className="App">
  //       <Switch>
  //         <Route exact path="/">
  //           <div className="page-wrapper">
  //             <Header />
  //             <Main content="Upcoming matches" component={MatchList} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route path="/matches/:matchId/order">
  //           <Login />
  //         </Route>
  //         <Route path="/matches/:matchId">
  //           <div className="page-wrapper">
  //             <Header />
  //             <Main content="Match overview" component={MatchOverview} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route path="/contact">
  //           <div className="page-wrapper">
  //             <Header />
  //             <Main content="Contact us" component={Contact} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route path="/privacy">
  //           <div className="page-wrapper">
  //             <Header />
  //             <Main content="Privacy policy" component={PrivacyPolicy} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route path="/terms">
  //           <div className="page-wrapper">
  //             <Header />
  //             <Main content="Terms and conditions" component={TermsAndConditions} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route exact path="/login">
  //           <Login />
  //         </Route>
  //         <Route path="/register">
  //           <Register />
  //         </Route>
  //         <Route path="/create-match">
  //           <CreateMatch />
  //         </Route>
  //         <Route path="*">
  //           <div className="page-wrapper">
  //             <Header />
  //             <Main content="Not found" component={NotFound} />
  //             <Footer />
  //           </div>
  //         </Route>
  //       </Switch>
  //     </div>
  //   );
  // };

  return (
    <div className="app">
      <Switch>
        <Route exact path="/">
          <Header />
          <Main content="Upcoming matches" component={MatchList} />
          <Footer />
        </Route>
        <Route path="/matches/:matchId/order">
          <Header />
          <Main content="Review your order" component={OrderOverview} />
          <Footer />
        </Route>
        <Route path="/matches/:matchId/edit">
          <EditMatch />
        </Route>
        <Route path="/matches/:matchId">
          <Header />
          <Main content="Match overview" component={MatchOverview} />
          <Footer />
        </Route>
        <Route path="/contact">
          <Header />
          <Main content="Contact us" component={Contact} />
          <Footer />
        </Route>
        <Route path="/privacy">
          <Header />
          <Main content="Privacy policy" component={PrivacyPolicy} />
          <Footer />
        </Route>
        <Route path="/terms">
          <Header />
          <Main content="Terms and conditions" component={TermsAndConditions} />
          <Footer />
        </Route>
        <Route exact path="/login">
          <Login />
        </Route>
        <Route path="/profile">
          <Header />
          <Main content="Profile" component={Profile} />
          <Footer />
        </Route>
        <Route path="/register">
          <Register />
        </Route>
        <Route path="/create-match">
          <CreateMatch />
        </Route>
        {/* <Route path="/forgot-password">
          <CreateMatch />
        </Route> */}
        <Route path="*">
          <Header />
          <Main content="Not found" component={NotFound} />
          <Footer />
        </Route>
      </Switch>
    </div>
  );
};

export default App;
