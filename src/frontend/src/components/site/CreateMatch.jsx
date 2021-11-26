import axios from 'axios';
import { useState } from 'react';
import { Redirect } from 'react-router';
import '../../css/site/match-form.css';
import MessageBox from '../shared/MessageBox';

const CreateMatch = () => {
  const [fields, setFields] = useState({
    homeTeam: '',
    awayTeam: '',
    startingDateTime: '',
    stadium: '',
    location: '',
    league: '',
    ticketsAvailable: 0,
    pricePerTicket: 0.0
  });
  const [redirect, setRedirect] = useState();
  const [responseError, setResponseError] = useState();

  const onChangeHandler = e => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const onSubmitHandler = e => {
    e.preventDefault();
    axios.post('/api/matches', { ...fields })
      .then(res => setRedirect({ pathname: `/matches/${res.data}`, state: { message: 'Match created successfully.' } }))
      .catch(err => setResponseError('Something went wrong. Please try again later.'));
  };

  if (redirect) return <Redirect to={redirect} />;

  return (
    <>
      {responseError && <MessageBox content={responseError} setContent={setResponseError} type='error' />}
      <div className='match-form'>
        <img src='/img/plus.png' alt='Plus' />
        <form onSubmit={onSubmitHandler}>
          <input
            name='homeTeam'
            placeholder='Home team'
            type='text'
            onChange={onChangeHandler} />
          <input
            name='awayTeam'
            placeholder='Away team'
            type='text'
            onChange={onChangeHandler} />
          <input
            name='startingDateTime'
            placeholder='Starting date time'
            type='text'
            onChange={onChangeHandler}
            onFocus={(e) => e.target.type = 'datetime-local'}
            onBlur={(e) => e.target.type = 'text'} />
          <input
            name='stadium'
            placeholder='Stadium'
            type='text'
            onChange={onChangeHandler} />
          <input
            name='location'
            placeholder='City, Country'
            type='text'
            onChange={onChangeHandler} />
          <input
            name='league'
            placeholder='League'
            type='text'
            onChange={onChangeHandler} />
          <input
            name='ticketsAvailable'
            placeholder='Tickets available'
            type='number'
            onChange={onChangeHandler}
            min='0' />
          <input
            name='pricePerTicket'
            placeholder='Price per ticket'
            type='number'
            onChange={onChangeHandler}
            min='0'
            max='10000' />
          <button type='submit'>Create</button>
        </form>
      </div>
    </>
  );
};

export default CreateMatch;
