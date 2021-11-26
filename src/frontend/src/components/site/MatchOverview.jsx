import '../../css/site/MatchOverview.css';

import React, { useState } from 'react';

import { Link } from 'react-router-dom';
import Loading from '../shared/Loading';
import MatchInfo from '../shared/MatchInfo';
import MatchPrice from '../shared/MatchPrice';
import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import useFetch from '../../hooks/useFetch';
import { Redirect, useParams } from 'react-router';
import useToken from '../../hooks/useToken';

const MatchOverview = () => {
  const { matchId } = useParams();
  const { fetchData: match, isFetching, fetchError: error } = useFetch(`/api/matches/${matchId}`);
  const [redirect, setRedirect] = useState();
  const [responseError, setResponseError] = useState();
  const { isAdmin } = useToken();

  const onClickHandler = () => {
    const confirmation = window.confirm('Are you sure you want to delete this match?');
    if (confirmation) {
      axios.delete(`/api/matches/${matchId}`)
        .then(res => setRedirect({ pathname: `/`, state: { message: 'Match deleted successfully.' } }))
        .catch(err => setResponseError('Something went wrong. Please try again later.'));
    }
  };


  if (redirect) return <Redirect to={redirect} />;


  return (
    <>
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
      {isFetching && <Loading />}
      {error && <MessageBox content={error} type='error' />}
      {match &&
        <div className='match-overview'>
          <img src='/img/stadium.png' alt='' />
          <MatchInfo {...match} />
          <div>
            <span className='ticket-amount'>{match.ticketsAvailable}</span>
            <span className='ticket-amount-desc'>&nbsp; Tickets available</span>
          </div>
          <MatchPrice pricePerTicket={match.pricePerTicket} />
          <Link to={`/matches/${matchId}/order`}>
            <button className='buy-button'>BUY</button>
          </Link>
          {isAdmin() &&
            <>
              <Link to={`/matches/${matchId}/edit`}>
                <button className='edit-button'>EDIT MATCH</button>
              </Link>
              <button className='delete-button' onClick={onClickHandler}>DELETE MATCH</button>
            </>
          }
        </div>
      }
    </>
  );
};

export default MatchOverview;
