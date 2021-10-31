import '../../css/site/App.css';

import { Route, Switch } from 'react-router';

import Contact from '../static/Contact';
import CreateMatch from '../site/CreateMatch';
import EditMatch from '../site/EditMatch';
import Footer from '../static/Footer';
import Header from '../static/Header';
import Home from '../site/Home';
import Login from '../account/Login';
import Main from '../static/Main';
import MatchOverview from '../site/MatchOverview';
import NotFound from '../static/NotFound';
import OrderOverview from '../site/OrderOverview';
import Privacy from '../static/Privacy';
import Profile from '../account/Profile';
import React from 'react';
import Register from '../account/Register';
import Terms from '../static/Terms';
import useToken from '../../hooks/useToken';

const App = () => {
  const { token } = useToken();

  // if (!token) {
  //   return (
  //     <div className='App'>
  //       <Switch>
  //         <Route exact path='/'>
  //           <div className='page-wrapper'>
  //             <Header />
  //             <Main content='Upcoming matches' component={MatchList} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route path='/matches/:matchId/order'>
  //           <Login />
  //         </Route>
  //         <Route path='/matches/:matchId'>
  //           <div className='page-wrapper'>
  //             <Header />
  //             <Main content='Match overview' component={MatchOverview} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route path='/contact'>
  //           <div className='page-wrapper'>
  //             <Header />
  //             <Main content='Contact us' component={Contact} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route path='/privacy'>
  //           <div className='page-wrapper'>
  //             <Header />
  //             <Main content='Privacy policy' component={Privacy} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route path='/terms'>
  //           <div className='page-wrapper'>
  //             <Header />
  //             <Main content='Terms and conditions' component={Terms} />
  //             <Footer />
  //           </div>
  //         </Route>
  //         <Route exact path='/login'>
  //           <Login />
  //         </Route>
  //         <Route path='/register'>
  //           <Register />
  //         </Route>
  //         <Route path='/create-match'>
  //           <CreateMatch />
  //         </Route>
  //         <Route path='*'>
  //           <div className='page-wrapper'>
  //             <Header />
  //             <Main content='Not found' component={NotFound} />
  //             <Footer />
  //           </div>
  //         </Route>
  //       </Switch>
  //     </div>
  //   );
  // };

  return (
    <div className='app'>
      <Switch>
        <Route exact path='/'>
          <Header />
          <Main content='Upcoming matches' component={Home} />
          <Footer />
        </Route>
        <Route exact path='/matches/create'>
          <Header />
          <Main content='Create match' component={CreateMatch} />
          <Footer />
        </Route>
        <Route exact path='/matches/:matchId'>
          <Header />
          <Main content='Match overview' component={MatchOverview} />
          <Footer />
        </Route>
        <Route exact path='/matches/:matchId/order'>
          <Header />
          <Main content='Review your order' component={OrderOverview} />
          <Footer />
        </Route>
        <Route exact path='/matches/:matchId/edit'>
          <Header />
          <Main content='Edit match' component={EditMatch} />
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
        <Route exact path='/terms'>
          <Header />
          <Main content='Terms and conditions' component={Terms} />
          <Footer />
        </Route>
        <Route exact path='/login'>
          <Login />
        </Route>
        <Route exact path='/register'>
          <Register />
        </Route>
        <Route exact path='/profile'>
          <Header />
          <Main content='Profile' component={Profile} />
          <Footer />
        </Route>
        {/* <Route path='/forgot-password'>
          <CreateMatch />
        </Route> */}
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
