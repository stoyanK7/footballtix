import '../../css/site/match-form.css';

import { useEffect, useState } from 'react';

import Loading from '../shared/Loading';
import MessageBox from '../shared/MessageBox';
import axios from 'axios';
import useGET from '../../hooks/useGET';
import { useParams } from 'react-router';

const EditMatch = () => {
  const { matchId } = useParams();

  const { data, isPending, error } = useGET(`/api/matches/${matchId}`);

  const [match, setMatch] = useState();

  useEffect(() => { if (data) setMatch(data) }, [data]);

  const onChangeHandler = e => {
    setMatch({
      ...match,
      [e.target.name]: e.target.value
    });
  };
  const [message, setMessage] = useState('');

  const onSubmitHandler = e => {
    e.preventDefault();

    axios.put(`/api/matches/${matchId}`, { ...match })
      .then(res => {
        setMessage('Update successfully.');
      })
      .catch(err => {
        // TODO: display err
      });
  };

  return (
    <>
      {message && <MessageBox content={message} type='success' />}
      {isPending && <Loading />}
      {error && <MessageBox content={error} type='error' />}
      {match &&
        <div className='match-form'>
          <img src='/img/edit.png' alt='Pencil' />
          <form onSubmit={onSubmitHandler}>
            <input
              name='homeTeam'
              placeholder='Home team'
              type='text'
              value={match.homeTeam}
              onChange={onChangeHandler} />
            <input
              name='awayTeam'
              placeholder='Away team'
              type='text'
              value={match.awayTeam}
              onChange={onChangeHandler} />
            <input
              name='startingDateTime'
              placeholder='Starting date time'
              type='text'
              onChange={onChangeHandler}
              defaultValue={match.startingDateTime}
              onFocus={(e) => e.target.type = 'datetime-local'}
              onBlur={(e) => e.target.type = 'text'} />
            <input
              name='stadium'
              placeholder='Stadium'
              type='text'
              value={match.stadium}
              onChange={onChangeHandler} />
            <input
              name='location'
              placeholder='City, Country'
              type='text'
              value={match.location}
              onChange={onChangeHandler} />
            <input
              name='league'
              placeholder='League'
              type='text'
              value={match.league}
              onChange={onChangeHandler} />
            {/* <input
              name='ticketsAvailable'
              placeholder='Tickets available'
              type='number'
              value={match.ticketsAvailable}
              onChange={onChangeHandler}
              min='0' />
            <input
              name='pricePerTicket'
              placeholder='Price per ticket'
              type='number'
              value={match.pricePerTicket}
              onChange={onChangeHandler}
              min='0'
              max='10000' /> */}
            <button type='submit'>Confirm</button>
          </form>
        </div>
      }
    </>
  );
};

export default EditMatch;
