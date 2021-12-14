import '../../css/site/MatchOverview.css';

import React, { useEffect, useState } from 'react';
import { Redirect, useParams } from 'react-router';

import { Link } from 'react-router-dom';
import Loading from '../shared/Loading';
import MatchInfo from '../shared/MatchInfo';
import MessageBox from '../shared/MessageBox';
import TicketInfo from '../shared/TicketInfo';
import TicketsLeft from '../shared/TicketsLeft';
import axios from 'axios';
import useFetch from '../../hooks/useFetch';
import useToken from '../../hooks/useToken';

const MatchOverview = () => {
  const { matchId } = useParams();
  const { fetchData: match, isFetching, fetchError } = useFetch(`/api/matches/${matchId}`);
  const [redirect, setRedirect] = useState(false);
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
  
  const isMatchOver = (matchStartingDateTime) => new Date(matchStartingDateTime) < new Date();

  if (redirect) return <Redirect to={redirect} />;

  return (
    <>
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
      {isFetching && <Loading />}
      {fetchError && <MessageBox content={fetchError} type='error' />}
      {match &&
        <div className='match-overview'>
          <img src='/img/stadium.webp' alt='' />
          <MatchInfo {...match} />
          <TicketsLeft ticketsAvailable={match.ticketsAvailable} />
          <TicketInfo pricePerTicket={match.pricePerTicket} />
          <Link to={`/matches/${matchId}/order`}>
            <button
              className='buy-button'
              disabled={match.ticketsAvailable === 0 || isMatchOver(match.startingDateTime)}>BUY</button>
          </Link>
          {isAdmin() &&
            <>
              <Link to={`/matches/${matchId}/edit`}>
                <button className='edit-button' disabled={isMatchOver(match.startingDateTime)}>EDIT MATCH</button>
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
