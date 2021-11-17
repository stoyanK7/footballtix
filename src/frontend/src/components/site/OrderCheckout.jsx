import '../../css/site/OrderCheckout.css';

import Confetti from 'react-confetti'
import { Link } from 'react-router-dom';
import Loading from '../shared/Loading';
import MatchInfo from '../shared/MatchInfo';
import MatchPrice from '../shared/MatchPrice';
import MessageBox from '../shared/MessageBox';
import PayPal from '../payment/PayPal';
import axios from 'axios';
import useGET from '../../hooks/useGET';
import { useParams } from 'react-router';
import { useState } from 'react';
import useToken from '../../hooks/useToken';
import useWindowSize from '../../hooks/useWindowSize';

const OrderCheckout = () => {
  const { matchId } = useParams();

  const { data: match, isPending, error } = useGET(`/api/matches/${matchId}`);

  const [fields, setFields] = useState({
    fullName: 'Stoyan Kostadinov',
    email: 'stoyank127@gmail.com',
    mobilePhone: '+359878890852',
    address: 'Argostraat 26',
    city: 'Eindhoven',
    country: 'Netherlands',
    postcode: '5631JZ'
  });

  const onChangeHandler = e => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const [checkout, setCheckout] = useState(false);

  const onSubmitHandler = e => {
    e.preventDefault();
    setCheckout(true);
  };

  const [numberOfPieces, setNumberOfPieces] = useState(0);

  const { email } = useToken();

  const [message, setMessage] = useState();

  const onSuccessHandler = order => {
    setNumberOfPieces(200);
    axios.post('/api/orders', {
      ...fields,
      footballMatch: match,
      transactionId: order.id,
      transactionDateTime: order['create_time'],
      accountEmail: email()
    })
      .then(res => {
        setMessage('Your order has been processed successfully. Check your email.');
        setTimeout(() => setNumberOfPieces(0), 6000);
      })
      .catch(err => {
        setNumberOfPieces(0);
        console.log(err);
      });
  };

  const onErrorHandler = () => setCheckout(false);

  const windowSize = useWindowSize();

  return (
    <>
      <Confetti
        width={windowSize.width}
        height={windowSize.height}
        style={{ position: 'fixed' }}
        numberOfPieces={numberOfPieces} />
      {isPending && <Loading />}
      {message && <MessageBox content={message} type='success' />}
      {error && <MessageBox content={error} type='error' />}
      {match &&
        <div className='order-checkout'>
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
            <form onSubmit={onSubmitHandler}>
              <fieldset disabled={checkout}>
                <input
                  name='fullName'
                  placeholder='Full name'
                  type='text'
                  onChange={onChangeHandler}
                  required
                  defaultValue={fields.fullName} />
                <input
                  name='email'
                  placeholder='Email'
                  type='email'
                  onChange={onChangeHandler}
                  required
                  defaultValue={fields.email} />
                <input
                  name='mobilePhone'
                  placeholder='Mobile phone'
                  type='number'
                  onChange={onChangeHandler}
                  required
                  defaultValue={fields.mobilePhone} />
                <input
                  name='address'
                  placeholder='Address'
                  type='text'
                  onChange={onChangeHandler}
                  required
                  defaultValue={fields.address} />
                <input
                  name='city'
                  placeholder='Town/City'
                  type='text'
                  onChange={onChangeHandler}
                  required
                  defaultValue={fields.city} />
                <input
                  name='country'
                  placeholder='Country'
                  type='text'
                  onChange={onChangeHandler}
                  required
                  defaultValue={fields.country} />
                <input
                  name='postcode'
                  placeholder='Postcode'
                  type='text'
                  onChange={onChangeHandler}
                  required
                  defaultValue={fields.postcode} />
                <div className='flex-start'>
                  <input type='checkbox' name='terms-and-conditions' id='terms-and-conditions' required />
                  <label htmlFor='terms-and-conditions'>I have read and agree to the <Link to='/terms' target="_blank">Terms and conditions</Link>.</label>
                </div>
                <div className='flex-start'>
                  <input type='checkbox' name='terms-and-conditions' id='terms-and-conditions' required />
                  <label htmlFor='terms-and-conditions'>I have read and understood the <Link to='/privacy' target="_blank">Privacy policy</Link>.</label>
                </div>
                {(checkout === true)
                  ? <div>
                    <PayPal
                      amount={match.pricePerTicket}
                      description={`Ticket for ${match.homeTeam} v ${match.awayTeam} on ${match.startingDateTime}`}
                      onSuccess={onSuccessHandler}
                      onError={onErrorHandler} />
                  </div>
                  :
                  <button type='submit'>MAKE PAYMENT</button>
                }
              </fieldset>
            </form>
          </section >
        </div>
      }
    </>
  );
};

export default OrderCheckout;
