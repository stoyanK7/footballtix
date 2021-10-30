import '../css/components/App.css';

import { Route, Switch } from 'react-router';

import Contact from './Contact';
import CreateMatch from './CreateMatch';
import EditMatch from './EditMatch';
import Footer from './Footer';
import Header from './Header';
import Home from './Home';
import Login from './Login';
import Main from './Main';
import MatchOverview from './MatchOverview';
import NotFound from './NotFound';
import OrderOverview from './OrderOverview';
import Privacy from './Privacy';
import Profile from './Profile';
import React from 'react';
import Register from './Register';
import Terms from './Terms';
import useToken from '../hooks/useToken';

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
        {/* Done */}
        <Route exact path='/'>
          <Header />
          <Main content='Upcoming matches' component={Home} />
          <Footer />
        </Route>
        <Route path='/matches/:matchId/order'>
          <Header />
          <Main content='Review your order' component={OrderOverview} />
          <Footer />
        </Route>
        <Route path='/matches/:matchId/edit'>
          <EditMatch />
        </Route>
        <Route path='/matches/:matchId'>
          <Header />
          <Main content='Match overview' component={MatchOverview} />
          <Footer />
        </Route>
        {/* Done */}
        <Route path='/contact'>
          <Header />
          <Main content='Contact us' component={Contact} />
          <Footer />
        </Route>
        {/* Done */}
        <Route exact path='/privacy'>
          <Header />
          <Main content='Privacy policy' component={Privacy} />
          <Footer />
        </Route>
        {/* Done */}
        <Route exact path='/terms'>
          <Header />
          <Main content='Terms and conditions' component={Terms} />
          <Footer />
        </Route>
        {/* Done */}
        <Route exact path='/login'>
          <Login />
        </Route>
        {/* Done */}
        <Route exact path='/register'>
          <Register />
        </Route>
        {/* Done */}
        <Route path='/profile'>
          <Header />
          <Main content='Profile' component={Profile} />
          <Footer />
        </Route>
        <Route path='/create-match'>
          <CreateMatch />
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
