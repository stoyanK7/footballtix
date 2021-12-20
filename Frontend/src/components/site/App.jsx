import '../../css/site/App.css';

import React, { useEffect } from 'react';
import { Route, Switch } from 'react-router';

import Chat from './Chat';
import ConfirmToken from '../account/ConfirmToken';
import Contact from '../static/Contact';
import CreateMatch from '../site/CreateMatch';
import EditMatch from '../site/EditMatch';
import Footer from '../static/Footer';
import ForgotPassword from '../account/ForgotPassword';
import Header from '../static/Header';
import Home from '../site/Home';
import Login from '../account/Login';
import Logout from '../account/Logout';
import Main from '../static/Main';
import MatchOverview from '../site/MatchOverview';
import MessageBox from '../shared/MessageBox';
import NotFound from '../static/NotFound';
import OrderCheckout from './OrderCheckout';
import OrderOverview from './OrderOverview';
import Orders from './Orders';
import PastMatches from './PastMatches';
import Privacy from '../static/Privacy';
import Profile from '../account/Profile';
import ProtectedAdminRoute from '../route/ProtectedAdminRoute';
import ProtectedRoute from '../route/ProtectedRoute';
import Register from '../account/Register';
import ResetPassword from '../account/ResetPassword';
import Terms from '../static/Terms';
import UserStatistics from './UserStatistics';
import axios from 'axios';
import useRedirectMessage from '../../hooks/useRedirectMessage';
import useToken from '../../hooks/useToken';

const App = () => {
  const redirectMessage = useRedirectMessage();
  const { getToken } = useToken();

  axios.defaults.baseURL = 'http://localhost:8080/';
  if (getToken())
    axios.defaults.headers.common['Authorization'] = `Bearer ${getToken()}`;

  return (
    <div className='app'>
      <Switch>
        <Route exact path='/'>
          {redirectMessage && <MessageBox content={redirectMessage} type='success' />}
          <Header />
          <Main content='Upcoming matches' component={Home} />
          <Footer />
        </Route>

        <Route exact path='/contact'>
          <Header />
          <Main content='Contact us' component={Contact} />
          <Footer />
        </Route>

        <Route exact path='/privacy'>
          <Header />
          <Main content='Privacy policy' component={Privacy} />
          <Footer />
        </Route>

        <Route exact path='/forgot-password'>
          <ForgotPassword />
        </Route>

        <Route exact path='/confirm-token/:confirmationToken'>
          <ConfirmToken />
        </Route>

        <Route exact path='/reset-password'>
          <ResetPassword />
        </Route>

        <Route exact path='/terms'>
          <Header />
          <Main content='Terms and conditions' component={Terms} />
          <Footer />
        </Route>

        <ProtectedRoute exact path='/chat'>
          <Header />
          <Main content='Public chat' component={Chat} />
          <Footer />
        </ProtectedRoute>

        <ProtectedRoute exact path='/orders/:orderId'>
          <Header />
          <Main content='Order overview' component={OrderOverview} />
          <Footer />
        </ProtectedRoute>

        <ProtectedRoute exact path='/orders'>
          <Header />
          <Main content='Orders' component={Orders} />
          <Footer />
        </ProtectedRoute>

        <ProtectedRoute exact path='/profile'>
          <Header />
          <Main content='Profile' component={Profile} />
          <Footer />
        </ProtectedRoute>

        <ProtectedRoute exact path='/matches/:matchId/order'>
          <Header />
          <Main content='Review your order' component={OrderCheckout} />
          <Footer />
        </ProtectedRoute>

        <ProtectedRoute exact path='/logout'>
          <Logout />
        </ProtectedRoute>

        <ProtectedAdminRoute exact path='/matches/create'>
          <Header />
          <Main content='Create match' component={CreateMatch} />
          <Footer />
        </ProtectedAdminRoute>

        <ProtectedRoute exact path='/matches/past'>
          <Header />
          <Main content='Past matches' component={PastMatches} />
          <Footer />
        </ProtectedRoute>

        <ProtectedAdminRoute exact path='/stats'>
          <Header />
          <Main content='User statistics' component={UserStatistics} />
          <Footer />
        </ProtectedAdminRoute>

        <Route exact path='/matches/:matchId'>
          {redirectMessage && <MessageBox content={redirectMessage} type='success' />}
          <Header />
          <Main content='Match overview' component={MatchOverview} />
          <Footer />
        </Route>

        <ProtectedAdminRoute exact path='/matches/:matchId/edit'>
          <Header />
          <Main content='Edit match' component={EditMatch} />
          <Footer />
        </ProtectedAdminRoute>

        <Route exact path='/login'>
          {redirectMessage && <MessageBox content={redirectMessage} type='success' />}
          <Login />
        </Route>
        <Route exact path='/register'>
          {redirectMessage && <MessageBox content={redirectMessage} type='success' />}
          <Register />
        </Route>

        <Route path='*'>
          <Header />
          <Main content='Not found' component={NotFound} />
          <Footer />
        </Route>

      </Switch>
    </div>
  );
};

export default App;
