import '../../css/site/OrderOverview.css';

import { useState } from 'react';

import { useParams } from 'react-router';
import useGET from '../../hooks/useGET';
import Loading from '../shared/Loading';
import MessageBox from '../shared/MessageBox';
import MatchInfo from '../shared/MatchInfo';
import MatchPrice from '../shared/MatchPrice';
import { Link } from 'react-router-dom';

const OrderOverview = () => {
  const { matchId } = useParams();

  const { data: match, isPending, error } = useGET(`/api/matches/${matchId}`);

  const [fields, setFields] = useState({
    fullName: '',
    email: '',
    mobilePhone: '',
    address: '',
    city: '',
    country: '',
    postcode: ''
  });

  const onChangeHandler = e => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  return (
    <>
      {isPending && <Loading />}
      {error && <MessageBox content={error} type='error' />}
      {match &&
        <div className='order-overview'>
          <section>
            <span>Match</span>
            <MatchInfo {...match} />
          </section>
          <section>
            <span>Delivery</span>
            <p><b>Electronic delivery</b> - your tickets will be emailed to you</p>
          </section>
          <section >
            <span>Price</span>
            <MatchPrice pricePerTicket={match.pricePerTicket} />
          </section>
          <section>
            <form >
              <input
                name='fullName'
                placeholder='Full name'
                type='text'
                onChange={onChangeHandler} />
              <input
                name='email'
                placeholder='Email'
                type='email'
                onChange={onChangeHandler} />
              <input
                name='mobilePhone'
                placeholder='Mobile phone'
                type='number'
                onChange={onChangeHandler} />
              <input
                name='address'
                placeholder='Address'
                type='text'
                onChange={onChangeHandler} />
              <input
                name='city'
                placeholder='Town/City'
                type='text'
                onChange={onChangeHandler} />
              <input
                name='country'
                placeholder='Country'
                type='text'
                onChange={onChangeHandler} />
              <input
                name='postcode'
                placeholder='Postcode'
                type='text'
                onChange={onChangeHandler} />

              <div className='flex-start'>
                <input type='checkbox' name='terms-and-conditions' id='terms-and-conditions' required />
                <label htmlFor='terms-and-conditions'>I have read and agree to the <Link to='/terms'>Terms and conditions</Link>.</label>
              </div>
              <div className='flex-start'>
                <input type='checkbox' name='terms-and-conditions' id='terms-and-conditions' required />
                <label htmlFor='terms-and-conditions'>I have read and understood the <Link to='/privacy'>Privacy policy</Link>.</label>
              </div>
              <button className=''>MAKE PAYMENT</button>
            </form>
          </section >
        </div>
      }
    </>
  );
};

export default OrderOverview;